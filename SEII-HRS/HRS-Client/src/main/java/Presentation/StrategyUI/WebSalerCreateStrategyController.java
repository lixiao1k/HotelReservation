package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;

public class WebSalerCreateStrategyController implements Initializable{
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Circle;
	@FXML TextField TimeLine1;
	@FXML TextField TimeLine2;
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
