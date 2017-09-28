package logic.service.impl.comment;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import info.ListWrapper;
import logic.service.CommentLogicService;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public class CommentLogicServiceImpl extends UnicastRemoteObject implements CommentLogicService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541498151711407908L;
	private CommentDO commentDO;
	public CommentLogicServiceImpl() throws RemoteException{
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
