package list;

import java.util.List;

import info.CommentInfo;

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

