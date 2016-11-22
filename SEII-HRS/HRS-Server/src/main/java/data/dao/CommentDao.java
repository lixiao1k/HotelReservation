package data.dao;

import info.ListWrapper;
import po.CommentPO;

public interface CommentDao {
	public void insert(CommentPO po);
	public ListWrapper<CommentPO> getUserComment(long userId);
	public ListWrapper<CommentPO> getHotelComment(long hotelId);
}
