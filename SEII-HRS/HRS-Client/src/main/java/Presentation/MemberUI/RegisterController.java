package Presentation.MemberUI;
/*
 * @author Shelton Lee 151250084
 */
import java.awt.Toolkit;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.VIPVO;

public class RegisterController implements Initializable {
	@FXML TextField nameOfCompanyTextField;
	@FXML DatePicker birthdayDatePicker;
	@FXML RadioButton peopleRadioButton;
	@FXML RadioButton companyRadioButton;
	@FXML Button register;
	final ToggleGroup toggle =new ToggleGroup();
	private ServiceFactory servicefactory;
	private long userid;
	@FXML
	protected void registerbuttonAction(ActionEvent e) throws ParseException, RemoteException{

		if(toggle.getSelectedToggle().getUserData()=="People"){
			LocalDate birthday=birthdayDatePicker.getValue();
			if(birthday==null){
				Toolkit.getDefaultToolkit().beep();
				return;
			}
			String datestr=birthday.toString();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date=sdf.parse(datestr);
			VIPVO vo=new VIPVO(VIPType.PERSON,date, userid,nameOfCompanyTextField.getText());
			MemberResultMessage result=servicefactory.getMemberLogicService().registerVIP(vo);
			checkResult(result);
		}else if(toggle.getSelectedToggle().getUserData()=="Company"){
			if(nameOfCompanyTextField.getText().length()>=1){
				VIPVO vo=new VIPVO(VIPType.COMPANY,null,userid,nameOfCompanyTextField.getText());
				MemberResultMessage result=servicefactory.getMemberLogicService().registerVIP(vo);
				checkResult(result);
			}else{
				Notifications.create().title("INFO").text("Please write company name").showConfirm();
				Toolkit.getDefaultToolkit().beep();
			}

		}
	}
    public void checkResult(MemberResultMessage result){
    	if(result==MemberResultMessage.FAIL_ALREADYVIP){
    		Notifications.create().title("INFO").text("Fail, you are already VIP").showConfirm();
			Toolkit.getDefaultToolkit().beep();
    	}else if(result==MemberResultMessage.FAIL_CREDITNOTENOUGH){
    		Notifications.create().title("INFO").text("Fail, your credit isn't enough").showConfirm();
			Toolkit.getDefaultToolkit().beep();
    	}else if(result==MemberResultMessage.SUCCESS){
    		Notifications.create().title("INFO").text("SUCCEE,Welcome!").showConfirm();
			Toolkit.getDefaultToolkit().beep();
    	}else{
    		Notifications.create().title("INFO").text("FAIL").showConfirm();
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
		Image peopleimage =new Image(getClass().getResourceAsStream("People.png"));
		peopleRadioButton.setGraphic(new ImageView(peopleimage));
		Image companyimage=new Image(getClass().getResourceAsStream("Company.png"));
		companyRadioButton.setGraphic(new ImageView(companyimage));
		Image registerButton = new Image(getClass().getResourceAsStream("register.png"));
		register.setGraphic(new ImageView(registerButton));
		register.getStylesheets().add(getClass().getResource("buttonFile.css").toExternalForm());
//		nameOfCompanyTextField.getStylesheets().add(getClass().getResource("buttonFile.css").toExternalForm());
	}
}
