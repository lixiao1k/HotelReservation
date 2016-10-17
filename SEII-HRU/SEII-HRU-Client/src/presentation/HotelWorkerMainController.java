package presentation;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class HotelWorkerMainController {
	@FXML private GridPane hotelmain;
	@FXML protected void goSetHotelInfo(ActionEvent event){
		try {
			Parent HotelInfo = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			HotelInfo.getProperties().put("NAME", "HotelInfo");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("List")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(HotelInfo, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
	@FXML protected void goOrderList(ActionEvent event){
		try {
			Parent browseOrderList = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseOrderListUI.fxml"));
			browseOrderList.getProperties().put("NAME", "browseOrderList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("List")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseOrderList, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
	@FXML protected void goRoomList(ActionEvent event){
		try {
			Parent browseRoomList = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseRoomListUI.fxml"));
			browseRoomList.getProperties().put("NAME", "browseRoomList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("List")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseRoomList, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
	@FXML protected void goStrategyList(ActionEvent event){
		try {
			Parent browseStrategyList = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseStrategyListUI.fxml"));
			browseStrategyList.getProperties().put("NAME", "browseStrategyList");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("List")){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(browseStrategyList, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
}
