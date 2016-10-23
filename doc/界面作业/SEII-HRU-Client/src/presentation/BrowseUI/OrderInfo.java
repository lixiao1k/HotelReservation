package presentation.BrowseUI;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import presentation.OrderUI.OrderRoomInfo;
import vo.OrderStatus;
import vo.OrderType;

public class OrderInfo {
	OrderStatus status;
	LocalDate beginTime;
	LocalDate endTime;
	int people;
	boolean child;
	double price=0.0;
	long orderID;
	List<OrderRoomInfo> orderRoom;
	public OrderInfo(LocalDate beginTime,LocalDate endTime,int people,boolean isHaveChild,List<OrderRoomInfo> orderRoom){
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.people = people;
		this.child = isHaveChild;
		this.orderRoom = orderRoom;
		this.status = OrderStatus.UNEXECUTED;
		// test code
		Random random = new Random();
		this.orderID = random.nextLong()+123456789;
	}
	public LocalDate getBeginTime(){
		return this.beginTime;
	}
	public LocalDate getEndTime(){
		return this.endTime;
	}
	public boolean isHavechild(){
		return this.child;
	}
	public double getPrice(){
		return this.price;
	}
	public int getPeople(){
		return this.people;
	}
	public List<OrderRoomInfo> getOrderRoomList(){
		return this.orderRoom;
	}
	public OrderStatus getStatus(){
		return this.status;
	}
	public void setOrderStatus(OrderStatus status){
		this.status = status;
	}
	public long getOrderID(){
		return this.orderID;
	}
}
