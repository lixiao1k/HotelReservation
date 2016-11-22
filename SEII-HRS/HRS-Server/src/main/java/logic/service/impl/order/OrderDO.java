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
	public ListWrapper<OrderVO> getUserOrderInfo(long userId, OrderStatus status) {
		
		return null;
	}


	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		
		return null;
	}


	public ListWrapper<OrderVO> getWEBOrderInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}
	/*
	 * ��������
	 * @param OrderVO ��view�㴫�����Ķ�������Ϣ
	 */
	public OrderResultMessage create(OrderVO vo) {
		//��Ȼ��������˼򵥵�������֤�жϣ����ﻹ��Ҫ�ظ�һ�Σ���ֹ�����ץ���ƻ����ݣ�����Ҫ�Ƚ������֤Ӧ����ϸ
		//��������ʶ
		boolean flag = false;
		// ��ǰʱ��
		Date now = new Date();
		//�򵥵�������֤
		if (!(vo.getCheckInTime().before(vo.getCheckOutTime())
				&& vo.getCheckInTime().after(now)))
			return null;
		else if (!(vo.getPeople()>0))
			return null;
		else if (!(vo.getHotelId()>0&&vo.getUserId()>0))
			return null;
		//�ȸ���userId��hotelId��ѯcache
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
		//���Ҳ��������������ݿ����
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
		
		//������֤�����ķ�����Ϣ�Ƿ���ʵ
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
		//�����¶�����Ϣ
		OrderPO po = DozerMappingUtil.getInstance().map(vo, OrderPO.class);
		po.setStatus(OrderStatus.UNEXECUTED);
		po.setAbnormalTime(null);
		po.setUser(upo);
		po.setHotel(hpo);
		//�洢�������������ݿ�����,ͬʱ�洢��cache��
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
		//��cache ���ޣ�������ݿ��л�ã����������cache
		if (cachePO==null){
			OrderPO po = null;
			//����orderId��ȡorderPO
			try{
				po = orderDao.getInfo(orderId);
				//������ص���Null,˵��û��������������ش��󣬷�����Ϊ��Ӧ��״̬
				if (po!=null){
					//���po��cache��
					orders.put(po.getOid(), po);
					//�ж�״̬�Ƿ�Ϊ������״̬
					if(po.getStatus()==judgeStatus[operation-1]){
						po.setStatus(changeStatus[operation-1]);
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
		// TODO �Զ����ɵķ������
		return false;
	}
	/*
	 * ��getTotal��������
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
	 * ���㶩�����ܼ۸�Ϊ������Ӧ�������Ժ�ļ۸�
	 */
	public double getTotal(long orderId) {
		//��ѯcache���Ƿ���ڸö���
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
					// ���뵽cache��
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
		}//����������������ݿ�
		else{
			Iterator<OrderItem> oiit = cachePO.getOrderRoomIterator();
			return calculate(oiit);
		}
	}

}
