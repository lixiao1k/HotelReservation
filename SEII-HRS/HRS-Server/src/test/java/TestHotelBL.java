import org.junit.Test;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Rank;
import info.Room;
import logic.service.HotelLogicService;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import resultmessage.HotelResultMessage;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelItemVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomInfoVO;
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
			System.out.println();
			System.out.println("----------------------------");
		}
	}
	@Test
	public void testGetHotels() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		SearchHotelVO vo = new SearchHotelVO();
		BusinessCity bc = new BusinessCity();
		bc.setName("南京");
		bc.setBcityId(1);
		vo.setBusinessCity(bc);
		BusinessCircle bcir = new BusinessCircle();
		bcir.setName("新街口");
		bcir.setBcircleId(1);
		vo.setBusinessCircle(bcir);
		vo.setCheckInTime(new Date(116,11,1));
		vo.setCheckOutTime(new Date(116,11,7));
		ListWrapper<BasicHotelVO> list = hotel.getHotels(vo);
		Assert.assertNotEquals("wrong", null, list);
		Iterator<BasicHotelVO> it = list.iterator();
		while(it.hasNext()){
			BasicHotelVO bhvo = it.next();
			System.out.println(bhvo.getHotelName()+" "+bhvo.getHotelId());
		}
	}
	@Test
	public void testGetBookHotel() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<Long> list = hotel.getBookHotel(2);
		Assert.assertNotEquals("wrong", null,list);
		Iterator<Long> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
	@Test
	public void setRoomInfo() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		MaintainRoomInfoVO vo = new MaintainRoomInfoVO();
		vo.setHotelId(1);
		Set<RoomInfoVO> list = new HashSet<>();
		RoomInfoVO rvo = new RoomInfoVO();
		rvo.setNum(3);
		Room room = new Room();
		room.setRid(1);
		room.setType("大床房");
		rvo.setSourceType(room);
		rvo.setTargetType(room);
		list.add(rvo);
		vo.setChangeInfo(list);
		HotelResultMessage result = hotel.setRoomInfo(vo);
		Assert.assertEquals("wrong", HotelResultMessage.SUCCESS,result);
		
	}
	@Test
	public void testGetExtraHotelDetail() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ExtraHotelVO vo = hotel.getExtraHotelDetail(1, 3);
		Assert.assertNotEquals("Wrong", null,vo);
		System.out.println(vo.getBookedOrders().size());
	}
	@Test
	public void testRoomCheckIn() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		CheckInRoomInfoVO cirivo = new CheckInRoomInfoVO();
		cirivo.setCheckInTime(new Date());
		cirivo.setHotelId(1);
		cirivo.setRoomNum(2);
		Room room = new Room();
		room.setRid(1);
		room.setType("大床房");
		cirivo.setRoom(room);
		HotelResultMessage result = hotel.roomCheckIn(cirivo);
		Assert.assertEquals("wrong", HotelResultMessage.SUCCESS,result);
	}
	@Test
	public void testRoomCheckOut() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		CheckOutRoomInfoVO vo = new CheckOutRoomInfoVO();
		vo.setActualCheckOutTime(new Date());
		vo.setOrderId(1);
		Assert.assertEquals("wrong"	,HotelResultMessage.SUCCESS,hotel.roomCheckOut(vo));
		
	}
	@Test
	public void testGetRoomInfo() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		ListWrapper<HotelItemVO> list = hotel.getRoomInfo(1);
		Assert.assertNotEquals("wrong", null,list);
		Iterator<HotelItemVO> it = list.iterator();
		while(it.hasNext()){
			HotelItemVO vo = it.next();
			System.out.println(vo.getRoom().getType()+" "+vo.getDate());
		}
	}
	@Test
	public void testSetHotelInfo() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		MaintainHotelInfoVO vo = new MaintainHotelInfoVO();
		vo.setAddress("江苏省南京市新街口224号");
		vo.setHotelId(1);
		HotelResultMessage result = hotel.setHotelInfo(vo);
		Assert.assertEquals("wrong", HotelResultMessage.SUCCESS,result);
	}
	@Test
	public void testAddHotel() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		BusinessCity bc = new BusinessCity();
		bc.setName("南京");
		bc.setBcityId(1);
		BusinessCircle bci = new BusinessCircle();
		bci.setBcircleId(2);
		bci.setName("大同路");
		HotelItemVO hi = new HotelItemVO();
		hi.setNum(88);
		hi.setPrice(233);
		Room room = new Room("大床房");
		room.setRid(1);
		hi.setRoom(room);
		hi.setTotal(100);
		HotelItemVO hi2 = new HotelItemVO();
		hi2.setNum(110);
		hi2.setPrice(233);
		Room room2 = new Room("双人房");
		room2.setRid(2);
		hi2.setRoom(room2);
		hi2.setTotal(1001);
		Set<HotelItemVO> items = new HashSet<>();
		items.add(hi);
		items.add(hi2);
		AddHotelVO vo = new AddHotelVO("如家","如家酒店", "床，wifi", "江苏省南京市新街口233号", "住房，餐饮", bci,  bc, Rank.THREE, items);
		vo.setUsername("NanjingDTLRuJia");
		vo.setPassword("123456");
		vo.setMemberName("小丸子");
		AddHotelResultVO ahrv = hotel.addHotel(vo);
		Assert.assertNotEquals("insert failed", null,ahrv);
		System.out.println(ahrv.getResultMessage());
	}
}
