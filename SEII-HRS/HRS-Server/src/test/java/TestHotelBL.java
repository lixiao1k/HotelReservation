import org.junit.Test;

import data.dao.HotelDao;
import data.dao.Impl.DaoManager;
import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Rank;
import info.Room;
import logic.service.HotelLogicService;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import resultmessage.HotelResultMessage;
import util.HibernateUtil;
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
	public void testAddCityAndCircle() throws RemoteException{
		try{

			HibernateUtil.getCurrentSession().beginTransaction();
			BusinessCity bc1 = new BusinessCity();
			bc1.setName("北京");
			BusinessCircle bcir1 = new BusinessCircle();
			bcir1.setName("五道口");
			BusinessCircle bcir2 = new BusinessCircle();
			bcir2.setName("虫鸣机");
			Set<BusinessCircle> set = new HashSet<>();
			bcir1.setBcity(bc1);
			bcir2.setBcity(bc1);
			set.add(bcir1);
			set.add(bcir2);
			bc1.setCircles(set);
			BusinessCity bc2 = new BusinessCity();
			bc2.setName("南京");
			BusinessCircle bcir3 = new BusinessCircle();
			bcir3.setName("新街口");
			BusinessCircle bcir4 = new BusinessCircle();
			bcir4.setName("苜蓿园");
			Set<BusinessCircle> set2 = new HashSet<>();
			bcir3.setBcity(bc2);
			bcir4.setBcity(bc2);
			set2.add(bcir3);
			set2.add(bcir4);
			bc2.setCircles(set2);
			HibernateUtil.getCurrentSession().save(bc1);
			HibernateUtil.getCurrentSession().save(bc2);
			HibernateUtil.getCurrentSession().save(bcir1);
			HibernateUtil.getCurrentSession().save(bcir2);
			HibernateUtil.getCurrentSession().save(bcir3);
			HibernateUtil.getCurrentSession().save(bcir4);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
			}catch(RuntimeException ex){
				ex.printStackTrace();
			}
		}
	}
	@Test
	public void testGetHotels() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		SearchHotelVO vo = new SearchHotelVO();
		BusinessCity bc = new BusinessCity();
		bc.setName("锟较撅拷");
		bc.setBcityId(1);
		vo.setBusinessCity(bc);
		BusinessCircle bcir = new BusinessCircle();
		bcir.setName("锟铰街匡拷");
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
		room.setType("锟襟床凤拷");
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
		room.setType("锟襟床凤拷");
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
		vo.setAddress("锟斤拷锟斤拷省锟较撅拷锟斤拷锟铰街匡拷224锟斤拷");
		vo.setHotelId(1);
		HotelResultMessage result = hotel.setHotelInfo(vo);
		Assert.assertEquals("wrong", HotelResultMessage.SUCCESS,result);
	}
	@Test
	public void testAddHotel() throws RemoteException{
		HotelLogicService hotel = new HotelLogicServiceImpl();
		BusinessCity bc = new BusinessCity();
		bc.setName("锟较撅拷");
		bc.setBcityId(1);
		BusinessCircle bci = new BusinessCircle();
		bci.setBcircleId(2);
		bci.setName("锟斤拷同路");
		HotelItemVO hi = new HotelItemVO();
		hi.setNum(88);
		hi.setPrice(233);
		Room room = new Room("锟襟床凤拷");
		room.setRid(1);
		hi.setRoom(room);
		hi.setTotal(100);
		HotelItemVO hi2 = new HotelItemVO();
		hi2.setNum(110);
		hi2.setPrice(233);
		Room room2 = new Room("双锟剿凤拷");
		room2.setRid(2);
		hi2.setRoom(room2);
		hi2.setTotal(1001);
		Set<HotelItemVO> items = new HashSet<>();
		items.add(hi);
		items.add(hi2);
		AddHotelVO vo = new AddHotelVO("锟斤拷锟�","锟斤拷揖频锟�", "锟斤拷锟斤拷wifi", "锟斤拷锟斤拷省锟较撅拷锟斤拷锟铰街匡拷233锟斤拷", "住锟斤拷锟斤拷锟斤拷锟斤拷", bci,  bc, Rank.THREE, items);
		vo.setUsername("NanjingDTLRuJia");
		vo.setPassword("123456");
		vo.setMemberName("小锟斤拷锟斤拷");
		AddHotelResultVO ahrv = hotel.addHotel(vo);
		Assert.assertNotEquals("insert failed", null,ahrv);
		System.out.println(ahrv.getResultMessage());
	}
}
