package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Base64;

import info.BusinessCity;
import info.ListWrapper;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelItemVO;
import vo.HotelVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomVO;
import vo.SearchHotelVO;

public interface HotelLogicService extends Remote{
	public ListWrapper<BusinessCity> getCity() throws RemoteException;
	public ListWrapper<BasicHotelVO> getHotels(SearchHotelVO vo) throws RemoteException;
	public ExtraHotelVO getExtraHotelDetail(long hotelId,long userId) throws RemoteException;
	public ListWrapper<Long> getBookHotel(long userId) throws RemoteException;
	public HotelResultMessage roomCheckIn(CheckInRoomInfoVO vo) throws RemoteException;
	public HotelResultMessage roomCheckOut(CheckOutRoomInfoVO vo) throws RemoteException;
	public ListWrapper<HotelItemVO> getRoomInfo(long hotelId) throws RemoteException;
	public HotelResultMessage setHotelInfo(MaintainHotelInfoVO vo) throws RemoteException;
	public HotelResultMessage setRoomInfo(MaintainRoomInfoVO vo) throws RemoteException;
	public AddHotelResultVO addHotel(AddHotelVO vo) throws RemoteException;
	
}
