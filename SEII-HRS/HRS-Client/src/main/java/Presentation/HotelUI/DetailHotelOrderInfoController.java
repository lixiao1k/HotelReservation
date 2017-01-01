package Presentation.HotelUI;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Presentation.OrderUI.HotelOrderInfoClientListCell;
import datacontroller.DataController;
import info.OrderStatus;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import vo.OrderVO;

public class DetailHotelOrderInfoController implements Initializable{
	@FXML private ChoiceBox<String> orderType;
	@FXML private ListView<OrderVO> orderListView;
	private ObservableList<OrderVO> orderData;
	private String[] otypes={"全部订单","未执行订单","异常订单","已执行订单","已撤销订单"};
	private Map<String,OrderStatus> otypeMap;

	public void searchInChoice(String value){
		if(orderData==null)
			return;
		if(value==null)
			return;
		else{
			if(value.equals("全部订单")){
				orderListView.setItems(orderData);
				return;
			}
			OrderStatus status = otypeMap.get(value);
			ObservableList<OrderVO> slist = FXCollections.observableArrayList();
			for(OrderVO vo:orderData){
				if(vo.getStatus()==status)
					slist.add(vo);
			}
			orderListView.setItems(slist);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		Object o = null;
		o = DataController.getInstance().get("OrderData");
		if(o==null){
			return;
		}
		orderData = (ObservableList<OrderVO>)o;
		orderListView.setItems(orderData);
		orderListView.setCellFactory(e->new HotelOrderInfoClientListCell());
    	ObservableList<String> t = FXCollections.observableArrayList(otypes);
    	orderType.setItems(t);
    	orderType.setValue("全部订单");
    	otypeMap = new HashMap<>();
    	otypeMap.put("未执行订单", OrderStatus.UNEXECUTED);
    	otypeMap.put("异常订单", OrderStatus.ABNORMAL);
    	otypeMap.put("已执行订单", OrderStatus.EXECUTED);
    	otypeMap.put("已撤销订单", OrderStatus.REVOKED);
    	orderType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchInChoice(newValue);
			}
		});
	}

}
