package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.HotelDataHelper;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;
import util.HibernateUtil;

public class HotelDataHelperMysqlImpl implements HotelDataHelper{
	private static final String getAllCityQuery = "form BusinessCity";
	private static final String getHotelByCityAndCircle = 
									"from Hotel as h where h.businessCity=:BCITY and h.businessCircle=:BCIRCLE";
	private static final String getHotelItemQuery = "from HotleItem as hi wher hi.hotel=:HOTEL and hi.room=:ROOM";
	private static final String getHotelListByRuleQuery = 
			"from Hotel as h where h.businessCircle=:BCIRCLE and h.businessCity=:BCITY and";
	@Override
	public void insert(HotelPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public void update(HotelPO po) {
		HibernateUtil.getCurrentSession().update(po);
	}

	@Override
	public HotelPO getInfo(long hotelId) {
		HotelPO po = (HotelPO)HibernateUtil.getCurrentSession().get(HotelPO.class, hotelId);
		return po;
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<HotelItem> getHotelListByString(String rule) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BusinessCity> getAllCity() {
		Query query = HibernateUtil.getCurrentSession().createQuery(getAllCityQuery);
		return query.list();
	}

	@Override
	public List<HotelPO> getHotelListByCityAndCircle(BusinessCity city, BusinessCircle circle) {
		Query query = HibernateUtil.getCurrentSession().createQuery(getHotelByCityAndCircle);
		query.setEntity("BCITY", city);
		query.setEntity("BCIRCLE", circle);
		return query.list();
	}

	@Override
	public List<HotelItem> getHotelItemByRoom(long hotelId,Room room) {
		HotelPO po = (HotelPO) HibernateUtil.getCurrentSession().get(HotelPO.class, hotelId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getHotelItemQuery);
		query.setEntity("HOTEL", po);
		query.setEntity("ROOM", room);
		return query.list();
	}
	
}
