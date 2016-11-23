package po;

/*
 * cid 评论编号
 * content 评论内容
 * score 评论的分数
 * member 评论的用户 和member表的id主键关联，由hibernate的懒加载机制，在用到时候才会加载，节省系统资源
 * hotel 评论的酒店 和hotel表的id主键关联
 * @author whk
 */
public class CommentPO {
	private long cid;
	private String content;
	private int score;
	private MemberPO member;
	private HotelPO hotel;
	public CommentPO(){}
	public long getCid(){
		return cid;
	}
	public String getContent(){
		return content;
	}
	public int getScore(){
		return score;
	}
	public MemberPO getMember(){
		return member;
	}
	public HotelPO getHotel(){
		return hotel;
	}
	private void setCid(long cid){
		this.cid = cid;
	}
	public void setHotel(HotelPO hotel){
		this.hotel = hotel;
	}
	public void setMember(MemberPO member){
		this.member = member;
	}
	public void setScore(int score){
		this.score = score;
	}
	public void setContent(String content){
		this.content = content;
	}
}
