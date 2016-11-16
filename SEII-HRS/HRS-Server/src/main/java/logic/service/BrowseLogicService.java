package logic.service;

import info.OrderStatus;
import list.CreditList;
import list.HotelList;
import list.OrderList;
import vo.HotelVO;
import vo.MemberVO;
import vo.StrategyVO;

public interface BrowseLogicService {
	public OrderList getUserOrderInfo(long userId,OrderStatus status);
	public OrderList getHotelOrderInfo(long hotelId,OrderStatus status);
	public OrderList getWEBOrderInfo();
	public HotelList getHotels();
	public CreditList getCreditInfo();
	public CreditList getCreditInfo(long userId);
	public HotelVO getHotel(long hotelId);
	public StrategyVO getStrategyInfo(String strategyName);
	public MemberVO getMemberInfo(long userid);
}
