package info;

import java.io.Serializable;

import vo.OrderVO;
/*
 * 订单项类，内存冗余的房间信息，防止因房间价格变化时导致的订单价格差异
 */
public class OrderItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8992770537085805016L;
	private long oiid;
	private OrderVO order;
	private Room room;
	private int num;
	private double price;
	public long getOiid(){
		return this.oiid;
	}
	public OrderVO getOrder(){
		return this.order;
	}
	public Room getRoom(){
		return this.room;
	}
	public int getNum(){
		return this.num;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setNum(int num){
		this.num = num;
	}
	public void setRoom(Room room){
		this.room = room;
	}
	public void setOrder(OrderVO order){
		this.order = order;
	}
	public void setOiid(long oiid){
		this.oiid = oiid;
	}
}
