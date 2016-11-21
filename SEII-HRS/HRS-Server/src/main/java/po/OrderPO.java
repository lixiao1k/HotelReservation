package po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.OrderItem;
import info.OrderStatus;
import vo.OrderVO;

public class OrderPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -413441769499293324L;
	private long oid;
	private OrderStatus status;
	private UserPO user;
	private HotelPO hotel;
	private Date checkInTime;
	private Date checkOutTime;
	private Timestamp abnormalTime;
	//��orderItems��ֵset��ʵ���࣬��ֹ��������Ը�������������׳�nullpointerexception�������ڵ������з���null����߳���׳��
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private boolean child;
	private int people;

	public long getOid(){
		return this.oid;
	}
	public Timestamp getAbnormalTime(){
		return this.abnormalTime;
	}
	public UserPO getUser(){
		return this.user;
	}
	public HotelPO getHotel(){
		return this.hotel;
	}
	public Date getCheckInTime(){
		return this.checkInTime;
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
	/*
	 * ����hibernate�ܹ�����java������ƴ����¼���ʽ��÷�������Ϊprivate������ϸ�ڣ���ǿ���������ṩ�����getIterator������ʹ��
	 */
	private Set<OrderItem> getOrderItems(){
		return this.orderItems;
	}
	/*
	 * ����ΪPO�����ṩ�ĵ�����
	 */
	public Iterator<OrderItem> getOrderRoomIterator(){
		return this.orderItems.iterator();
	}
	public void setAbnormalTime(Timestamp abnormalTime){
		this.abnormalTime = abnormalTime;
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
	public void setUser(UserPO user){
		this.user = user;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	public void setOid(long oid){
		this.oid = oid;
	}
	public void setOrderItems(Set<OrderItem> orderItems){
		this.orderItems = orderItems;
	}
	
}
