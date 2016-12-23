package info;

import java.io.Serializable;

public class StrategyType implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5248419732398619630L;
	private long id;
	private String name;
	public void setName(String name){
		this.name = name;
	}
	public void setId(long id){
		this.id = id;
	}
	public long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
}
