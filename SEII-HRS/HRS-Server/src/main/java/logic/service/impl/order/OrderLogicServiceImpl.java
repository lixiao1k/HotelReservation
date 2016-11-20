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
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO 自动生成的方法存根
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
			//开始事务
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//根据orderId获取orderPO
			OrderPO po = orderDao.getInfo(orderId);
			//如果返回的是Null,说明没有这个订单，返回错误，否则置为abnormal状态
			if (po!=null){
				if(po.getStatus()==OrderStatus.UNEXECUTED){
					po.setStatus(OrderStatus.ABNORMAL);
					orderDao.update(po);
					//提交事务
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
				//提交事务
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
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderResultMessage execute(long orderId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderResultMessage reExecute(long orderId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public boolean isUsed(StrategyVO vo) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public double getTotal(long orderId) {
		try{
			HibernateUtil.getCurrentSession()
							.beginTransaction();
			//根据orderId获取orderPO
			OrderPO po = orderDao.getInfo(orderId);
			if (po!=null){
				Iterator<OrderItem> oiit = po.getOrderRoomIterator();
				double sum = 0.0;
				while(oiit.hasNext()){
					OrderItem oi = oiit.next();
					sum+=oi.getPrice()*oi.getNum();
				}
				//提交事务
				HibernateUtil.getCurrentSession()
								.getTransaction()
								.commit();
				return sum;
			}
			else{
				//提交事务
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
