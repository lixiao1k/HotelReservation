package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.ListSelectionView;

import datacontroller.DataController;
import info.ListWrapper;
import info.Room;
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
import vo.HotelItemVO;
import vo.StrategyItemVO;
import vo.StrategyVO;

public class RoomController implements Initializable{
	@FXML GridPane mainPane;
	@FXML TextField Name;
	@FXML TextField Off;
	@FXML TextField RoomNum;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	HotelLogicService hotelLogic;
	ListSelectionView<Room> Room;
	long hotelid;
	
	@FXML 
	protected void Create() throws RemoteException{
		StrategyVO svo=new StrategyVO();
		svo.setName(Name.getText());
		svo.setHotelId(hotelid);
		double off=Double.valueOf(Off.getText());
		svo.setOff(off);
		ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
		Iterator<StrategyType> it=typelist.iterator();
		while(it.hasNext()){
			StrategyType type=it.next();
			if(type.getName().equals("Room"))
				svo.setStrategyType(type);
				break;
		}
		svo.setExtraInfo(RoomNum.getText());
		ListWrapper<HotelItemVO> volist=hotelLogic.getRoomInfo(hotelid);
		Set<StrategyItemVO> voset=new HashSet<>();
		ObservableList<Room> targetlist=Room.getTargetItems();
		for(Room room:targetlist){
			Iterator<HotelItemVO> itt=volist.iterator();
			while(itt.hasNext()){
				HotelItemVO hvo=itt.next();
				if(hvo.getRoom().getRid()==room.getRid()){
					StrategyItemVO sivo=new StrategyItemVO();
					sivo.setRoom(room);
					sivo.setPriceBefore(hvo.getPrice());
					sivo.setOff(off);
					sivo.setPriceAfter(hvo.getPrice()*(1-off));
					voset.add(sivo);
				}
			}
		}
		svo.setItems(voset);
		strategyLogic.create(svo);
	}
	
	public void initlistsev() throws RemoteException{
		Room=new ListSelectionView<>();
		ObservableList<Room> sourcelist=FXCollections.observableArrayList();
		ListWrapper<HotelItemVO> volist=hotelLogic.getRoomInfo(hotelid);
		Iterator<HotelItemVO> it=volist.iterator();
		while(it.hasNext()){
			Room room=it.next().getRoom();
			sourcelist.add(room);
		}
		Room.setSourceItems(sourcelist);
		mainPane.add(Room, 0, 1);
	}
	
	public void setBaseInfo(){
		hotelid=(long)DataController.getInstance().get("HotelId");
		DataController.getInstance().put("HotelId", hotelid);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
			hotelLogic=RemoteHelper.getInstance().getServiceFactory().getHotelLogicService();
			initlistsev();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
