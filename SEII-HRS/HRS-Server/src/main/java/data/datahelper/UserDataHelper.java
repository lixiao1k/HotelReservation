package data.datahelper;

import po.UserPO;

public interface UserDataHelper {
	public void insert(UserPO po);
	public UserPO getInfo(long userId);
}
