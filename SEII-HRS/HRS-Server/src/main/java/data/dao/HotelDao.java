package data.dao;

import java.rmi.RemoteException;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.HotelPO;
import vo.BasicHotelVO;
import vo.SearchHotelVO;

public interface HotelDao {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public ListWrapper<Room> getAllRooms()throws RemoteException;
	//锟斤拷锟截斤拷锟斤拷姆锟斤拷锟�
	public HotelItem getRoomByRid(long hotelId,Room room);
	public void updateRoom(long hotelId,HotelItem rpo);
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule)throws RemoteException ;
	public ListWrapper<HotelPO> getHotelListByString(String rule)throws RemoteException ;
	public ListWrapper<BusinessCity> getAllCity()throws RemoteException ;
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city,BusinessCircle circle)throws RemoteException ;
	//锟斤拷锟斤拷锟斤拷锟叫的凤拷锟斤拷
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId,Room room)throws RemoteException ;
}
