package Presentation.OrderUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import Presentation.MainUI.ClientMainUIController;
import datacontroller.DataController;
import info.ListWrapper;
import info.OrderStatus;
import info.Room;
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
import logic.service.OrderLogicService;
import rmi.RemoteHelper;
import vo.OrderVO;

public class HotelWorkerBrowseOrderListController implements Initializable{
	@FXML ListView<OrderVO> orderListView;
	@FXML ChoiceBox<String> orderType;
	@FXML TextField searchText;
	@FXML GridPane mainPane;
	GridPane clientmain;
	OrderLogicService orderLogic;
	private long hotelid;
	@FXML 
	protected void searchInText(ActionEvent e){
		
	}
	
	//基本信息
    public void setBaseInfo(){
    	this.hotelid=(long)DataController.getInstance().get("HotelId");
    }
    
    //补登记订单界面
    public void setHotelWorkerIssueOrder(){
    	try {
			Parent IssueOrder = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/HotelWorkerIssueOrder.fxml"));
			IssueOrder.getProperties().put("NAME", "IssueOrder");
			clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(IssueOrder, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    //正常执行订单界面
    public void setHotelWorkerExecuteOrder(){
    	try {
			Parent ExecuteOrder = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/HotelWorkerExecuteOrder.fxml"));
			ExecuteOrder.getProperties().put("NAME", "ExecuteOrder");
			clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Order")||value.contains("Pane")||value.contains("List")||value.contains("Info"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(ExecuteOrder, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    //初始化列表
    public void initListView() throws RemoteException{
    	ListWrapper<OrderVO> volist=orderLogic.getHotelOrderInfo(hotelid);
    	ObservableList<OrderVO> olist=FXCollections.observableArrayList();
    	Iterator<OrderVO> it=volist.iterator();
    	while(it.hasNext()){
    		olist.add(it.next());
    	}
    	orderListView.setItems(olist);
    	orderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if(newValue.getStatus()==OrderStatus.ABNORMAL){
    			DataController.getInstance().put("OrderVO", newValue);
    			setHotelWorkerIssueOrder();
    		}else if(newValue.getStatus()==OrderStatus.UNEXECUTED){
    			DataController.getInstance().put("OrderVO", newValue);
    			setHotelWorkerExecuteOrder();
    		}
        });
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			orderLogic=RemoteHelper.getInstance().getServiceFactory().getOrderLogicService();
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
