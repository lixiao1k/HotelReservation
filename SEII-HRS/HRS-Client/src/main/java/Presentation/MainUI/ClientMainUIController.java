package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ClientMainUIController implements Initializable{
	@FXML Button goHotelListButton;
	@FXML Button goVIPRegisterButton;
	@FXML Button goPersonInfoButton;
	@FXML Button goBrowseOrderListButton;
	@FXML GridPane clientmain;
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
			// log 日志&&状态栏
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
			// log 日志&&状态栏
			e1.printStackTrace();
		}
	}
	@FXML
	protected void goPersonInfo(ActionEvent e){
		try {
			Parent PersonInfo = FXMLLoader.load(getClass().getResource("MemberUI/Keeppersoninfo.fxml"));
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
			// log 日志&&状态栏
			e1.printStackTrace();
		}
		
	}
	@FXML
	protected void goBrowseOrderList(ActionEvent e){
		try {
			Parent PersonInfo = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/BrowseOrderListUI.fxml"));
			PersonInfo.getProperties().put("NAME", "BrowseOrderPane");
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
			// log 日志&&状态栏
			e1.printStackTrace();
		}
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
