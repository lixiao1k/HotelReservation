package logic.service.impl.order;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

import javax.management.RuntimeErrorException;
import javax.persistence.TemporalType;

import org.hibernate.engine.HibernateIterator;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.ListWrapper;
import info.OrderItem;
import info.OrderStatus;
import po.HotelPO;
import po.OrderPO;
import po.UserPO;
import resultmessage.OrderResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
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
	public ListWrapper<OrderVO> getUserOrderInfo(long userId, OrderStatus status) {
		
		return null;
	}


	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO 自动生成的方法存根
		
		return null;
	}


	public ListWrapper<OrderVO> getWEBOrderInfo() {
		// TODO 自动生成的方法存根
		return null;
	}
	/*
	 * 创建订单
	 * @param OrderVO 从view层传回来的订单表单信息
	 */
	public OrderResultMessage create(OrderVO vo) {
		//虽然界面层做了简单的数据验证判断，这里还是要重复一次，防止因恶意抓包破坏数据，并且要比界面层验证应更详细
		//事务开启标识
		boolean flag = false;
		// 当前时间
		Date now = new Date();
		//简单的数据验证
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return null;
		else if (!(vo.getPeople()>0))
			return null;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return null;
		//先根据userId和hotelId查询cache
		Iterator cacheIt = orders.getKeys();
		UserPO upo = null;
		HotelPO hpo = null;
		while(cacheIt.hasNext()){
			long orderId = (long) cacheIt.next();
			OrderPO cachePO = orders.get(orderId);
			if (upo!=null&&cachePO.getUser().getUid()==vo.getUserId()){
				upo = cachePO.getUser();
			}
			if (hpo!=null&&cachePO.getHotel().getHid()==vo.getHotelId()){
				hpo = cachePO.getHotel();
			}
			if (hpo!=null&&upo!=null)
				break;
		}
		//若找不到，则连接数据库查找
		if (hpo==null||upo==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
				if (upo==null)
					upo = DaoManager.getInstance()
										.getUserDao()
										.getInfo(vo.getUserId());
			//	if (hpo==null)
			//		hpo = DaoManager.getInstance();
			//整个order创建事务还未完成，不提交事务				
			}catch(RuntimeException e){
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
		
		//二次验证订单的房间信息是否属实
		Iterator<OrderItem> oiit = vo.getOrderRoomIterator();
		//Iterator<HotelItem> hoiit = hpo.getHotelRoomIterator();
		if (oiit==null)
			return null;
		while(oiit.hasNext()){
			OrderItem oi = oiit.next();
			boolean roomFlag = false;
		    //while(hoiit.hasNext()){
			//	HotelItem hi = hoiit.next();
			//	if (hi.getRoom().getType()==oi.getRoom().getType()){
			//		if (hi.getNum()>=oi.getNum()&&Math.abs(hi.getPrice()-oi.getPrice())<=0.001){
			//			roomFlag = true;
			//		}else{
			//			break;
			//		}
			//	}
			//}
			if (!roomFlag){
				return null;
			}
		}
		//设置新订单信息
		OrderPO po = DozerMappingUtil.getInstance().map(vo, OrderPO.class);
		po.setStatus(OrderStatus.UNEXECUTED);
		po.setAbnormalTime(null);
		po.setUser(upo);
		po.setHotel(hpo);
		//存储订单，开启数据库事务,同时存储到cache中
		try{
			if (!flag)
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//po = orderDao.insert(po);
			orders.put(po.getOid(), po);
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
		return OrderResultMessage.SUCCESS;
	}
	/*
	 * 由于execute,reExecute,abnormal和cancel的方法大体相同，采用表驱动的方式,1为订单异常，2为执行，3为补执行，4为用户撤销，5为网站人员撤销
	 */
	private OrderResultMessage changeStatus(long orderId,int operation){
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
		//若cache 中无，则从数据库中获得，操作后存入cache
		if (cachePO==null){
			OrderPO po = null;
			//根据orderId获取orderPO
			try{
				po = orderDao.getInfo(orderId);
				//如果返回的是Null,说明没有这个订单，返回错误，否则置为相应的状态
				if (po!=null){
					//添加po到cache中
					orders.put(po.getOid(), po);
					//判断状态是否为给定的状态
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
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
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
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
				HibernateUtil.getCurrentSession().beginTransaction();
				
				if (tempStatus==judgeStatus[operation-1]){
					cachePO.setStatus(changeStatus[operation-1]);
					//这里设置额外的操作
					if (operation==1)
						cachePO.setAbnormalTime(new Timestamp(System.currentTimeMillis()));
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
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}
	}
	public OrderResultMessage abnormal(long orderId) {
		return changeStatus(orderId,1);
	}
	public OrderResultMessage userCancel(long orderId) {
		return changeStatus(orderId,4);
	}
	public OrderResultMessage execute(long orderId) {
		return changeStatus(orderId,2);
	}
	public OrderResultMessage reExecute(long orderId) {
		return changeStatus(orderId,3);
	}
	public OrderResultMessage webCancel(long orderId){
		return changeStatus(orderId,5);
	}
	public boolean isUsed(StrategyVO vo) {
		// TODO 自动生成的方法存根
		return false;
	}
	/*
	 * 供getTotal方法调用
	 */
	private double calculate(Iterator<OrderItem> it){
		double sum = 0.0;
		while(it.hasNext()){
			OrderItem oi = it.next();
			sum+=oi.getPrice()*oi.getNum();
		}
		return sum;
	}
	/*
	 * 计算订单的总价格，为计算相应促销策略后的价格
	 */
	public double getTotal(long orderId) {
		//查询cache中是否存在该订单
		OrderPO cachePO = null;
		try{
			cachePO = orders.get(orderId);
		}catch(IllegalArgumentException e){
			System.err.println("OrderNumber incorrect!");
		}
		if(cachePO==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				OrderPO po = orderDao.getInfo(orderId);
				if (po!=null){
					// 加入到cache中
					orders.put(orderId, po);
					Iterator<OrderItem> oiit = po.getOrderRoomIterator();
					return calculate(oiit);
				}else{
					return -1;
				}
			}catch(RuntimeException e){
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
				}catch(RuntimeErrorException ex){
					ex.printStackTrace();
				}
				throw e;
			}
		}//存在则无需访问数据库
		else{
			Iterator<OrderItem> oiit = cachePO.getOrderRoomIterator();
			return calculate(oiit);
		}
	}

}
