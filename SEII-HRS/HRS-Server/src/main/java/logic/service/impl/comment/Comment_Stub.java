package logic.service.impl.comment;

import java.rmi.RemoteException;
import java.util.Date;

import info.ListWrapper;
import info.Room;
import logic.service.CommentLogicService;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public class Comment_Stub implements CommentLogicService{

	@Override
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException {
		HotelCommentVO hcvo = new HotelCommentVO();
		hcvo.setComment("ÆÀÂÛ");
		hcvo.setDate(new Date());
		hcvo.setGrade(3);
		hcvo.setHide(false);
		hcvo.setRoom(new Room());
		hcvo.setName("111");
		ListWrapper<HotelCommentVO> hcvoList = new ListWrapper<>();
		hcvoList.add(hcvo);
		System.out.println("Comment.getHotelInfo  :  hotelId normal|return normal result");
		return hcvoList;
	}

	@Override
	public CommentResultMessage review(CommentVO vo) throws RemoteException {
		if(vo.getOrderId()==1){
			System.out.println("Comment.review  :  vo.orderId=1|return CommentResultMessage.FAIL_HAVEREVIEWED");
			return CommentResultMessage.FAIL_HAVEREVIEWED;
		}
		System.out.println("Comment.review  :  vo.orderId=other number|return CommentResultMessage.SUCCESS");
		return CommentResultMessage.SUCCESS;
	}
	
}
