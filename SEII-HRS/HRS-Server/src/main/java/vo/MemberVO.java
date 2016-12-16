package vo;

import java.io.Serializable;
import java.util.Random;


public class MemberVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1303786503704861339L;
	private String phone;
	private String name;
	private int credit;
	private boolean VIPFlag;
	private String VIPInfo;
	private long memberId;
	public MemberVO(String phone,String name){
		this.phone = phone;
		this.name = name;
		this.credit = 0;
		this.VIPFlag = false;
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
	public boolean isVIPFlag() {
		return VIPFlag;
	}
	public void setVIPFlag(boolean vIPFlag) {
		VIPFlag = vIPFlag;
	}
	public String getVIPInfo() {
		return VIPInfo;
	}
	public void setVIPInfo(String vIPInfo) {
		VIPInfo = vIPInfo;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
