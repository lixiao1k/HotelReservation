package logic.service.impl;

import info.OrderStatus;
import list.OrderList;
import logic.service.OrderLogicService;
import resultmessage.OrderResultMessage;
import vo.OrderVO;
import vo.StrategyVO;

public class OrderLogicServiceImpl implements OrderLogicService{

	@Override
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage create(OrderVO vo) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage abnormal(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage userCancel(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage execute(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public OrderResultMessage reExecute(long orderId) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public boolean isUsed(StrategyVO vo) {
		// TODO �Զ����ɵķ������
		return false;
	}

	@Override
	public double getTotal(long orderId) {
		// TODO �Զ����ɵķ������
		return 0;
	}

}
