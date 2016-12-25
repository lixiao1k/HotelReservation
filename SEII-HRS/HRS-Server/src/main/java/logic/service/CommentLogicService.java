package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CommentResultMessage;
import vo.CommentVO;
import vo.HotelCommentVO;

public interface CommentLogicService extends Remote{
	/**
	 * ��ȡ�Ƶ����������
	 * @param hotelId
	 * �Ƶ�id
	 * @return ListWrapper<HotelCommentVO>
	 * �Ƶ�������Ϣ
	 * @throws RemoteException
	 */
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException;

	/**
	 * �û����۾Ƶ�ʱ����
	 * @param vo
	 * ������Ϣ
	 * @return CommentResultMessage
	 * ���۽��
	 * @throws RemoteException
	 */
	public CommentResultMessage review(CommentVO vo) throws RemoteException;
}
