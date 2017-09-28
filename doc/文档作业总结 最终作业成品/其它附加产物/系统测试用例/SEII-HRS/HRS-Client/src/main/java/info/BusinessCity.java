package info;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BusinessCity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7571936670288363320L;
	private long bcityId;
	private Set<BusinessCircle> circles = new HashSet<BusinessCircle>();
	private String name;
	
	public BusinessCity() throws RemoteException{
		
	}
	public String getName(){
		return this.name;
	}
	public Set<BusinessCircle> getCircles(){
		return circles;
	}
	public Iterator<BusinessCircle> getCircleIterator(){
		return circles.iterator();
	}
	private void setCircles(Set<BusinessCircle> circles){
		this.circles = circles;
	}
	public long getBcityId(){
		return this.bcityId;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setBcityId(long bcityId){
		this.bcityId = bcityId;
	}
}
