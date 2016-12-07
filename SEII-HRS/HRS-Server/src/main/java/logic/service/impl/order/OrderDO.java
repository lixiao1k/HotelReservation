package logic.service.impl.order;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;
import org.hibernate.engine.HibernateIterator;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.OrderStatus;
import info.Room;
import info.StrategyItem;
import po.ClientMemberPO;
import po.CreditPO;
import po.HotelPO;
import po.HotelWorkerPO;
import po.MemberPO;
import po.OrderPO;
import po.StrategyPO;
import po.UserPO;
import resultmessage.HotelResultMessage;
import resultmessage.OrderResultMessage;
import util.DateUtil;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.NewOrderVO;
import vo.OrderVO;
import vo.StrategyVO;
/*
 * Order模块的领域对象，具体实现各种Order的相关操作
 */
public class OrderDO {
	private OrderDao orderDao;
	//缓存从数据库中取出的OrderPO对象
	private Cache<OrderPO> orders;
	public OrderDO(){
		orderDao = DaoManager.getInstance().getOrderDao();
		//默认缓存20条OrderPO
		orders = new Cache<OrderPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize-指定特定的cacheSize
	 */
	public OrderDO(int cacheSize){
		orderDao = DaoManager.getInstance().getOrderDao();
		orders = new Cache<OrderPO>(cacheSize);
	}
	/*
	 * 内部转化器，由于三个getOrder方法在逻辑上的实现都差不多，故抽离出来
	 */
	private ListWrapper<OrderVO> transform(ListWrapper<OrderPO> list){
		Iterator<OrderPO> it = list.iterator();
		Set<OrderVO> set = new HashSet<OrderVO>();
		while(it.hasNext()){
			OrderPO po = it.next();
			OrderVO vo = DozerMappingUtil.getInstance().map(po, OrderVO.class);
			double price = vo.getRoomPrice()*vo.getRoomNum();
			double priceAfterStrategy = price*vo.getStrategyOff();
			vo.setPrice(price);
			vo.setPriceAfterStrategy(priceAfterStrategy);
			set.add(vo);
		}
		return new ListWrapper<OrderVO>(set);
	}
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return this.transform(orderDao.getUserOrders(userId));
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}

	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return this.transform(orderDao.getHotelOrders(hotelId));
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}


	public ListWrapper<OrderVO> getWEBOrderInfo() {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return this.transform(orderDao.getTodayOrders());
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	/*
	 * 创建订单
	 * @param OrderVO 从view层传回来的订单表单信息
	 */
	public OrderResultMessage create(NewOrderVO vo) {
		//虽然界面层做了简单的数据验证判断，这里还是要重复一次，防止因恶意抓包破坏数据，并且要比界面层验证应更详细
		//事务开启标识
		boolean flag = false;
		// 当前时间
		Date now = new Date();
		//简单的数据验证
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getPeople()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		//先根据userId和hotelId查询cache
		Iterator cacheIt = orders.getKeys();
		MemberPO mpo = null;
		HotelPO hpo = null;
		StrategyPO spo = null;
		while(cacheIt.hasNext()){
			long orderId;
			//cache中的键可能非orderId
			try{
				orderId = (long) cacheIt.next();
			}catch(NumberFormatException e){
				continue;
			}
			OrderPO cachePO = orders.get(orderId);
			if (cachePO==null) continue;
			if (mpo!=null&&cachePO.getMember().getMid()==vo.getUserId()){
				mpo = cachePO.getMember();
			}
			if (hpo!=null&&cachePO.getHotel().getHid()==vo.getHotelId()){
				hpo = cachePO.getHotel();
			}
			if (spo!=null&&cachePO.getStrategy().getId()==vo.getStrategyId()){
				spo = cachePO.getStrategy();
			}
			if (hpo!=null&&mpo!=null&&spo!=null)
				break;
		}
		//若找不到，则连接数据库查找
		if (hpo==null||mpo==null||spo==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				if (mpo==null)
					mpo = DaoManager.getInstance()
										.getMemberDao()
										.getInfo(vo.getUserId());
				if (hpo==null)
					hpo = DaoManager.getInstance().getHotelDao().getInfo(vo.getHotelId());
				if (spo==null)
					spo = DaoManager.getInstance().getStrategyDao().getInfo(vo.getStrategyId());
			//整个order创建事务还未完成，不提交事务		
			}catch(RuntimeException e){
				e.printStackTrace();
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return OrderResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
		if (hpo==null||mpo==null||spo==null) return OrderResultMessage.FAIL_WRONGORDERINFO;
		
		//二次验证订单的房间信息是否属实
		Room room = vo.getRoom();
		Iterator<HotelItem> hoiit = hpo.getRoom();
		if (room==null)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		boolean roomFlag = false;
		while(hoiit.hasNext()){
			HotelItem oi = hoiit.next();
			roomFlag = false;
		    if (oi.getRoom().getType().equals(room.getType())){
		    	if (oi.getNum()>=vo.getRoomNum()&&Math.abs(oi.getPrice()-vo.getRoomPrice())<1){
		    		roomFlag = true;
		    	}
		    	break;
		    }
		}
		if (!roomFlag) return OrderResultMessage.FAIL_WRONGORDERINFO;
		//设置新订单信息
		OrderPO po = DozerMappingUtil.getInstance().map(vo, OrderPO.class);
		if(spo.getType().getName().contains("WEB")){
			po.setOff(spo.getOff());
		}else{
			Iterator<StrategyItem> siit = spo.getStrategyRoom(); 
			while(siit.hasNext()){
				StrategyItem si = siit.next();
				if(si.getRoom().getType().equals(po.getRoom().getType())){
					po.setOff(si.getOff());
					break;
				}
			}
		}
		double price = po.getPrice();
		if(((ClientMemberPO)mpo).getCredit()<price)
			return OrderResultMessage.FAIL_NOTENOUGHCREDIT;
		ListWrapper<HotelItem> hiList = DaoManager.getInstance().getHotelDao().getHotelItemByRoom(hpo.getHid(), vo.getRoom());
		if(hiList==null)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else{
			Iterator<HotelItem> hiit = hiList.iterator();
			while(hiit.hasNext()){
				HotelItem hi = hiit.next();
				if(hi.getDate().before(vo.getCheckOutTime())&&hi.getDate().after(vo.getCheckInTime())){
					hi.setNum(hi.getNum()-vo.getRoomNum());
					DaoManager.getInstance().getHotelDao().updateRoom(hpo.getHid(), hi);
				}
			}
		}
		po.setStatus(OrderStatus.UNEXECUTED);
		po.setAbnormalTime(null);
		po.setMember(mpo);
		po.setHotel(hpo);
		po.setStrategy(spo);

		String orderNum = "SE-"+now.getDate()+po.hashCode();
		po.setOrderId(orderNum);
		//存储订单，开启数据库事务,同时存储到cache中
		try{
			if (!flag)
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			orders.put(po.getOid(), po);
			orderDao.insert(po);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return OrderResultMessage.SUCCESS;
		}catch(RuntimeException e){
			orders.remove(po.getOid());
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
				return OrderResultMessage.FAIL;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	private OrderResultMessage extraOperation(OrderPO po,int operation,int extraOperation){
		double[] rank = {1,0.5};
		//revoke 数据
		Date now = new Date();
		Date judge = DateUtil.getBeforeDate(po.getCheckInTime(), 6);
		Date judge2 = DateUtil.getFutureDate(po.getCheckInTime(), 6);
		double[] sync = {-1,1,1,0,0};
		int[] roomSync = {-1,0,1,1};
		String[] reason ={"订单异常","执行订单","补执行订单","用户撤销订单","网站工作人员撤销订单"};
		if(operation==4)
			sync[4] = rank[extraOperation];
		sync[3] = (now.before(judge))? 1:(now.before(po.getCheckInTime())? 0.5:0);
		if(operation==3&&sync[3]==0)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		// abnormal额外操作
		if (operation==1&&now.after(po.getCheckInTime())&&now.before(judge2))
			po.setAbnormalTime(new Timestamp(System.currentTimeMillis()));
		else if(operation==1&&!(now.after(po.getCheckInTime())&&now.before(judge2)))
			return OrderResultMessage.FAIL_WRONGSTATUS;
			double delta = sync[operation]*po.getPrice();
			MemberPO member = po.getMember();
			((ClientMemberPO)member).setCredit((int) (((ClientMemberPO)member).getCredit()+delta));
			CreditPO cpo = new CreditPO(member, new Date(), (int)delta, ((ClientMemberPO)member).getCredit(), reason[operation]);
			DaoManager.getInstance().getMemberDao().update(member);
			DaoManager.getInstance().getCreditDao().insert(cpo);
		if(operation==0||operation==2||operation==3){
			ListWrapper<HotelItem> hiList = DaoManager.getInstance().getHotelDao().getHotelItemByRoom(po.getHotel().getHid(), po.getRoom());
			if(hiList==null)
				return OrderResultMessage.FAIL_WRONGORDERINFO;
			else{
				Iterator<HotelItem> hiit = hiList.iterator();
				while(hiit.hasNext()){
					HotelItem hi = hiit.next();
					if(hi.getDate().before(po.getCheckOutTime())&&hi.getDate().after(po.getCheckInTime())){
						hi.setNum(hi.getNum()+roomSync[operation]*po.getRoomNum());
						DaoManager.getInstance().getHotelDao().updateRoom(po.getHotel().getHid(), hi);
					}
				}
			}
		}
		return OrderResultMessage.SUCCESS;
	}
	/*
	 * 由于execute,reExecute,abnormal和cancel的方法大体相同，采用表驱动的方式,1为订单异常，2为执行，3为补执行，4为用户撤销，5为网站人员撤销
	 */
	private OrderResultMessage changeStatus(long orderId,int operation,int extraOperation){
		//判别订单的状态
		OrderStatus[] judgeStatus = {OrderStatus.UNEXECUTED
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL};
		//需要更改的订单的状态
		OrderStatus[] changeStatus = {OrderStatus.ABNORMAL
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.REVOKED
									 ,OrderStatus.REVOKED};
		//先查询缓存中有无该Order
		OrderPO cachePO = null;
		try{
			cachePO = orders.get(orderId);
		}catch(IllegalArgumentException e){
			System.err.println("传入参数为空");
		}
		boolean flag = false;
		//若cache 中无，则从数据库中获得，操作后存入cache
		if (cachePO==null){
			OrderPO po = null;
			//根据orderId获取orderPO
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				flag = true;
				po = orderDao.getInfo(orderId);
				//如果返回的是Null,说明没有这个订单，返回错误，否则置为相应的状态
				if (po!=null){
					//添加po到cache中
					orders.put(po.getOid(), po);
					//判断状态是否为给定的状态
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
						//额外事务卸载这里
						OrderResultMessage temp = extraOperation(po, operation-1, extraOperation);
						if(temp!=OrderResultMessage.SUCCESS){
							HibernateUtil.getCurrentSession().getTransaction().rollback();
							return temp;
						}
						
						orderDao.update(po);
						//提交事务并返回成功
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.SUCCESS;
					}
					else{
						//状态错误则结束事务并返回错误
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.FAIL_WRONGSTATUS;
					}
				}
				else{
					//提交事务
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.FAIL_WRONGID;
				}
			}catch(RuntimeException e){
				//遇到运行时相关错误则回滚事务
				e.printStackTrace();
				orders.remove(po.getOid());
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return OrderResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}//若成功在缓存中找到了对应订单，则更新订单并将之持久化
		else{
			//由于涉及到数据库操作，注意到这里如果数据库回滚时，cache中的数据也要相应的发生改变，先保存原始状态
			OrderStatus tempStatus = cachePO.getStatus();
			try{
				if(!flag)
				HibernateUtil.getCurrentSession().beginTransaction();
				
				if (tempStatus==judgeStatus[operation-1]){
					cachePO.setStatus(changeStatus[operation-1]);
					//这里设置额外的操作
					OrderResultMessage temp =extraOperation(cachePO, operation-1, extraOperation);
					if(temp==OrderResultMessage.FAIL_WRONGORDERINFO){
						HibernateUtil.getCurrentSession().getTransaction().rollback();
						return temp;
					}
					//更新数据库和cache
					orderDao.update(cachePO);
					//提交事务并返回
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.SUCCESS;			
				}else{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.FAIL_WRONGSTATUS;	
				}
			}catch(RuntimeException e){
				//恢复cachePO中的数据
				cachePO.setStatus(tempStatus);
				//回滚事务
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
					return OrderResultMessage.FAIL;
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
	}
	public OrderResultMessage abnormal(long orderId) {
		return changeStatus(orderId,1,-1);
	}
	public OrderResultMessage userRevoke(long orderId) {
		return changeStatus(orderId,4,-1);
	}
	public OrderResultMessage execute(long orderId) {
		return changeStatus(orderId,2,-1);
	}
	public OrderResultMessage reExecute(long orderId) {
		return changeStatus(orderId,3,-1);
	}
	public OrderResultMessage webRevoke(long orderId,int rank){
		return changeStatus(orderId,5,rank);
	}

}
