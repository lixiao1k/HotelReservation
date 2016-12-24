import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Rank;
import info.Room;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.service.HotelLogicService;
import logic.service.impl.hotel.HotelLogicServiceImpl;
import po.HotelPO;
import po.UserPO;
import resultmessage.HotelResultMessage;
import util.HibernateUtil;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.AddRoomVO;
import vo.BasicHotelVO;
import vo.CheckInRoomInfoVO;
import vo.CheckOutRoomInfoVO;
import vo.ExtraHotelVO;
import vo.HotelItemVO;
import vo.HotelVO;
import vo.MaintainHotelInfoVO;
import vo.MaintainRoomInfoVO;
import vo.RoomInfoVO;
import vo.SearchHotelVO;

public class HotelMockTest {
	private HotelLogicService service;
	@Before
	public void setup() throws RemoteException{
		service = mock(HotelLogicService.class);
		UserPO user = mock(UserPO.class);
		when(user.getUsername()).thenReturn("123");
		when(user.getUid()).thenReturn((long)1);
		HotelPO hotel = mock(HotelPO.class);
		when(hotel.getHid()).thenReturn((long)1);
		BusinessCity bc = mock(BusinessCity.class);
		when(bc.getCircles()).thenAnswer((InvocationOnMock invocation)->{
			return anySetOf(BusinessCircle.class);
		});
		when(bc.getName()).thenReturn("北京");
		when(service.addHotel(any(AddHotelVO.class))).thenAnswer((InvocationOnMock invocation)->{
			AddHotelVO vo = (AddHotelVO) invocation.getArguments()[0];
			AddHotelResultVO result = new AddHotelResultVO();
			if(vo.getUsername().equals(user.getUsername())){
				result.setHotelResultMessage(HotelResultMessage.FAIL_WRONGID);
				return result;
			}
			result.setHotelResultMessage(HotelResultMessage.SUCCESS);
			return result;
		});
		when(service.addNewRoom(any(AddRoomVO.class))).thenAnswer((InvocationOnMock invocation)->{
			AddRoomVO vo = (AddRoomVO) invocation.getArguments()[0];
			if(vo.getHotelId()==hotel.getHid())
				return HotelResultMessage.FAIL_WRONGID;
			return HotelResultMessage.SUCCESS;
		});
		when(service.getBookHotel(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0)
				return null;
			ListWrapper<Long> result = new ListWrapper<>();
			if(id==user.getUid())
				result.add(hotel.getHid());
			return result;
		});
		when(service.getCity()).thenAnswer((InvocationOnMock invocation)->{
			ListWrapper<BusinessCity> list = new ListWrapper<>();
			list.add(bc);
			return list;
		});
		when(service.getExtraHotelDetail(anyLong(), anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long hotelId = (long) invocation.getArguments()[0];
			long userId = (long) invocation.getArguments()[1];
			if(!(hotelId==1&&userId==1))
				return null;
			ExtraHotelVO ehvo = new ExtraHotelVO();
			return ehvo;
		});
		when(service.getHotelInfo(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long) invocation.getArguments()[0];
			if(id<=0)
				return null;
			if(id==hotel.getHid()){
				HotelVO h = new HotelVO();
				h.setHid(hotel.getHid());
				return h;
			}
			return new HotelVO();
		});
		when(service.getHotels(any(SearchHotelVO.class))).thenAnswer((InvocationOnMock invocation)->{
			SearchHotelVO vo = (SearchHotelVO) invocation.getArguments()[0];
			if(vo.getBusinessCity()==null)
				return null;
			ListWrapper<BasicHotelVO> list = new ListWrapper<>();
			if(vo.getBusinessCity().getName().equals(bc.getName())){
				BasicHotelVO bvo = new BasicHotelVO();
				bvo.setHotelId(hotel.getHid());
				list.add(bvo);
			}
			return list;
		});
		when(service.getRoomInfo(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long) invocation.getArguments()[0];
			if(id<=0)
				return null;
			ListWrapper<HotelItemVO> list = new ListWrapper<>();
			if(id==hotel.getHid()){
				HotelItemVO hivo = new HotelItemVO();
				list.add(hivo);
			}
			return list;
		});
		when(service.roomCheckIn(any(CheckInRoomInfoVO.class))).thenAnswer((InvocationOnMock invocation)->{
			CheckInRoomInfoVO vo = (CheckInRoomInfoVO) invocation.getArguments()[0];
			if(vo==null)
				return HotelResultMessage.FAIL;
			if(vo.getHotelId()==hotel.getHid()){
				return HotelResultMessage.SUCCESS;
			}
			return HotelResultMessage.FAIL;
		});
		when(service.roomCheckOut(any(CheckOutRoomInfoVO.class))).thenAnswer((InvocationOnMock invocation)->{
			CheckOutRoomInfoVO vo = (CheckOutRoomInfoVO) invocation.getArguments()[0];
			if(vo==null)
				return HotelResultMessage.FAIL;
			if(vo.getOrderId()==1){
				return HotelResultMessage.SUCCESS;
			}
			return HotelResultMessage.FAIL;
		});
		when(service.setHotelInfo(any(MaintainHotelInfoVO.class))).thenAnswer((InvocationOnMock invocation)->{
			MaintainHotelInfoVO vo = (MaintainHotelInfoVO) invocation.getArguments()[0];
			if(vo.getHotelId()==hotel.getHid()){
				return HotelResultMessage.SUCCESS;
			}
			return HotelResultMessage.FAIL;
		});
		when(service.setRoomInfo(any(MaintainRoomInfoVO.class))).thenAnswer((InvocationOnMock invocation)->{
			MaintainRoomInfoVO vo = (MaintainRoomInfoVO) invocation.getArguments()[0];
			if(vo.getHotelId()==hotel.getHid()){
				return HotelResultMessage.SUCCESS;
			}
			return HotelResultMessage.FAIL;
		});
	}
	@Test
	public void testGetCity() throws RemoteException{
		ListWrapper<BusinessCity> list = service.getCity();
		assertNotEquals("wrong", null,list);
		assertEquals("wrong", 1,list.size());
	}
	@Test
	public void testGetHotels() throws RemoteException{
		SearchHotelVO vo = new SearchHotelVO();
		ListWrapper<BasicHotelVO> list = service.getHotels(vo);
		assertEquals("wrong", null,list);
		BusinessCity bc = new BusinessCity();
		bc.setName("北京");
		vo.setBusinessCity(bc);
		list = service.getHotels(vo);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong",1,list.size());
		assertEquals("wrong", 1,list.iterator().next().getHotelId());
		bc.setName("1");
		vo.setBusinessCity(bc);
		list = service.getHotels(vo);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong",0,list.size());
	}
	@Test
	public void testGetBookHotel() throws RemoteException{
		ListWrapper<Long> list = service.getBookHotel(1);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong",1,list.size());
		assertEquals("wrong", (long)1,list.iterator().next().longValue());
		list = service.getBookHotel(2);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong",0,list.size());
		list = service.getBookHotel(0);
		assertEquals("wrong", null,list);
	}
	@Test
	public void setRoomInfo() throws RemoteException{
		MaintainRoomInfoVO vo = new MaintainRoomInfoVO();
		assertEquals("wrong", HotelResultMessage.FAIL,service.setRoomInfo(vo));
		vo.setHotelId(1);
		assertEquals("wrong", HotelResultMessage.SUCCESS,service.setRoomInfo(vo));
	}
	@Test
	public void testGetExtraHotelDetail() throws RemoteException{
		ExtraHotelVO vo = service.getExtraHotelDetail(1, 1);
		assertNotEquals("wrong", null,vo);
		vo = service.getExtraHotelDetail(0, 1);
		assertEquals("wrong", null,vo);
	}
	@Test
	public void testRoomCheckIn() throws RemoteException{
		CheckInRoomInfoVO vo = new CheckInRoomInfoVO();
		assertEquals("wrong", HotelResultMessage.FAIL,service.roomCheckIn(null));
		assertEquals("wrong", HotelResultMessage.FAIL,service.roomCheckIn(vo));
		vo.setHotelId(1);
		assertEquals("wrong", HotelResultMessage.SUCCESS,service.roomCheckIn(vo));
	}
	@Test
	public void testRoomCheckOut() throws RemoteException{
		CheckOutRoomInfoVO vo = new CheckOutRoomInfoVO();
		assertEquals("wrong", HotelResultMessage.FAIL,service.roomCheckIn(null));
		assertEquals("wrong", HotelResultMessage.FAIL,service.roomCheckOut(vo));
		vo.setOrderId(1);
		assertEquals("wrong", HotelResultMessage.SUCCESS,service.roomCheckOut(vo));
	}
	@Test
	public void testGetRoomInfo() throws RemoteException{
		ListWrapper<HotelItemVO> list = service.getRoomInfo(0);
		assertEquals("wrong", null,list);
		list = service.getRoomInfo(1);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong", 1,list.size());
	}
	@Test
	public void testSetHotelInfo() throws RemoteException{
		MaintainHotelInfoVO vo = new MaintainHotelInfoVO();
		assertEquals("wrong", HotelResultMessage.FAIL,service.setHotelInfo(vo));
		vo.setHotelId(1);
		assertEquals("wrong", HotelResultMessage.SUCCESS,service.setHotelInfo(vo));
	}
	@Test
	public void testAddHotel() throws RemoteException{
		AddHotelVO vo = new AddHotelVO();
		vo.setUsername("123");
		AddHotelResultVO r = service.addHotel(vo);
		assertEquals("wrong", HotelResultMessage.FAIL_WRONGID,r.getResultMessage());
		vo.setUsername("11");
		r = service.addHotel(vo);
		assertEquals("wrong", HotelResultMessage.SUCCESS,r.getResultMessage());
	}
}
