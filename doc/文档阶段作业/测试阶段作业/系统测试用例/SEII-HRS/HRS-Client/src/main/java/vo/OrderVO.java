package vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import info.OrderStatus;
import info.Room;
import util.DateUtil;
import util.DoubleUtil;


public class OrderVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4951629071046395517L;
	private long orderId;
	private String orderNum;
	private String name;
	private OrderStatus status;
	private String hotelName;
	private Date checkInTime;
	private Date checkOutTime;
	private Date actualCheckInTime;
	private Date actualCheckOutTime;
	private Timestamp abnormalTime;
	private Room room;
	private int roomNum;
	private double roomPrice;
	private String strategy;
	private double strategyOff;
	private double price;
	private double priceAfterStrategy;
	private boolean child;
	private int people;
	private String contactWay;
	private String contactName;
	private boolean commented;
	public String getContactWay(){
		return contactWay;
	}
	public String getContactName(){
		return contactName;
	}
	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}
	public void setContactName(String contactName){
		this.contactName = contactName;
	}
	public Date getCheckInTime(){
		return this.checkInTime;
	}
	public String getName(){
		return this.name;
	}
	public String getHotelName(){
		return this.hotelName;
	}
	public Date getCheckOutTime(){
		return this.checkOutTime;
	}
	public String getOrderNum(){
		return orderNum;
	}
	public OrderStatus getStatus(){
		return status;
	}
	public Date getActualCheckInTime(){
		return actualCheckInTime;
	}
	public Date getActualCheckOutTime(){
		return actualCheckOutTime;
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
	public double getPrice(){
		return price;
	}
	public double getPriceAfterStrategy(){
		return priceAfterStrategy;
	}
	public String getStrategy(){
		return strategy;
	}
	public double getStrategyOff(){
		return strategyOff;
	}
	public boolean getChild(){
		return this.child;
	}
	public int getPeople(){
		return this.people;
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
	public void setActualCheckInTime(Date actualCheckInTime){
		this.actualCheckInTime = actualCheckInTime;
	}
	public void setActualCheckOutTime(Date actualCheckOutTime){
		this.actualCheckOutTime = actualCheckOutTime;
	}
	public void setOrderNum(String orderNum){
		this.orderNum = orderNum;
	}
	public void setName(String name){
		this.name = name;
	}
	public String toDetailedString() {
		return "�����ţ�" + orderNum + "\n�����ˣ�" + name + "\n����״̬��" + status + "\n�Ƶ꣺" + hotelName+ "\nԤ��ʱ�䣺" + checkInTime + "\n�˷�ʱ�䣺" + checkOutTime + "\nʵ����סʱ�䣺"+ actualCheckInTime + "\nʵ���˷�ʱ�䣺" + actualCheckOutTime + "\n���䣺" + room.getType() + "\n����ţ�"+ roomNum + "\n����۸�" + roomPrice + "\nʹ���Żݣ�" + strategy + "\n�ۿۣ�" + strategyOff+ "\n�ܼ۸�" + price + "\n�Żݺ�۸�" + priceAfterStrategy + "\n���޶�ͯ��" + child + "\n������"+ people + "\n��ϵ�绰��" + contactWay + "\n��ϵ�ˣ�" + contactName;
	}
	@Override
	public String toString() {
		return orderNum + " " + hotelName + " " + room.getType() +" "+ DateUtil.format(checkInTime)+" "+DateUtil.format(checkOutTime)+" "+name+" "+status+" "+strategy+" "+(child? "�ж�ͯ":"�޶�ͯ")+" "+contactName+" "+contactWay+" "+people+" "+DoubleUtil.format(priceAfterStrategy);
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setRoomNum(int roomNum){
		this.roomNum =roomNum;
	}
	public void setRoomPrice(double roomPrice){
		this.roomPrice = roomPrice;
	}
	public void setPriceAfterStrategy(double priceAfterStrategy){
		this.priceAfterStrategy = priceAfterStrategy;
	}
	public void setStatus(OrderStatus status){
		this.status = status;
	}
	public void setStrategy(String strategy){
		this.strategy = strategy;
	}
	public void setStrategyOff(double strategyOff){
		this.strategyOff = strategyOff;
	}
	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}
	public Timestamp getAbnormalTime() {
		return abnormalTime;
	}
	public void setAbnormalTime(Timestamp abnormalTime) {
		this.abnormalTime = abnormalTime;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public boolean isCommented() {
		return commented;
	}
	public void setCommented(boolean commented) {
		this.commented = commented;
	}
}