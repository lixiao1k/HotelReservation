package presentation.UserUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class ManageUserController implements Initializable{
	@FXML private ChoiceBox<String> ManageTypeChoice;
	@FXML private GridPane ManagePane;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> list = new ArrayList<String>();
		list.add("�ͻ�");
		list.add("�Ƶ깤����Ա");
		list.add("��վӪ����Ա");
		ManageTypeChoice.setItems(FXCollections.observableArrayList(list));
		ManageTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<Node> list = ManagePane.getChildren();
				String newNode=null;			
				if(newValue!=null){
					switch(newValue){
						case "�ͻ�" : 
							newNode="ManageClient.fxml";
							break;
						case "�Ƶ깤����Ա" : 
							newNode="ManageHotelWorker.fxml";
							break;
						case "��վӪ����Ա" : 
							newNode="ManageWebSaler.fxml";
							break;
					}
				}
				for (Node node:list){
					String value = (String) node.getProperties().get("NAME");
					if (value!=null&&value.equals(oldValue)){
						list.remove(node);
						break;
					}
				}
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(newNode));
					Parent newManage = loader.load();
					newManage.getProperties().put("NAME", newValue);
					ManagePane.add(newManage, 0,1,2,1);
				} catch (IOException e) {
					//��־
					System.out.println(e.getCause()+e.getMessage());
				}	
			}		
		});
	}
}
