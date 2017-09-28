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
	public void initial(StrategyInfo item){
		super.initial(item);
		this.beginTime.setValue(((FestivalStrategyInfo)item).getBeginTime());
		this.endTime.setValue(((FestivalStrategyInfo)item).getEndTime());
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		beginTime.setDayCellFactory(e -> new StrategyBeginDateCell());
		beginTime.setValue(LocalDate.now());
		endTime.setDayCellFactory(e -> new StrategyEndDateCell());
		endTime.setValue(beginTime.getValue());
		
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
