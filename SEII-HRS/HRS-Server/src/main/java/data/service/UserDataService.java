package data.service;

import po.UserPO;

public interface UserDataService {
	public void login(String username,String password);
	public void register(UserPO po);
}
