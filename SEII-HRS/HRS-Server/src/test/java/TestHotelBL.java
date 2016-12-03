import org.junit.Test;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Rank;
import info.Room;
import logic.service.HotelLogicService;
import logic.service.ServiceFactory;
import logic.service.impl.ServiceFactoryImpl;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.HotelItemVO;
import vo.SearchHotelVO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
public class TestHotelBL {
	@Test
	public void testGetCity() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<BusinessCity> cities = hotel.getCity();
		Assert.assertNotEquals("wrong!", null,cities);
		Iterator<BusinessCity> it = cities.iterator();
		while(it.hasNext()){
			BusinessCity bc = it.next();
			Iterator<BusinessCircle> itt = bc.getCircleIterator();
			System.out.println("----------------------------");
			System.out.println(bc.getName());
			while(itt.hasNext()){
				System.out.print(itt.next().getName()+"  ");
			}
			System.out.println("----------------------------");
		}
	}
	@Test
	public void testGetHotels() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		SearchHotelVO vo = new SearchHotelVO();
		BusinessCity bc = new BusinessCity();
		bc.setName("南京");
		vo.setBusinessCity(bc);
		ListWrapper<BasicHotelVO> list = hotel.getHotels(vo);
		Assert.assertEquals("wrong", null, list);
	}
	@Test
	public void testGetBookHotel() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<Long> list = hotel.getBookHotel(1);
		Assert.assertEquals("wrong", null,list);
	}
	@Test
	public void testRoomCheckIn() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		CheckInRoomInfoVO cirivo = new CheckInRoomInfoVO();
		cirivo.setCheckInTime(new Date());
		cirivo.setHotelId(1);
		cirivo.setRoomNum(1);
		cirivo.setRoom(new Room());
		HotelResultMessage result = hotel.roomCheckIn(cirivo);
		Assert.assertEquals("wrong", HotelResultMessage.FAIL,result);
	}
	@Test
	public void testRoomCheckOut() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		CheckOutRoomInfoVO vo = new CheckOutRoomInfoVO();
		vo.setActualCheckOutTime(new Date());
		vo.setOrderId(1);
		Assert.assertEquals("wrong"	,HotelResultMessage.FAIL,hotel.roomCheckOut(vo));
		
	}
	@Test
	public void testGetRoomInfo() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<HotelItemVO> list = hotel.getRoomInfo(1);
		Assert.assertEquals("wrong", null,list);
	}
	@Test
	public void testSetHotelInfo() throws RemoteException{
		
	}
	@Test
	public void testAddHotel() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		BusinessCity bc = new BusinessCity();
		bc.setName("南京");
		BusinessCircle bci = new BusinessCircle();
		bci.setBcircleId(1);
		bci.setName("新街口");
		HotelItemVO hi = new HotelItemVO();
		hi.setNum(88);
		hi.setPrice(233);
		hi.setRoom(new Room());
		Set<HotelItemVO> items = new HashSet<>();
		items.add(hi);
		AddHotelVO vo = new AddHotelVO("如家","如家酒店", "床，wifi", "江苏省南京市新街口233号", "住房，餐饮", bci,  bc, Rank.THREE, items);
		AddHotelResultVO ahrv = hotel.addHotel(vo);
		Assert.assertNotEquals("insert failed", null,ahrv);
		System.out.println(ahrv.getResultMessage());
	}
}
