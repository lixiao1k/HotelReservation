package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;
import vo.RuleVO;

public interface HotelLogicService extends Remote{
	public HotelVO getHotelInfo(long hotelId) throws RemoteException;
	public RoomVO getRoomInfo(long hotelId) throws RemoteException;
	public HotelResultMessage setHotelInfo(HotelVO vo) throws RemoteException;
	public HotelResultMessage setRoomInfo(long hotelId,RoomVO vo) throws RemoteException;
	public HotelResultMessage addHotel(HotelVO vo) throws RemoteException;
	public HotelResultMessage deleteHotel(HotelVO vo) throws RemoteException;
	public ListWrapper<HotelVO> getHotelList(RuleVO vo,int size) throws RemoteException;
	
}
