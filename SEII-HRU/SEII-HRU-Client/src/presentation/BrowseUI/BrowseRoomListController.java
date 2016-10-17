package presentation.BrowseUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import presentation.StrategyUI.CreateNewStrategy;
import vo.RoomInfo;
import vo.RoomVO;

public class BrowseRoomListController implements Initializable{
	@FXML ListView<RoomInfo> roomListView;
	@FXML ObservableList<RoomInfo> roomListViewData;
	@FXML
	public void createNewRoom(ActionEvent e){
		Stage stage = new Stage();
		try {
			Parent newRoomInfo = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/HotelUI/RoomInfo.fxml"));
			Scene scene = new Scene(newRoomInfo,350,150);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log info && status
		}
		
	}
	public void updateRoom(String type,int num,double price,String operation){
		if (operation.equals("CREATE")){
			RoomInfo newItem = new RoomInfo(type, num, num, price);
			roomListView.getItems().add(newItem);
			roomListViewData.add(roomListViewData.size(),newItem);
			System.out.println(1);
			roomListView.layout();
			roomListView.scrollTo(roomListView.getItems().size()-1);
		}
		if (operation.equals("CHANGE")){
			System.out.println(2);
			for (RoomInfo item:roomListViewData){
				if (item.getType().equals(type)){
					item.setCurrentNum(num);
					item.setPrice(price);
					break;
				}
			}
		}
	}
	public void changeRoomInfo(ActionEvent e,String roomType,String roomNum,String roomPrice){
		Stage stage = new Stage();
		try {
			Parent newRoomInfo = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/HotelUI/RoomInfo.fxml"));
			TextField type = (TextField) newRoomInfo.lookup("#roomType");
			TextField num = (TextField) newRoomInfo.lookup("#roomNum");
			TextField price = (TextField) newRoomInfo.lookup("#roomPrice");
			type.setText(roomType);
			type.setEditable(false);
			type.setFocusTraversable(false);
			num.setText(roomNum);
			price.setText(roomPrice);
			Scene scene = new Scene(newRoomInfo,350,150);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log info && status
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RoomInfo doubleBed = new RoomInfo("双人房", 53, 110, 389);
		RoomInfo bigBed = new RoomInfo("大床房", 36, 89, 413);
		RoomInfo singleBes = new RoomInfo("单人房", 46, 57, 277);
		roomListViewData = FXCollections.observableArrayList();
		roomListViewData.add(doubleBed);
		roomListViewData.add(bigBed);
		roomListViewData.add(singleBes);
		roomListView.setCellFactory(e -> new roomListCell());
		roomListView.setItems(roomListViewData);

	}
	
	class roomListCell extends ListCell<RoomInfo>{
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
                change.getProperties().put("NAME", item.getType()+" "+item.getCurrentNum()+" "+item.getPrice());
                change.setOnAction((ActionEvent e)->{
                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
                	changeRoomInfo(e,temp[0],temp[1],temp[2]);
                });
                Button delete = new Button("delete");
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
