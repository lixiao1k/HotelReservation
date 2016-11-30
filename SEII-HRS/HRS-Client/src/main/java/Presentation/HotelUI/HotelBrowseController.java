package Presentation.HotelUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HotelBrowseController implements Initializable{
	@FXML TextField searchTextField;
	@FXML Button hotelSearchButton;
	@FXML ChoiceBox<String> starChoiceBox;
	@FXML ChoiceBox<String> gradeRangeChoiceBox;
	@FXML ChoiceBox<String> roomChoiceBox;
	@FXML ListView<HotelInfo> listView;
	
	@FXML
	protected void searchHotel(ActionEvent e){
		
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
