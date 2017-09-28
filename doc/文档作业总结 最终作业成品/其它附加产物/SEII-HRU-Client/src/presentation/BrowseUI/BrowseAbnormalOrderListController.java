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
import presentation.BrowseUI.BrowseOrderListController.OrderRoomListCell;
import presentation.OrderUI.OrderRoomInfo;
import vo.OrderStatus;

public class BrowseAbnormalOrderListController implements Initializable{
	@FXML TextField searchField;
	@FXML ListView<OrderInfo> orderListView;
	@FXML ChoiceBox<String> orderTypeChoice;
	ObservableList<OrderInfo> orderListViewData;
	List<Boolean> isOrderListViewDataSearched;
	ObservableList<String> creditRevokeData;
	ObservableList<String> typeChoiceData;
	String[] creditRevoke = {"HALF","ALL"};
	String[] typeChoice = {"ABNORMAL","REVOKED"};
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
	@FXML
	protected void searchInText(ActionEvent e){
		Collections.fill(isOrderListViewDataSearched, false);
		String searchStr = searchField.getText();
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
	};
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<OrderRoomInfo> roomList = new ArrayList<OrderRoomInfo>();
		creditRevokeData = FXCollections.observableArrayList("一半","全部");
		typeChoiceData = FXCollections.observableArrayList("异常","已撤销");
		orderTypeChoice.setItems(typeChoiceData);
		orderTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String value = typeChoice[orderTypeChoice.getItems().indexOf(newValue)];
				searchInChoice(value);
			}
			
		});
		isOrderListViewDataSearched = new ArrayList<Boolean>();
		orderListViewData = FXCollections.observableArrayList();
		OrderRoomInfo room = new OrderRoomInfo("双人房", 1, 233);
		roomList.add(room);
		OrderInfo oinfo = new OrderInfo(LocalDate.now(),LocalDate.now().plusDays(2),2,false,roomList);
		OrderInfo oinfo2 = new OrderInfo(LocalDate.now(),LocalDate.now().plusDays(2),2,false,roomList);
		oinfo.setOrderStatus(OrderStatus.ABNORMAL);
		oinfo2.setOrderStatus(OrderStatus.ABNORMAL);
		orderListViewData.add(oinfo);
		orderListViewData.add(oinfo2);
		isOrderListViewDataSearched.add(false);
		isOrderListViewDataSearched.add(false);
		orderListView.setCellFactory(e -> new AbnormalOrderListCell());
		orderListView.setItems(orderListViewData);
		//orderListView.setOnMouseClicked(e -> );
	}
	protected void revoke(ActionEvent e,OrderInfo item,String creditRevokeValue){
		//根据传入的参数恢复用户信用值
		item.setOrderStatus(OrderStatus.REVOKED);
		int index = orderListViewData.indexOf(item);
		orderListViewData.set(index,null);
		orderListViewData.set(index, item);
		if (orderTypeChoice.getSelectionModel().getSelectedItem()!=null){
			String value = typeChoice[orderTypeChoice.getItems().indexOf(orderTypeChoice.getSelectionModel().getSelectedItem())];
			searchInChoice(value);
		}
	}
	class AbnormalOrderListCell extends ListCell<OrderInfo>{
		protected void updateItem(OrderInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
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
					if (item.status==OrderStatus.ABNORMAL){
						ChoiceBox<String> creditValue = new ChoiceBox<String>();
						creditValue.setItems(creditRevokeData);
						creditValue.getSelectionModel().selectFirst();
						Button revoke = new Button("revoke");
						revoke.setOnAction((ActionEvent e)->{
							revoke(e,item,creditRevoke[creditRevokeData.indexOf(creditValue.getSelectionModel().getSelectedItem())]);
						});
						cell.add(creditValue, 3, 0);
						cell.add(revoke, 4, 0);
					}
					cell.add(id, 0, 0,1,2);
					cell.add(time, 0, 2);
					cell.add(status, 1, 0);
					cell.add(people, 1, 1);
					cell.add(price, 1, 2);
					cell.add(room, 2, 0,1,2);

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
}
