package presentation.BrowseUI;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import presentation.OrderUI.OrderRoomInfo;
import vo.OrderStatus;

public class BrowseOrderListController implements Initializable{
	@FXML ListView<OrderInfo> orderListView;
	@FXML ChoiceBox<String> orderType;
	@FXML TextField searchText;
	ObservableList<OrderInfo> orderListViewData;
	String[] itemValues = {"UNEXECUTED","EXECUTED","ABNORMAL","REVOKED"};
	ObservableList<String> typeStr = FXCollections.observableArrayList("未执行订单","已执行订单","异常订单","已撤销订单");
	List<Boolean> isOrderListViewDataSearched;
	@FXML
	protected void searchInText(ActionEvent e){
		Collections.fill(isOrderListViewDataSearched, false);
		String searchStr = searchText.getText();
		long id = Long.parseLong(searchStr);
		int size = orderListViewData.size();
		//记得以后评审把所有size()去掉！
		for (int i=0;i<size;i++){
			if (orderListViewData.get(i).getOrderID()==id){
				isOrderListViewDataSearched.set(i, true);
				
			}
		}
		orderListView.setItems(null);
		orderListView.setItems(orderListViewData);
	}
	private void execute(ActionEvent e,long id){
		for (OrderInfo oinfo:orderListViewData){
			if (oinfo.getOrderID()==id){
				int index = orderListViewData.indexOf(oinfo);
				OrderInfo newItem = new OrderInfo(oinfo.beginTime, oinfo.endTime, oinfo.people, oinfo.child, oinfo.orderRoom);
				newItem.orderID = oinfo.orderID;
				newItem.setOrderStatus(OrderStatus.EXECUTED);
				orderListViewData.set(index, null);
				orderListViewData.set(index, newItem);
			}
		}
		if (orderType.getSelectionModel().getSelectedItem()!=null)
		searchInChoice(itemValues[typeStr.indexOf(orderType.getSelectionModel().getSelectedItem())]);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//test data
		List<OrderRoomInfo> orderR = new ArrayList<OrderRoomInfo>();
		isOrderListViewDataSearched = new ArrayList<Boolean>();
		orderR.add(new OrderRoomInfo("双人房",1,289));
		orderR.add(new OrderRoomInfo("单人房", 2, 356));
		OrderInfo oinfo = new OrderInfo(LocalDate.now(), LocalDate.now().minusDays(2), 4, false, orderR);
		OrderInfo oinfo2 = new OrderInfo(LocalDate.now(), LocalDate.now().minusDays(10), 4, false, orderR);
		orderListViewData = FXCollections.observableArrayList();
		orderListViewData.add(oinfo);
		orderListViewData.add(oinfo2);
		isOrderListViewDataSearched.add(false);
		isOrderListViewDataSearched.add(false);
		orderListView.setCellFactory(e -> new OrderListCell());
		orderListView.setItems(orderListViewData);
		
		orderType.setItems(typeStr);

		orderType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String value = itemValues[orderType.getItems().indexOf(newValue)];
				searchInChoice(value);
			}
		});
	}
	protected void searchInChoice(String newValue) {
		Collections.fill(isOrderListViewDataSearched, false);
		int size = orderListViewData.size();
		for (int i=0;i<size;i++){
			if (orderListViewData.get(i).getStatus().toString().equals(newValue)){
				isOrderListViewDataSearched.set(i, true);
			}
		}
		orderListView.setItems(null);
		orderListView.setItems(orderListViewData);
	}
	class OrderListCell extends ListCell<OrderInfo>{
		public void updateItem(OrderInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				GridPane cell = new GridPane();
				Label id = new Label(item.getOrderID()+"");
				id.setFont(new Font("YouYuan",15));
				Label time = new Label(item.getBeginTime().toString()+" - "+item.getEndTime().toString());
				time.setFont(new Font("YouYuan",7));
				Label status = new Label(item.getStatus().toString());
				status.setFont(new Font("YouYuan",10));
				Label people = new Label();
				people.setText(item.getPeople()+" "+((item.isHavechild())? "有儿童":"无儿童"));
				people.setFont(new Font("YouYuan",10));
				Label price = new Label(item.getPrice()+"");
				price.setFont(new Font("YouYuan",10));
				TitledPane room = new TitledPane();
				ListView<OrderRoomInfo> content = new ListView<OrderRoomInfo>();
				ObservableList<OrderRoomInfo> contentData = FXCollections.observableArrayList();
				contentData.addAll(item.getOrderRoomList());
				content.setCellFactory(e -> new OrderRoomListCell());
				content.setItems(contentData);
				room.setText("房间信息");
				room.setContent(content);
				room.setExpanded(false);
				Button execute = new Button("execute");
				execute.setOnAction((ActionEvent e) -> {
					execute(e, item.getOrderID());
				});
				Button issue = new Button("issue");
				issue.setOnAction((ActionEvent e)->{
					execute(e, item.getOrderID());
				});
				cell.add(id, 0, 0,1,2);
				cell.add(time, 0, 2);
				cell.add(status, 1, 0);
				cell.add(people, 1, 1);
				cell.add(price, 1, 2);
				cell.add(room, 2, 0,1,2);
				cell.add(execute, 3, 0);
				cell.add(issue, 4, 0);
				if (isOrderListViewDataSearched.get(orderListViewData.indexOf(item))){
					setStyle("-fx-background-color:#FF8000");
				}else{
					setStyle(null);
				}
				setGraphic(cell);
			}
			else {
				setGraphic(null);
			}
		}
	}
	
	class OrderRoomListCell extends ListCell<OrderRoomInfo>{
		public void updateItem(OrderRoomInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				GridPane cell = new GridPane();
				Label type = new Label(item.getType());
				type.setFont(new Font("YouYuan",15));
				Label num = new Label(item.getNum()+"间");
				num.setFont(new Font("YouYuan",15));
				Label price = new Label(item.getPrice()+"RMB");
				price.setFont(new Font("YouYuan",15));
				cell.add(type, 0, 0);
				cell.add(price, 2, 0);
				cell.add(num, 1, 0);
				setGraphic(cell);
			}
			else {
				setGraphic(null);
			}
		}
	}

}
