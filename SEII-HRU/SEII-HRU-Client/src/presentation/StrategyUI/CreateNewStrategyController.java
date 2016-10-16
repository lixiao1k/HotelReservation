package presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class CreateNewStrategyController implements Initializable{
	@FXML private ChoiceBox<String> strategyTypeChoice;
	@FXML private GridPane createStratrgyPane;
	@FXML
	public void cancel(){
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		strategyTypeChoice.getSelectionModel().selectFirst();
		strategyTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				ObservableList<Node> list = createStratrgyPane.getChildren();
				String oldkey = null,newNode=null,newkey=null;
				if(oldValue!=null){
					switch(oldValue){
						case "节日促销策略" : oldkey="Festival";
										  break;
						case "生日促销策略" : oldkey="Birthday";
										  break;
						case "合作企业促销策略" : oldkey="Company";
											break;
						case "房间预订促销策略" : oldkey="Room";
											break;
						case "VIP商圈促销策略" : oldkey="VIP";
											break;
					}
				}
				
				if(newValue!=null){
					switch(newValue){
						case "节日促销策略" : newNode="FestivalStrategyForm.fxml";
										  newkey="Festival";
										  break;
						case "生日促销策略" : newNode="BirthdayStrategyForm.fxml";
										  newkey="Birthday";
										  break;
						case "合作企业促销策略" : newNode="CompanyStrategyForm.fxml";
											newkey="Company";
											break;
						case "房间预订促销策略" : newNode="RoomPrebookStrategyForm.fxml";
											newkey="Room";
											break;
						case "VIP商圈促销策略" : newNode="VIPTradeStrategyForm.fxml";
											newkey="VIP";
											break;
					}
				}
				for (Node node:list){
					String value = (String) node.getProperties().get("NAME");
					if (value!=null&&value.equals(oldkey)){
						list.remove(node);
						break;
					}
				}
				try {
					Parent newStrategyForm = FXMLLoader.load(getClass().getResource(newNode));
					newStrategyForm.getProperties().put("NAME", newkey);
					createStratrgyPane.add(newStrategyForm, 0,2,4,1);
				} catch (IOException e) {
					//日志
				}
				
			}

			
		});
	}
}
