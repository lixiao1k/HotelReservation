package po;

public class Role {
	private long id;
	private String name;
	public long getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public void setId(long userId){
		this.id = userId;
	}
	public void setName(String name){
		this.name = name;
	}
	public String toString(){
		return this.name;
	}
}
