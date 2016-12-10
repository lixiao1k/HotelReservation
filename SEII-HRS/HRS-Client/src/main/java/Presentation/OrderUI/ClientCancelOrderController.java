package Presentation.OrderUI;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import info.OrderStatus;
import info.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import vo.OrderVO;

public class ClientCancelOrderController implements Initializable{
	@FXML GridPane ClientCancelPane;
	@FXML Label InfoLabel;
	@FXML Button CancelButton;
	private long orderid;
	@FXML 
	protected void CancelOrder(ActionEvent e){
		
	}
	
	public void setOrderInfo(){
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
	
	public void initialize(URL location, ResourceBundle resources) {
		setOrderInfo();
	}
}
