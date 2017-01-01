package Presentation.MainUI;

import java.awt.Window;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import datacontroller.DataController;
import info.UserType;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
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
		stage.initStyle(StageStyle.UNDECORATED);
		scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
		stage.show();
		Stage login = (Stage) usernameField.getScene().getWindow();
		login.close();
	}
	@FXML
	protected void login(ActionEvent e) throws IOException {
		Task<Void> sleep = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                }
                return null;
			}
		};
		ProgressStage waitStage = new ProgressStage(sleep,(Stage)usernameField.getScene().getWindow(), "登录中...");
		waitStage.show();
		String username = usernameField.getText();
	    String password = passwordField.getText();
	    String[] titles = {"HRS-Client","HRS-HotelWorker","HRS-WebSaler","HRS-WebManager"};
	    String[] mainPath = {"ClientMainUI.fxml"
	    					,"HotelWorkerMainUI.fxml"
	    					,"WebSalerMainUI.fxml"
	    					,"WebManagerMainUI.fxml"
	    					};
	    String[] contentPath = {"Presentation/MemberUI/PersonInfo.fxml"
	    					   ,"Presentation/HotelUI/SetHotelInfo.fxml"
	    					   ,"Presentation/StrategyUI/WebSalerBrowseStrategyListUI.fxml"
	    					   ,"Presentation/HotelUI/AddHotelInfo.fxml"
	    					   };
	    String[] nameProperties = {"KeepPersonInfoPane"
	    						  ,"SetHotelInfoPane"
	    						  ,"NewStrategyPane"
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
		if(result.getHotelid()!=-1)
			DataController.getInstance().put("HotelId",result.getHotelid());
		int userType = type.get(result.getUserType());
		FXMLLoader loader =new FXMLLoader();
		loader.setLocation(getClass().getResource(mainPath[userType]));
		Parent content = FXMLLoader.load(getClass().getClassLoader().getResource(contentPath[userType]));
		content.getProperties().put("NAME", nameProperties[userType]);
		GridPane root = (GridPane) loader.load();
	    root.add(content, 3, 1);
	    DataController.getInstance().putAndUpdate("mainPane", root);
		Scene scene = new Scene(root,900,600);
		Stage stage = new Stage();
		stage.setTitle(titles[userType]);
		stage.setResizable(false);
		scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
		stage.setScene(scene);
		final long id = result.getUserid();
		stage.setOnCloseRequest((WindowEvent e2)->{
			try {
				RemoteHelper.getInstance().getServiceFactory().getUserLogicService().logout(id);
			} catch (RemoteException e3) {
				Notifications.create().owner(stage).title("登出").text("登出失败！").showError();
				e3.printStackTrace();
			}
		});
		stage.show();
		Stage login = (Stage) usernameField.getScene().getWindow();
		waitStage.close();
		login.close();
	}
	@FXML
	protected void exit(ActionEvent e){
		((Stage)usernameField.getScene().getWindow()).close();
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
