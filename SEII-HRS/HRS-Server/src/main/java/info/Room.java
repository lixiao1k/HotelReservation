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
}
