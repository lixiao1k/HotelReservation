package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import Presentation.MainUI.ClientMainUIController;
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

public class HotelWorkerBrowseStrategyListController implements Initializable{
	@FXML ListView<OrderVO> orderListView;
	@FXML ChoiceBox<String> orderType;
	@FXML TextField searchText;
	@FXML GridPane mainPane;
	GridPane clientmain;
	OrderLogicService orderLogic;
	ClientMainUIController clientMainUIController;
	private String username;
	private long userid;
	@FXML 
	protected void searchInText(ActionEvent e){
		
	}
	
	//基本信息
    public void setBaseInfo(){
    	this.username=clientMainUIController.getUsername();
    	this.userid=clientMainUIController.getUserid();
    	System.out.println(username+": "+userid);
    }
    
    //撤销未执行订单界面
    public void setClientCancalOrder(){
    	try {
			Parent CancelOrder = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/ClientCancelOrder.fxml"));
			CancelOrder.getProperties().put("NAME", "ClientCancelOrderPane");
			clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
			ObservableList<Node> list = clientmain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			clientmain.add(CancelOrder, 2, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    }
    
    //初始化列表
    public void initListView() throws RemoteException{
    	OrderVO vo1=new OrderVO();
    	Room room1=new Room();
    	room1.setType("双人房");
    	vo1.setRoom(room1);
    	vo1.setOrderNum("0000001");
    	vo1.setHotelName("酱油");
    	vo1.setCheckInTime(new Date(2016, 12, 11));
    	vo1.setStatus(OrderStatus.UNEXECUTED);
    	OrderVO vo2=new OrderVO();
    	Room room2=new Room();
    	room2.setType("单人房");
    	vo2.setRoom(room2);
    	vo2.setName("冰与火2");
    	vo2.setOrderNum("0000002");
    	vo2.setHotelName("酱油2");
      	vo2.setCheckInTime(new Date(2016, 12, 12));
    	List<OrderVO> list=new ArrayList<>();
    	list.add(vo1);
    	list.add(vo2);
    	ListWrapper<OrderVO> volist=new ListWrapper<>(list);
    	ObservableList<OrderVO> olist=FXCollections.observableArrayList();
    	Iterator<OrderVO> it=volist.iterator();
    	while(it.hasNext()){
    		olist.add(it.next());
    	}
    	orderListView.setItems(olist);
    	orderListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		if(newValue.getStatus()==OrderStatus.UNEXECUTED){
    			setClientCancalOrder();
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
		//setBaseInfo();
		try {
			initListView();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
