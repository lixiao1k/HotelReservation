package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.MemberResultMessage;
import vo.ManageClientVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;
import vo.ManageWEBSalerVO;
import vo.MemberVO;
import vo.VIPVO;

public interface MemberLogicService extends Remote{
	public MemberResultMessage registerVIP(VIPVO vo) throws RemoteException;
	public MemberResultMessage cancel(long id) throws RemoteException;
	public ManageClientVO getClient(String username) throws RemoteException;
	public ListWrapper<ManageHotelVO> getAllHotelWorker(String hotelname) throws RemoteException;
	public ManageWEBSalerVO getWEBSaler(String username) throws RemoteException;
	public MemberResultMessage addWEBSaler(ManageWEBSalerVO vo) throws RemoteException;
	public MemberResultMessage updateClient(ManageClientVO vo) throws RemoteException;
	public MemberResultMessage updateHotelWorker(ManageHotelWorkerVO vo) throws RemoteException;
	public MemberResultMessage updateWEBSaler(ManageWEBSalerVO vo) throws RemoteException;
	public MemberResultMessage delete(long id) throws RemoteException;
	public MemberVO getInfo(long userId) throws RemoteException;
}
