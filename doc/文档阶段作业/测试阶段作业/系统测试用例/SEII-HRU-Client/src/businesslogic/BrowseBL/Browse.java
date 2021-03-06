package businesslogic.BrowseBL;

import businesslogic.CreditBL.CreditController;
import businesslogic.CreditBL.CreditList;
import businesslogic.HotelBL.Hotel;
import businesslogic.HotelBL.HotelList;
import businesslogic.MemberBL.MemberController;
import businesslogic.OrderBL.OrderController;
import businesslogic.OrderBL.OrderList;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderStatus;
import vo.StrategyVO;

public class Browse {
	OrderController orderController;
	MemberController memberController;
	CreditController creditController;
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO Auto-generated method stub
		return orderController.getUserOrderInfo(userId, status);
	}

	
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO Auto-generated method stub
		return orderController.getHotelOrderInfo(hotelId, status);
	}


	public OrderList getWEBOrderInfo() {
		// TODO Auto-generated method stub
		return orderController.getWEBOrderInfo();
	}


	public HotelList getHotels() {
		// TODO Auto-generated method stub
		return null;
	}


	public CreditList getCreditInfo(long userId) {
		// TODO Auto-generated method stub
		return creditController.getInfo(userId);
	}
	
	public HotelVO getHotel(long hotelId) {
		// TODO Auto-generated method stub
		return null;
	}


	public StrategyVO getStrategyInfo(String strategyName) {
		// TODO Auto-generated method stub
		return null;
	}


	public MemberVO getMemberInfo(long userid) {
		// TODO Auto-generated method stub
		return memberController.getInfo(userid);
	}
	public void setMemberController(MemberController memberController){
		this.memberController = memberController;
	}
	public void setOrderController(OrderController orderController){
		this.orderController = orderController;
	}
}
