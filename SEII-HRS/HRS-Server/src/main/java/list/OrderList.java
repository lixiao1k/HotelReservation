package list;

import java.util.List;

import info.OrderInfo;

public class OrderList {
	private List<OrderInfo> list;
	public OrderList(List<OrderInfo> list){
		this.list = list;
	}
	public void setOrderList(List<OrderInfo> list){
		this.list = list;
	}
	public List<OrderInfo> getOrderList(){
		return this.list;
	}
}
