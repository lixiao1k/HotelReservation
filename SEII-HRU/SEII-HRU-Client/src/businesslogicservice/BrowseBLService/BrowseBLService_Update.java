package businesslogicservice.BrowseBLService;

import businesslogic.CreditBL.CreditList;
import businesslogic.HotelBL.Hotel;
import businesslogic.OrderBL.OrderList;
import businesslogicservice.OrderBLService.OrderResultMessage;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderStatus;
import vo.OrderVO;
import vo.StrategyVO;

public interface BrowseBLService_Update {
	public OrderList getUserOrderInfo(long userId,OrderStatus status);
	public OrderList getHotelOrderInfo(long hotelId,OrderStatus status);
	public OrderList getWEBOrderInfo();
	public Hotel getHotels();
	public CreditList getCreditInfo();
	public CreditList getCreditInfo(long userId);
	public HotelVO getHotel(long hotelId);
	public StrategyVO getStrategyInfo(String strategyName);
	public MemberVO getMemberInfo(long userid);
}
