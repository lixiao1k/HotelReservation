package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import Presentation.MemberUI.KeepPersonInfoController;
import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resultmessage.UserResultMessage;
import rmi.RemoteHelper;

public class ClientMainUIController implements Initializable{
	@FXML Button goHotelListButton;
	@FXML Button goVIPRegisterButton;
	@FXML Button goPersonInfoButton;
	@FXML Button goBrowseOrderListButton;
	@FXML GridPane clientmain;
	private long userId;
	private void goToPane(int i){
		String[] name = {"HotelListPane"
						,"VIPRegisterPane"
						,"KeepPersonInfoPane"
						,"BrowseOrderPane"
		};
		String[] path = {"Presentation/HotelUI/HotelBrowse.fxml"
						,"Presentation/MemberUI/Register.fxml"
						,"Presentation/MemberUI/PersonInfo.fxml"
						,"Presentation/OrderUI/ClientBrowseOrderListUI.fxml"
		};
		try {
			Parent pane = null;
			pane = FXMLLoader.load(getClass().getClassLoader().getResource(path[i]));
			pane.getProperties().put("NAME", name[i]);
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(pane, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML
	protected void goHotelList(ActionEvent e){
		goToPane(0);
	}
	@FXML
	protected void goVIPRegister(ActionEvent e){
		goToPane(1);
	}
	@FXML
	protected void goPersonInfo(ActionEvent e){
		goToPane(2);
	}
	@FXML
	protected void goBrowseOrderList(ActionEvent e){
		goToPane(3);
	}
    public void setBaseInfo(){
		Object o = DataController.getInstance().get("UserId");
		if(o!=null)
			userId = (long) o;
    }
    @FXML
    protected void logout(ActionEvent e){
    	try {
			RemoteHelper.getInstance().getServiceFactory().getUserLogicService().logout(userId);
			((Stage)clientmain.getScene().getWindow()).close();
		} catch (RemoteException e2) {
			Notifications.create().owner(clientmain.getScene().getWindow()).title("登出").text("登出失败").showError();
			e2.printStackTrace();
		}
    }
    @FXML
    protected void changePassword(ActionEvent e){
    	PopOver popOver = new PopOver();
		GridPane pane = new GridPane();
		Font font = new Font("YouYuan",15);
		Text text1 = new Text("原密码");
		Text text2 = new Text("新密码");
		text2.setFont(font);
		PasswordField password = new PasswordField();
		PasswordField newPassword = new PasswordField();
		Button btn = new Button("确认");
		btn.setId("green-button");
		btn.setFont(new Font("YouYuan",15));
		btn.setOnAction((ActionEvent e3)->{
			if(password.getText()==null||password.getText().equals("\\s")
					||newPassword.getText()==null||newPassword.getText().equals("\\s")){
				Notifications.create().owner(clientmain.getScene().getWindow()).title("更改密码").text("请输入密码！").showError();
				return;
			}
			changePasswordAction(password.getText(),newPassword.getText());
			popOver.hide();
		});
		Button btn2 = new Button("取消");
		btn2.setId("red-button");
		btn2.setFont(new Font("YouYuan",15));
		btn2.setOnAction((ActionEvent e2)->{
			popOver.hide();
		});
		pane.add(text1, 0, 0);
		pane.add(text2, 0, 1);
		pane.add(password, 1, 0);
		pane.add(newPassword, 1, 1);
		pane.add(btn, 1, 2);
		pane.add(btn2, 1, 2);
		pane.setHalignment(btn, HPos.RIGHT);
		pane.setMargin(btn, new Insets(5,5,5,0));
		pane.setHalignment(btn2, HPos.RIGHT);
		pane.setMargin(text1, new Insets(5,5,5,5));
		pane.setMargin(text2, new Insets(5,5,5,5));
		pane.setMargin(password, new Insets(5,5,5,5));
		pane.setMargin(newPassword, new Insets(5,5,5,5));
		pane.setMargin(btn2, new Insets(5,90,5,0));
		popOver.setContentNode(pane);
		popOver.setArrowLocation(ArrowLocation.TOP_CENTER);
		popOver.centerOnScreen();
		popOver.show(((Node)e.getSource()));
    }
    private void changePasswordAction(String password,String newPassword){
    	try{
    		UserResultMessage result = RemoteHelper.getInstance().getServiceFactory().getUserLogicService().changePassword(userId, password, newPassword);
    		if(result==UserResultMessage.FAIL_WRONGID){
    			Notifications.create().owner(clientmain.getScene().getWindow()).title("更改密码").text("错误ID").showError();
    		}else if(result==UserResultMessage.FAIL_WRONGINFO){
    			Notifications.create().owner(clientmain.getScene().getWindow()).title("更改密码").text("错误信息").showError();
    		}
    		else
    			Notifications.create().owner(clientmain.getScene().getWindow()).title("更改密码").text("成功").showConfirm();
    	}catch(RemoteException e){
    		Notifications.create().owner(clientmain.getScene().getWindow()).title("更改密码").text("网络错误").showError();
    		e.printStackTrace();
    	}
    }
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
}
