package businesslogicservice.CommentBLService;

import businesslogic.CommentBL.CommentList;
import vo.CommentVO;

public interface CommentBLService_Update {
	public CommentList getHotelInfo(long hotelId);
	public CommentResultMessage review(CommentVO vo);
}
