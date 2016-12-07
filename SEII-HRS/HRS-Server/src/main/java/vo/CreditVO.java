package vo;

import java.util.Date;


public class CreditVO {
	private long userId;
	private Date date;
	private int delta;
	private int credit;
	private String reason;
	public CreditVO(){
		
	}
	public CreditVO(long userId,Date date,int delta,int credit){
		this.userId = userId;
		this.date = date;
		this.delta = delta;
		this.credit = credit;
	}
	public String toString(){
		return "UserId:"+userId+"; date:"+date+"; "+delta+" -> "+credit;
	}
	public long getUserId(){
		return this.userId;
	}
	public Date getDate(){
		return this.date;
	}
	public int getDelta(){
		return this.delta;
	}
	public int getCredit(){
		return this.credit;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setDelta(int delta) {
		this.delta = delta;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
