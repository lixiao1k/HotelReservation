package vo;

public class StrategyRoomInfo{
	private String type;
	private int num;
	private int total;
	private double price;
	private double off;
	private double offPrice;
	public StrategyRoomInfo(String type,int num,int total,double price,double off){
		this.type = type;
		this.num = num;
		this.total = total;
		this.price = price;
		this.off = off;
		this.offPrice = price*off;
	}
	public double getOff(){
		return this.off;
	}
	public double getOffPrice(){
		return this.offPrice;
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