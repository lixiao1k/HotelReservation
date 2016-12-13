package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;

public class HotelWorkerCreateStrategyController  implements Initializable{
	@FXML GridPane mainPane;
	@FXML RadioButton BirthRadio;
	@FXML TextField NameLine;
	@FXML TextField TimeLine1;
	@FXML TextField TimeLine2;
	@FXML TextField RoomLine;
	@FXML TextField CompanyLine;
	@FXML TextField OffLine;
	StrategyLogicService strategyLogic;
	@FXML 
	protected void createStrategy(ActionEvent e){
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
