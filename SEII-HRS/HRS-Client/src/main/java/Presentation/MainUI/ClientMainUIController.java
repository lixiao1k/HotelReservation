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
	@FXML Label stateLabel;
	private long userid;
	@FXML
	protected void goHotelList(ActionEvent e){
		try {
			Parent HotelList = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/HotelBrowse.fxml"));
			HotelList.getProperties().put("NAME", "HotelListPane");
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(HotelList, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@FXML
	protected void goVIPRegister(ActionEvent e){
		try {
			Parent Register = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/Register.fxml"));
			Register.getProperties().put("NAME", "VIPRegisterPane");
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(Register, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	@FXML
	protected void goPersonInfo(ActionEvent e){
		try {
			FXMLLoader loader =new FXMLLoader(getClass().getClassLoader().getResource("Presentation/MemberUI/KeepPersonInfo.fxml"));
			Parent PersonInfo=loader.load();
			KeepPersonInfoController controller=loader.getController();
			controller.setClientMainUIController(this);
//			Parent PersonInfo = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/KeepPersonInfo.fxml"));
			PersonInfo.getProperties().put("NAME", "PersonInfoPane");
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(PersonInfo, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	@FXML
	protected void goBrowseOrderList(ActionEvent e){
		try {
			Parent BrowseOrder = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/ClientBrowseOrderListUI.fxml"));
			BrowseOrder.getProperties().put("NAME", "BrowseOrderPane");
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(BrowseOrder, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	public void setStateLabel(String state){
		stateLabel.setText(state);
	}
	
    public void setBaseInfo(){
    	this.userid=(long)DataController.getInstance().get("UserId");
    }
    
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
		DataController.getInstance().put("UserId", userid);
	}
}
