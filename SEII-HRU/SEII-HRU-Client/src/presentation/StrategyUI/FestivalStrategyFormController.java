package presentation.StrategyUI;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import vo.RoomInfo;


public class FestivalStrategyFormController extends StrategyFormController implements Initializable{
	@FXML DatePicker beginTime;
	@FXML DatePicker endTime;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		beginTime.setDayCellFactory(e -> new StrategyBeginDateCell());
		beginTime.setValue(LocalDate.now());
		endTime.setDayCellFactory(e -> new StrategyEndDateCell());
		endTime.setValue(beginTime.getValue());
		hotelRoomListViewData = FXCollections.observableArrayList();
		RoomInfo doubleBed = new RoomInfo("双人房", 53, 110, 389);
		RoomInfo bigBed = new RoomInfo("大床房", 36, 89, 413);
		RoomInfo singleBed = new RoomInfo("单人房", 46, 57, 277);
		hotelRoomListViewData.add(doubleBed);
		hotelRoomListViewData.add(bigBed);
		hotelRoomListViewData.add(singleBed);
		hotelRoomListView.setCellFactory(e -> new HotelRoomListCell());
		hotelRoomListView.setItems(hotelRoomListViewData);
		strategyRoomListViewData = FXCollections.observableArrayList();
		strategyRoomListView.setCellFactory(e -> new StrategyRoomListCell());
		strategyRoomListView.setItems(strategyRoomListViewData);
	}
	public DatePicker getBeginTime(){
		return this.beginTime;
	}
	public DatePicker getEndTime(){
		return this.endTime;
	}
	class StrategyBeginDateCell extends DateCell{
		public void updateItem(LocalDate item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				if (item.isBefore(LocalDate.now())){
					setDisable(false);
					setStyle("-fx-background-color:#ffc0cb");
				}
			}
		}
	}
	class StrategyEndDateCell extends DateCell{
		public void updateItem(LocalDate item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				if (item.isBefore(beginTime.getValue())){
					setDisable(false);
					setStyle("-fx-background-color:#ffc0cb");
				}
			}
		}
	}
}
