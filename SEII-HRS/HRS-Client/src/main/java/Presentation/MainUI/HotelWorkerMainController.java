package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
			Notifications.create().owner(hotelmain.getScene().getWindow()).title("登出").text("登出失败！").showError();
			e.printStackTrace();
		}
	}
	//基本信息
    public void setBaseInfo(){
		Object o = DataController.getInstance().get("HotelId");
		if(o!=null)
			hotelId = (long) o;
		o = DataController.getInstance().get("UserId");
		if(o!=null)
			userId = (long) o;
    }
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
}
