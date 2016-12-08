package Presentation.HotelUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;

public class SetHotelInfoController implements Initializable{
	private ServiceFactory serviceFactory;
	@FXML
	private ImageView image;
	@FXML
	protected void commit(ActionEvent e){
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();

	}
}
