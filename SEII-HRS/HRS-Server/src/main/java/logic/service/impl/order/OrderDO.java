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
 * Orderģ���������󣬾���ʵ�ָ���Order����ز���
 */
public class OrderDO {
	private OrderDao orderDao;
	//��������ݿ���ȡ����OrderPO����
	private Cache<OrderPO> orders;
	public OrderDO(){
		orderDao = DaoManager.getInstance().getOrderDao();
		//Ĭ�ϻ���20��OrderPO
		orders = new Cache<OrderPO>(20);
	}
	/*
	 * Constructor
	 * @param cacheSize-ָ���ض���cacheSize
	 */
	public OrderDO(int cacheSize){
		orderDao = DaoManager.getInstance().getOrderDao();
		orders = new Cache<OrderPO>(cacheSize);
	}
	/*
	 * �ڲ�ת��������������getOrder�������߼��ϵ�ʵ�ֶ���࣬�ʳ������
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
	 * ��������
	 * @param OrderVO ��view�㴫�����Ķ�������Ϣ
	 */
	public OrderResultMessage create(NewOrderVO vo)throws RemoteException  {
		//��Ȼ��������˼򵥵�������֤�жϣ����ﻹ��Ҫ�ظ�һ�Σ���ֹ�����ץ���ƻ����ݣ�����Ҫ�Ƚ������֤Ӧ����ϸ
		//��������ʶ
		boolean flag = false;
		// ��ǰʱ��
		Date now = new Date();
		//�򵥵�������֤
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getPeople()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		//�ȸ���userId��hotelId��ѯcache
		Iterator cacheIt = orders.getKeys();
		MemberPO mpo = null;
		HotelPO hpo = null;
		StrategyPO spo = null;
		while(cacheIt.hasNext()){
			long orderId;
			//cache�еļ����ܷ�orderId
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
		//���Ҳ��������������ݿ����
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
			//����order��������δ��ɣ����ύ����		
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
		
		//������֤�����ķ�����Ϣ�Ƿ���ʵ
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
		//�����¶�����Ϣ
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
		//�洢�������������ݿ�����,ͬʱ�洢��cache��
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
		//revoke ����
		Date now = new Date();
		Date judge = DateUtil.getBeforeDate(po.getCheckInTime(), 6);
		Date judge2 = DateUtil.getFutureDate(po.getCheckInTime(), 6);
		double[] sync = {-1,1,1,0,0};
		int[] roomSync = {-1,0,1,1};
		String[] reason ={"�����쳣","ִ�ж���","��ִ�ж���","�û���������","��վ������Ա��������"};
		if(operation==4)
			sync[4] = rank[extraOperation];
		sync[3] = (now.before(judge))? 1:(now.before(po.getCheckInTime())? 0.5:0);
		if(operation==3&&sync[3]==0)
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		// abnormal�������
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
	 * ����execute,reExecute,abnormal��cancel�ķ���������ͬ�����ñ������ķ�ʽ,1Ϊ�����쳣��2Ϊִ�У�3Ϊ��ִ�У�4Ϊ�û�������5Ϊ��վ��Ա����
	 */
	private OrderResultMessage changeStatus(long orderId,int operation,int extraOperation)throws RemoteException {
		//�б𶩵���״̬
		OrderStatus[] judgeStatus = {OrderStatus.UNEXECUTED
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL
									 ,OrderStatus.UNEXECUTED
									 ,OrderStatus.ABNORMAL};
		//��Ҫ���ĵĶ�����״̬
		OrderStatus[] changeStatus = {OrderStatus.ABNORMAL
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.EXECUTED
									 ,OrderStatus.REVOKED
									 ,OrderStatus.REVOKED};
		//�Ȳ�ѯ���������޸�Order
		OrderPO cachePO = null;
		try{
			cachePO = orders.get(orderId);
		}catch(IllegalArgumentException e){
			System.err.println("�������Ϊ��");
		}
		boolean flag = false;
		//��cache ���ޣ�������ݿ��л�ã����������cache
		if (cachePO==null){
			OrderPO po = null;
			//����orderId��ȡorderPO
			try{
				HibernateUtil.getCurrentSession().beginTransaction();
				flag = true;
				po = orderDao.getInfo(orderId);
				//������ص���Null,˵��û��������������ش��󣬷�����Ϊ��Ӧ��״̬
				if (po!=null){
					//���po��cache��
					orders.put(po.getOid(), po);
					//�ж�״̬�Ƿ�Ϊ������״̬
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
						//��������ж������
						OrderResultMessage temp = extraOperation(po, operation-1, extraOperation);
						if(temp!=OrderResultMessage.SUCCESS){
							HibernateUtil.getCurrentSession().getTransaction().rollback();
							return temp;
						}
						
						orderDao.update(po);
						//�ύ���񲢷��سɹ�
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.SUCCESS;
					}
					else{
						//״̬������������񲢷��ش���
						HibernateUtil.getCurrentSession()
										.getTransaction()
										.commit();
						return OrderResultMessage.FAIL_WRONGSTATUS;
					}
				}
				else{
					//�ύ����
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.commit();
					return OrderResultMessage.FAIL_WRONGID;
				}
			}catch(RuntimeException e){
				//��������ʱ��ش�����ع�����
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
		}//���ɹ��ڻ������ҵ��˶�Ӧ����������¶�������֮�־û�
		else{
			//�����漰�����ݿ������ע�⵽����������ݿ�ع�ʱ��cache�е�����ҲҪ��Ӧ�ķ����ı䣬�ȱ���ԭʼ״̬
			OrderStatus tempStatus = cachePO.getStatus();
			try{
				if(!flag)
				HibernateUtil.getCurrentSession().beginTransaction();
				
				if (tempStatus==judgeStatus[operation-1]){
					cachePO.setStatus(changeStatus[operation-1]);
					//�������ö���Ĳ���
					OrderResultMessage temp =extraOperation(cachePO, operation-1, extraOperation);
					if(temp==OrderResultMessage.FAIL_WRONGORDERINFO){
						HibernateUtil.getCurrentSession().getTransaction().rollback();
						return temp;
					}
					//�������ݿ��cache
					orderDao.update(cachePO);
					//�ύ���񲢷���
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
				//�ָ�cachePO�е�����
				cachePO.setStatus(tempStatus);
				//�ع�����
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
