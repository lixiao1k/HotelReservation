package businesslogicservice.UserBLService;

public interface UserBLService{
	public LoginResultMessage login(String username,String password);
	public void logout(long userid);
	public RegisterResultMessage register(String username,String password);
}
