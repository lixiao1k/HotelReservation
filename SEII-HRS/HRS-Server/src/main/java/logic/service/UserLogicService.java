package logic.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import resultmessage.RegisterResultMessage;
import resultmessage.UserResultMessage;
import vo.LoginResultVO;

public interface UserLogicService extends Remote{
	/**
	 * 用户登录时的验证方法
	 * @param username
	 * 登录的用户名
	 * @param password
	 * 登录的密码
	 * @return LoginResultVO
	 * 登录成功，VO内resultmessage为成功，并返回相应的用户类型和id，酒店工作人员还会返回酒店id
	 * 登录失败，VO内resultmessage为失败，其他数值为无效数值
	 * @throws RemoteException
	 */
	public LoginResultVO login(String username,String password) throws RemoteException;
	/**用户登出的方法，在窗口关闭和用户点击登出图标，将会调用此方法
	 * @param userid
	 * 用户id
	 * @throws RemoteException
	 */
	public void logout(long userid) throws RemoteException;
	/**用户注册的方法
	 * @param username
	 * 注册的用户名
	 * @param password
	 * 注册的密码
	 * @return RegisterResultMessage
	 * 注册是否成功的消息
	 * @throws RemoteException
	 */
	public RegisterResultMessage register(String username,String password) throws RemoteException;
	/**
	 * 用户更改密码所调用的方法
	 * @param userId
	 * 要更改密码的用户id
	 * @param password
	 * 要更改用户传入的原密码
	 * @param newPassword
	 * 要更改的新密码
	 * @return UserResultMessage
	 * 更改结果
	 */
	public UserResultMessage changePassword(long userId,String password,String newPassword) throws RemoteException;
}
