package po;

import java.util.HashSet;
import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.HotelRoom;
import info.Rank;

public class HotelPO {
	private long hid;
	private String name;
	private String description;
	private BusinessCircle businessCircle;
	private BusinessCity businessCity;
	private double score;
	private Rank rank;
	private Set<HotelItem> rooms = new HashSet<HotelItem>();
	public HotelPO(){
		
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
	public void setHid(long hid){
		this.hid = hid;
	}
	public long getHid(){
		return this.hid;
	}
}
