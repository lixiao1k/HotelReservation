package businesslogic.CommentBL;

import java.util.List;

public class CommentList {
	List<CommentInfo> list;
	public CommentList(List<CommentInfo> list){
		this.list = list;
	}
	public void setList(List<CommentInfo> list){
		this.list = list;
	}
	public List<CommentInfo> getList(){
		return this.list;
	}
}

