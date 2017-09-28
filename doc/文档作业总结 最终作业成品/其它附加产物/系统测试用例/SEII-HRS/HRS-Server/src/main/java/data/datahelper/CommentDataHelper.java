package data.datahelper;

import java.util.List;

import po.CommentPO;

public interface CommentDataHelper {
	public void insert(CommentPO po);
	public List<CommentPO> getUserComment(long userId);
	public List<CommentPO> getHotelComment(long hotelId);
}
