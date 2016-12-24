package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.HotelDao;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Room;
import info.Rule;
import po.CreditPO;
import po.HotelPO;

public class HotelDao_Stub implements HotelDao{

	@Override
	public void insert(HotelPO po) {
		System.out.println("HotelDao.insert  :  insert success");
	}

	@Override
	public void update(HotelPO po) {
		System.out.println("HotelDao.insert  :  update success");
	}

	@Override
	public HotelPO getInfo(long hotelId) {
		if(hotelId==1){
			System.out.println("HotelDao.getInfo  :  hotelId=1|return NULL");
			return null;
		}
		System.out.println("HotelDao.getInfo  :  hotelId=other number|return normal result");
		return new HotelPO();
	}

	@Override
	public ListWrapper<Room> getAllRooms() throws RemoteException {
		System.out.println("HotelDao.getAllRooms  :  return normal result");
		ListWrapper<Room> result = new ListWrapper<Room>();
		result.add(new Room());
		return result;
	}

	@Override
	public HotelItem getRoomByRid(long hotelId, Room room) {
		if(hotelId==1){
			System.out.println("HotelDao.getRoomByRid  :  hotelId=1|return null");
			return null;
		}
		if(room==null){
			System.out.println("HotelDao.getRoomByRid  :  hotelId!=-1,room=null|return null");
			return null;
		}
		System.out.println("HotelDao.getRoomByRid  :  hotelId!=-1,room!=null|return normal HotelItem");
		return new HotelItem();
	}

	@Override
	public void updateRoom(long hotelId, HotelItem rpo) {
		if(hotelId==1){
			System.out.println("HotelDao.updateRoom  :  hotelId=1|fail");
		}
		if(rpo==null){
			System.out.println("HotelDao.updateRoom  :  hotelId!=-1,rpo=null|fail");
		}
		System.out.println("HotelDao.updateRoom  :  hotelId!=-1,rpo!=null|success");
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByRule(Rule rule) throws RemoteException {
		if(rule==null){
			System.out.println("HotelDao.getHotelListByRule  :  rule=null|return null");
			return null;
		}
		if(rule.getCheckInTime()==null){
			System.out.println("HotelDao.getHotelListByRule  :  rule's info incomplete|return empty ListWrapper");
			return new ListWrapper<>();
		}
		ListWrapper<HotelPO> list = new ListWrapper<>();
		list.add(new HotelPO());
		System.out.println("HotelDao.getHotelListByRule  :  rule normal|return normal result");
		return list;
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByString(String rule) throws RemoteException {
		if(rule==null){
			System.out.println("HotelDao.getHotelListByString  :  rule=null|return null");
			return null;
		}
		if(rule.equals("\\s")){
			System.out.println("HotelDao.getHotelListByString  :  rule's info wrong|return empty ListWrapper");
			return new ListWrapper<>();
		}
		ListWrapper<HotelPO> list = new ListWrapper<>();
		list.add(new HotelPO());
		System.out.println("HotelDao.getHotelListByString  :  rule normal|return normal result");
		return list;
	}

	@Override
	public ListWrapper<BusinessCity> getAllCity() throws RemoteException {
		ListWrapper<BusinessCity> list = new ListWrapper<>();
		list.add(new BusinessCity());
		System.out.println("HotelDao.getAllCity  :  return normal result");
		return list;
	}

	@Override
	public ListWrapper<HotelPO> getHotelListByCityAndCircle(BusinessCity city, BusinessCircle circle)
			throws RemoteException {
		if(city==null||circle==null){
			System.out.println("HotelDao.getHotelListByCityAndCircle  :  city==null||circle==null|return null");
			return null;
		}
		if(city.getName().equals("±±¾©")){
			System.out.println("HotelDao.getHotelListByCityAndCircle  :  info wrong|return empty ListWrapper");
			return new ListWrapper<>();
		}
		ListWrapper<HotelPO> list = new ListWrapper<>();
		list.add(new HotelPO());
		System.out.println("HotelDao.getHotelListByCityAndCircle  :  info normal|return normal result");
		return list;
	}

	@Override
	public ListWrapper<HotelItem> getHotelItemByRoom(long hotelId, Room room) throws RemoteException {
		if(hotelId==1||room==null){
			System.out.println("HotelDao.getHotelItemByRoom  :  hotelId=1||room==null|return null");
			return null;
		}
		if(hotelId==2){
			System.out.println("HotelDao.getHotelItemByRoom  :  hotelId==2|return empty result");
			return new ListWrapper<>();
		}ListWrapper<HotelItem> list = new ListWrapper<>();
		list.add(new HotelItem());
		System.out.println("HotelDao.getHotelItemByRoom  :  info normal|return normal result");
		return list;
	}

}
