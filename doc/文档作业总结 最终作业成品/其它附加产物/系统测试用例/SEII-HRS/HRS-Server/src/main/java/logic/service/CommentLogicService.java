package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public interface CommentLogicService extends Remote{
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException;
	public CommentResultMessage review(CommentVO vo) throws RemoteException;
}
