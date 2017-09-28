package businesslogic.OrderBL;

import java.util.List;

import businesslogicservice.OrderBLService.OrderBLService_Update;
import businesslogicservice.OrderBLService.OrderResultMessage;
import presentation.BrowseUI.OrderInfo;
import vo.OrderStatus;
import vo.OrderVO;
import vo.StrategyVO;

public class OrderController implements OrderBLService_Update{
	Order orderObject = new Order();
	public void setOrders(List<OrderInfo> orders){
		orderObject.setOrders(orders);
	}
	@Override
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		// TODO Auto-generated method stub
		return orderObject.getUserOrderInfo(userId, status);
	}

	@Override
	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		// TODO Auto-generated method stub
		return orderObject.getHotelOrderInfo(hotelId, status);
	}

	@Override
	public OrderList getWEBOrderInfo() {
		// TODO Auto-generated method stub
		return orderObject.getWEBOrderInfo();
	}

	@Override
	public OrderResultMessage create(OrderVO vo) {
		// TODO Auto-generated method stub
		return orderObject.create(vo);
	}

	@Override
	public OrderResultMessage abnormal(long orderId) {
		// TODO Auto-generated method stub
		return orderObject.abnormal(orderId);
	}

	@Override
	public OrderResultMessage userCancel(long orderId) {
		// TODO Auto-generated method stub
		return orderObject.userCancel(orderId);
	}

	@Override
	public OrderResultMessage execute(long orderId) {
		// TODO Auto-generated method stub
		return orderObject.execute(orderId);
	}

	@Override
	public OrderResultMessage reExecute(long orderId) {
		// TODO Auto-generated method stub
		return orderObject.reExecute(orderId);
	}

	@Override
	public boolean isUsed(StrategyVO vo) {
		// TODO Auto-generated method stub
		return orderObject.isUsed(vo);
	}

	@Override
	public double getTotal(long orderId) {
		// TODO Auto-generated method stub
		return orderObject.getTotal(orderId);
	}

}
