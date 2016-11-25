package info;

import java.time.LocalDate;

import vo.CreditVO;

public class CreditInfo {
	private long userId;
	private LocalDate date;
	private int delta;
	private int credit;
	public CreditInfo(long userId,LocalDate date,int delta,int credit){
		this.userId = userId;
		this.date = date;
		this.delta = delta;
		this.credit = credit;
	}
	public String toString(){
		return "UserId:"+userId+"; date:"+date+"; "+delta+" -> "+credit;
	}
	public CreditInfo(CreditVO vo){
		this.userId = vo.getUserId();
		this.date = vo.getDate();
		this.delta = vo.getDelta();
		this.credit = vo.getCredit();
	}
	public long getUserId(){
		return this.userId;
	}
	public LocalDate getDate(){
		return this.date;
	}
	public int getDelta(){
		return this.delta;
	}
	public int getCredit(){
		return this.credit;
	}
}
