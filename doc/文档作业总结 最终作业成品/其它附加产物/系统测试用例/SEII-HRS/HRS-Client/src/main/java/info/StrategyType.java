package info;

public class StrategyType {
	private long id;
	private String name;
	public void setName(String name){
		this.name = name;
	}
	private void setId(long id){
		this.id = id;
	}
	public long getId(){
		return id;
	}
	public String getName(){
		return name;
	}
}
