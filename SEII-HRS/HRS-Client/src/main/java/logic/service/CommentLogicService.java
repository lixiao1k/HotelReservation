package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public interface CommentLogicService extends Remote{
	/**
	 * 获取酒店的所有评论
	 * @param hotelId
	 * 酒店id
	 * @return ListWrapper<HotelCommentVO>
	 * 酒店评论信息
	 * @throws RemoteException
	 */
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException;

	/**
	 * 用户评论酒店时调用
	 * @param vo
	 * 评论信息
	 * @return CommentResultMessage
	 * 评论结果
	 * @throws RemoteException
	 */
	public CommentResultMessage review(CommentVO vo) throws RemoteException;
}
