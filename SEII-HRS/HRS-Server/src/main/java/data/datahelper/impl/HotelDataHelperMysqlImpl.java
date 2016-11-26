package data.datahelper.impl;

import data.datahelper.HotelDataHelper;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;
import util.HibernateUtil;

public class HotelDataHelperMysqlImpl implements HotelDataHelper{
	private static final String getInfoQuery = "from Hotel as h where h.hid=:HOTELID";
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
	public ListWrapper<BusinessCity> getAllCity() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
