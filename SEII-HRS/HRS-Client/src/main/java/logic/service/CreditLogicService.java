package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import info.ListWrapper;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public interface CreditLogicService extends Remote{
	/**
	 * 信用充值
	 * 网站营销人员进行信用充值操作若调用的方法
	 * @param userId
	 * 需要充值的对象
	 * 用户id
	 * @param delta
	 * 用户所充的钱，实际充值信用为delta*100
	 * @return CreditResultMessage
	 * 充值成功消息
	 */
	public CreditResultMessage excharge(long userId, int delta)throws RemoteException;
	/**
	 * 客户查看自身信用记录时调用，返回用户的所有的信用记录
	 * @param userId
	 * 用户id
	 * @return ListWrapper<CreditVO>
	 * 用户的信用记录列表
	 * @throws RemoteException
	 */
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException;
}
