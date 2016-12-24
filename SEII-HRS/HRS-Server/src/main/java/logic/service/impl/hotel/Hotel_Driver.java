package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.BusinessCircle;
import logic.service.HotelLogicService;
import vo.AddHotelVO;
import vo.AddRoomVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.CommentVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomInfoVO;
import vo.SearchHotelVO;

public class Hotel_Driver {
	public void drive(HotelLogicService service) throws RemoteException{
		service.getCity();
		service.getHotels(new SearchHotelVO());
		service.getExtraHotelDetail(1, 1);
		service.getBookHotel(1);
		CheckInRoomInfoVO cvo = new CheckInRoomInfoVO();
		cvo.setCheckInTime(new Date(100,1,1));
		cvo.setHotelId(1);
		service.roomCheckIn(cvo);
		cvo.setCheckInTime(new Date(155,1,1));
		service.roomCheckIn(cvo);
		cvo.setCheckInTime(new Date(100,1,1));
		cvo.setHotelId(2);
		service.roomCheckIn(cvo);
		CheckOutRoomInfoVO cvo2 = new CheckOutRoomInfoVO();
		cvo2.setActualCheckOutTime(new Date(100,1,1));
		cvo2.setOrderId(1);
		service.roomCheckOut(cvo2);
		cvo2.setActualCheckOutTime(new Date(155,1,1));
		service.roomCheckOut(cvo2);
		cvo2.setActualCheckOutTime(new Date(100,1,1));
		cvo2.setOrderId(2);
		service.roomCheckOut(cvo2);
		service.getRoomInfo(1);
		MaintainHotelInfoVO vo = new MaintainHotelInfoVO();
		vo.setHotelId(1);
		service.setHotelInfo(vo);
		vo.setHotelId(2);
		vo.setBusinessCircle(null);
		service.setHotelInfo(vo);
		vo.setBusinessCircle(new BusinessCircle());
		service.setHotelInfo(vo);
		MaintainRoomInfoVO vo2 = new MaintainRoomInfoVO();
		vo2.setHotelId(1);
		service.setRoomInfo(vo2);
		vo2.setHotelId(2);
		vo2.setChangeInfo(null);
		service.setRoomInfo(vo2);
		Set<RoomInfoVO> i = new HashSet<>();
		vo2.setChangeInfo(i);
		service.setRoomInfo(vo2);
		AddHotelVO ahvo = new AddHotelVO();
		ahvo.setUsername("1234");
		service.addHotel(ahvo);
		ahvo.setUsername("123");
		service.addHotel(ahvo);
		service.getHotelInfo(1);
		service.getHotelInfo(2);
		AddRoomVO arvo = new AddRoomVO();
		arvo.setHotelId(1);
		service.addNewRoom(arvo);
		arvo.setHotelId(2);
		arvo.setNum(-1);
		service.addNewRoom(arvo);
		arvo.setNum(10);
		service.addNewRoom(arvo);
	}
}