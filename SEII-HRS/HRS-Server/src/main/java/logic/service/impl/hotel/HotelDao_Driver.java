package logic.service.impl.hotel;

import java.rmi.RemoteException;
import java.util.Date;

import data.dao.HotelDao;
import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.Room;
import info.Rule;
import po.HotelPO;


public class HotelDao_Driver {
	public void drive(HotelDao service) throws RemoteException{
		service.insert(new HotelPO());
		service.update(new HotelPO());
		service.getInfo(1);
		service.getInfo(2);
		service.getAllCity();
		service.getAllRooms();
		service.getRoomByRid(1, null);
		service.getRoomByRid(2, null);
		service.getRoomByRid(2, new Room());
		service.updateRoom(1, null);
		service.updateRoom(2, null);
		service.updateRoom(2, new HotelItem());
		Rule rule = new Rule();
		service.getHotelListByRule(null);
		service.getHotelListByRule(rule);
		rule.setCheckInTime(new Date());
		service.getHotelListByRule(rule);
		service.getHotelListByString(null);
		service.getHotelListByString(" ");
		service.getHotelListByString("as");
		service.getAllCity();
		BusinessCity bc = new BusinessCity();
		service.getHotelListByCityAndCircle(null, null);
		bc.setName("北京");
		service.getHotelListByCityAndCircle(bc, new BusinessCircle());
		bc.setName("南京");
		service.getHotelListByCityAndCircle(bc, new BusinessCircle());
		Room room = new Room();
		service.getHotelItemByRoom(1,null);
		service.getHotelItemByRoom(2, room);
		service.getHotelItemByRoom(3, room);
	}
}
