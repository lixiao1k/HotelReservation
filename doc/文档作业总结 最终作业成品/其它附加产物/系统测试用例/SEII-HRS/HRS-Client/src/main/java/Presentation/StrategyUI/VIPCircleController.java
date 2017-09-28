package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.StrategyType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.HotelLogicService;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class VIPCircleController implements Initializable{
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Circle;
	@FXML TextField Name;
	@FXML TextField Off;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	HotelLogicService hotelLogic;
	
	@FXML 
	protected void Create() throws RemoteException{
		StrategyVO svo=new StrategyVO();
		svo.setName(Name.getText());
		svo.setOff(Double.valueOf(Off.getText()));
		ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
		Iterator<StrategyType> it=typelist.iterator();
		while(it.hasNext()){
			StrategyType type=it.next();
			if(type.getName().equals("VIPCircle"))
				svo.setStrategyType(type);
				break;
		}
		svo.setExtraInfo(Circle.getSelectionModel().getSelectedItem());
		strategyLogic.create(svo);
	}
	
	public void initcircle() throws RemoteException{
		ListWrapper<BusinessCity> citylist=hotelLogic.getCity();
		Iterator<BusinessCity> it=citylist.iterator();
		ObservableList<String> circlelist=FXCollections.observableArrayList();
		while(it.hasNext()){
			BusinessCity city=it.next();
			Set<BusinessCircle> circleset=city.getCircles();
			for(BusinessCircle circle:circleset){
				circlelist.add(circle.getName());
			}
		}
		Circle.setItems(circlelist);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
			hotelLogic=RemoteHelper.getInstance().getServiceFactory().getHotelLogicService();
			initcircle();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
