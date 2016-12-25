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
	/**
	 * 插入酒店
	 * @param po
	 * 
	 */
	public void insert(HotelPO po);
	/**
	 * 更新酒店
	 * @param po
	 */
	public void update(HotelPO po);
	/**
	 * 获取酒店的持久化类
	 * @param hotelId
	 * @return
	 */
	public HotelPO getInfo(long hotelId);
	/**
	 * 获取所有房间
	 * @return
	 * 所有房间类型
	 * @throws RemoteException
	 */
	public ListWrapper<Room> getAllRooms()throws RemoteException;
	
	/**
	 * 根据id 和 房间查找该酒店当天的该房间
	 * @param hotelId
	 * @param room
	 * @return
	 * 该酒店的房间信息
	 */
	public HotelItem getRoomByRid(long hotelId,Room room);
	/**
	 * 更新房间
	 * @param hotelId
	 * @param rpo
	 */
	public void updateRoom(long hotelId,HotelItem rpo);
	/**
	 * 根据传入的Rule查找相应酒店，封装起来，增强可维护性
	 * @param rule
	 * @return
	 * 查询到的酒店列表
	 * @throws RemoteException
	 */
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule)throws RemoteException ;
	/**
	 * 根据字符串rule查找酒店，一般是酒店名
	 * @param rule
	 * @return
	 * 所有酒店
	 * @throws RemoteException
	 */
	public ListWrapper<HotelPO> getHotelListByString(String rule)throws RemoteException ;
	/**
	 * @return
	 * 所有的城市信息
	 * @throws RemoteException
	 */
	public ListWrapper<BusinessCity> getAllCity()throws RemoteException ;
	/**
	 * 根据城市和商圈查找酒店
	 * @param city
	 * @param circle
	 * @return
	 * 所有查询到的酒店
	 * @throws RemoteException
	 */
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city,BusinessCircle circle)throws RemoteException ;
	
	/**
	 * 根据ID和房间查询该房间的15天内的所有房间信息
	 * @param hotelId
	 * @param room
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId,Room room)throws RemoteException ;
}
