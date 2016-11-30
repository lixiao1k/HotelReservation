package vo;

import info.UserType;
import resultmessage.LoginResultMessage;

public class LoginResultVO {
    LoginResultMessage resultMessage;
    UserType userType;
    long userid;
    public LoginResultVO(LoginResultMessage resultMessage,UserType userType,long userid) {
		// TODO Auto-generated constructor stub
    	this.resultMessage=resultMessage;
    	this.userType=userType;
    	this.userid=userid;
	}
    public LoginResultMessage getResultMessage(){
    	return this.resultMessage;
    }
    public UserType getUserType(){
    	return this.userType;
    }
    public long getUserID(){
    	return this.userid;
    }
    public void setLoginResultMessage(LoginResultMessage resultMessage){
    	this.resultMessage=resultMessage;
    }
    public void setUserType(UserType userType){
    	this.userType=userType;
    }
    public void setUserID(long userid){
    	this.userid=userid;
    }
    
}
