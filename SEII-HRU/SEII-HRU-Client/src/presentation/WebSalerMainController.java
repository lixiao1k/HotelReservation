package presentation;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class WebSalerMainController {
	@FXML private GridPane webSalerMain;
	@FXML protected void goCreateNewStrategy(ActionEvent event){
		try {
			Parent NewStrategy = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseStrategyListUI.fxml"));
			NewStrategy.getProperties().put("NAME", "browseStrategyList");
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("List"))){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(NewStrategy, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
	@FXML protected void goCancelOrder(ActionEvent event){
		try {
			Parent CancelOrder = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseAbnormalOrderListUI.fxml"));
			CancelOrder.getProperties().put("NAME", "browseAbnormalOrderList");
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("List"))){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(CancelOrder, 2, 1);
		} catch (IOException e) {
			// log 日志&&状态栏
		}
		
	}
	
}