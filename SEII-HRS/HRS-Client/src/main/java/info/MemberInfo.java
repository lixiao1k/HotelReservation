package info;

import java.util.Random;

import vo.MemberVO;

public class MemberInfo {
	private String phone;
	private String name;
	private int credit;
	private boolean VIPFlag;
	private VIPInfo vipInfo;
	private long memberId;
	public MemberInfo(String phone,String name){
		this.phone = phone;
		this.name = name;
		this.credit = 70;
		this.VIPFlag = false;
		Random rnd = new Random();
		this.memberId = rnd.nextLong();
	}
	public void setAllInfo(MemberVO vo){
		this.phone = vo.getPhone();
		this.name = vo.getName();
		this.credit = vo.getCredit();
		this.VIPFlag = vo.isVIP();
		this.vipInfo = vo.getVIP();
	}
	public String toString(){
		return "name:"+this.name+"; phone:"+this.phone+"; credit:"+this.credit+"; "+getVIPInfo();
	}
	public void setFlag(boolean flag){ this.VIPFlag = flag; }
	public VIPInfo getVIP(){ return this.vipInfo; }
	public long getId(){ return this.memberId; }
	public void setPhone(String phone){ this.phone = phone; }
	public void setName(String name){ this.name = name; }
	public void setVIP(VIPInfo info){ this.vipInfo = info; this.VIPFlag = true; }
	public void updateCredit(int delta){ this.credit += delta; };
	public String getPhone(){ return this.phone; }
	public String getVIPInfo(){ return (VIPFlag)? this.vipInfo.getInfo():"Not VIP!!"; }
	public String getName(){ return this.name; }
	public int getCredit(){ return this.credit; }
	public boolean isVIP(){ return VIPFlag; }
}
