package po;

import java.util.Date;

public class CreditPO {
	private long id;
	private MemberPO member;
	private Date date;
	private int delta;
	private int credit;
	private String reason;
	public String getReason(){
		return reason;
	}
	public void setReason(String reason){
		this.reason = reason;
	}
	public long getId(){
		return id;
	}
	public MemberPO getMember(){
		return this.member;
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
	private void setId(long id){
		this.id = id;
	}
	public void setMember(MemberPO member){
		this.member = member;
	}
	public void setDate(Date date){
		this.date = date;
	}
	public void setDelta(int delta){
		this.delta = delta;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
}
