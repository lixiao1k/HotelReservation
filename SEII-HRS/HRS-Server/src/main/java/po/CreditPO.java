package po;

import java.sql.Timestamp;

/*
 * cid ���ü�¼���
 * member ��¼����
 * changeCredit �ı������ֵ����
 * credit �ı���û�������ֵ
 * time ����ʱ��
 * operation ��������
 * @author whk
 */
public class CreditPO {
	private long cid;
	private MemberPO member;
	private int changeCredit;
	private int credit;
	private Timestamp time;
	private String operation;
	public CreditPO(){}
	public long getCid(){
		return this.cid;
	}
	public int getCredit(){
		return this.credit;
	}
	public String getOperation(){
		return operation;
	}
	public MemberPO getMember(){
		return member;
	}
	public int getChangeCredit(){
		return changeCredit;
	}
	public Timestamp getTime(){
		return this.time;
	}
	private void setCid(long cid){
		this.cid = cid;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
	public void setMember(MemberPO member){
		this.member = member;
	}
	public void setChangeCredit(int changeCredit){
		this.changeCredit = changeCredit;
	}
	public void setOperation(String operation){
		this.operation = operation;
	}
}
