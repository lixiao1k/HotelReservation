package info;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1830300546353465658L;
	private long rid;
	private String type;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	public long getRid(){
		return this.rid;
	}
	public String getType(){
		return this.type;
	}
	public void setRid(long rid){
		this.rid = rid;
	}
	public void setType(String type){
		this.type = type;
	}
	private Set<OrderItem> getOrderItems(){
		return this.orderItems;
	}
	private void setOrderItems(Set<OrderItem> orderItems){
		this.orderItems = orderItems;
	}
}
