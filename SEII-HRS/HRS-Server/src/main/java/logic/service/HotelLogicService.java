package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import resultmessage.HotelResultMessage;
import vo.HotelVO;
import vo.RoomVO;

public interface HotelLogicService extends Remote{
	public HotelVO GetHotelInfo(long Hotelid) throws RemoteException;
	public RoomVO GetRoomInfo(long Hotelid) throws RemoteException;
	public HotelResultMessage SetHotelInfo(HotelVO vo) throws RemoteException;
	public HotelResultMessage SetRoomInfo(long roomid,RoomVO vo) throws RemoteException;
	public HotelResultMessage AddHotel(HotelVO vo) throws RemoteException;
	public HotelResultMessage DeleteHotel(HotelVO vo) throws RemoteException;
}
