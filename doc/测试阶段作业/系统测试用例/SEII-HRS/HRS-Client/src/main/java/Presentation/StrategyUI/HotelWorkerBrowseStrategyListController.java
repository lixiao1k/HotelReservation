package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import datacontroller.DataController;
import info.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class HotelWorkerBrowseStrategyListController implements Initializable{
	@FXML ListView<StrategyVO> strategyListView;
	@FXML ChoiceBox<String> strategyType;
	@FXML TextField searchText;
	@FXML GridPane mainPane;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	private long hotelid;
	@FXML 
	protected void searchInText(ActionEvent e){
		
	}
	
	@FXML 
	protected void addStrategy(ActionEvent e){
    	try {
			Parent addStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/HotelWorkerCreateStrategy.fxml"));
			addStrategy.getProperties().put("NAME", "addStrategyPane");
			clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(addStrategy, 3, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//基本信息
    public void setBaseInfo(){
    	DataController.getInstance().put("HotelId", (long)1);
    	hotelid=(long)DataController.getInstance().get("HotelId");
    	DataController.getInstance().put("HotelId", hotelid);
    }
    
    //初始化列表
    public void initListView() throws RemoteException{
    	List<StrategyVO> list=new ArrayList<>();
    	StrategyVO vo1=new StrategyVO();
    	vo1.setName("双十一优惠");
    	vo1.setHotelId(1);
    	vo1.setOff(0.3);
    	list.add(vo1);
    	StrategyVO vo2=new StrategyVO();
    	vo2.setName("双十二优惠");
    	vo2.setHotelId(2);
    	vo2.setOff(0.2);
    	list.add(vo2);
    	ListWrapper<StrategyVO> volist=new ListWrapper<>(list);
    	ObservableList<StrategyVO> olist=FXCollections.observableArrayList();
    	Iterator<StrategyVO> it=volist.iterator();
    	while(it.hasNext()){
    		olist.add(it.next());
    	}
    	strategyListView.setItems(olist);
    	strategyListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		
        });
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		setBaseInfo();
		try {
			initListView();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
