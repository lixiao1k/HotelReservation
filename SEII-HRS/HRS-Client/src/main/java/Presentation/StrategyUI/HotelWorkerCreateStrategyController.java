package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class HotelWorkerCreateStrategyController  implements Initializable{
	@FXML GridPane mainPane;
	@FXML RadioButton BirthRadio;
	@FXML RadioButton RoomRadio;
	@FXML RadioButton TimeRadio;
	@FXML RadioButton CompanyRadio;
	ToggleGroup group;
	@FXML TextField NameLine;
	@FXML TextField TimeLine1;
	@FXML TextField TimeLine2;
	@FXML TextField RoomLine;
	@FXML TextField CompanyLine;
	@FXML TextField OffLine;
	GridPane clientmain;
	long hotelid;
	StrategyLogicService strategyLogic;
	@FXML 
	protected void createStrategy(ActionEvent e){
		StrategyVO svo=new StrategyVO();
		svo.setName(NameLine.getText());
		svo.setHotelId((long)DataController.getInstance().get("HotelId"));
		svo.setOff(Long.valueOf(OffLine.getText()));
		if(BirthRadio.isSelected()){
			svo.setExtraInfo("");
		}
		if(RoomRadio.isSelected()){
			svo.setExtraInfo(RoomLine.getText());
		}
		if(TimeRadio.isSelected()){
			svo.setExtraInfo(TimeLine1.getText()+"|"+TimeLine2.getText());
		}
		if(CompanyRadio.isSelected()){
			svo.setExtraInfo(CompanyLine.getText());
		}
		try {
			strategyLogic.create(svo);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		back();
	}
	
	public void back(){
    	try {
			Parent NewStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/HotelWorkerBrowseStrategyListUI.fxml"));
			NewStrategy.getProperties().put("NAME", "NewStrategyPane");
			clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(NewStrategy, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		group=new ToggleGroup();
		BirthRadio.setToggleGroup(group);
		RoomRadio.setToggleGroup(group);
		TimeRadio.setToggleGroup(group);
		CompanyRadio.setToggleGroup(group);
	}
}
