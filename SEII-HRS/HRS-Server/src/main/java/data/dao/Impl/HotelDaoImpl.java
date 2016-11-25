package data.dao.Impl;

import data.dao.HotelDao;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;

public class HotelDaoImpl implements HotelDao {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListWrapper<HotelItem> getRoom(long hotelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRoom(long hotelId,HotelItem rpo) {
		// TODO Auto-generated method stub
		
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

}
