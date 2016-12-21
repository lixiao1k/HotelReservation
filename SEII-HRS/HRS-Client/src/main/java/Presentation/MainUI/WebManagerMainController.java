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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import rmi.RemoteHelper;

public class WebManagerMainController implements Initializable{
	@FXML private GridPane webManagerMain;
	private long userId;
	private void goToPane(int i){
		String[] name = {"AddHotelInfoPane"
					  ,"ManageUserPane"
		};
		String[] path = {"Presentation/HotelUI/AddHotelInfo.fxml"
						,"Presentation/UserUI/ManageUser.fxml"
		};
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
			ObservableList<Node> list = webManagerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			webManagerMain.add(pane, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML protected void goAddHotelInfo(ActionEvent event){
		goToPane(0);
	}
	@FXML 
	protected void goManageUser(ActionEvent event){
		goToPane(1);
	}
	private void setBaseInfo(){
		Object o = DataController.getInstance().get("UserId");
		if(o!=null)
			userId = (long) o;
	}
    @FXML
    protected void logout(ActionEvent e){
    	try {
			RemoteHelper.getInstance().getServiceFactory().getUserLogicService().logout(userId);
			((Stage)webManagerMain.getScene().getWindow()).close();
		} catch (RemoteException e2) {
			Notifications.create().owner(webManagerMain.getScene().getWindow()).title("�ǳ�").text("�ǳ�ʧ�ܣ�").showError();
			e2.printStackTrace();
		}
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
	
}