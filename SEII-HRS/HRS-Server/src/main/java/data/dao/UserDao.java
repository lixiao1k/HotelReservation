package data.dao;

import po.UserPO;

public interface UserDao {
	public void insert(UserPO po);
	public UserPO getInfo(long userId);
	public UserPO getInfo(String username);
	public void update(UserPO po);
}
