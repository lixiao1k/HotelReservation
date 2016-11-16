package info;

public class OrderRoomInfo {
	String type;
	int num;
	double price;
	public OrderRoomInfo(String type,int num,double price){
		this.type = type;
		this.num = num;
		this.price = price;
	}
	public double getPrice(){
		return this.price;
	}
	public double getTotal(){
		return num*price;
	}
	public int getNum(){
		return this.num;
	}
	public String getType(){
		return this.type;
	}
	public String toString(){
		return "Type:"+type+"; num:"+num+"; price:"+price+";";
	}
}
