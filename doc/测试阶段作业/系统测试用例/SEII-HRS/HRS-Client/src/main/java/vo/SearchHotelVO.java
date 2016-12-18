package vo;

import java.io.Serializable;
import java.util.Date;

import info.BusinessCircle;
import info.BusinessCity;

public class SearchHotelVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5266158635794187956L;
	private BusinessCity businessCity;
	private BusinessCircle businessCircle;
	private Date checkInTime;
	private Date checkOutTime;
	public void setBusinessCity(BusinessCity businessCity){
		this.businessCity = businessCity;
	}
	public void setBusinessCircle(BusinessCircle businessCircle){
		this.businessCircle = businessCircle;
	}
	public void setCheckInTime(Date checkInTime){
		this.checkInTime = checkInTime;
	}
	public void setCheckOutTime(Date checkOutTime){
		this.checkOutTime = checkOutTime;
	}
	public Date getCheckInTime(){
		return checkInTime;
	}
	public Date getCheckOutTime(){
		return checkOutTime;
	}
	public BusinessCircle getBusinessCircle(){
		return businessCircle;
	}
	public BusinessCity getBusinessCity(){
		return businessCity;
	}
}
