package logic.service.impl.order;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;
import org.hibernate.engine.HibernateIterator;

import data.dao.OrderDao;
import data.dao.impl.DaoManager;
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

public class OrderDO {
	private OrderDao orderDao;
	
	/**
	 * 订单cache
	 */
	private Cache<OrderPO> orders;
	public OrderDO(){
		orderDao = DaoManager.getInstance().getOrderDao();
		orders = new Cache<OrderPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize-cache大小
	 */
	public OrderDO(int cacheSize){
		orderDao = DaoManager.getInstance().getOrderDao();
		orders = new Cache<OrderPO>(cacheSize);
	}
	
	/**
	 * 内部调用的方法，用于将订单的持久化类转化为传输的VO类，主要是转化Dozer未能映射的复杂类型映射
	 * @param list
	 * @return ListWrapper<OrderVO>
	 * 映射后的VO List
	 * @throws RemoteException
	 */
	private ListWrapper<OrderVO> transform(ListWrapper<OrderPO> list)throws RemoteException {
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
	/**
	 * 客户查看自身订单时所调用的方法
	 * @param userId
	 * 用户id
	 * @return ListWrapper<OrderVO> 
	 * 用户的所有订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			return this.transform(orderDao.getUserOrders(userId));
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
	/**
	 * 酒店工作人员查看自身订单时所调用的方法
	 * @param hotelId
	 * 酒店id
	 * @return ListWrapper<OrderVO> 
	 * 酒店的所有订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException {
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

	/**
	 * 网站营销人员查看自身订单时所调用的方法
	 * @return ListWrapper<OrderVO> 
	 * 当天的所有异常的订单
	 * @throws RemoteException
	 */
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException {
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

	/**
	 * 客户在新建订单时所调用的方法
	 * @param vo
	 * 新订单信息
	 * @return OrderResultMessage
	 * 创建成功的结果
	 * @throws RemoteException
	 */
	public OrderResultMessage create(NewOrderVO vo)throws RemoteException  {
		//事务开始的标志
		boolean flag = false;
		// 时间标记
		Date now = new Date();
		//为了安全性问题加入数据验证，
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getPeople()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		//先根据id查询cache以获取相关的数据进行关联
		Iterator cacheIt = orders.getKeys();
		MemberPO mpo = null;
		HotelPO hpo = null;
		StrategyPO spo = null;
		while(cacheIt.hasNext()){
			long orderId;
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
		//若没有从数据库中拿，这里也可以用Hibernate.get直接获取
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
		if (hpo==null||mpo==null||(spo==null&&vo.getStrategyId()!=-1)) return OrderResultMessage.FAIL_WRONGORDERINFO;
		
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
		//使用Dozer快速转换包括可能存在深克隆的属性
		OrderPO po = DozerMappingUtil.getInstance().map(vo, OrderPO.class);
		po.setCommented(false);
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
		if(spo!=null)
		po.setOff(spo.getOff());
		else
			po.setOff(1);
		//给订单加上编号
		String orderNum = "SE-"+now.getDate()+po.hashCode();
		po.setOrderId(orderNum);
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
	/**
	 * 内部进行订单一系列操作的额外方法，采用表驱动的方式调用
	 * @param po
	 * 执行额外操作的订单
	 * @param operation
	 * 执行的操作
	 * @param extraOperation
	 * 可能存在的额外参数
	 * @return OrderResultMessage
	 * 额外操作成功或失败的消息
	 * @throws RemoteException
	 */
	private OrderResultMessage extraOperation(OrderPO po,int operation,int extraOperation)throws RemoteException {
		double[] rank = {1,0.5};
		//revoke 锟斤拷锟斤拷
		Date now = new Date();
		Date judge = DateUtil.getBeforeDate(po.getCheckInTime(), 6);
		Date judge2 = DateUtil.getFutureDate(po.getCheckInTime(), 6);
		double[] sync = {-1,1,1,0,0};
		int[] roomSync = {-1,0,1,1};
		//因操作发生用户信用值的改变需要录入信用记录，这里为用印
		String[] reason ={"锟斤拷锟斤拷锟届常","执锟叫讹拷锟斤拷","锟斤拷执锟叫讹拷锟斤拷","锟矫伙拷锟斤拷锟斤拷锟斤拷锟斤拷","锟斤拷站锟斤拷锟斤拷锟斤拷员锟斤拷锟斤拷锟斤拷锟斤拷"};
		if(operation==4)
			sync[4] = rank[extraOperation];
		sync[3] = (now.before(judge))? 1:(now.before(po.getCheckInTime())? 0.5:0);
		if(operation==3&&sync[3]==0)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		// abnormal的相关操作
		if (operation==0&&now.after(po.getCheckInTime())&&now.before(judge2))
			po.setAbnormalTime(new Timestamp(System.currentTimeMillis()));
		else if(operation==0&&!(now.after(po.getCheckInTime())&&now.before(judge2)))
			return OrderResultMessage.FAIL_WRONGSTATUS;
			double delta = sync[operation]*po.getPrice();
			MemberPO member = po.getMember();
			((ClientMemberPO)member).setCredit((int) (((ClientMemberPO)member).getCredit()+delta));
			CreditPO cpo = new CreditPO(member, new Date(), (int)delta, ((ClientMemberPO)member).getCredit(), reason[operation]);
			DaoManager.getInstance().getMemberDao().update(member);
			DaoManager.getInstance().getCreditDao().insert(cpo);
		if(operation==1||operation==2)
			po.setActualCheckInTime(new Date());
		if(operation==0||operation==2||operation==3){
			ListWrapper<HotelItem> hiList = DaoManager.getInstance().getHotelDao().getHotelItemByRoom(po.getHotel().getHid(), po.getRoom());
			System.out.println(po.getHotel().getHid()+" "+po.getRoom().getType());
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
	
	/**
	 * 由于对订单一系列相关操作实质上都是对订单状态的判断和改变（以及其他额外操作）
	 * 故采用表驱动的方式，让代码更精炼课外胡
	 * 其中0为异常，1为执行，2为补执行，3为用户撤销，4为网站营销人员撤销
	 * 额外操作可能在4中出现，因为此时要选择恢复信用值的量级，其中0为一般，1为全部
	 * @param orderId
	 * 订单id
	 * @param operation
	 * 执行的操作
	 * @param extraOperation
	 * 可能执行的额外操作
	 * @return
	 * 订单执行的结果
	 * @throws RemoteException
	 */
	private OrderResultMessage changeStatus(long orderId,int operation,int extraOperation)throws RemoteException {
		//判断的订单状态
		OrderStatus[] judgeStatus = {OrderStatus.UNEXECUTED
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL};
		//需要进行转换的订单状态
		OrderStatus[] changeStatus = {OrderStatus.ABNORMAL
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.REVOKED
									 ,OrderStatus.REVOKED};
		//先从cache中查找是否有相应的订单
		OrderPO cachePO = null;
		try{
			cachePO = orders.get(orderId);
		}catch(IllegalArgumentException e){
			System.err.println("参数错误!");
		}
		boolean flag = false;
		//若没有从数据库中查找
		if (cachePO==null){
			OrderPO po = null;
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				flag = true;
				po = orderDao.getInfo(orderId);
				//查找到存入cache
				if (po!=null){
					orders.put(po.getOid(), po);
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
						//进行额外操作
						OrderResultMessage temp = extraOperation(po, operation-1, extraOperation);
						//额外操作不成功，则需要回滚操作
						if(temp!=OrderResultMessage.SUCCESS){
							HibernateUtil.getCurrentSession().getTransaction().rollback();
							return temp;
						}
						
						orderDao.update(po);
						//提交事务
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.SUCCESS;
					}
					else{
						//若判断状态错误，则返回错误状态
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.FAIL_WRONGSTATUS;
					}
				}
				else{
					//数据库中找不到则返回id错误
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.FAIL_WRONGID;
				}
			}catch(RuntimeException e){
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
		}//找到了就直接对cache找到的po操作即可
		else{
			OrderStatus tempStatus = cachePO.getStatus();
			try{
				if(!flag)
				HibernateUtil.getCurrentSession().beginTransaction();
				
				if (tempStatus==judgeStatus[operation-1]){
					cachePO.setStatus(changeStatus[operation-1]);
					//进行额外操作
					OrderResultMessage temp =extraOperation(cachePO, operation-1, extraOperation);
					if(temp==OrderResultMessage.FAIL_WRONGORDERINFO){
						HibernateUtil.getCurrentSession().getTransaction().rollback();
						return temp;
					}
					orderDao.update(cachePO);
					//提交事务
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
				e.printStackTrace();
				cachePO.setStatus(tempStatus);
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
	public OrderResultMessage abnormal(long orderId)throws RemoteException  {
		return changeStatus(orderId,1,-1);
	}
	public OrderResultMessage userRevoke(long orderId) throws RemoteException {
		return changeStatus(orderId,4,-1);
	}
	public OrderResultMessage execute(long orderId) throws RemoteException {
		return changeStatus(orderId,2,-1);
	}
	public OrderResultMessage reExecute(long orderId)throws RemoteException  {
		return changeStatus(orderId,3,-1);
	}
	public OrderResultMessage webRevoke(long orderId,int rank)throws RemoteException {
		return changeStatus(orderId,5,rank);
	}

}
