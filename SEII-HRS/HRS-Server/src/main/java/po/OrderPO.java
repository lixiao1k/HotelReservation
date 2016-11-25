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
/*
 * oid 代理主键
 * orderId 订单编号
 * status 订单状态
 * member 下订单人的编号
 * hotel 下订单酒店
 * checkInTime 入住时间
 * checkOutTime 预计退房时间
 * abnormalTime 订单异常时间
 * orderItems 订单项
 * child 是否有儿童
 * people 订单人数
 * @author whk
 */
public class OrderPO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -413441769499293324L;
	private long oid;
	private String orderId;
	private OrderStatus status;
	private MemberPO member;
	private HotelPO hotel;
	private Date checkInTime;
	private Date checkOutTime;
	private Timestamp abnormalTime;
	//给orderItems赋值set的实现类，防止在其他类对该类操作过程中抛出nullpointerexception，而是在迭代器中返回null，提高程序健壮性
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	private boolean child;
	private int people;

	public long getOid(){
		return this.oid;
	}
	public Timestamp getAbnormalTime(){
		return this.abnormalTime;
	}
	public MemberPO getMember(){
		return this.member;
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
	public String getOrderId(){
		return orderId;
	}
	public OrderStatus getStatus(){
		return this.status;
	}
	/*
	 * 由于hibernate能够利用java反射机制存入记录，故将该方法设置为private，隐藏细节，增强操作，而提供另外的getIterator方法供使用
	 */
	private Set<OrderItem> getOrderItems(){
		return this.orderItems;
	}
	/*
	 * 这里为PO对外提供的迭代器
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
	public void setMember(MemberPO member){
		this.member = member;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	private void setOid(long oid){
		this.oid = oid;
	}
	public void setOrderItems(Set<OrderItem> orderItems){
		this.orderItems = orderItems;
	}
	public void setOrderId(String  orderId){
		this.orderId = orderId;
	}
}
