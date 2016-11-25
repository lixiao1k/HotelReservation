package data.dao;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;

public interface HotelDao {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public ListWrapper<HotelItem> getRoom(long hotelId);
	public void updateRoom(long hotelId,HotelItem rpo);
	public ListWrapper<HotelItem> getHotelListByRule(Rule rule);
	public ListWrapper<HotelItem> getHotelListByString(String rule);
}
