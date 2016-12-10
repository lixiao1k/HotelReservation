package Presentation.OrderUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import datacontroller.DataController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import logic.service.OrderLogicService;
import rmi.RemoteHelper;
import vo.OrderVO;

public class HotelWorkerExecuteOrderController implements Initializable{
	@FXML GridPane HotelWorkerExecutePane;
	@FXML Label InfoLabel;
	@FXML Button ExecuteButton;
	private OrderVO ovo;
	private OrderLogicService orderLogic;
	@FXML 
	protected void ExecuteOrder(ActionEvent e) throws NumberFormatException, RemoteException{
		orderLogic.execute(Long.valueOf(ovo.getOrderNum()));
	}
	
	public void setOrderInfo(){
    	ovo=(OrderVO)DataController.getInstance().get("OrderVO");
    	InfoLabel.setText(ovo.toDetailedString());
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		try {
			orderLogic=RemoteHelper.getInstance().getServiceFactory().getOrderLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		setOrderInfo();
	}
}
