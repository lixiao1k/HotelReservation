package vo;

import java.awt.Image;
import java.io.Serializable;

import info.BusinessCircle;


public class MaintainHotelInfoVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1058967707133372688L;
	private long hotelId;
	private String description;
	private String facility;
	private String address;
	private String service;
	private Image image;
	private BusinessCircle circle;
	public long getHotelId(){
		return hotelId;
	}
	public String getDescription(){
		return description;
	}
	public String getFacility(){
		return facility;
	}
	public String getAddress(){
		return address;
	}
	public String getService(){
		return service;
	}
	public BusinessCircle getCircle(){
		return circle;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setFacility(String facility){
		this.facility = facility;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setService(String service){
		this.service = service;
	}
	public void setBusinessCircle(BusinessCircle businessCircle){
		this.circle = businessCircle;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public void setCircle(BusinessCircle circle) {
		this.circle = circle;
	}
}
