package logic.service.impl;

import info.OrderStatus;
import list.CreditList;
import list.HotelList;
import list.OrderList;
import logic.service.BrowseLogicService;
import vo.HotelVO;
import vo.MemberVO;
import vo.StrategyVO;

public class BrowseLogicServiceImpl implements BrowseLogicService{

	@Override
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public HotelList getHotels() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditList getCreditInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public CreditList getCreditInfo(long userId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public HotelVO getHotel(long hotelId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public StrategyVO getStrategyInfo(String strategyName) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberVO getMemberInfo(long userid) {
		// TODO 自动生成的方法存根
		return null;
	}

}
