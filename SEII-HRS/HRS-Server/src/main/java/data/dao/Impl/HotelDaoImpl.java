package data.dao.Impl;

import java.util.List;

import data.dao.HotelDao;
import data.datahelper.HotelDataHelper;
import data.datahelper.impl.HotelDataHelperMysqlImpl;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;

public class HotelDaoImpl implements HotelDao {
	private HotelDataHelper hotelDataHelper = new HotelDataHelperMysqlImpl();
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
	public ListWrapper<BusinessCity> getAllCity() {
		List<BusinessCity> list = hotelDataHelper.getAllCity();
		return new ListWrapper<BusinessCity>(list);
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city, BusinessCircle circle) {
		List<HotelPO> list = hotelDataHelper.getHotelListByCityAndCircle(city, circle);
		return new ListWrapper<HotelPO>(list);
	}


	@Override
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId,Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HotelItem getRoomByRid(long hotelId, Room room) {
		// TODO Auto-generated method stub
		return null;
	}

}
