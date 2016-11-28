package logic.service.impl.order;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.management.RuntimeErrorException;
import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.Cache;
import info.HotelItem;
import info.ListWrapper;
import info.OrderStatus;
import info.Room;
import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import po.UserPO;
import resultmessage.OrderResultMessage;
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
	/*
	 * ��������
	 * @param OrderVO ��view�㴫�����Ķ�������Ϣ
	 */
	public OrderResultMessage create(NewOrderVO vo) {
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
			if (hpo!=null&&mpo!=null)
				break;
		}
		//���Ҳ��������������ݿ����
		if (hpo==null||mpo==null){
			try{
				HibernateUtil.getCurrentSession()
								.beginTransaction();
				flag = true;
			//	if (mpo==null)
			//		mpo = DaoManager.getInstance()
			//							.getUserDao()
			//							.getInfo(vo.getUserId());
			//	if (hpo==null)
			//		hpo = DaoManager.getInstance();
			//����order��������δ��ɣ����ύ����				
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
		if (hpo==null||mpo==null) return OrderResultMessage.FAIL_WRONGORDERINFO;
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
		po.setStatus(OrderStatus.UNEXECUTED);
		po.setAbnormalTime(null);
		po.setMember(mpo);
		po.setHotel(hpo);
		String orderNum = "SE-"+now;
		po.setOrderId(orderNum);
		//�洢�������������ݿ�����,ͬʱ�洢��cache��
		try{
			if (!flag)
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			orders.put(po.getOid(), po);
		}catch(RuntimeException e){
			orders.remove(po.getOid());
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
	 * ����execute,reExecute,abnormal��cancel�ķ���������ͬ�����ñ������ķ�ʽ,1Ϊ�����쳣��2Ϊִ�У�3Ϊ��ִ�У�4Ϊ�û�������5Ϊ��վ��Ա����
	 */
	private OrderResultMessage changeStatus(long orderId,int operation){
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
						if (operation==1)
							po.setAbnormalTime(new Timestamp(System.currentTimeMillis()));
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
				orders.remove(po.getOid());
				try{
					HibernateUtil.getCurrentSession()
									.getTransaction()
									.rollback();
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
					if (operation==1)
						cachePO.setAbnormalTime(new Timestamp(System.currentTimeMillis()));
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
	public OrderResultMessage userRevoke(long orderId) {
		return changeStatus(orderId,4);
	}
	public OrderResultMessage execute(long orderId) {
		return changeStatus(orderId,2);
	}
	public OrderResultMessage reExecute(long orderId) {
		return changeStatus(orderId,3);
	}
	public OrderResultMessage webRevoke(long orderId){
		return changeStatus(orderId,5);
	}

}
