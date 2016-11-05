package businesslogic.OrderBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.OrderBLService.OrderResultMessage;
import presentation.BrowseUI.OrderInfo;
import vo.OrderStatus;
import vo.OrderVO;
import vo.StrategyVO;

public class Order {
	List<OrderInfo> orders;
	public void setOrders(List<OrderInfo> orders){
		this.orders = orders;
	}
	public OrderList getUserOrderInfo(long userId, OrderStatus status) {
		List<OrderInfo> result = new ArrayList<OrderInfo>();
		for (OrderInfo order:orders){
			if (order.getUserID()==userId&&order.getStatus() ==status){
				result.add(order);
			}
		}
		return new OrderList(result);
	}


	public OrderList getHotelOrderInfo(long hotelId, OrderStatus status) {
		List<OrderInfo> result = new ArrayList<OrderInfo>();
		for (OrderInfo order:orders){
			if (order.getHotelID()==hotelId&&order.getStatus() ==status){
				result.add(order);
			}
		}
		return new OrderList(result);
	}


	public OrderList getWEBOrderInfo() {
		List<OrderInfo> result = new ArrayList<OrderInfo>();
		for (OrderInfo order:orders){
			if (order.getStatus() == OrderStatus.ABNORMAL){
				result.add(order);
			}
		}
		return new OrderList(result);
	}


	public OrderResultMessage create(OrderVO vo) {
		this.orders.add(new OrderInfo(vo));
		return OrderResultMessage.SUCCESS;
	}


	public OrderResultMessage abnormal(long orderId) {
		for (OrderInfo order:orders){
			if (order.getOrderID() == orderId){
				if (order.getStatus()==OrderStatus.UNEXECUTED){
					order.setOrderStatus(OrderStatus.ABNORMAL);
					return OrderResultMessage.SUCCESS;
				}else{
					return OrderResultMessage.FAIL_WRONGSTATUS;
				}
			}
		}
		return OrderResultMessage.FAIL_WRONGID;
	}

	public OrderResultMessage userCancel(long orderId) {
		for (OrderInfo order:orders){
			if (order.getOrderID() == orderId){
				if (order.getStatus()==OrderStatus.UNEXECUTED){
					order.setOrderStatus(OrderStatus.REVOKED);
					return OrderResultMessage.SUCCESS;
				}else{
					return OrderResultMessage.FAIL_WRONGSTATUS;
				}
			}
		}
		return OrderResultMessage.FAIL_WRONGID;
	}


	public OrderResultMessage execute(long orderId) {
		for (OrderInfo order:orders){
			if (order.getOrderID() == orderId){
				if (order.getStatus()==OrderStatus.UNEXECUTED){
					order.setOrderStatus(OrderStatus.EXECUTED);
					return OrderResultMessage.SUCCESS;
				}else{
					return OrderResultMessage.FAIL_WRONGSTATUS;
				}
			}
		}
		return OrderResultMessage.FAIL_WRONGID;
	}


	public OrderResultMessage reExecute(long orderId) {
		for (OrderInfo order:orders){
			if (order.getOrderID() == orderId){
				if (order.getStatus()==OrderStatus.ABNORMAL){
					order.setOrderStatus(OrderStatus.EXECUTED);
					return OrderResultMessage.SUCCESS;
				}else{
					return OrderResultMessage.FAIL_WRONGSTATUS;
				}
			}
		}
		return OrderResultMessage.FAIL_WRONGID;
	}


	public boolean isUsed(StrategyVO vo) {
		// TODO Auto-generated method stub
		return false;
	}


	public double getTotal(long orderId) {
		for(OrderInfo order:orders){
			if (order.getOrderID() == orderId){
				return order.getPrice();
			}
		}
		return 0.0;
	}
}
