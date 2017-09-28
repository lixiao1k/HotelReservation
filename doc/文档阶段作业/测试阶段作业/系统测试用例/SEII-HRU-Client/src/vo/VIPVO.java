package vo;

public class VIPVO {
	private int type;
	private String info;
	private long userId;
	public VIPVO(int type,String info,long userId){
		this.type = type;
		this.info = info;
		this.userId = userId;
	}
	public void setId(long userId){ this.userId = userId; }
	public long getId(){ return this.userId; }
	public int getType(){ return this.type; }
	public String getInfo(){ return this.info; }
	
}
