package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.controlsfx.control.ListSelectionView;

import info.Room;
import info.ListWrapper;
import info.StrategyType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.HotelLogicService;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class BirthController implements Initializable{
	@FXML GridPane mainPane;
	@FXML TextField Name;
	@FXML TextField Off;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	HotelLogicService hotelLogic;
	ListSelectionView<Room> Room;
	
	@FXML 
	protected void Create() throws RemoteException{
		StrategyVO svo=new StrategyVO();
		svo.setName(Name.getText());
		svo.setOff(Double.valueOf(Off.getText()));
		ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
		Iterator<StrategyType> it=typelist.iterator();
		while(it.hasNext()){
			StrategyType type=it.next();
			if(type.getName().equals("Birth"))
				svo.setStrategyType(type);
				break;
		}
		svo.setExtraInfo("");
		strategyLogic.create(svo);
	}
	
	public void initlistsev(){
		Room=new ListSelectionView<>();
		Room room1=new Room(101, "µ¥ÈË´²");
		Room room2=new Room(102, "Ë«ÈË´²");
		ObservableList<Room> sourcelist=FXCollections.observableArrayList();
		sourcelist.addAll(room1,room2);
		Room.setSourceItems(sourcelist);
		mainPane.add(Room, 0, 1);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initlistsev();
	}
}
