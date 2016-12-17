package vo;

import java.io.Serializable;
import java.time.LocalDate;


public class CreditVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5574650565997514653L;
	private long userId;
	private LocalDate date;
	private int delta;
	private int credit;
	public CreditVO(long userId,LocalDate date,int delta,int credit){
		this.userId = userId;
		this.date = date;
		this.delta = delta;
		this.credit = credit;
	}
	public CreditVO(){
		
	}
	public String toString(){
		return "UserId:"+userId+"; date:"+date+"; "+delta+" -> "+credit;
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
