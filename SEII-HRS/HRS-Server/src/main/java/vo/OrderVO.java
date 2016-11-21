package vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.OrderItem;
import info.OrderStatus;


public class OrderVO{
	private OrderStatus status;
	private long userId;
	private long hotelId;
	private Date checkInTime;
	private Date checkOutTime;
	//给orderItems赋值set的实现类，防止在其他类对该类操作过程中抛出nullpointerexception，而是在迭代器中返回null，提高程序健壮性
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private boolean child;
	private int people;
	public Date getCheckInTime(){
		return this.checkInTime;
	}
	public long getUserId(){
		return this.userId;
	}
	public long getHotelId(){
		return this.hotelId;
	}
	public Date getCheckOutTime(){
		return this.checkOutTime;
	}
	public boolean getChild(){
		return this.child;
	}
	public int getPeople(){
		return this.people;
	}
	public OrderStatus getStatus(){
		return this.status;
	}
	public Iterator<OrderItem> getOrderRoomIterator(){
		return this.orderItems.iterator();
	}
	public void setStatus(OrderStatus status){
		this.status = status;
	}
	public void setPeople(int people){
		this.people = people;
	}
	public void setChild(boolean child){
		this.child = child;
	}
	public void setCheckOutTime(Date checkOutTime){
		this.checkOutTime = checkOutTime;
	}
	public void setCheckInTime(Date checkInTime){
		this.checkInTime = checkInTime;
	}
	public void setOrderItems(Set<OrderItem> orderItems){
		this.orderItems = orderItems;
	}
	public void setUserId(long userId){
		this.userId = userId;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
}