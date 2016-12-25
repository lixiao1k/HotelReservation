import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import static org.junit.Assert.*;
import info.ListWrapper;
import info.OrderStrategy;
import info.Room;
import info.StrategyType;

import static org.mockito.Mockito.*;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import logic.service.StrategyLogicService;
import logic.service.impl.strategy.StrategyLogicServiceImpl;
import po.HotelPO;
import po.UserPO;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
import vo.StrategyItemVO;
import vo.StrategyResultVO;
import vo.StrategyVO;

public class StrategyMockTest {
	private StrategyLogicService service;
	@Before
	public void setup() throws RemoteException{
		service = mock(StrategyLogicService.class);
		UserPO user = mock(UserPO.class);
		when(user.getUsername()).thenReturn("123");
		when(user.getUid()).thenReturn((long)1);
		HotelPO hotel = mock(HotelPO.class);
		when(hotel.getHid()).thenReturn((long)1);
		HotelStrategyVO strategy = mock(HotelStrategyVO.class);
		when(strategy.getId()).thenReturn((long)1);
		when(service.getStrategyForOrder(any(OrderStrategy.class))).thenAnswer((InvocationOnMock invocation)->{
			OrderStrategy vo = (OrderStrategy) invocation.getArguments()[0];
			if(vo.getUserId()==0||vo.getHotelId()==0)
				return null;
			ListWrapper<HotelStrategyVO> list = new ListWrapper<>();
			if(vo.getUserId()==user.getUid()&&vo.getHotelId()==hotel.getHid()){
				list.add(strategy);
			}
			return list;
		});
		when(service.getStrategyList(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long) invocation.getArguments()[0];
			if(id<=0)
				return null;
			ListWrapper<HotelStrategyVO> list = new ListWrapper<>();
			if(id==hotel.getHid()){
				list.add(strategy);
			}
			return list;
		});
		when(service.getTypes()).thenReturn(new ListWrapper<>());
		when(service.getWEBStrategyList()).thenReturn(new ListWrapper<>());
		when(service.delete(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long) invocation.getArguments()[0];
			if(id==strategy.getId())
				return StrategyResultMessage.SUCCESS;
			return StrategyResultMessage.FAIL_WRONGID;
		});
		when(service.create(any(StrategyVO.class))).thenAnswer((InvocationOnMock invocation)->{
			StrategyVO vo = (StrategyVO) invocation.getArguments()[0];
			StrategyResultVO r = new StrategyResultVO();
			if(vo.getHotelId()<=0){
				r.setResultMessage(StrategyResultMessage.FAIL_WRONGID);
				return r;
			}
			r.setResultMessage(StrategyResultMessage.SUCCESS);
			return r;
		});
	}
	@Test
	public void testCreate() throws RemoteException{
		StrategyVO vo = new StrategyVO();
		StrategyResultVO r = service.create(vo);
		assertEquals("wrong",StrategyResultMessage.FAIL_WRONGID,r.getResultMessage());
		vo.setHotelId(1);
		r = service.create(vo);
		assertEquals("wrong", StrategyResultMessage.SUCCESS,r.getResultMessage());
	}
	@Test
	public void testDelete() throws RemoteException{
		StrategyResultMessage r = service.delete(1);
		assertEquals("wrong", StrategyResultMessage.SUCCESS,r);
		r = service.delete(2);
		assertEquals("wrong", StrategyResultMessage.FAIL_WRONGID,r);
	}
	@Test
	public void testGetStrategyList() throws RemoteException{
		ListWrapper<HotelStrategyVO> list = service.getStrategyList(0);
		assertEquals("wrong",null,list);
		list = service.getStrategyList(1);
		assertNotEquals("wrong",null,list);
		assertEquals("wrong",1,list.size());
	}
	@Test
	public void testGetStrategyForOrder() throws RemoteException{
		OrderStrategy vo = new OrderStrategy();
		ListWrapper<HotelStrategyVO> list = service.getStrategyForOrder(vo);
		assertEquals("wrong", null,list);
		vo.setHotelId(1);
		vo.setUserId(1);
		list = service.getStrategyForOrder(vo);
		assertNotEquals("wrong", null,list);
		assertEquals("wrong", 1,list.size());
	}
	@Test
	public void testGetTypes() throws RemoteException{
		ListWrapper<StrategyType> list = service.getTypes();
		assertNotEquals("wrong", null,list);
	}
	
}
