package Presentation.OrderUI;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import info.OrderStatus;
import info.Room;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vo.OrderVO;

public class WebSalerCancelOrderController implements Initializable{
	@FXML GridPane WebSalerCancelPane;
	@FXML Button searchButton;
	@FXML Button submitButton;
	@FXML Label InfoLabel;
	@FXML TextField searchText;
	
	@FXML 
	protected void search(ActionEvent e){
    	OrderVO vo1=new OrderVO();
    	Room room1=new Room();
    	room1.setType("Ë«ÈË·¿");
    	vo1.setRoom(room1);
    	vo1.setOrderNum("0000001");
    	vo1.setHotelName("½´ÓÍ");
    	vo1.setCheckInTime(new Date(2016, 12, 11));
    	vo1.setStatus(OrderStatus.UNEXECUTED);
    	InfoLabel.setText(vo1.toDetailedString());
	}
	
	@FXML 
	protected void submit(ActionEvent e){
		
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
