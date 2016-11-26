package po;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.Rank;

public class HotelPO {
	private long hid;
	private String name;
	private String description;
	private String facility;
	private String address;
	private String service;
	private BusinessCircle businessCircle;
	private BusinessCity businessCity;
	private double score;
	private Rank rank;
	private Set<CommentPO> comments = new HashSet<CommentPO>();
	private Set<OrderPO> orders = new HashSet<OrderPO>();
	private Set<HotelItem> rooms = new HashSet<HotelItem>();
	public HotelPO(){
		
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
	private void setComments(Set<CommentPO> comments){
		this.comments = comments;
	}
	private void setRooms(Set<HotelItem> rooms){
		this.rooms = rooms;
	}
	private void setOrders(Set<OrderPO> orders){
		this.orders = orders;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public void setBusinessCircle(BusinessCircle businessCircle){
		this.businessCircle = businessCircle;
	}
	public void setBussinessCity(BusinessCity businessCity){
		this.businessCity = businessCity;
	}
	private void setHid(long hid){
		this.hid = hid;
	}
	public long getHid(){
		return this.hid;
	}
	public Iterator<OrderPO> getOrder(){
		return orders.iterator();
	}
	private Set<OrderPO> getOrders(){
		return orders;
	}
	public Iterator<HotelItem> getRoom(){
		return this.rooms.iterator();
	}
	private Set<HotelItem> getRooms(){
		return rooms;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public BusinessCircle getBusinessCircle(){
		return businessCircle;
	}
	public BusinessCity getBusinessCity(){
		return businessCity;
	}
	public double getScore(){
		return score;
	}
	public Rank getRank(){
		return this.rank;
	}
	public Iterator<CommentPO> getComment(){
		return comments.iterator();
	}
	private Set<CommentPO> getComments(){
		return comments;
	}
}
