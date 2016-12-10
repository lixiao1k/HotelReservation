package Presentation.HotelUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class HotelBrowseController implements Initializable{
	@FXML TextField searchField;
	@FXML Button hotelSearchButton;
	@FXML ComboBox businessCityBox;
	@FXML ComboBox circleBox;
	@FXML TableColumn hotelName;
	@FXML TableColumn history;
	@FXML TableColumn rank;
	@FXML TableColumn star;
	@FXML TableColumn roomType;
	@FXML TableColumn leastPrice;
	@FXML ChoiceBox<String> roomChoiceBox;
	@FXML ListView<HotelInfo> listView;
	
	@FXML
	protected void search(ActionEvent e){
		
	}
	
	public void createOrder(ActionEvent e)
	{
		
	}
	

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
