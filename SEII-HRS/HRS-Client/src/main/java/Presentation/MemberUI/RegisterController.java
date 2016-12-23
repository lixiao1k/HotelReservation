package Presentation.MemberUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import info.VIPType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.VIPVO;

public class RegisterController implements Initializable {
	@FXML TextField nameOfCompanyTextField;
	@FXML DatePicker birthdayDatePicker;
	@FXML RadioButton peopleRadioButton;
	@FXML RadioButton companyRadioButton;
	final ToggleGroup toggle =new ToggleGroup();
	private ServiceFactory servicefactory;
	private long userid;
	@FXML
	protected void registerbuttonAction(ActionEvent e) throws ParseException, RemoteException{

		if(toggle.getSelectedToggle().getUserData()=="People"){
			LocalDate birthday=birthdayDatePicker.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			Date date=sdf.parse(birthday.toString());
			VIPVO vo=new VIPVO(VIPType.PERSON,date, userid,nameOfCompanyTextField.getText());
			MemberResultMessage result=servicefactory.getMemberLogicService().registerVIP(vo);
			checkResult(result);
		}else if(toggle.getSelectedToggle().getUserData()=="Company"){
			VIPVO vo=new VIPVO(VIPType.COMPANY,null,userid,nameOfCompanyTextField.getText());
			MemberResultMessage result=servicefactory.getMemberLogicService().registerVIP(vo);
			checkResult(result);
		}
	}
    public void checkResult(MemberResultMessage result){
    	if(result==MemberResultMessage.FAIL_ALREADYVIP){
    		Notifications.create().title("提示").text("您已是会员").showConfirm();
    	}else if(result==MemberResultMessage.FAIL_CREDITNOTENOUGH){
    		Notifications.create().title("提示").text("您的信用不足，请及时充值").showConfirm();
    	}else if(result==MemberResultMessage.SUCCESS){
    		Notifications.create().title("提示").text("恭喜您注册成功").showConfirm();
    	}else{
    		Notifications.create().title("提示").text("注册失败").showConfirm();
    	}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		final Tooltip tip=new Tooltip();
		tip.setText("个人用户必填！");
		birthdayDatePicker.setTooltip(tip);
		final Tooltip tip1=new Tooltip();
		tip1.setText("企业用户必填，个人用户选填！");
		nameOfCompanyTextField.setTooltip(tip1);
		servicefactory=RemoteHelper.getInstance().getServiceFactory();
		userid=(long)DataController.getInstance().get("UserId");
		peopleRadioButton.setToggleGroup(toggle);
		peopleRadioButton.setUserData("People");
		companyRadioButton.setToggleGroup(toggle);
		companyRadioButton.setUserData("Company");
		peopleRadioButton.setSelected(true);
	}
}
