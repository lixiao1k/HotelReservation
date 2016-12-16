package vo;

import java.io.Serializable;
import java.util.Random;

import logic.service.ServiceFactory;


public class MemberVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID =1303786503704861339L;
	private String phone;
	private String name;
	private int credit;
	private boolean VIPFlag;
	private long memberId;
	public MemberVO(String phone,String name){
		this.phone = phone;
		this.name = name;
		this.credit = 0;
		this.VIPFlag = false;
		Random rnd = new Random();
		this.memberId = rnd.nextLong();
	}
	public MemberVO(){
		
	}
	public String toString(){
		return "name:"+this.name+"; phone:"+this.phone+"; credit:"+this.credit;
	}
	public long getId(){ return this.memberId; }
	public void setPhone(String phone){ this.phone = phone; }
	public void setName(String name){ this.name = name; }
	public void updateCredit(int delta){ this.credit += delta; };
	public String getPhone(){ return this.phone; }
	public String getName(){ return this.name; }
	public int getCredit(){ return this.credit; }
	public boolean isVIP(){ return VIPFlag; }
}
