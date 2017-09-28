package presentation.HotelUI;

import businesslogicservice.HotelBLService.HotelBLService;
import businesslogicservice.HotelBLService.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public class HotelBLService_Driver {
	public void drive(HotelBLService service){
		HotelVO vo = service.GetHotelInfo(1234);
		if (vo!=null) System.out.println("HotelBLService.getHotelInfo SUCCESS");
		HotelResultMessage result = service.AddHotel(vo);
		if (result==HotelResultMessage.SUCCESS) System.out.println("HotelBLService.addhotel SUCCESS");
		RoomVO roomvo = new RoomVO(null);
		result = service.AddRoom(roomvo);
		if (result==HotelResultMessage.SUCCESS) System.out.println("HotelBLService.addroom SUCCESS");
		result = service.SetHotelInfo(vo);
		if (result==HotelResultMessage.SUCCESS) System.out.println("HotelBLService.SetHotelInfo SUCCESS");
		result = service.SetRoomInfo(1234, roomvo);
		if (result==HotelResultMessage.SUCCESS) System.out.println("HotelBLService.SetRoomInfo SUCCESS");
	}

}
