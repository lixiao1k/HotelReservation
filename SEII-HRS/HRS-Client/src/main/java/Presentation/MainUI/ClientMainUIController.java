package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Presentation.MemberUI.KeepPersonInfoController;
import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
						,"Presentation/MemberUI/KeepPersonInfo.fxml"
						,"Presentation/OrderUI/ClientBrowseOrderListUI.fxml"
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
    
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
}
