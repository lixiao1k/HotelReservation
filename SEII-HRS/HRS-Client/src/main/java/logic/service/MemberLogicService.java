package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import list.MemberInfoList;
import resultmessage.MemberResultMessage;
import vo.MemberVO;
import vo.VIPVO;

public interface MemberLogicService extends Remote{
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException;
	public MemberResultMessage cancel(long id) throws RemoteException;
	public MemberVO getInfo(long id) throws RemoteException;
	public MemberResultMessage changeInfo(MemberVO vo) throws RemoteException;
	public MemberInfoList manageInfo() throws RemoteException;
}
