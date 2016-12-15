package info;

import java.io.Serializable;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1830300546353465658L;
	private long rid;
	private String type;
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
	@Override
	public String toString() {
		return  rid + "  " + type;
	}
	public Room(){}
	public Room(long rid, String type) {
		super();
		this.rid = rid;
		this.type = type;
	}
	
}
