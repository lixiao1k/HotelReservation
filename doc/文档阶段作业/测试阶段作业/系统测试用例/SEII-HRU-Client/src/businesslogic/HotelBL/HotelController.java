package businesslogic.HotelBL;

import businesslogicservice.HotelBLService.HotelBLService_Update;
import businesslogicservice.HotelBLService.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public class HotelController implements HotelBLService_Update{
	Hotel hotelobject=new Hotel();

	@Override
	public HotelVO GetHotelInfo(long Hotelid) {
		return hotelobject.GetHotelInfo(Hotelid);
	}

	@Override
	public RoomVO GetRoomInfo(long Hotelid) {
		return hotelobject.GetRoomInfo(Hotelid);
	}

	@Override
	public HotelResultMessage SetHotelInfo(HotelVO vo) {
		return hotelobject.SetHotelInfo(vo);
	}

	@Override
	public HotelResultMessage SetRoomInfo(long hotelid, RoomVO vo) {
		return hotelobject.SetRoomInfo(hotelid, vo);
	}

	@Override
	public HotelResultMessage AddHotel(HotelVO vo) {
		return hotelobject.AddHotel(vo);
	}

	@Override
	public HotelResultMessage DeleteHotel(HotelVO vo) {
		return hotelobject.DeleteHotel(vo);
	}
	
}
