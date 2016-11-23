package Presentation.MainUI;

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
			Parent NewStrategy = FXMLLoader.load(getClass().getResource("StrategyUI/Strategy.fxml"));
			NewStrategy.getProperties().put("NAME", "NewStrategy");
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Strategy")||value.contains("Order")||value.contains("Credit"))){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(NewStrategy, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	@FXML protected void goCancelOrder(ActionEvent event){
		try {
			Parent CancelOrder = FXMLLoader.load(getClass().getResource("BrowseUI/BrowseAbnormalOrderListUI.fxml"));
			CancelOrder.getProperties().put("NAME", "CancelOrder");
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Strategy")||value.contains("Order")||value.contains("Credit"))){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(CancelOrder, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	@FXML protected void goRechargeCredit(ActionEvent event){
		try {
			Parent RechargeCredit = FXMLLoader.load(getClass().getResource("CreditUI/RechargeCredit.fxml"));
			RechargeCredit.getProperties().put("NAME", "CancelOrder");
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Strategy")||value.contains("Order")||value.contains("Credit"))){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(RechargeCredit, 2, 1);
		} catch (IOException e) {
			// log ��־&&״̬��
		}
		
	}
	
}