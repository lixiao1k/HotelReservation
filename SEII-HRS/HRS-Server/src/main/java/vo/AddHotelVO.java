package vo;

import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.Rank;

public class AddHotelVO {
	private String username;
	private String password;
	private String memberName;
	private String name;
	private String description;
	private String facility;
	private String address;
	private String service;
	private BusinessCircle businessCircle;
	private BusinessCity businessCity;
	private Rank rank;
	private Set<HotelItemVO> items;
	public void setFacility(String facility){
		this.facility = facility;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public void setService(String service){
		this.service = service;
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
	public void setName(String name){
		this.name = name;
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
	public Rank getRank(){
		return this.rank;
	}
	public void setRank(Rank rank){
		this.rank = rank;
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
	public Set<HotelItemVO> getItems(){
		return items;
	}
	public void setItems(Set<HotelItemVO> items){
		this.items = items;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public void setBusinessCity(BusinessCity businessCity) {
		this.businessCity = businessCity;
	}
}
