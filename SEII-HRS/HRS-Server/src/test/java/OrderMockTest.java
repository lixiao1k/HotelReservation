import logic.service.OrderLogicService;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;
import vo.OrderVO;
import static org.mockito.Mockito.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import info.ListWrapper;
import info.OrderStatus;

public class OrderMockTest {
	private OrderLogicService service;
	@Before
	public void setup() throws RemoteException{
		service = mock(OrderLogicService.class);
		//依次为未执行订单，已执行订单，异常订单，撤销订单,前两个为酒店1订单，后两个为酒店2订单，1,3为用户1订单，2,4为用户2订单
		OrderVO orderVO1 = mock(OrderVO.class);
		when(orderVO1.getStatus()).thenReturn(OrderStatus.UNEXECUTED);
		OrderVO orderVO2 = mock(OrderVO.class);
		when(orderVO2.getStatus()).thenReturn(OrderStatus.EXECUTED);
		OrderVO orderVO3 = mock(OrderVO.class);
		when(orderVO3.getStatus()).thenReturn(OrderStatus.ABNORMAL);
		OrderVO orderVO4 = mock(OrderVO.class);
		when(orderVO4.getStatus()).thenReturn(OrderStatus.REVOKED);
		List<OrderVO> testList = new ArrayList<OrderVO>();
		testList.add(orderVO1);
		testList.add(orderVO2);
		testList.add(orderVO3);
		testList.add(orderVO4);
		when(service.getHotelOrderInfo(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0)
				return null;
			ListWrapper<OrderVO> list = new ListWrapper<>();
			if(id==1){
				list.add(orderVO1);
				list.add(orderVO2);
			}
			else if (id==2){
				list.add(orderVO3);
				list.add(orderVO4);
			}
			return list;
		});
		when(service.getUserOrderInfo(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0)
				return null;
			ListWrapper<OrderVO> list = new ListWrapper<>();
			if(id==1){
				list.add(orderVO1);
				list.add(orderVO3);
			}
			else if (id==2){
				list.add(orderVO2);
				list.add(orderVO4);
			}
			return list;
		});
		when(service.getWEBOrderInfo()).thenAnswer((InvocationOnMock invocation)->{
			ListWrapper<OrderVO> list = new ListWrapper<>();
			list.add(orderVO3);
			return list;
		});
		when(service.abnormal(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0||id>testList.size())
				return OrderResultMessage.FAIL_WRONGID;
			if(id==1)
				return OrderResultMessage.SUCCESS;
			else return OrderResultMessage.FAIL_WRONGSTATUS;
		});
		when(service.userRevoke(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0||id>testList.size())
				return OrderResultMessage.FAIL_WRONGID;
			if(id==1)
				return OrderResultMessage.SUCCESS;
			else return OrderResultMessage.FAIL_WRONGSTATUS;
		});
		when(service.execute(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0||id>testList.size())
				return OrderResultMessage.FAIL_WRONGID;
			if(id==1)
				return OrderResultMessage.SUCCESS;
			else return OrderResultMessage.FAIL_WRONGSTATUS;
		});
		when(service.reExecute(anyLong())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			if(id<=0||id>testList.size())
				return OrderResultMessage.FAIL_WRONGID;
			if(id==3)
				return OrderResultMessage.SUCCESS;
			else return OrderResultMessage.FAIL_WRONGSTATUS;
		});
		when(service.webRevoke(anyLong(),anyInt())).thenAnswer((InvocationOnMock invocation)->{
			long id = (long)invocation.getArguments()[0];
			int rank = (int)invocation.getArguments()[1];
			if(!(rank==0||rank==1))
				return OrderResultMessage.FAIL_WRONGORDERINFO;
			if(id<=0||id>testList.size())
				return OrderResultMessage.FAIL_WRONGID;
			if(id==3)
				return OrderResultMessage.SUCCESS;
			else return OrderResultMessage.FAIL_WRONGSTATUS;
		});
		when(service.create(any(NewOrderVO.class))).thenAnswer((InvocationOnMock invocation)->{
			return OrderResultMessage.SUCCESS;
		});
	}
	@Test 
	public void testCreate() throws RemoteException{
		NewOrderVO vo = mock(NewOrderVO.class);
		assertEquals("wrong",OrderResultMessage.SUCCESS,service.create(vo));
	}
	@Test
	public void testAbnormal() throws RemoteException{
		assertEquals("Failed in abnormal success test",OrderResultMessage.SUCCESS,service.abnormal(1));
		assertEquals("Failed in abnormal success test",OrderResultMessage.FAIL_WRONGID,service.abnormal(0));
		assertEquals("Failed in abnormal success test",OrderResultMessage.FAIL_WRONGID,service.abnormal(5));
		assertEquals("Failed in abnormal success test",OrderResultMessage.FAIL_WRONGSTATUS,service.abnormal(2));
	}
	@Test 
	public void testExecute() throws RemoteException{
		assertEquals("Failed in execute success test",OrderResultMessage.SUCCESS,service.execute(1));
		assertEquals("Failed in execute success test",OrderResultMessage.FAIL_WRONGID,service.execute(0));
		assertEquals("Failed in execute success test",OrderResultMessage.FAIL_WRONGID,service.execute(5));
		assertEquals("Failed in execute success test",OrderResultMessage.FAIL_WRONGSTATUS,service.execute(2));
	}
	@Test 
	public void testReExecute() throws RemoteException{
		assertEquals("Failed in reExecute success test",OrderResultMessage.SUCCESS,service.reExecute(3));
		assertEquals("Failed in reExecute success test",OrderResultMessage.FAIL_WRONGID,service.reExecute(0));
		assertEquals("Failed in reExecute success test",OrderResultMessage.FAIL_WRONGID,service.reExecute(5));
		assertEquals("Failed in reExecute success test",OrderResultMessage.FAIL_WRONGSTATUS,service.reExecute(2));
	}
	@Test 
	public void testRevoke() throws RemoteException{
		assertEquals("Failed in revoke success test",OrderResultMessage.SUCCESS,service.userRevoke(1));
		assertEquals("Failed in revoke success test",OrderResultMessage.FAIL_WRONGID,service.userRevoke(0));
		assertEquals("Failed in revoke success test",OrderResultMessage.FAIL_WRONGID,service.userRevoke(5));
		assertEquals("Failed in revoke success test",OrderResultMessage.FAIL_WRONGSTATUS,service.userRevoke(2));
	}
	@Test 
	public void testWebRevoke() throws RemoteException{
		assertEquals("Failed in webRevoke success test",OrderResultMessage.SUCCESS,service.webRevoke(3,0));
		assertEquals("Failed in webRevoke success test",OrderResultMessage.FAIL_WRONGID,service.webRevoke(0,1));
		assertEquals("Failed in webRevoke success test",OrderResultMessage.FAIL_WRONGORDERINFO,service.webRevoke(5,3));
		assertEquals("Failed in webRevoke success test",OrderResultMessage.FAIL_WRONGSTATUS,service.webRevoke(2,1));
	}
	@Test 
	public void testGetUserOrder() throws RemoteException{
		ListWrapper<OrderVO> result = service.getUserOrderInfo(1);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong", 2,result.size());
		result = service.getUserOrderInfo(2);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong", 2,result.size());
		result = service.getUserOrderInfo(0);
		assertEquals("wrong", null,result);
		result = service.getUserOrderInfo(3);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong",0,result.size());
	}
	@Test 
	public void testGetHotelOrder() throws RemoteException{
		ListWrapper<OrderVO> result = service.getUserOrderInfo(1);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong", 2,result.size());
		result = service.getUserOrderInfo(2);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong", 2,result.size());
		result = service.getUserOrderInfo(0);
		assertEquals("wrong", null,result);
		result = service.getUserOrderInfo(3);
		assertNotEquals("wrong", null,result);
		assertEquals("wrong",0,result.size());
	}
	@Test 
	public void testWEBOrder() throws RemoteException{
		ListWrapper<OrderVO> result = service.getWEBOrderInfo();
		assertNotEquals("wrong", null,result);
		assertEquals("wrong",1,result.size());
		assertEquals("wrong", OrderStatus.ABNORMAL,result.iterator().next().getStatus());
	}
}
