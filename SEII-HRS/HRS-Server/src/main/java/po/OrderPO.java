package po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.OrderStatus;
import info.Room;
import vo.OrderVO;
/*
 * oid ��������
 * orderId �������
 * status ����״̬
 * member �¶����˵ı��
 * hotel �¶����Ƶ�
 * checkInTime ��סʱ��
 * checkOutTime Ԥ���˷�ʱ��
 * actualCheckInTime ʵ����סʱ��
 * actualCheckOutTime ʵ���˷�ʱ��
 * abnormalTime �����쳣ʱ��
 * room ����
 * roomNum ��������
 * roomPrice ����۸�
 * child �Ƿ��ж�ͯ
 * people ��������
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
	private Date actualCheckInTime;
	private Date actualCheckOutTime;
	private Room room;
	private int roomNum;
	private double roomPrice;
	private Timestamp abnormalTime;
	private boolean child;
	private int people;
	private StrategyPO strategy;
	private String contactWay;
	private String contactName;
	public String getContactWay(){
		return contactWay;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}
	public long getOid(){
		return this.oid;
	}
	public Date getActualCheckInTime(){
		return actualCheckInTime;
	}
	public Date getActualCheckOutTime(){
		return actualCheckOutTime;
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
	public Room getRoom(){
		return room;
	}
	public int getRoomNum(){
		return roomNum;
	}
	public double getRoomPrice(){
		return roomPrice;
	}
	public StrategyPO getStrategy(){
		return strategy;
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

	public void setOrderId(String  orderId){
		this.orderId = orderId;
	}
	public void setActualCheckInTime(Date actualCheckInTime){
		this.actualCheckInTime = actualCheckInTime;
	}
	public void setActualCheckOutTime(Date actualCheckOutTime){
		this.actualCheckOutTime = actualCheckOutTime;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setRoomNum(int roomNum){
		this.roomNum = roomNum;
	}
	public void setRoomPrice(double roomPrice){
		this.roomPrice = roomPrice;
	}
	public void setStrategy(StrategyPO strategy){
		this.strategy = strategy;
	}
}
