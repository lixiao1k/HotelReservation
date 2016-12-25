package data.dao;


import java.rmi.RemoteException;

import info.ListWrapper;
import po.MemberPO;

public interface MemberDao {
	/**
	 * 根据id查询member持久化类
	 * @param userid
	 * @return
	 */
	public MemberPO getInfo(long userid);
	/**
	 * 根据用户名查询member持久化类
	 * @param username
	 * @return
	 */
	public MemberPO getInfo(String username);
	/**
	 * 根据酒店名查询member持久化类
	 * @param name
	 * @return
	 */
	public MemberPO getInfoByName(String name);
	/**
	 * 更新持久化类
	 * @param po
	 */
	public void update(MemberPO po);
	/**
	 * 插入member
	 * @param po
	 */
	public void add(MemberPO po);
	/**
	 * @param name
	 * @return
	 * @throws RemoteException
	 */
	public ListWrapper<MemberPO> manageInfo(String name)throws RemoteException ;
	/**
	 * 删除用户
	 * @param userId
	 */
	public void delete(long userId);
}	
