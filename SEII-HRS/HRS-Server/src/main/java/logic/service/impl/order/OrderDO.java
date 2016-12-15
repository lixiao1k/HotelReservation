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
 * Order模锟斤拷锟斤拷锟斤拷锟斤拷锟襟，撅拷锟斤拷实锟街革拷锟斤拷Order锟斤拷锟斤拷夭锟斤拷锟�
 */
public class OrderDO {
	private OrderDao orderDao;
	//锟斤拷锟斤拷锟斤拷锟斤拷菘锟斤拷锟饺★拷锟斤拷锟絆rderPO锟斤拷锟斤拷
	private Cache<OrderPO> orders;
	public OrderDO(){
		orderDao = DaoManager.getInstance().getOrderDao();
		//默锟较伙拷锟斤拷20锟斤拷OrderPO
		orders = new Cache<OrderPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize-指锟斤拷锟截讹拷锟斤拷cacheSize
	 */
	public OrderDO(int cacheSize){
		orderDao = DaoManager.getInstance().getOrderDao();
		orders = new Cache<OrderPO>(cacheSize);
	}
	/*
	 * 锟节诧拷转锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷getOrder锟斤拷锟斤拷锟斤拷锟竭硷拷锟较碉拷实锟街讹拷锟筋不锟洁，锟绞筹拷锟斤拷锟斤拷锟�
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
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException {
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
	/*
	 * 锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param OrderVO 锟斤拷view锟姐传锟斤拷锟斤拷锟侥讹拷锟斤拷锟斤拷锟斤拷息
	 */
	public OrderResultMessage create(NewOrderVO vo)throws RemoteException  {
		//锟斤拷然锟斤拷锟斤拷锟斤拷锟斤拷思虻サ锟斤拷锟斤拷锟斤拷锟街わ拷卸希锟斤拷锟斤拷锘癸拷锟揭拷馗锟揭伙拷危锟斤拷锟街癸拷锟斤拷锟斤拷抓锟斤拷锟狡伙拷锟斤拷锟捷ｏ拷锟斤拷锟斤拷要锟饺斤拷锟斤拷锟斤拷锟街び︼拷锟斤拷锟较�
		//锟斤拷锟斤拷锟斤拷锟斤拷识
		boolean flag = false;
		// 锟斤拷前时锟斤拷
		Date now = new Date();
		//锟津单碉拷锟斤拷锟斤拷锟斤拷证
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getPeople()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		//锟饺革拷锟斤拷userId锟斤拷hotelId锟斤拷询cache
		Iterator cacheIt = orders.getKeys();
		MemberPO mpo = null;
		HotelPO hpo = null;
		StrategyPO spo = null;
		while(cacheIt.hasNext()){
			long orderId;
			//cache锟叫的硷拷锟斤拷锟杰凤拷orderId
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
		//锟斤拷锟揭诧拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷锟�
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
			//锟斤拷锟斤拷order锟斤拷锟斤拷锟斤拷锟斤拷未锟斤拷桑锟斤拷锟斤拷峤伙拷锟斤拷锟�		
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
		
		//锟斤拷锟斤拷锟斤拷证锟斤拷锟斤拷锟侥凤拷锟斤拷锟斤拷息锟角凤拷锟斤拷实
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
		//锟斤拷锟斤拷锟铰讹拷锟斤拷锟斤拷息
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
		po.setOff(spo.getOff());
		String orderNum = "SE-"+now.getDate()+po.hashCode();
		po.setOrderId(orderNum);
		//锟芥储锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷锟斤拷锟斤拷,同时锟芥储锟斤拷cache锟斤拷
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
	private OrderResultMessage extraOperation(OrderPO po,int operation,int extraOperation)throws RemoteException {
		double[] rank = {1,0.5};
		//revoke 锟斤拷锟斤拷
		Date now = new Date();
		Date judge = DateUtil.getBeforeDate(po.getCheckInTime(), 6);
		Date judge2 = DateUtil.getFutureDate(po.getCheckInTime(), 6);
		double[] sync = {-1,1,1,0,0};
		int[] roomSync = {-1,0,1,1};
		String[] reason ={"锟斤拷锟斤拷锟届常","执锟叫讹拷锟斤拷","锟斤拷执锟叫讹拷锟斤拷","锟矫伙拷锟斤拷锟斤拷锟斤拷锟斤拷","锟斤拷站锟斤拷锟斤拷锟斤拷员锟斤拷锟斤拷锟斤拷锟斤拷"};
		if(operation==4)
			sync[4] = rank[extraOperation];
		sync[3] = (now.before(judge))? 1:(now.before(po.getCheckInTime())? 0.5:0);
		if(operation==3&&sync[3]==0)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		// abnormal锟斤拷锟斤拷锟斤拷锟�
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
	 * 锟斤拷锟斤拷execute,reExecute,abnormal锟斤拷cancel锟侥凤拷锟斤拷锟斤拷锟斤拷锟斤拷同锟斤拷锟斤拷锟矫憋拷锟斤拷锟斤拷锟侥凤拷式,1为锟斤拷锟斤拷锟届常锟斤拷2为执锟叫ｏ拷3为锟斤拷执锟叫ｏ拷4为锟矫伙拷锟斤拷锟斤拷锟斤拷5为锟斤拷站锟斤拷员锟斤拷锟斤拷
	 */
	private OrderResultMessage changeStatus(long orderId,int operation,int extraOperation)throws RemoteException {
		//锟叫别订碉拷锟斤拷状态
		OrderStatus[] judgeStatus = {OrderStatus.UNEXECUTED
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL};
		//锟斤拷要锟斤拷锟侥的讹拷锟斤拷锟斤拷状态
		OrderStatus[] changeStatus = {OrderStatus.ABNORMAL
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.REVOKED
									 ,OrderStatus.REVOKED};
		//锟饺诧拷询锟斤拷锟斤拷锟斤拷锟斤拷锟睫革拷Order
		OrderPO cachePO = null;
		try{
			cachePO = orders.get(orderId);
		}catch(IllegalArgumentException e){
			System.err.println("锟斤拷锟斤拷锟斤拷锟轿拷锟�");
		}
		boolean flag = false;
		//锟斤拷cache 锟斤拷锟睫ｏ拷锟斤拷锟斤拷锟斤拷菘锟斤拷谢锟矫ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷锟絚ache
		if (cachePO==null){
			OrderPO po = null;
			//锟斤拷锟斤拷orderId锟斤拷取orderPO
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				flag = true;
				po = orderDao.getInfo(orderId);
				//锟斤拷锟斤拷锟斤拷氐锟斤拷锟絅ull,说锟斤拷没锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷卮锟斤拷螅锟斤拷锟斤拷锟轿拷锟接︼拷锟阶刺�
				if (po!=null){
					//锟斤拷锟絧o锟斤拷cache锟斤拷
					orders.put(po.getOid(), po);
					//锟叫讹拷状态锟角凤拷为锟斤拷锟斤拷锟斤拷状态
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
						//锟斤拷锟斤拷锟斤拷锟斤拷卸锟斤拷锟斤拷锟斤拷
						OrderResultMessage temp = extraOperation(po, operation-1, extraOperation);
						if(temp!=OrderResultMessage.SUCCESS){
							HibernateUtil.getCurrentSession().getTransaction().rollback();
							return temp;
						}
						
						orderDao.update(po);
						//锟结交锟斤拷锟今并凤拷锟截成癸拷
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.SUCCESS;
					}
					else{
						//状态锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷癫⒎锟斤拷卮锟斤拷锟�
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.FAIL_WRONGSTATUS;
					}
				}
				else{
					//锟结交锟斤拷锟斤拷
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.FAIL_WRONGID;
				}
			}catch(RuntimeException e){
				//锟斤拷锟斤拷锟斤拷锟斤拷时锟斤拷卮锟斤拷锟斤拷锟截癸拷锟斤拷锟斤拷
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
		}//锟斤拷锟缴癸拷锟节伙拷锟斤拷锟斤拷锟揭碉拷锟剿讹拷应锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷露锟斤拷锟斤拷锟斤拷锟街拷志没锟�
		else{
			//锟斤拷锟斤拷锟芥及锟斤拷锟斤拷锟捷匡拷锟斤拷锟斤拷锟阶拷獾斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟捷匡拷毓锟绞憋拷锟絚ache锟叫碉拷锟斤拷锟斤拷也要锟斤拷应锟侥凤拷锟斤拷锟侥变，锟饺憋拷锟斤拷原始状态
			OrderStatus tempStatus = cachePO.getStatus();
			try{
				if(!flag)
				HibernateUtil.getCurrentSession().beginTransaction();
				
				if (tempStatus==judgeStatus[operation-1]){
					cachePO.setStatus(changeStatus[operation-1]);
					//锟斤拷锟斤拷锟斤拷锟矫讹拷锟斤拷牟锟斤拷锟�
					OrderResultMessage temp =extraOperation(cachePO, operation-1, extraOperation);
					if(temp==OrderResultMessage.FAIL_WRONGORDERINFO){
						HibernateUtil.getCurrentSession().getTransaction().rollback();
						return temp;
					}
					//锟斤拷锟斤拷锟斤拷锟捷匡拷锟絚ache
					orderDao.update(cachePO);
					//锟结交锟斤拷锟今并凤拷锟斤拷
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
				//锟街革拷cachePO锟叫碉拷锟斤拷锟斤拷
				cachePO.setStatus(tempStatus);
				//锟截癸拷锟斤拷锟斤拷
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
