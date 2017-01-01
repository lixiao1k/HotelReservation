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
	private String[] otypes={"ȫ������","δִ�ж���","��ִ�ж���","�쳣����","�ѳ�������"};
	private Map<String,OrderStatus> otypeMap;

	public void searchInChoice(String value){
		if(orderData==null)
			return;
		if(value==null)
			return;
		else{
			if(value.equals("ȫ������")){
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
    	orderType.setValue("ȫ������");
    	otypeMap = new HashMap<>();
    	otypeMap.put("δִ�ж���", OrderStatus.UNEXECUTED);
    	otypeMap.put("�쳣����", OrderStatus.ABNORMAL);
    	otypeMap.put("��ִ�ж���", OrderStatus.EXECUTED);
    	otypeMap.put("�ѳ�������", OrderStatus.REVOKED);
    	orderType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchInChoice(newValue);
			}
		});
	}

}
