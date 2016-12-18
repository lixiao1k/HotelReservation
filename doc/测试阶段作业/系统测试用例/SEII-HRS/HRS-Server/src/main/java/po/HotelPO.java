package po;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.Rank;
import javafx.scene.image.Image;

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
	private MemberPO hotelworker;
	private byte[] imageData;
	private Set<CommentPO> comments = new HashSet<CommentPO>();
	private Set<OrderPO> orders = new HashSet<OrderPO>();
	private Set<HotelItem> rooms = new HashSet<HotelItem>();
	private Set<StrategyPO> strategies = new HashSet<StrategyPO>();
	public HotelPO(){
		
	}
	public void printRoom(){
		for(HotelItem hi:rooms){
			System.out.println(hi.getRoom().getType()+":"+hi.getDate());
		}
	}
	public void setHotelworker(MemberPO hotelworker){
		this.hotelworker = hotelworker;
	}
	private void setStrategies(Set<StrategyPO> strategies){
		this.strategies = strategies;
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
	public void setComments(Set<CommentPO> comments){
		this.comments = comments;
	}
	public void setRooms(Set<HotelItem> rooms){
		this.rooms = rooms;
	}
	public void setOrders(Set<OrderPO> orders){
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
	public void setRank(Rank rank){
		this.rank = rank;
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
	public Set<HotelItem> getRooms(){
		return rooms;
	}
	private Set<StrategyPO> getStrategies(){
		return strategies;
	}
	public Iterator<StrategyPO> getStrategyIterator(){
		return strategies.iterator();
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
	public MemberPO getHotelworker(){
		return hotelworker;
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
	public void setBusinessCity(BusinessCity businessCity) {
		this.businessCity = businessCity;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public byte[] getImageData() {
		return imageData;
	}
	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}
}
