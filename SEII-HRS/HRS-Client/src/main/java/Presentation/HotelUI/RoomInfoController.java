package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RoomInfoController implements Initializable{
	FXMLLoader loader;
	Parent father;
	BrowseRoomListController controller;
	@FXML TextField roomType;
	@FXML TextField roomPrice;
	@FXML TextField roomNum;
	@FXML TextField roomTotal;
	@FXML
	protected void update(ActionEvent e){
	
		try {
			String type = roomType.getText();
			double price = Double.parseDouble(roomPrice.getText());
			int num = Integer.parseInt(roomNum.getText());
			int total = Integer.parseInt(roomTotal.getText());
			if (roomType.isEditable()) 
				controller.updateRoom(type, num,total, price, "CREATE");
			else 
				controller.updateRoom(type, num,total, price, "CHANGE");
			
		} catch(NumberFormatException e1){
			Stage stage = new Stage();
		}
		Stage stage = (Stage)roomType.getScene().getWindow();
		stage.close();
	}
	@FXML 
	protected void cancel(ActionEvent e){
		Stage stage = (Stage) roomNum.getScene().getWindow();
		stage.close();
	}
	public void setController(BrowseRoomListController controller){
		this.controller = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
