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
				Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("����ʧ�ܣ�������ؼ��֣�").showWarning();
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
				Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("����ʧ�ܣ�������ؼ��֣�").showWarning();
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
				Notifications.create().owner(mainPane.getScene().getWindow()).title("ɾ������").text("ɾ���ɹ���").showConfirm();
			}
			if(m==StrategyResultMessage.FAIL_WRONGID){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("ɾ������").text("ɾ��ʧ�ܣ������ڴ˲��ԣ�").showWarning();
			}
			if(m==StrategyResultMessage.FAIL_WRONG){
				Notifications.create().owner(mainPane.getScene().getWindow()).title("ɾ������").text("ɾ��ʧ�ܣ�δ֪����").showWarning();
			}
		} catch (Exception e1) {
			Notifications.create().owner(mainPane.getScene().getWindow()).title("ɾ������").text("ɾ��ʧ�ܣ�δ֪����").showWarning();
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
				Button Del=new Button("ɾ��");
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
	
	//������Ϣ
    public void setBaseInfo(){
    	hotelid=(long)DataController.getInstance().get("HotelId");
    	DataController.getInstance().putAndUpdate("HotelId", hotelid);
    }
    
    //��ʼ������
    public void initType(){
		ObservableList<String> typelist=FXCollections.observableArrayList();
		typelist.addAll("�����Żݲ���","����Ԥ���Żݲ���","������ҵ�Żݲ���","�����Żݲ���");
		Type.setItems(typelist);
    }
    
    //��������
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
    
    //���Ӽ���
	public void addchoiceboxlistener(){
		Type.getSelectionModel().selectedItemProperty().addListener((ov,oldvalue,newvalue)->{
			if(newvalue.equals("�����Żݲ���")){
				swift(0);
			}
			if(newvalue.equals("����Ԥ���Żݲ���")){
				swift(1);
			}
			if(newvalue.equals("������ҵ�Żݲ���")){
				swift(2);
			}
			if(newvalue.equals("�����Żݲ���")){
				swift(3);
			}
        });
	}
    
    //��ʼ���б�
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
