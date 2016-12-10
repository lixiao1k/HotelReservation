package Presentation.MainUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class HotelWorkerMainController implements Initializable{
	@FXML private GridPane hotelmain;
	@FXML private Button goStrategyListButton;
	private long hotelid;
	
	@FXML protected void goSetHotelInfo(ActionEvent event){
		try {
			Parent HotelInfo = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			HotelInfo.getProperties().put("NAME", "HotelInfo");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(HotelInfo, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	@FXML protected void goOrderList(ActionEvent event){
		try {
			Parent browseOrderList = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/HotelWorkerBrowseOrderListUI.fxml"));
			browseOrderList.getProperties().put("NAME", "browseOrderList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseOrderList, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	@FXML protected void goRoomList(ActionEvent event){
		try {
		
			Parent browseRoomList = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/BrowseUI/BrowseRoomListUI.fxml"));
			browseRoomList.getProperties().put("NAME", "browseRoomList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			
			hotelmain.add(browseRoomList, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
			System.out.println(e.getCause());
		}
		
	}
	@FXML protected void goStrategyList(ActionEvent event){
		try {
			Parent browseStrategyList = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseStrategyListUI.fxml"));
			browseStrategyList.getProperties().put("NAME", "browseStrategyList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseStrategyList, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
			System.out.println(e.getCause()+e.getMessage());
		}

		//goStrategyListButton.getStyleClass().add("main-pane-button");
	}
	
	//������Ϣ
    public void setBaseInfo(){
    	this.hotelid=(long)DataController.getInstance().get("HotelId");
    }
	
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
		DataController.getInstance().put("HotelId", hotelid);
	}
}
