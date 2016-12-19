package Presentation.HotelUI;

import java.net.URL;
import java.util.ResourceBundle;

import datacontroller.DataController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class DetailHotelDetailInfoController implements Initializable{
	@FXML private Text description;
	@FXML private Text facility;
	@FXML private Text service;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Object o = null;
		o = DataController.getInstance().get("description");
		if(o!=null)
			description.setText((String) o);
		o = DataController.getInstance().get("facility");
		if(o!=null)
			facility.setText((String) o);
		o = DataController.getInstance().get("service");
		if(o!=null)
			service.setText((String) o);
	}

}
