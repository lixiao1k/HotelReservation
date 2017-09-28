package businesslogic.UserBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.UserBLService.LoginResultMessage;
import businesslogicservice.UserBLService.RegisterResultMessage;

public class TestUserBL {
	UserController controller;
	long testId;
	public TestUserBL(){
		MockUserInfo user = new MockUserInfo("1234", "1234");
		MockUserInfo user2 = new MockUserInfo("4321", "4321");
		List<UserInfo> users = new ArrayList<UserInfo>();
		users.add(user);
		users.add(user2);
		controller = new UserController();
		controller.setUsers(users);
		testId = user.getId();
	}
	public void test(){
		LoginResultMessage result;
		result = controller.login("1234", "1234");
		System.out.println(result);
		result = controller.login("123", "123");
		System.out.println(result);
		result = controller.login("", "");
		System.out.println(result);
		controller.logout(testId);
		RegisterResultMessage result2;
		result2 = controller.register("1234", "");
		System.out.println(result2);
		result2 = controller.register("1234", "12341231231");
		System.out.println(result2);
		result2 = controller.register("123", "123142142");
		System.out.println(result2);
	}
	public static void main(String[] args) {
		TestUserBL testUserBL = new TestUserBL();
		testUserBL.test();
	}
}
