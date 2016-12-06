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
	//返回今天的房间
	public HotelItem getRoomByRid(long hotelId,Room room);
	public void updateRoom(long hotelId,HotelItem rpo);
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule);
	public ListWrapper<HotelPO> getHotelListByString(String rule);
	public ListWrapper<BusinessCity> getAllCity();
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city,BusinessCircle circle);
	//返回所有的房间
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId,Room room);
}
