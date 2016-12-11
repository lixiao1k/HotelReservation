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
			Parent hotelInfo = null;
			Object o = DataController.getInstance().get("HotelInfoPane");
			if(o==null){
				hotelInfo = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/SetHotelInfo.fxml"));
				hotelInfo.getProperties().put("NAME", "HotelInfoPane");
				DataController.getInstance().put("HotelInfoPane", hotelInfo);
			}
			else 
				hotelInfo = (Parent) o;
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(hotelInfo, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML protected void goOrderList(ActionEvent event){
		try {
			Parent browseOrderList = null;
			Object o = DataController.getInstance().get("BrowseOrderListPane");
			if(o==null){
				browseOrderList = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/HotelWorkerBrowseOrderListUI.fxml"));
				browseOrderList.getProperties().put("NAME", "BrowseOrderListPane");
				DataController.getInstance().put("BrowseOrderListPane", browseOrderList);
			}
			else 
				browseOrderList = (Parent) o;
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseOrderList, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML protected void goRoomList(ActionEvent event){
		try {
			Parent browseRoomList = null;
			Object o = DataController.getInstance().get("BrowseRoomListPane");
			if(o==null){
				browseRoomList = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/BrowseRoomListUI.fxml"));
				browseRoomList.getProperties().put("NAME", "BrowseRoomListPane");
				DataController.getInstance().put("BrowseRoomListPane", browseRoomList);
			}
			else 
				browseRoomList = (Parent) o;
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseRoomList, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	@FXML protected void goStrategyList(ActionEvent event){
		try {
			Parent browseStrategyList = null;
			Object o = DataController.getInstance().get("BrowseStrategyListPane");
			if(o==null){
				browseStrategyList = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/HotelWorkerBrowseStrategyListUI.fxml"));
				browseStrategyList.getProperties().put("NAME", "BrowseStrategyListPane");
				DataController.getInstance().put("BrowseStrategyListPane", browseStrategyList);
			}
			else 
				browseStrategyList = (Parent) o;
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseStrategyList, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//goStrategyListButton.getStyleClass().add("main-pane-button");
	}
	
	//基本信息
    public void setBaseInfo(){
    	DataController.getInstance().put("HotelId", (long)1);
    	this.hotelid=(long)DataController.getInstance().get("HotelId");
    	DataController.getInstance().put("HotelId", hotelid);
    }
	
	public void initialize(URL location, ResourceBundle resources) {
		
		setBaseInfo();
	}
}
