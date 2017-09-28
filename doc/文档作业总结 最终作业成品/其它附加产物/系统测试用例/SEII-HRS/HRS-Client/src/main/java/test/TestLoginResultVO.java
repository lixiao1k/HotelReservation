package test;
import info.UserType;
import resultmessage.LoginResultMessage;
import vo.LoginResultVO;

public class TestLoginResultVO {
     public LoginResultVO returnResultVO(){
    	 LoginResultMessage resultMessage=LoginResultMessage.SUCCESS;
    	 UserType userType=UserType.CLIENT;
//    	 UserType userType=UserType.HOTEL_WORKER;
//    	 UserType userType=UserType.WEB_MANAGER;
//    	 UserType userType=UserType.WEB_SALER;
//    	 UserType userType=UserType.NOT_USER;
    	 Long userid =(long) 151250084;
    	 LoginResultVO loginResultVO=new LoginResultVO(resultMessage, userType, userid);
    	 return loginResultVO;
     }
}
