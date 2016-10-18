package presentation.BrowseUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentation.StrategyUI.StrategyController;
import presentation.StrategyUI.StrategyFormController;
import presentation.StrategyUI.StrategyInfo;
import vo.RoomInfo;
import vo.StrategyRoomInfo;

public class BrowseStrategyListController implements Initializable{
	@FXML ListView<StrategyInfo> strategyList;
	ObservableList<StrategyInfo> strategyListData;
	public void update(StrategyInfo item,String operation){
		if (operation.equals("CREATE")){
			strategyListData.add(item);
		}
		if (operation.equals("CHANGE")){
			for (StrategyInfo oldItem:strategyListData){
				if (oldItem.getType().equals(item.getType())){
					int index = strategyListData.indexOf(oldItem);
					strategyListData.set(index, null);
					strategyListData.set(index, item);
					break;
				}
			}
		}
	}
	@FXML
	protected void createNewStrategy(ActionEvent e){
		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/Strategy.fxml"));
			Parent createNewStrategy = loader.load();
			StrategyController controller = loader.getController();
			controller.setBrowseController(this);
			GridPane pane = (GridPane) createNewStrategy.lookup("#stratrgyPane");
			FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/FestivalStrategyForm.fxml"));
			Parent content = loader2.load();
			StrategyFormController formController = loader2.getController();
			controller.setFormController(formController);
			content.getProperties().put("NAME", "Festival");
			pane.add(content, 0, 1,4,2);
			Scene scene = new Scene(createNewStrategy,450,600);
			stage.setTitle("创建新策略");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log 日志&&状态栏显示
		}
	}
	public void changeStrategy(ActionEvent e,StrategyInfo item){
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/Strategy.fxml"));
		try {
			Parent changeStrategy = loader.load();
			StrategyController controller = loader.getController();
			controller.setBrowseController(this);
			ChoiceBox<String> strategyTypeChoice = (ChoiceBox<String>) changeStrategy.lookup("#strategyTypeChoice");
			strategyTypeChoice.setValue(item.getType());
			//strategyTypeChoice.
		} catch (IOException e1) {
			//log
		}
	}
	class StrategyListCell extends ListCell<StrategyInfo>{
		public void updateItem(StrategyInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				GridPane cell = new GridPane();
				Label name = new Label(item.getName());
				name.setFont(new Font("YouYuan",20));
				Label type = new Label(item.getType());
				type.setFont(new Font("YouYuan",12));
				Label time = new Label(item.getBeginTime().toString()+" - "+item.getEndTime().toString());
				time.setFont(new Font("YouYuan",8));
				Button change = new Button("change");
				change.setOnAction((ActionEvent e) ->{
					changeStrategy(e,item);
				});
				Button delete = new Button("delete");
				TitledPane room = new TitledPane();
				ListView<StrategyRoomInfo> content = new ListView<StrategyRoomInfo>();
				ObservableList<StrategyRoomInfo> contentData = FXCollections.observableArrayList();
				contentData.addAll(item.getStrategyRoomList());
				content.setCellFactory(e -> new StrategyRoomListCell());
				content.setItems(contentData);
				room.setText("房间信息");
				room.setContent(content);
				room.setExpanded(false);
				cell.add(name, 0, 0);
				cell.add(type, 0, 1);
				cell.add(time, 0, 2);
				cell.add(room, 1, 0);
				cell.add(change, 2, 0);
				cell.add(delete, 3, 0);
				setGraphic(cell);
			}
			else {
				setGraphic(null);
			}
		}
	}
	class StrategyRoomListCell extends ListCell<StrategyRoomInfo>{
		public void updateItem(StrategyRoomInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				GridPane cell = new GridPane();
				Label type = new Label(item.getType());
				type.setFont(new Font("YouYuan",15));
				Label num = new Label(item.getCurrentNum()+"/"+item.getTotal());
				num.setFont(new Font("YouYuan",7));
				Label price = new Label(item.getOffPrice()+"");
				price.setFont(new Font("YouYuan",10));
				cell.add(type, 0, 0);
				cell.add(price, 1, 0);
				cell.add(num, 0, 1);
				setGraphic(cell);
			}
			else {
				setGraphic(null);
			}
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		strategyListData = FXCollections.observableArrayList();
		strategyList.setCellFactory(e -> new StrategyListCell());
		strategyList.setItems(strategyListData);
		
	}
}
