package logic.service.impl.user;

import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import logic.service.UserLogicService;
import resultmessage.LoginResultMessage;
import resultmessage.RegisterResultMessage;

public class UserLogicServiceImpl implements UserLogicService{
	private UserDao userDao;
	public UserLogicServiceImpl() {
		userDao=DaoManager.getInstance().getUserDao();
	}
	@Override
	public LoginResultMessage login(String username, String password) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void logout(long userid) {
		// TODO �Զ����ɵķ������
		
	}

	@Override
	public RegisterResultMessage register(String username, String password) {
		// TODO �Զ����ɵķ������
		return null;
	}

}
