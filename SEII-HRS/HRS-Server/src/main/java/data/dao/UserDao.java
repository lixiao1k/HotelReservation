package data.dao;

import po.UserPO;

public interface UserDao {
	/**
	 * �����û�
	 * @param po
	 */
	public void insert(UserPO po);
	/**
	 * ����id��ѯ�û�
	 * @param userId
	 * @return
	 */
	public UserPO getInfo(long userId);
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	public UserPO getInfo(String username);
	/**
	 * �����û�
	 * @param po
	 */
	public void update(UserPO po);
}
