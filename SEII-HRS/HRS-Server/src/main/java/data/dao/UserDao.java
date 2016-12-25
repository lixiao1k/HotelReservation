package data.dao;

import po.UserPO;

public interface UserDao {
	/**
	 * 插入用户
	 * @param po
	 */
	public void insert(UserPO po);
	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	public UserPO getInfo(long userId);
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public UserPO getInfo(String username);
	/**
	 * 更新用户
	 * @param po
	 */
	public void update(UserPO po);
}
