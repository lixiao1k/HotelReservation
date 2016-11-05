package presentation.BrowseUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import presentation.HotelUI.RoomInfoController;
import vo.RoomInfo;


public class BrowseRoomListController implements Initializable{
	@FXML ListView<RoomInfo> roomListView;
	@FXML ObservableList<RoomInfo> roomListViewData;
	@FXML
	public void createNewRoom(ActionEvent e){
		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("presentation/HotelUI/RoomInfo.fxml"));
			Parent newRoomInfo = loader.load();
			RoomInfoController controller = loader.getController();
			TextField num = (TextField) newRoomInfo.lookup("#roomNum");
			controller.setController(this);
			num.setEditable(false);
			Scene scene = new Scene(newRoomInfo,350,150);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log info && status
		}
		
	}
	public void updateRoom(String type,int num,int total,double price,String operation){
		if (operation.equals("CREATE")){
			RoomInfo newItem = new RoomInfo(type, num, total, price);
			roomListViewData.add(newItem);
		}
		if (operation.equals("CHANGE")){

			for (RoomInfo item:roomListViewData){
				if (item.getType().equals(type)){
					RoomInfo newItem = new RoomInfo(item.getType(),num,total,price);
					int index =roomListViewData.indexOf(item);
					roomListViewData.set(index, null);
					roomListViewData.set(index, newItem);
					break;
				}
			}
		}
	}
	public void changeRoomInfo(ActionEvent e,String roomType,String roomNum,String roomTotal,String roomPrice){
		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("presentation/HotelUI/RoomInfo.fxml"));
			Parent newRoomInfo = loader.load();
			RoomInfoController controller = loader.getController();
			controller.setController(this);
			TextField type = (TextField) newRoomInfo.lookup("#roomType");
			TextField num = (TextField) newRoomInfo.lookup("#roomNum");
			TextField price = (TextField) newRoomInfo.lookup("#roomPrice");
			TextField total = (TextField) newRoomInfo.lookup("#roomTotal");
			type.setText(roomType);
			type.setEditable(false);
			type.setFocusTraversable(false);
			num.setText(roomNum);
			total.setText(roomTotal);
			price.setText(roomPrice);
			Scene scene = new Scene(newRoomInfo,350,150);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log info && status
		}
	}
	public void deleteItem(ActionEvent e,String type){
		for (RoomInfo item:roomListViewData){
			if (item.getType().equals(type)){
				roomListViewData.remove(item);
				break;
			}
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		RoomInfo doubleBed = new RoomInfo("双人房", 53, 110, 389);
		RoomInfo bigBed = new RoomInfo("大床房", 36, 89, 413);
		RoomInfo singleBed = new RoomInfo("单人房", 46, 57, 277);
		roomListViewData = FXCollections.observableArrayList();
		roomListViewData.add(doubleBed);
		roomListViewData.add(bigBed);
		roomListViewData.add(singleBed);
		roomListView.setCellFactory(e -> new RoomListCell());
		roomListView.setItems(roomListViewData);
		
	}
	
	class RoomListCell extends ListCell<RoomInfo>{

		public void updateItem(RoomInfo item,boolean empty){
			super.updateItem(item, empty);

            if (item != null) {
                GridPane cell = new GridPane();
                Label type = new Label(item.getType());
                type.setFont(new Font("YouYuan",20));
                Label avaliableNum = new Label(item.getCurrentNum()+"/"+item.getTotal());
                avaliableNum.setFont(new Font("YouYuan",8));
                Label price = new Label(item.getPrice()+"RMB");
                price.setFont(new Font("YouYuan",13));
                Button change = new Button("change");
                change.getProperties().put("NAME", item.getType()+" "+item.getCurrentNum()+" "+item.getTotal()+" "+item.getPrice());
                change.setOnAction((ActionEvent e)->{
                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
                	changeRoomInfo(e,temp[0],temp[1],temp[2],temp[3]);
                });
                Button delete = new Button("delete");
                delete.setOnAction((ActionEvent e) ->{
                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
                	deleteItem(e,temp[0]);
                });
                cell.add(type, 0, 0);
                cell.add(avaliableNum, 0, 1);
                cell.add(price, 1, 0);
                cell.add(change, 2, 0);
                cell.add(delete, 3, 0);
                setGraphic(cell);
            } else {

                setGraphic(null);
            }
        }
	}
}
