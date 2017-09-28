package businesslogicservice.HotelBLService;

import dataservice.HotelDataService;
import po.HotelPO;
import po.RoomPO;

public class HotelDataService_Driver {
	public void drive(HotelDataService service){
		service.insert(new HotelPO());
		service.insertRoom(new RoomPO());
		service.setHotelInfo(new HotelPO());
		service.setRoomInfo(new RoomPO());
		HotelPO hpo = service.getHotelInfo(1234);
		if (hpo!=null) System.out.println("HotelDataService.getHotelInfo SUCCESS");
		RoomPO rpo = service.getRoomInfo(1234);
		if (rpo!=null) System.out.println("HotelDataService.getRoomInfo SUCCESS");
	}
}
