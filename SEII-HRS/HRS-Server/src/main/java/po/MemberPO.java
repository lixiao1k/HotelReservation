package po;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MemberPO {
	private String name;
	private long mid;
	private String contactWay;
	private int credit;
	private Set<OrderPO> orders = new HashSet<OrderPO>();
	private Set<CommentPO> comment = new HashSet<CommentPO>();
	public MemberPO(){
	}
	private Set<OrderPO> getOrders(){
		return orders;
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
	public long getMid(){
		return mid;
	}
	public int getCredit(){
		return credit;
	}
	public String getContactWay(){
		return contactWay;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setContactWay(String contactWay){
		this.contactWay = contactWay;
	}
	public void setCredit(int credit){
		this.credit = credit;
	}
	private void setMid(long mid){
		this.mid = mid;
	}
	private void setComment(Set<CommentPO> comment){
		this.comment = comment;
	}
	private void setOrders(Set<OrderPO> orders){
		this.orders = orders;
	}
}

