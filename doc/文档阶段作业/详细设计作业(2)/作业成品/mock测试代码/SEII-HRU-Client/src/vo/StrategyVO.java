package vo;

public class StrategyVO {
	String date;
	long id;
	String content;
	String begintime;
	String endtime;
	String style;
	String roomstyle;
	long discount;
	
	public long getID(){
		return id;
	}
	
	public StrategyVO(String date,long id,String content,String begintime,String endtime,String style,String roomstyle,long discount){
		this.date=date;
		this.id=id;
		this.content=content;
		this.begintime=begintime;
		this.endtime=endtime;
		this.style=style;
		this.roomstyle=roomstyle;
		this.discount=discount;
	}
	public StrategyVO() {
		// TODO Auto-generated constructor stub
	}
}
