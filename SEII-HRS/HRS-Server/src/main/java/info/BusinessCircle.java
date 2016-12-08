package info;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BusinessCircle extends UnicastRemoteObject{
	private long bcircleId;
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
}
