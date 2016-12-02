package po;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ClientMemberPO extends MemberPO{
	private String contactWay;
	private int credit;
	private boolean vip;
	private VIPPO vipInfo;
	private Set<OrderPO> orders = new HashSet<OrderPO>();
	private Set<CommentPO> comment = new HashSet<CommentPO>();
	private Set<OrderPO> getOrders(){
		return orders;
	}
	public boolean isVip(){
		return vip;
	}
	public VIPPO getVipInfo(){
		return vipInfo;
	}
	public Iterator<OrderPO> getOrder(){
		return orders.iterator();
	}
	private Set<CommentPO> getComment(){
		return comment;
	}
	public Iterator<CommentPO> getComments(){
		return comment.iterator();
	}
	public int getCredit(){
		return credit;
	}
	public String getContactWay(){
		return contactWay;
	}
	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
	public void setComment(Set<CommentPO> comment){
		this.comment = comment;
	}
	public void setOrders(Set<OrderPO> orders){
		this.orders = orders;
	}
	public void setVip(boolean vip){
		this.vip = vip;
	}
	public void setVipInfo(VIPPO vipInfo){
		this.vipInfo = vipInfo;
	}
}
