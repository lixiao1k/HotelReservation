package businesslogic.BrowseBL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import businesslogic.MemberBL.MemberController;
import businesslogic.MemberBL.MemberInfo;
import businesslogic.OrderBL.OrderController;
import businesslogic.OrderBL.OrderList;
import businesslogicservice.MemberBLService.MemberResultMessage;
import presentation.BrowseUI.OrderInfo;
import presentation.OrderUI.OrderRoomInfo;
import vo.MemberVO;
import vo.OrderStatus;

public class TestBrowseBL {
	BrowseController controller;
	long membettest;
	public TestBrowseBL(){
		controller = new BrowseController();
		OrderController orderController = new OrderController();
		MemberController memberController = new MemberController();
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
		orderController.setOrders(orderList);
		MemberInfo member1 = new MemberInfo("13312345678", "hyj");
		this.membettest = member1.getId();
		MemberInfo member2 = new MemberInfo("13587654321", "lyp");
		List<MemberInfo> members = new ArrayList<MemberInfo>();
		members.add(member1);
		members.add(member2);
		MemberResultMessage result;
		memberController.setMembers(members);
		controller.setMemberController(memberController);
		controller.setOrderController(orderController);
	}
	public void test(){
		MemberVO memberVO1 = controller.getMemberInfo(membettest);
		System.out.println(memberVO1);
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
		TestBrowseBL test = new TestBrowseBL();
		test.test();
	}
}
