package data.dao;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;
import vo.BasicHotelVO;
import vo.SearchHotelVO;

public interface HotelDao {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public ListWrapper<HotelItem> getRoom(long hotelId);
	public HotelItem getRoomByRid(long hotelId,long roomId);
	public void updateRoom(long hotelId,HotelItem rpo);
	public ListWrapper<HotelItem> getHotelListByRule(Rule rule);
	public ListWrapper<HotelItem> getHotelListByString(String rule);
	public ListWrapper<BusinessCity> getAllCity();
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city,BusinessCircle circle);
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId,Room room);
}
