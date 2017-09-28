package vo;

import java.util.Date;

public class CheckOutRoomInfoVO {
	private long orderId;
	private Date actualCheckOutTime;
	public long getOrderId(){
		return orderId;
	}
	public Date getActualCheckOutTime(){
		return actualCheckOutTime;
	}
	public void setOrderId(long orderId){
		this.orderId = orderId;
	}
	public void setActualCheckOutTime(Date actualCheckOutTime){
		this.actualCheckOutTime = actualCheckOutTime;
	}
}
