package dataservice;

import po.HotelPO;
import po.RoomPO;

public interface HotelDataService extends DatabaseService{
	public void insert(HotelPO po);
	public HotelPO getHotelInfo(long hotelid);
	public void setHotelInfo(HotelPO po);
	public void insertRoom(RoomPO po);
	public RoomPO getRoomInfo(long hotelID);
	public void setRoomInfo(RoomPO po);
}
