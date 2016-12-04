package Presentation.UserUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ManageUserController implements Initializable{
	@FXML private ChoiceBox<String> ManageTypeChoice;
	@FXML private GridPane ManagePane;

				  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<String> list = new ArrayList<String>();
		list.add("客户");
		list.add("酒店工作人员");
		list.add("网站营销人员");
		ManageTypeChoice.setItems(FXCollections.observableArrayList(list));
		ManageTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<Node> list = ManagePane.getChildren();
				String newNode=null;
			//	String newNode=null;			
				if(newValue!=null){
					switch(newValue){
						case "客户" : 
							newNode="ManageClient1.fxml";
							break;
						case "酒店工作人员" : 
							newNode="ManageHotelWorker.fxml";
							break;
						case "网站营销人员" : 
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
					FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Presentation/UserUI/"+newNode));
					Parent newManage = loader.load();
					newManage.getProperties().put("NAME", newValue);
					ManagePane.add(newManage, 0,1,3,1);
				} catch (IOException e) {
					//日志
					System.out.println(e.getCause()+e.getMessage());
				}	
			}		
		});
	}
	


}
