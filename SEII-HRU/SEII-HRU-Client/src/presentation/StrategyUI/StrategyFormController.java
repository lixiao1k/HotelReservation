package presentation.StrategyUI;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import vo.RoomInfo;
import vo.StrategyRoomInfo;

public class StrategyFormController{
	@FXML ListView<RoomInfo> hotelRoomListView;
	@FXML ListView<StrategyRoomInfo> strategyRoomListView;
	ObservableList<RoomInfo> hotelRoomListViewData;
	ObservableList<StrategyRoomInfo> strategyRoomListViewData;
	public ObservableList<StrategyRoomInfo> getStrategyRoomListViewData(){
		return this.strategyRoomListViewData;
	}
	public void changeToStrategyRoom(ActionEvent e,String type,double off){
		for (RoomInfo item:hotelRoomListViewData){
			if (item.getType().equals(type)){
				StrategyRoomInfo newItem = new StrategyRoomInfo(item.getType(), item.getCurrentNum(), item.getTotal(), item.getPrice(), off);
				strategyRoomListViewData.add(newItem);
				hotelRoomListViewData.remove(item);
				break;
			}
		}
	}
	public void changeToHotelRoom(ActionEvent e,String type){
		for (StrategyRoomInfo item:strategyRoomListViewData){
			if (item.getType().equals(type)){
				RoomInfo newItem = new RoomInfo(item.getType(), item.getCurrentNum(), item.getTotal(), item.getPrice());
				hotelRoomListViewData.add(newItem);
				strategyRoomListViewData.remove(item);
				break;
			}
		}
	}
	class StrategyRoomListCell extends ListCell<StrategyRoomInfo>{
		public void updateItem(StrategyRoomInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
                GridPane cell = new GridPane();
                Label type = new Label(item.getType());
                type.setFont(new Font("YouYuan",20));
                Label avaliableNum = new Label(item.getCurrentNum()+"/"+item.getTotal());
                avaliableNum.setFont(new Font("YouYuan",8));
                Label price = new Label(item.getPrice()+"RMB -> "+item.getOffPrice()+"RMB");
                price.setFont(new Font("YouYuan",13));
                Button change = new Button("¡ü");
                change.getProperties().put("NAME", item.getType()+" "+item.getCurrentNum()+" "+item.getPrice());
                change.setOnAction((ActionEvent e)->{
                	
                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
                	changeToHotelRoom(e,temp[0]);
                });
                cell.add(type, 0, 0);
                cell.add(avaliableNum, 0, 1);
                cell.add(price, 1, 0);
                cell.add(change,2, 0);
                setGraphic(cell);
			}
			else{
				setGraphic(null);
			}
		}
	}
	class HotelRoomListCell extends ListCell<RoomInfo>{
		public void updateItem(RoomInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
                GridPane cell = new GridPane();
                Label type = new Label(item.getType());
                type.setFont(new Font("YouYuan",20));
                Label avaliableNum = new Label(item.getCurrentNum()+"/"+item.getTotal());
                avaliableNum.setFont(new Font("YouYuan",8));
                Label price = new Label(item.getPrice()+"RMB");
                TextField offTextField = new TextField();
                price.setFont(new Font("YouYuan",13));
                Button change = new Button("¡ý");
                change.getProperties().put("NAME", item.getType()+" "+item.getCurrentNum()+" "+item.getPrice());
                change.setOnAction((ActionEvent e)->{
                	try{
                	double off = Double.parseDouble(offTextField.getText());
                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
                	changeToStrategyRoom(e,temp[0],off);
                	}catch(NumberFormatException e1){
                		//log
                	}
                });
                cell.add(type, 0, 0);
                cell.add(avaliableNum, 0, 1);
                cell.add(price, 1, 0);
                cell.add(offTextField, 2, 0);
                cell.add(change, 3, 0);
                setGraphic(cell);
			}
			else{
				setGraphic(null);
			}
		}
	}

}
