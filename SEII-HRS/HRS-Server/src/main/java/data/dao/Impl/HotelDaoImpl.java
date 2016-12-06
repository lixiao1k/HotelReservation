package data.dao.Impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import data.dao.HotelDao;
import data.datahelper.HotelDataHelper;
import data.datahelper.impl.DataFactory;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;

public class HotelDaoImpl implements HotelDao {
	private HotelDataHelper hotelDataHelper;
	public HotelDaoImpl() {
		hotelDataHelper = DataFactory
							.getInstance()
							.getHotelDataHelper();
	}
	@Override
	public void insert(HotelPO po) {
		hotelDataHelper.insert(po);
	}

	@Override
	public void update(HotelPO po) {
		hotelDataHelper.update(po);
	}

	@Override
	public HotelPO getInfo(long hotelId) {
		return hotelDataHelper.getInfo(hotelId);
	}
	
	@Override
	public void updateRoom(long hotelId,HotelItem rpo) {
		HotelPO po = hotelDataHelper.getInfo(hotelId);
		if(po!=null){
			Iterator<HotelItem> it = po.getRoom();
			while(it.hasNext()){
				HotelItem hi = it.next();
				if(hi.getHiId()==rpo.getHiId()){
					hi.setDate(rpo.getDate());
					hi.setNum(rpo.getNum());
					hi.setPrice(rpo.getPrice());
					hi.setRoom(rpo.getRoom());
					hi.setTotal(rpo.getTotal());
					hotelDataHelper.update(po);
					break;
				}
			}
		}
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule) {
		List<HotelPO> list = hotelDataHelper.getHotelListByRule(rule);
		return new ListWrapper<HotelPO>(list);
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByString(String rule) {
		List<HotelPO> list = hotelDataHelper.getHotelListByString(rule);
		return new ListWrapper<>(list);
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
		List<HotelItem> rooms = hotelDataHelper.getHotelItemByRoom(hotelId, room);
		return new ListWrapper<HotelItem>(rooms);
	}

	@Override
	public HotelItem getRoomByRid(long hotelId, Room room) {
		List<HotelItem> rooms = hotelDataHelper.getHotelItemByRoom(hotelId, room);
		HotelItem result = null;
		Date now = new Date();
		if(rooms!=null){
			Iterator<HotelItem> it = rooms.iterator();
			while(it.hasNext()){
				HotelItem hi = it.next();
				if(hi.getRoom().getType().equals(room.getType())
						&& hi.getDate().getDate()==now.getDate()){
					result = hi;
					break;
				}
			}
		}
		return result;
	}

}
