package businesslogic.CommentBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.CommentBLService.CommentResultMessage;
import vo.CommentVO;

public class Comment {
	List<CommentInfo> list;
	public void setComments(List<CommentInfo> list){
		this.list = list;
	}

	public CommentList getHotelInfo(long hotelId) {
		// TODO Auto-generated method stub
		List<CommentInfo> result = new ArrayList<>();
		for (CommentInfo info:list){
			if (info.getHotelId()==hotelId)
				result.add(info);
		}
		return new CommentList(result);
	}

	public CommentResultMessage review(CommentVO vo) {
		// TODO Auto-generated method stub
		CommentInfo info = new CommentInfo(vo);
		list.add(info);
		return CommentResultMessage.SUCCESS;
	}
}
