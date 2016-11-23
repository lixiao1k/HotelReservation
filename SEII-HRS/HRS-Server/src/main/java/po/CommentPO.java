package po;

/*
 * cid ���۱��
 * content ��������
 * score ���۵ķ���
 * member ���۵��û� ��member���id������������hibernate�������ػ��ƣ����õ�ʱ��Ż���أ���ʡϵͳ��Դ
 * hotel ���۵ľƵ� ��hotel���id��������
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
