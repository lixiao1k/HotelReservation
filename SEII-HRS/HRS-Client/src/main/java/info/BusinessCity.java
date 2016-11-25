package info;

public class BusinessCity {
	private long bcityId;
	private String name;
	public BusinessCity(){
		
	}
	public String getName(){
		return this.name;
	}
	private long getBcityId(){
		return this.bcityId;
	}
	public void setName(String name){
		this.name = name;
	}
	private void setBcityId(long bcityId){
		this.bcityId = bcityId;
	}
}
