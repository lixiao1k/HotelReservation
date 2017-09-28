package businesslogic.OrderBL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Or;

import businesslogicservice.OrderBLService.OrderResultMessage;
import presentation.BrowseUI.OrderInfo;
import presentation.OrderUI.OrderRoomInfo;
import vo.OrderStatus;
import vo.OrderVO;
import vo.StrategyVO;

public class TestOrderBL {
	OrderController controller;
	public TestOrderBL(){
		controller = new OrderController();
	}
	public void test(){
		LocalDate begin = LocalDate.now().plusDays(2);
		LocalDate end = LocalDate.now().plusDays(4);
		OrderRoomInfo room1 = new OrderRoomInfo("双人房", 4, 123);
		OrderRoomInfo room2 = new OrderRoomInfo("单人房", 3, 100);
		List<OrderRoomInfo> roomList = new ArrayList<>();
		roomList.add(room1);
		roomList.add(room2);
		OrderInfo order1 = new OrderInfo(begin, end, 3, false, roomList, 12342234, 12);
		roomList.remove(room1);
		OrderInfo order2 = new OrderInfo(begin.minusDays(1),end,5,true,roomList,33442313,11);
		List<OrderInfo> orderList = new ArrayList<>();
		orderList.add(order1);
		orderList.add(order2);
		controller.setOrders(orderList);
		OrderResultMessage result;
		result = controller.abnormal(order1.getOrderID());
		System.out.println(result);
		result = controller.abnormal(1233);
		System.out.println(result);
		result = controller.abnormal(order1.getOrderID());
		System.out.println(result);
		order1.setOrderStatus(OrderStatus.UNEXECUTED);
		result = controller.execute(order1.getOrderID());
		System.out.println(result);
		result = controller.execute(1233);
		System.out.println(result);
		result = controller.execute(order1.getOrderID());
		System.out.println(result);
		order1.setOrderStatus(OrderStatus.ABNORMAL);
		result = controller.reExecute(order1.getOrderID());
		System.out.println(result);
		result = controller.reExecute(1233);
		System.out.println(result);
		result = controller.reExecute(order1.getOrderID());
		System.out.println(result);
		order1.setOrderStatus(OrderStatus.UNEXECUTED);
		result = controller.userCancel(order1.getOrderID());
		System.out.println(result);
		result = controller.userCancel(1233);
		System.out.println(result);
		result = controller.userCancel(order1.getOrderID());
		System.out.println(result);
		roomList.add(room1);
		roomList.remove(room2);
		OrderVO vo = new OrderVO(begin.minusDays(1), end, 3, true, roomList, 33442313, 10);
		result = controller.create(vo);
		System.out.println(result);
		System.out.println(controller.isUsed(new StrategyVO()));
		System.out.println(controller.getTotal(order1.getOrderID()));
		OrderList olist = controller.getUserOrderInfo(33442313, OrderStatus.UNEXECUTED);
		List<OrderInfo> oolist = olist.getOrderList();
		for (int i=0;i<oolist.size();i++){
			System.out.println(oolist.get(i));
		}
		olist = controller.getUserOrderInfo(33442313, OrderStatus.ABNORMAL);
		oolist = olist.getOrderList();
		for (int i=0;i<oolist.size();i++){
			System.out.println(oolist.get(i));
		}
		olist = controller.getHotelOrderInfo(11, OrderStatus.UNEXECUTED);
		oolist = olist.getOrderList();
		for (int i=0;i<oolist.size();i++){
			System.out.println(oolist.get(i));
		}
		olist = controller.getHotelOrderInfo(11, OrderStatus.ABNORMAL);
		oolist = olist.getOrderList();
		for (int i=0;i<oolist.size();i++){
			System.out.println(oolist.get(i));
		}
		olist = controller.getWEBOrderInfo();
		oolist = olist.getOrderList();
		for (int i=0;i<oolist.size();i++){
			System.out.println(oolist.get(i));
		}
	}
	public static void main(String[] args) {
		TestOrderBL test = new TestOrderBL();
		test.test();
	}
}
