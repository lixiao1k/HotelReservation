package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resultmessage.UserResultMessage;
import rmi.RemoteHelper;

public class HotelWorkerMainController implements Initializable{
	@FXML private GridPane hotelmain;
	@FXML private Button goStrategyListButton;
	@FXML private VBox mainUIVBox;
	private long hotelId;
	private long userId;
	private void goToPane(int i){
		String[] name = {"HotelInfoPane"
						,"BrowseOrderListPane"
						,"BrowseRoomListPane"
						,"BrowseStrategyListPane"};
		String[] path = {"Presentation/HotelUI/SetHotelInfo.fxml"
						,"Presentation/OrderUI/HotelWorkerBrowseOrderListUI.fxml"
						,"Presentation/HotelUI/BrowseRoomListUI.fxml"
						,"Presentation/StrategyUI/HotelWorkerBrowseStrategyListUI.fxml"};
		try {
			Parent pane = null;
			Object o = DataController.getInstance().get(name[i]);
			if(o==null){
				pane = FXMLLoader.load(getClass().getClassLoader().getResource(path[i]));
				pane.getProperties().put("NAME", name[i]);
				DataController.getInstance().put(name[i], pane);
			}
			else 
				pane = (Parent) o;
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(pane, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML protected void goSetHotelInfo(ActionEvent event){
		goToPane(0);
	}
	@FXML protected void goOrderList(ActionEvent event){	
		goToPane(1);
	}
	@FXML protected void goRoomList(ActionEvent event){
			goToPane(2);
	}
	@FXML protected void goStrategyList(ActionEvent event){
		goToPane(3);
	}
	@FXML protected void logout(ActionEvent event){
		try {
			RemoteHelper.getInstance().getServiceFactory().getUserLogicService().logout(userId);
			((Stage)hotelmain.getScene().getWindow()).close();
		} catch (RemoteException e) {
			Notifications.create().owner(hotelmain.getScene().getWindow()).title("登出").text("失败").showError();
			e.printStackTrace();
		}
	}
    @FXML
    protected void changePassword(ActionEvent e){
    	PopOver popOver = new PopOver();
		GridPane pane = new GridPane();
		Font font = new Font("YouYuan",15);
		Text text1 = new Text("原密码");
		text1.setFont(font);
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
				Notifications.create().owner(hotelmain.getScene().getWindow()).title("��������").text("���������룡��").showError();
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
		popOver.setArrowLocation(ArrowLocation.BOTTOM_CENTER);
		popOver.show(((Node)e.getSource()));
    }
    private void changePasswordAction(String password,String newPassword){
    	try{
    		UserResultMessage result = RemoteHelper.getInstance().getServiceFactory().getUserLogicService().changePassword(userId, password, newPassword);
    		if(result==UserResultMessage.FAIL_WRONGID){
    			Notifications.create().owner(hotelmain.getScene().getWindow()).title("更改密码").text("错误ID").showError();
    		}else if(result==UserResultMessage.FAIL_WRONGINFO){
    			Notifications.create().owner(hotelmain.getScene().getWindow()).title("更改密码").text("错误信息").showError();
    		}
    		else
    			Notifications.create().owner(hotelmain.getScene().getWindow()).title("更改密码").text("成功").showConfirm();
    	}catch(RemoteException e){
    		Notifications.create().owner(hotelmain.getScene().getWindow()).title("更改密码").text("网络错误").showError();
    		e.printStackTrace();
    	}
    }
	//������Ϣ
    public void setBaseInfo(){
		Object o = DataController.getInstance().get("HotelId");
		if(o!=null)
			hotelId = (long) o;
		o = DataController.getInstance().get("UserId");
		if(o!=null)
			userId = (long) o;
		DataController.getInstance().putAndUpdate("mainPane", hotelmain);
    }
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
}
