package data.dao;


import java.rmi.RemoteException;

import info.ListWrapper;
import po.MemberPO;

public interface MemberDao {
	/**
	 * ����id��ѯmember�־û���
	 * @param userid
	 * @return
	 */
	public MemberPO getInfo(long userid);
	/**
	 * �����û�����ѯmember�־û���
	 * @param username
	 * @return
	 */
	public MemberPO getInfo(String username);
	/**
	 * ���ݾƵ�����ѯmember�־û���
	 * @param name
	 * @return
	 */
	public MemberPO getInfoByName(String name);
	/**
	 * ���³־û���
	 * @param po
	 */
	public void update(MemberPO po);
	/**
	 * ����member
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
	 * ɾ���û�
	 * @param userId
	 */
	public void delete(long userId);
}	
