package Presentation.OrderUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ClientCancelOrderController implements Initializable{
	@FXML GridPane ClientCancelPane;
	@FXML Label InfoLabel;
	@FXML Button CancelButton;
	
	@FXML 
	protected void CancelOrder(ActionEvent e){
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		InfoLabel.setText("1111\n11111");
	}
}
