package vo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
	
public class RoomInfo {
	private String type;
	private int num;
	private int total;
	private double price;
	public RoomInfo(String type,int num,int total,double price){
		this.type = type;
		this.num = num;
		this.total = total;
		this.price = price;
	}
	public String getType(){
		return this.type;
	}
	public int getCurrentNum(){
		return this.num;
	}
	public int getTotal(){
		return this.total;
	}
	public double getPrice(){
		return this.price;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public void setTotal(int total){
		this.total = total;
	}
	public void setCurrentNum(int num){
		this.num = num;
	}

}
