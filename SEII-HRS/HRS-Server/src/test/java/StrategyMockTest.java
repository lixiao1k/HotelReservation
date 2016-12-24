import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;

import info.ListWrapper;
import info.OrderStrategy;

import static org.mockito.Mockito.*;

import java.rmi.RemoteException;

import logic.service.StrategyLogicService;
import po.HotelPO;
import po.UserPO;
import resultmessage.StrategyResultMessage;
import vo.HotelStrategyVO;
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
			return null;
		});
	}
}
