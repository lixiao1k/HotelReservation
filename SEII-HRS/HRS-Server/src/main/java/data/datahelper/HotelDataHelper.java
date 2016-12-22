package data.datahelper;

import java.util.List;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;

public interface HotelDataHelper {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public List<HotelPO> getHotelListByRule(Rule rule);
	public List<HotelPO> getHotelListByString(String rule);
	public List<BusinessCity> getAllCity();
	public List<HotelPO> getHotelListByCityAndCircle(BusinessCity city, BusinessCircle circle);
	public List<HotelItem> getHotelItemByRoom(long hotelId,Room room);
	public List<Room> getAllRooms();
}
