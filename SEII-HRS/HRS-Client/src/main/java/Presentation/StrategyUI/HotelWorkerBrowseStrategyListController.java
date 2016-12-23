package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import info.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import rmi.RemoteHelper;
import vo.HotelStrategyVO;
import vo.StrategyVO;

public class HotelWorkerBrowseStrategyListController implements Initializable{
	@FXML ListView<StrategyVO> strategyListView;
	@FXML TextField searchText;
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Type;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	ObservableList<StrategyVO> olist;
	ObservableList<StrategyVO> selist;
	private long hotelid;
	@FXML 
	protected void searchInText(ActionEvent e){
		if(selist==null){
			selist=FXCollections.observableArrayList();
			String key=searchText.getText();
			if(key.equals("")){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("搜索策略").text("搜索失败！请输入关键字！").showWarning();
			}
			for(StrategyVO vo:olist){
				if(vo.getName().contains(key)){
					selist.add(vo);
				}
			}
		   	strategyListView.setItems(selist);
		}else{
			selist.clear();
			String key=searchText.getText();
			if(key.equals("")){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("搜索策略").text("搜索失败！请输入关键字！").showWarning();
			}
			for(StrategyVO vo:olist){
				if(vo.getName().contains(key)){
					selist.add(vo);
				}
			}
		   	strategyListView.setItems(selist);
		}
	}
	
	public void delete(StrategyVO vo, ActionEvent e){
		try {
			StrategyResultMessage m=strategyLogic.delete(vo.getId());
			if(m==StrategyResultMessage.SUCCESS){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("删除策略").text("删除成功！").showConfirm();
			}
			if(m==StrategyResultMessage.FAIL_WRONGID){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("删除策略").text("删除失败！不存在此策略！").showWarning();
			}
			if(m==StrategyResultMessage.FAIL_WRONG){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("删除策略").text("删除失败！未知错误！").showWarning();
			}
		} catch (Exception e1) {
			Notifications.create().owner(mainPane.getScene().getWindow()).title("删除策略").text("删除失败！未知错误！").showWarning();
			e1.printStackTrace();
		}
	}
	
	class StrategyCell extends ListCell<StrategyVO>{
		public void updateItem(StrategyVO item, boolean empty) {
			super.updateItem(item, empty);
			if(item!=null){
				GridPane gridPane=new GridPane();
				Label Info=new Label();
				Info.setText(item.toString());
				Button Del=new Button("删除");
				Del.setOnAction((ActionEvent e)->{
					delete(item,e);
				});
				gridPane.add(Info, 0, 0);
				gridPane.add(Del, 2, 0,1, 2);
				gridPane.setHalignment(Del, HPos.RIGHT);
				gridPane.setHgrow(Del, Priority.ALWAYS);
				gridPane.setMargin(Del, new Insets(2,10,2,0));
				setGraphic(gridPane);
			}else{
				setGraphic(null);
			}  
		}
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
    	hotelid=(long)DataController.getInstance().get("HotelId");
    	DataController.getInstance().putAndUpdate("HotelId", hotelid);
    }
    
    //初始化类型
    public void initType(){
		ObservableList<String> typelist=FXCollections.observableArrayList();
		typelist.addAll("生日优惠策略","房间预订优惠策略","合作企业优惠策略","节日优惠策略");
		Type.setItems(typelist);
    }
    
    //类型搜索
    public void swift(int i){
		String name[]={
				"BirthStrategy",
				"RoomStrategy",
				"CompanyStrategy",
				"HotelFestivalStrategy"};
		if(selist==null){
			selist=FXCollections.observableArrayList();
			for(StrategyVO vo:olist){
				if(vo.getType().getName().equals(name[i])){
					selist.add(vo);
				}
			}
			strategyListView.setItems(selist);
		}else{
			selist.clear();
			for(StrategyVO vo:olist){
				if(vo.getType().getName().equals(name[i])){
					selist.add(vo);
				}
			}
			strategyListView.setItems(selist);
		}
    }
    
    //增加监听
	public void addchoiceboxlistener(){
		Type.getSelectionModel().selectedItemProperty().addListener((ov,oldvalue,newvalue)->{
			if(newvalue.equals("生日优惠策略")){
				swift(0);
			}
			if(newvalue.equals("房间预订优惠策略")){
				swift(1);
			}
			if(newvalue.equals("合作企业优惠策略")){
				swift(2);
			}
			if(newvalue.equals("节日优惠策略")){
				swift(3);
			}
        });
	}
    
    //初始化列表
    public void initListView() throws RemoteException{
    	ListWrapper<HotelStrategyVO> volist=strategyLogic.getStrategyList(hotelid);
    	olist=FXCollections.observableArrayList();
    	Iterator<HotelStrategyVO> it=volist.iterator();
    	while(it.hasNext()){
    		HotelStrategyVO hsvo=it.next();
    		StrategyVO svo=new StrategyVO();
    		svo.setExtraInfo(hsvo.getExtraInfo());
    		svo.setHotelId(hsvo.getHotelId());
    		svo.setId(hsvo.getId());
    		svo.setItems(hsvo.getItems());
    		svo.setName(hsvo.getName());
    		svo.setOff(hsvo.getOff());
    		svo.setStrategyType(hsvo.getType());
    		olist.add(svo);
    	}
    	strategyListView.setItems(olist);
    	strategyListView.setCellFactory(e -> new StrategyCell());
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		setBaseInfo();
		initType();
		addchoiceboxlistener();
		try {
			initListView();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
