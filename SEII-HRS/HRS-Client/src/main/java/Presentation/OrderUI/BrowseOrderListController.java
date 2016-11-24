package Presentation.OrderUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class BrowseOrderListController implements Initializable{
	@FXML ListView<OrderInfo> orderListView;
	@FXML ChoiceBox<String> orderType;
	@FXML TextField searchText;
	
	@FXML 
	protected void searchInText(ActionEvent e){
		
	}
	
	
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
