package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class WebSalerCreateStrategyController implements Initializable{
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Circle;
	@FXML TextField NameLine;
	@FXML TextField TimeLine1;
	@FXML TextField TimeLine2;
	@FXML TextField OffLine;
	StrategyLogicService strategyLogic;
	@FXML 
	protected void createStrategy(ActionEvent e){
		StrategyVO svo=new StrategyVO();
		svo.setName(NameLine.getText());
		svo.setOff(Double.valueOf(OffLine.getText()));
		if(Circle.getSelectionModel().getSelectedItem()==null){
			svo.setExtraInfo(TimeLine1.getText()+"|"+TimeLine2.getText());
		}else{
			svo.setExtraInfo(Circle.getSelectionModel().getSelectedItem());
		}
		try {
			strategyLogic.create(svo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}
	
	public void initCircle(){
		ObservableList<String> circlelist=FXCollections.observableArrayList();
		circlelist.addAll("circle1","circle2");
		Circle.setItems(circlelist);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initCircle();
	}
}
