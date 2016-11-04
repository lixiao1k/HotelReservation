package businesslogic.HotelBL;

import java.util.List;

import businesslogicservice.HotelBLService.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public class Hotel {
	private List<HotelVO> HotelList;
	public HotelVO GetHotelInfo(long Hotelid){
		for(HotelVO hotel:HotelList){
			if(hotel.getid()==Hotelid){
				return hotel;
			}
		}
		return null;
	}
	public RoomVO GetRoomInfo(long Hotelid){
		for(HotelVO hotel:HotelList){
			if(hotel.getid()==Hotelid){
				return hotel.getRoomPO();
			}
		}
		return null;
	}
	public HotelResultMessage SetHotelInfo(HotelVO vo){
		for(HotelVO hotel:HotelList){
			if(hotel.getid()==vo.getid()){
				hotel=vo;
				return HotelResultMessage.SUCCESS;
			}
		}
		return HotelResultMessage.FAIL;
	}
	public HotelResultMessage SetRoomInfo(long hotelid,RoomVO vo){
		for(HotelVO hotel:HotelList){
			if(hotel.getid()==hotelid){
				hotel.setRoomPO(vo);
				return HotelResultMessage.SUCCESS;
			}
		}
		return HotelResultMessage.FAIL;
	}
	public HotelResultMessage AddHotel(HotelVO vo){
		if(HotelList.add(vo)){
			return HotelResultMessage.SUCCESS;
		}else{
			return HotelResultMessage.FAIL;
		}
	}

	public HotelResultMessage DeleteHotel(HotelVO vo){
		if(HotelList.remove(vo)){
			return HotelResultMessage.SUCCESS;
		}else{
			return HotelResultMessage.FAIL;
		}
	}

}
