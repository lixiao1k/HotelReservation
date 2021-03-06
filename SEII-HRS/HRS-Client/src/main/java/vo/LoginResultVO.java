package vo;

import java.io.Serializable;

import info.UserType;
import resultmessage.LoginResultMessage;

public class LoginResultVO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2584247491131641796L;
	private LoginResultMessage resultMessage;
    private UserType userType;
    private long userid;
    private long hotelid;
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
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public long getHotelid() {
		return hotelid;
	}
	public void setHotelid(long hotelid) {
		this.hotelid = hotelid;
	}
	public void setResultMessage(LoginResultMessage resultMessage) {
		this.resultMessage = resultMessage;
	}
    
}
