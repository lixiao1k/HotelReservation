package dataservice;

import po.CommentPO;

public interface CommentDataService extends DatabaseService{
	public void insert(CommentPO po);
	public CommentPO getInfo(long id);
}
