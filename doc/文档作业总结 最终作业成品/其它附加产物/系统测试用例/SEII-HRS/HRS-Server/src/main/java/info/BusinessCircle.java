package info;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BusinessCircle implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9218009948134160174L;
	private long bcircleId;
	private BusinessCity bcity;
	private String name;
	public BusinessCircle() throws RemoteException{
		
	}
	public String getName(){
		return this.name;
	}
	public long getBcircleId(){
		return this.bcircleId;
	}
	public void setName(String name){
		this.name = name;
	}
	private void setBcircleid(long bcircleId){
		this.bcircleId = bcircleId;
	}
	public void setBcircleId(long bcircleId) {
		this.bcircleId = bcircleId;
	}
	public BusinessCity getBcity() {
		return bcity;
	}
	public void setBcity(BusinessCity bcity) {
		this.bcity = bcity;
	}
}
