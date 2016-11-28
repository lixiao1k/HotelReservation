package info;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BusinessCity {
	private long bcityId;
	private Set<BusinessCircle> circles = new HashSet<BusinessCircle>();
	private String name;
	
	public BusinessCity(){
		
	}
	public String getName(){
		return this.name;
	}
	private Set<BusinessCircle> getCircles(){
		return circles;
	}
	public Iterator<BusinessCircle> getCircleIterator(){
		return circles.iterator();
	}
	private void setCircles(Set<BusinessCircle> circles){
		this.circles = circles;
	}
	private long getBcityId(){
		return this.bcityId;
	}
	public void setName(String name){
		this.name = name;
	}
	private void setBcityId(long bcityId){
		this.bcityId = bcityId;
	}
}
