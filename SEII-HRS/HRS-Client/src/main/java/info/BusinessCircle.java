package info;

public class BusinessCircle {
	private long bcircleId;
	private String name;
	public BusinessCircle(){
		
	}
	public String getName(){
		return this.name;
	}
	private long getBcircleId(){
		return this.bcircleId;
	}
	public void setName(String name){
		this.name = name;
	}
	private void setBcircleid(long bcircleId){
		this.bcircleId = bcircleId;
	}
}
