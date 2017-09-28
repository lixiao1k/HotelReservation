package vo;

public class BrowseVO {
	int browseID;
	int userID;
	int toUserID;
	long time;
	public BrowseVO(int userID,int toUserID,long time){
		//initialize browseID;
		this.userID = userID;
		this.toUserID = toUserID;
		this.time = time;
	}
	public BrowseVO() {
		// TODO Auto-generated constructor stub
	}
}
