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
	@FXML private GridPane hotelmain;
	@FXML protected void goCreateNewStrategy(ActionEvent event){
		try {
			Parent NewStrategy = FXMLLoader.load(getClass().getResource("StrategyUI/CreateNewStrategy.fxml"));
			NewStrategy.getProperties().put("NAME", "NewStrategy");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Strategy")||value.contains("Order"))){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(NewStrategy, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	@FXML protected void goCancelOrder(ActionEvent event){
		try {
			Parent CancelOrder = FXMLLoader.load(getClass().getResource("OrderUI/CancelOrder.fxml"));
			CancelOrder.getProperties().put("NAME", "CancelOrder");
			ObservableList<Node> list = hotelmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Strategy")||value.contains("Order"))){
					list.remove(node);
					break;
				}
			}
			hotelmain.add(CancelOrder, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	
}