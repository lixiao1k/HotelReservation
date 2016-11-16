package logic.service;

import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;

public interface UserLogicService {
	public LoginResultMessage login(String username,String password);
	public void logout(long userid);
	public RegisterResultMessage register(String username,String password);
}
