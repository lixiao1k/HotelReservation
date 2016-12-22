package data.datahelper.impl;

import java.util.List;

import org.hibernate.HibernateException;
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
	private static final String getAllCityQuery = "from BusinessCity";
	private static final String getHotelByCityAndCircle = 
									"from HotelPO as h where h.businessCity=:BCITY and h.businessCircle=:BCIRCLE";
	private static final String getHotelItemQuery = "from HotelItem as hi where hi.hotel=:HOTEL and hi.room=:ROOM";
	private static final String getHotelListByRuleQuery = 
			"select h from HotelPO as h,HotelItem as hi where h.businessCircle=:BCIRCLE and h.businessCity=:BCITY"
			+ " and hi.hotel=h";
	private static final String getAllRooms = "from Room";
	private static final String getHotelListByString = 
			"from HotelPO as h where h.name like :STRING";
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
	public List<HotelPO> getHotelListByRule(Rule rule) {
		StringBuilder temp = new StringBuilder(getHotelListByRuleQuery);
		try{
			System.out.println(rule.getBusinessCircle().getBcircleId());
			if(rule.getCheckInTime()!=null)
				temp.append(" and hi.date>=:beginTime");
			if(rule.getCheckOutTime()!=null)
				temp.append(" and hi.date<:endTime");
			Query query = HibernateUtil.getCurrentSession().createQuery(temp.toString());
			query.setEntity("BCITY", rule.getBusinessCity());
			query.setEntity("BCIRCLE", rule.getBusinessCircle());
			if(rule.getCheckInTime()!=null)
				query.setDate("beginTime", rule.getCheckInTime());
			if(rule.getCheckOutTime()!=null)
				query.setDate("endTime", rule.getCheckOutTime());
			return query.list();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<HotelPO> getHotelListByString(String rule) {
		Query query = HibernateUtil.getCurrentSession().createQuery(getHotelListByString);
		query.setEntity("STRING", rule);
		return query.list();
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
		HotelPO po = (HotelPO) HibernateUtil.getCurrentSession().load(HotelPO.class, hotelId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getHotelItemQuery);
		query.setEntity("HOTEL", po);
		query.setEntity("ROOM", room);
		return query.list();
	}

	@Override
	public List<Room> getAllRooms() {
		Query query = HibernateUtil.getCurrentSession().createQuery(getAllRooms);
		return query.list();
	}
	
}
