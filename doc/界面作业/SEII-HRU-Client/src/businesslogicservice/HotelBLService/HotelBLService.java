package businesslogicservice.HotelBLService;

import vo.HotelVO;
import vo.RoomVO;

public interface HotelBLService {
	public HotelVO GetHotelInfo(long hotelid);
	public HotelResultMessage SetHotelInfo(HotelVO vo);
	public HotelResultMessage SetRoomInfo(long hotelid, RoomVO vo);
	public HotelResultMessage AddHotel(HotelVO vo);
	public HotelResultMessage AddRoom(RoomVO vo);
}
