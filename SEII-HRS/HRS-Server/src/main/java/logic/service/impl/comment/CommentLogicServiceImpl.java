package logic.service.impl.comment;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.CommentLogicService;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public class CommentLogicServiceImpl implements CommentLogicService{
	private CommentDO commentDO;
	public CommentLogicServiceImpl() {
		commentDO=new CommentDO();
	}
	@Override
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException {
		return commentDO.getHotelInfo(hotelId);
	}

	@Override
	public CommentResultMessage review(CommentVO vo) throws RemoteException {
		return commentDO.review(vo);
	}

}
