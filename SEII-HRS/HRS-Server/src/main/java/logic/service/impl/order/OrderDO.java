package logic.service.impl.order;

import java.sql.Timestamp;
import java.util.Iterator;

import javax.management.RuntimeErrorException;
import javax.persistence.TemporalType;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import info.OrderItem;
import info.OrderStatus;
import list.Cache;
import list.OrderList;
import po.OrderPO;
import resultmessage.OrderResultMessage;
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
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}


	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}


	public OrderList getWEBOrderInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}
	public OrderResultMessage create(OrderVO vo) {
		//orderDao.insert(po);
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
	private double calculate(Iterator<OrderItem> it){
		double sum = 0.0;
		while(it.hasNext()){
			OrderItem oi = it.next();
			sum+=oi.getPrice()*oi.getNum();
		}
		return sum;
	}
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
