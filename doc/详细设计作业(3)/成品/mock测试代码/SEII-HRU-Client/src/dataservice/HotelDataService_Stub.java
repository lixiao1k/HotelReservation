package dataservice;

import po.HotelPO;
import po.RoomPO;

public class HotelDataService_Stub implements HotelDataService{

	@Override
	public void insert(HotelPO po) {
		// TODO Auto-generated method stub
		System.out.println("HotelDataService.insert SUCCESS");
	}

	@Override
	public HotelPO getHotelInfo(long hotelid) {
		// TODO Auto-generated method stub
		return new HotelPO();
	}

	@Override
	public void setHotelInfo(HotelPO po) {
		// TODO Auto-generated method stub
		System.out.println("HotelDataService.setHotelInfo SUCCESS");
	}

	@Override
	public void insertRoom(RoomPO po) {
		// TODO Auto-generated method stub
		System.out.println("HotelDataService.insertRoom SUCCESS");
	}

	@Override
	public RoomPO getRoomInfo(long hotelID) {
		// TODO Auto-generated method stub
		return new RoomPO();
	}

	@Override
	public void setRoomInfo(RoomPO po) {
		// TODO Auto-generated method stub
		System.out.println("HotelDataService.setRoomInfo SUCCESS");
	}

}
