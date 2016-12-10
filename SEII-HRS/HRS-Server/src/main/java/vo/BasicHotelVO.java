package vo;

import java.awt.Image;
import java.io.Serializable;
import java.util.Set;
import info.Rank;

public class BasicHotelVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 283447768362502163L;
	private long hotelId;
	private String hotelName;
	private Set<HotelItemVO> rooms;
	private Rank rank;
	private double score;
	private String description;
	private String facility;
	private String address;
	private String service;
	private Image image;
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return description;
	}
	public void setRank(Rank rank){
		this.rank = rank;
	}
	public void setScore(double score){
		this.score = score;
	}
	public void setHotelId(long hotelId){
		this.hotelId = hotelId;
	}
	public void setHotelName(String hotelName){
		this.hotelName = hotelName;
	}
	public void setRooms(Set<HotelItemVO> rooms){
		this.rooms = rooms;
	}
	public Set<HotelItemVO> getRooms(){
		return rooms;
	}
	public Rank getRank(){
		return rank;
	}
	public double getScore(){
		return score;
	}
	public long getHotelId(){
		return hotelId;
	}
	public String getHotelName(){
		return hotelName;
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
	public String getFacility(){
		return facility;
	}
	public String getAddress(){
		return address;
	}
	public String getService(){
		return service;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
}
