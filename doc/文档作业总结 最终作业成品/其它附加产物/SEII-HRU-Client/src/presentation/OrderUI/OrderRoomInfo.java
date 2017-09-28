package presentation.OrderUI;

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
	public int getNum(){
		return this.num;
	}
	public String getType(){
		return this.type;
	}
}
