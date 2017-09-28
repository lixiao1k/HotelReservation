package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CommentResultMessage;
import vo.CommentVO;

public interface CommentLogicService extends Remote{
	public ListWrapper<CommentVO> getHotelInfo(long hotelId) throws RemoteException;
	public CommentResultMessage review(CommentVO vo) throws RemoteException;
}
