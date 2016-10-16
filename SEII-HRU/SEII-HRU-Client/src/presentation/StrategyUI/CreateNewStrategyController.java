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
						case "���մ�������" : oldkey="Festival";
										  break;
						case "���մ�������" : oldkey="Birthday";
										  break;
						case "������ҵ��������" : oldkey="Company";
											break;
						case "����Ԥ����������" : oldkey="Room";
											break;
						case "VIP��Ȧ��������" : oldkey="VIP";
											break;
					}
				}
				
				if(newValue!=null){
					switch(newValue){
						case "���մ�������" : newNode="FestivalStrategyForm.fxml";
										  newkey="Festival";
										  break;
						case "���մ�������" : newNode="BirthdayStrategyForm.fxml";
										  newkey="Birthday";
										  break;
						case "������ҵ��������" : newNode="CompanyStrategyForm.fxml";
											newkey="Company";
											break;
						case "����Ԥ����������" : newNode="RoomPrebookStrategyForm.fxml";
											newkey="Room";
											break;
						case "VIP��Ȧ��������" : newNode="VIPTradeStrategyForm.fxml";
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
					//��־
				}
				
			}

			
		});
	}
}
