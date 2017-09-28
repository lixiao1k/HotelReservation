import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import info.ListWrapper;
import info.Room;
import logic.service.OrderLogicService;
import logic.service.impl.order.OrderLogicServiceImpl;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;
import vo.OrderVO;

public class TestOrderBL {
	@Test 
	public void testCreate() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		Room room = new Room();
		room.setRid(1);
		room.setType("�󴲷�");
	//	NewOrderVO vo = new NewOrderVO(2,2,false,4,"13307612344","����",new Date(116,11,11),new Date(116, 11, 12),room,2,233,2,0.3);
	//	OrderResultMessage result = orderLogic.create(vo);
	//	Assert.assertEquals("wrong", OrderResultMessage.SUCCESS,result);
	}
	@Test
	public void testAbnormal() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.abnormal(5);
		Assert.assertEquals("Failed in abnormal success test",OrderResultMessage.SUCCESS,result);
	}
	@Test 
	public void testExecute() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.execute(3);
		Assert.assertEquals("Failed",OrderResultMessage.SUCCESS,result);
	}
	@Test 
	public void testReExecute() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.reExecute(2);
		Assert.assertEquals("Failed",OrderResultMessage.SUCCESS,result);
	}
	@Test 
	public void testRevoke() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.userRevoke(4);
		Assert.assertEquals("Failed",OrderResultMessage.SUCCESS,result);
	}
	@Test 
	public void testWebRevoke() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		OrderResultMessage result = orderLogic.webRevoke(1,0);
		Assert.assertEquals("Failed",OrderResultMessage.SUCCESS,result);
	}
	@Test 
	public void testGetUserOrder() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		ListWrapper<OrderVO> result = orderLogic.getUserOrderInfo(2);
		Assert.assertNotEquals("wrong", null,result);
		Iterator<OrderVO> it = result.iterator();
		while(it.hasNext()){
			OrderVO vo = it.next();
			System.out.println(vo.getOrderNum());
		}
	}
	@Test 
	public void testGetHotelOrder() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		ListWrapper<OrderVO> result = orderLogic.getHotelOrderInfo(1);
		Assert.assertNotEquals("wrong", null,result);
		Iterator<OrderVO> it = result.iterator();
		while(it.hasNext()){
			OrderVO vo = it.next();
			System.out.println(vo.getOrderNum());
		}
	}
	@Test 
	public void testWEBOrder() throws RemoteException{
		OrderLogicService orderLogic = new OrderLogicServiceImpl();
		ListWrapper<OrderVO> result = orderLogic.getWEBOrderInfo();
		Assert.assertNotEquals("wrong", null,result);
		Iterator<OrderVO> it = result.iterator();
		while(it.hasNext()){
			OrderVO vo = it.next();
			System.out.println(vo.getOrderNum());
		}
	}
}
