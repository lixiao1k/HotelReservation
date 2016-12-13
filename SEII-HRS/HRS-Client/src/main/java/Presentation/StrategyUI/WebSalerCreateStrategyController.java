package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class WebSalerCreateStrategyController implements Initializable{
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Circle;
	@FXML RadioButton CircleRadio;
	@FXML RadioButton TimeRadio;
	ToggleGroup group;
	@FXML TextField NameLine;
	@FXML TextField TimeLine1;
	@FXML TextField TimeLine2;
	@FXML TextField OffLine;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	@FXML 
	protected void createStrategy(ActionEvent e){
		StrategyVO svo=new StrategyVO();
		svo.setName(NameLine.getText());
		svo.setOff(Double.valueOf(OffLine.getText()));
		if(TimeRadio.isSelected()){
			svo.setExtraInfo(TimeLine1.getText()+"|"+TimeLine2.getText());
		}
		if(CircleRadio.isSelected()){
			svo.setExtraInfo(Circle.getSelectionModel().getSelectedItem());
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
			Parent NewStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/WebSalerBrowseStrategyListUI.fxml"));
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
		group=new ToggleGroup();
		CircleRadio.setToggleGroup(group);
		TimeRadio.setToggleGroup(group);
	}
}
