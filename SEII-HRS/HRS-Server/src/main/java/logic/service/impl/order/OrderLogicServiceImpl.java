package logic.service.impl.order;

import java.rmi.RemoteException;
import java.util.Iterator;

import javax.management.RuntimeErrorException;

import data.dao.OrderDao;
import data.dao.Impl.DaoManager;
import data.datahelper.HibernateUtil;
import info.OrderItem;
import info.OrderStatus;
import list.OrderList;
import logic.service.OrderLogicService;
import po.OrderPO;
import resultmessage.OrderResultMessage;
import vo.OrderVO;
import vo.StrategyVO;

public class OrderLogicServiceImpl implements OrderLogicService{
	private OrderDao orderDao;
	public OrderLogicServiceImpl() {
		orderDao = DaoManager
					.getInstance()
					.getOrderDao();
	}
	@Override
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage create(OrderVO vo) {
		//orderDao.insert(po);
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage abnormal(long orderId) {
		try{
			//��ʼ����
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//����orderId��ȡorderPO
			OrderPO po = orderDao.getInfo(orderId);
			//������ص���Null,˵��û��������������ش��󣬷�����Ϊabnormal״̬
			if (po!=null){
				if(po.getStatus()==OrderStatus.UNEXECUTED){
					po.setStatus(OrderStatus.ABNORMAL);
					orderDao.update(po);
					//�ύ����
					HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
					return OrderResultMessage.ABNORMAL_SUCCESS;
				}
				else{
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
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}finally{
			
		}
	}

	@Override
	public OrderResultMessage userCancel(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage execute(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage reExecute(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean isUsed(StrategyVO vo) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public double getTotal(long orderId) {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//����orderId��ȡorderPO
			OrderPO po = orderDao.getInfo(orderId);
			if (po!=null){
				Iterator<OrderItem> oiit = po.getOrderRoomIterator();
				double sum = 0.0;
				while(oiit.hasNext()){
					OrderItem oi = oiit.next();
					sum+=oi.getPrice()*oi.getNum();
				}
				//�ύ����
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return sum;
			}
			else{
				//�ύ����
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return -1;
			}
		}catch(RuntimeException e){
			try{
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.rollback();
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}finally{
		
		}
	}

}
