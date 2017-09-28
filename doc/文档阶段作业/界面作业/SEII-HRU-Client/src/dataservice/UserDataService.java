package dataservice;

import po.UserPO;

public interface UserDataService extends DatabaseService{
	public void insert(UserPO po);
}
