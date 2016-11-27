package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.HotelDataHelper;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;
import util.HibernateUtil;

public class HotelDataHelperMysqlImpl implements HotelDataHelper{
	private static final String getInfoQuery = "from Hotel as h where h.hid=:HOTELID";
	private static final String getAllCityQuery = "form BusinessCity as bc";
	private static final String getHotelByCityAndCircle = 
									"from Hotel as h where h.businessCity=:BCITY and h.businessCircle=:BCIRCLE";
	@Override
	public void insert(HotelPO po) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(HotelPO po) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HotelPO getInfo(long hotelId) {
		HotelPO po = (HotelPO)HibernateUtil.getCurrentSession().get(HotelPO.class, hotelId);
		return po;
	}

	@Override
	public ListWrapper<HotelItem> getHotelListByRule(Rule rule) {
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
	
}
