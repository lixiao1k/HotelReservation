package vo;

public class CommentVO {
	int grade;
    String comment;
    long time;
    long userid;
    public CommentVO(int grade,String comment,long userid){
    	//get time;
    	this.userid = userid;
    	this.grade = grade;
    	this.comment = comment;
    }
	public CommentVO() {
		// TODO Auto-generated constructor stub
	}
}
