package data.dao;

import info.HotelItem;
import info.ListWrapper;
import po.HotelPO;

public interface HotelDao {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public ListWrapper<HotelItem> getRoom(long hotelId);
	public void updateRoom(long hotelId);
}
