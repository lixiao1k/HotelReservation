package businesslogic.BrowseBL;

import businesslogic.CreditBL.CreditList;
import businesslogic.HotelBL.Hotel;
import businesslogic.MemberBL.MemberController;
import businesslogic.OrderBL.OrderController;
import businesslogic.OrderBL.OrderList;
import businesslogicservice.BrowseBLService.BrowseBLService_Update;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderStatus;
import vo.StrategyVO;

public class BrowseController implements BrowseBLService_Update{
	Browse browseObject = new Browse();
	@Override
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO Auto-generated method stub
		return browseObject.getUserOrderInfo(userId, status);
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO Auto-generated method stub
		return browseObject.getHotelOrderInfo(hotelId, status);
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO Auto-generated method stub
		return browseObject.getWEBOrderInfo();
	}

	@Override
	public Hotel getHotels() {
		// TODO Auto-generated method stub
		return browseObject.getHotels();
	}

	@Override
	public CreditList getCreditInfo() {
		// TODO Auto-generated method stub
		return browseObject.getCreditInfo();
	}

	@Override
	public HotelVO getHotel(long hotelId) {
		// TODO Auto-generated method stub
		return browseObject.getHotel(hotelId);
	}

	@Override
	public StrategyVO getStrategyInfo(String strategyName) {
		// TODO Auto-generated method stub
		return browseObject.getStrategyInfo(strategyName);
	}

	@Override
	public MemberVO getMemberInfo(long userid) {
		// TODO Auto-generated method stub
		return browseObject.getMemberInfo(userid);
	}
	public void setMemberController(MemberController memberController){
		browseObject.setMemberController(memberController);
	}
	public void setOrderController(OrderController orderController){
		browseObject.setOrderController(orderController);
	}
}
