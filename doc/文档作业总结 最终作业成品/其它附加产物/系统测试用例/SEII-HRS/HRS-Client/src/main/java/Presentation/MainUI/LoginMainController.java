package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import datacontroller.DataController;
import info.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.service.ServiceFactory;
import resultmessage.LoginResultMessage;
import rmi.RemoteHelper;
import vo.LoginResultVO;

public class LoginMainController implements Initializable{
	@FXML private TextField usernameField;
	@FXML private PasswordField passwordField;
	private ServiceFactory serviceFactory;
	private Map<LoginResultMessage,String> info;
	private Map<UserType,Integer> type = new HashMap<>();
	@FXML 
	protected void register(ActionEvent e) throws IOException{ 
		Parent register =FXMLLoader.load(getClass().getResource("RegisterMainUI.fxml"));
		Scene scene =new Scene(register);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("注册");
		stage.show();
		Stage login = (Stage) usernameField.getScene().getWindow();
		login.close();
	}
	@FXML
	protected void login(ActionEvent e) throws IOException {
		String username = usernameField.getText();
	    String password = passwordField.getText();
	    String[] titles = {"HRS-Client","HRS-HotelWorker","HRS-WebSaler","HRS-WebManager"};
	    String[] mainPath = {"ClientMainUI.fxml"
	    					,"HotelWorkerMainUI.fxml"
	    					,"WebSalerMainUI.fxml"
	    					,"WebManagerMainUI.fxml"
	    					};
	    String[] contentPath = {"Presentation/MemberUI/KeepPersonInfo.fxml"
	    					   ,"Presentation/HotelUI/SetHotelInfo.fxml"
	    					   ,"Presentation/StrategyUI/WebSalerBrowseStrategyListUI.fxml"
	    					   ,"Presentation/HotelUI/AddHotelInfo.fxml"
	    					   };
	    String[] nameProperties = {"KeepPersonInfoPane"
	    						  ,"SetHotelInfoPane"
	    						  ,"NewStrategy"
	    						  ,"AddHotelInfoPane"
	    						  };
		LoginResultVO result = null;
		result = serviceFactory.getUserLogicService().login(username, password);
		if(result==null){
			Notifications.create().title("登录").text("未知错误！").showError();
			return;
		}
		else if(result.getResultMessage()!=LoginResultMessage.SUCCESS){
			Notifications.create().title("登录").text(info.get(result.getResultMessage())).showWarning();
			return;
		}
		DataController.getInstance().put("UserId", result.getUserID());
		int userType = type.get(result.getUserType());
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource(mainPath[userType]));
		Parent content = FXMLLoader.load(getClass().getClassLoader().getResource(contentPath[userType]));
		content.getProperties().put("NAME", nameProperties[userType]);
		GridPane root = (GridPane) loader.load();
	    root.add(content, 2, 1);
		Scene scene = new Scene(root,900,600);
		Stage stage = new Stage();
		stage.setTitle(titles[userType]);
		stage.setScene(scene);
		stage.show();
		Stage login = (Stage) usernameField.getScene().getWindow();
		login.close();
	}
	public void initialize(URL location, ResourceBundle resources) {
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		info = new HashMap<>();
		info.put(LoginResultMessage.FAIL_LOGGED, "已登录！请不要重复登录！");
		info.put(LoginResultMessage.FAIL_NOINFO, "用户不存在！");
		info.put(LoginResultMessage.FAIL_WRONG, "用户名或密码错误！");	
		type.put(UserType.CLIENT, 0);
		type.put(UserType.HOTEL_WORKER, 1);
		type.put(UserType.WEB_SALER, 2);
		type.put(UserType.WEB_MANAGER, 3);
		type.put(UserType.NOT_USER, -1);
	}

}
