package logic.service;

import list.CommentList;
import resultmessage.CommentResultMessage;
import vo.CommentVO;

public interface CommentLogicService {
	public CommentList getHotelInfo(long hotelId);
	public CommentResultMessage review(CommentVO vo);
}
