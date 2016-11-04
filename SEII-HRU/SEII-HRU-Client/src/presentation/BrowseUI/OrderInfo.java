package presentation.BrowseUI;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import presentation.OrderUI.OrderRoomInfo;
import vo.OrderStatus;
import vo.OrderType;
import vo.OrderVO;
import vo.RoomInfo;

public class OrderInfo {
	OrderStatus status;
	LocalDate beginTime;
	LocalDate endTime;
	int people;
	boolean child;
	double price=0.0;
	long orderID;
	long userId;
	long hotelId;
	List<OrderRoomInfo> orderRoom;
	public OrderInfo(OrderVO info){
		this.userId = info.getUserID();
		this.hotelId = info.getHotelID();
		this.orderID = info.getOrderID();
		this.status = info.getStatus();
		this.beginTime = info.getBeginTime();
		this.endTime = info .getEndTime();
		this.people = info.getPeople();
		this.child = info.isHavechild();
		this.price = info.getPrice();
		this.orderRoom = info.getOrderRoomList();
	}
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
	public OrderInfo(LocalDate beginTime,LocalDate endTime,int people,boolean isHaveChild,List<OrderRoomInfo> orderRoom,long userid,long hotelid){
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.people = people;
		this.child = isHaveChild;
		this.orderRoom = orderRoom;
		this.status = OrderStatus.UNEXECUTED;
		this.userId = userid;
		this.hotelId = hotelid;
		// test code
		Random random = new Random();
		this.orderID = random.nextLong()+123456789;
	}
	public String toString(){
		String result = "";
		result+="id:"+orderID+"; status:"+status+"; From "+beginTime+" to "+endTime+"; people:"+people+"; child:"+child+";\n room:";
		for (int i=0;i<orderRoom.size();i++){
			result+=orderRoom.get(i);
		}
		result+="\n price:"+price;
		return result;
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
		if (price==0.0){
			for (int i=0;i<orderRoom.size();i++)
				price+= orderRoom.get(i).getTotal();
		}
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
	public long getUserID(){
		return this.userId;
	}
	public long getHotelID(){
		return this.hotelId;
	}
	public void setBeginTime(LocalDate date){ this.beginTime = date; }
	public void setEndTime(LocalDate date){ this.endTime = date; }
}
