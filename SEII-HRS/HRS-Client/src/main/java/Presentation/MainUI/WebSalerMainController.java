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
import javafx.scene.layout.GridPane;

public class WebSalerMainController implements Initializable{
	@FXML private GridPane webSalerMain;
	private long userId;
	private void goToPane(int i){
		String[] name = {"NewStrategyPane"
						,"CancelOrderPane"
						,"RechargeCredit"
		};
		String[] path = {"Presentation/StrategyUI/WebSalerBrowseStrategyListUI.fxml"
						,"Presentation/OrderUI/WebSalerCancelOrder.fxml"
						,"Presentation/CreditUI/RechargeCredit.fxml"
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
			ObservableList<Node> list = webSalerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			webSalerMain.add(pane, 3, 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML protected void goCreateNewStrategy(ActionEvent event){
		goToPane(0);
	}
	@FXML protected void goCancelOrder(ActionEvent event){
		goToPane(1);
	}
	@FXML protected void goRechargeCredit(ActionEvent event){
		goToPane(2);
	}
	private void setBaseInfo(){
		Object o = DataController.getInstance().get("UserId");
		if(o!=null)
			userId = (long) o;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
	}
	
}