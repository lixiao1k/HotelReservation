package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import data.datahelper.OrderDataHelper;
import info.OrderStatus;
import po.OrderPO;
import util.HibernateUtil;

public class OrderDataHelperMysqlImpl implements OrderDataHelper{
	private static final String abnormalQuery = "from Order as o where o.status=ABNORMAL";
	private static final String userQuery = "from Order as o where o.member=:USERID";
	private static final String hotelQuery = "from Order as o where o.hotel=:HOTELID";
	private static final String hotelUserQuery = "from Order as o where o.member=:USERID and o.hotel=:HOTELID";
	@Override
	public void insert(OrderPO po) {
		HibernateUtil.getCurrentSession()
						.save(po);
	}

	@Override
	public void update(OrderPO po) {
		HibernateUtil.getCurrentSession()
						.saveOrUpdate(po);
		
	}

	@Override
	public OrderPO getInfo(long orderId) {

		OrderPO po = (OrderPO)HibernateUtil.getCurrentSession()
								.get(OrderPO.class, orderId);
	
		return po;
	}

	@Override
	public List<OrderPO> getWEBOrders() {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(abnormalQuery);
		List<OrderPO> list = query.list();
		return list;
	}

	@Override
	public List<OrderPO> getUserOrders(long userId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(userQuery);
		query.setLong("USERID", userId);
		List<OrderPO> list = query.list();
		return list;
	}

	@Override
	public List<OrderPO> getHotelOrders(long hotelId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(userQuery);
		query.setLong("HOTELID", hotelId);
		List<OrderPO> list = query.list();
		return list;
	}

	@Override
	public List<OrderPO> getHotelUserOrders(long hotelId, long userId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(hotelUserQuery);
		query.setLong("HOTELID", hotelId);
		query.setLong("USERID", userId);
		List<OrderPO> list = query.list();
		return list;
	}

}
