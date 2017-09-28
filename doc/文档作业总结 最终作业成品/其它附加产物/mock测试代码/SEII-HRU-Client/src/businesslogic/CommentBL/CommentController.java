package businesslogic.CommentBL;

import java.util.List;

import businesslogicservice.CommentBLService.CommentBLService_Update;
import businesslogicservice.CommentBLService.CommentResultMessage;
import vo.CommentVO;

public class CommentController implements CommentBLService_Update{
	private Comment commentObject = new Comment();
	public void setComments(List<CommentInfo> list){
		this.commentObject.setComments(list);
	}
	@Override
	public CommentList getHotelInfo(long hotelId) {
		// TODO Auto-generated method stub
		return commentObject.getHotelInfo(hotelId);
	}

	@Override
	public CommentResultMessage review(CommentVO vo) {
		// TODO Auto-generated method stub
		return commentObject.review(vo);
	}

}
