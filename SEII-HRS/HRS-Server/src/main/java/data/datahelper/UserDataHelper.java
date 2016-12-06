package data.datahelper;

import po.UserPO;

public interface UserDataHelper {
	public void insert(UserPO po);
	public UserPO getInfo(long userId);
	public UserPO getInfo(String username);
	public void update(UserPO po);
	public void delete(long userid);
}
