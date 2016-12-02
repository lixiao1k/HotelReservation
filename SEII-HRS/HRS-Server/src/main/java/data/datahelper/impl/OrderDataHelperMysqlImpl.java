package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import data.datahelper.OrderDataHelper;
import info.OrderStatus;
import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import util.HibernateUtil;

public class OrderDataHelperMysqlImpl implements OrderDataHelper{
	private static final String abnormalQuery = "from Order as o where o.status=ABNORMAL";
	private static final String userQuery = "from Order as o where o.member=:MEMBER";
	private static final String hotelQuery = "from Order as o where o.hotel=:HOTEL";
	private static final String hotelUserQuery = "from Order as o where o.member=:MEMBER and o.hotel=:HOTEL";
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
		MemberPO member = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class	, userId);
		query.setEntity("MEMBER", member);
		List<OrderPO> list = query.list();
		return list;
	}

	@Override
	public List<OrderPO> getHotelOrders(long hotelId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(userQuery);
		HotelPO hotel = (HotelPO) HibernateUtil.getCurrentSession().get(HotelPO.class	, hotelId);
		query.setEntity("HOTEL", hotel);
		List<OrderPO> list = query.list();
		return list;
	}

	@Override
	public List<OrderPO> getHotelUserOrders(long hotelId, long userId) {
		Session session = HibernateUtil.getCurrentSession();
		Query query = session.createQuery(hotelUserQuery);
		HotelPO hotel = (HotelPO) HibernateUtil.getCurrentSession().get(HotelPO.class	, hotelId);
		query.setEntity("HOTEL", hotel);
		MemberPO member = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class	, userId);
		query.setEntity("MEMBER", member);
		List<OrderPO> list = query.list();
		return list;
	}

}
