package logic.service;

import resultmessage.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public interface HotelLogicService {
	public HotelVO GetHotelInfo(long Hotelid);
	public RoomVO GetRoomInfo(long Hotelid);
	public HotelResultMessage SetHotelInfo(HotelVO vo);
	public HotelResultMessage SetRoomInfo(long roomid,RoomVO vo);
	public HotelResultMessage AddHotel(HotelVO vo);
	public HotelResultMessage DeleteHotel(HotelVO vo);
}
