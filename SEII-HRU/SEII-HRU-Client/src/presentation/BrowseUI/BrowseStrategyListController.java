package presentation.BrowseUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import presentation.StrategyUI.FestivalStrategyInfo;
import presentation.StrategyUI.StrategyController;
import presentation.StrategyUI.StrategyFormController;
import presentation.StrategyUI.StrategyInfo;
import vo.RoomInfo;
import vo.StrategyRoomInfo;

public class BrowseStrategyListController implements Initializable{
	@FXML ListView<StrategyInfo> strategyList;
	@FXML TextField searchField;
	ObservableList<StrategyInfo> strategyListData;
	List<Boolean> isStrategyListDataSearched;
	@FXML
	protected void search(ActionEvent e){
		Collections.fill(isStrategyListDataSearched, false);
		String searchText = searchField.getText();
		for (int i=0;i<strategyListData.size();i++){
			//这里需要相似度计算？
			if (searchText!=null&&searchText!=""&&strategyListData.get(i).getName().contains(searchText)){
				isStrategyListDataSearched.set(i, true);
			}
		}
		strategyList.setItems(null);
		strategyList.setItems(strategyListData);
	}
	public void update(StrategyInfo item,String operation){
		if (operation.equals("CREATE")){
			isStrategyListDataSearched.add(false);
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
			FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/FestivalStrategyForm.fxml"));
			GridPane content = loader2.load();
			StrategyFormController formController = loader2.getController();
			controller.setFormController(formController);
			controller.setSaveOption(true);
			formController.initial();
			ChoiceBox<String> strategyChoiceBox = (ChoiceBox<String>) createNewStrategy.lookup("#strategyTypeChoice");
			strategyChoiceBox.getSelectionModel().selectFirst();
			Scene scene = new Scene(createNewStrategy,450,600);
			stage.setTitle("创建新策略");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log 日志&&状态栏显示
		}
	}
	private void changeStrategy(ActionEvent e,StrategyInfo item){
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/Strategy.fxml"));
		FXMLLoader loader2 = new FXMLLoader(getClass().getClassLoader().getResource("presentation/StrategyUI/FestivalStrategyForm.fxml"));
		try {
			Parent changeStrategy = loader.load();
			Parent strategyForm = loader2.load();
			StrategyController controller = loader.getController();
			StrategyFormController formController = loader2.getController();
			GridPane content = (GridPane) strategyForm;
			//这里也要
			content.getProperties().put("NAME", "节日促销策略");
			controller.setBrowseController(this);
			controller.setSaveOption(false);
			controller.setFormController(formController);
			TextField strategyName =  (TextField) changeStrategy.lookup("#strategyName");
			strategyName.setText(item.getName());
			ChoiceBox<String> strategyChoiceBox = (ChoiceBox<String>) changeStrategy.lookup("#strategyTypeChoice");
			strategyChoiceBox.getSelectionModel().select(item.getType());
			
			//循环判断策略类型,调用controller的initial方法，其controller再调用自己的formController对应方法，各具体的controller重写该方法完成初始化
			controller.initial(item);
			Scene scene = new Scene(changeStrategy,450,600);
			stage.setTitle("更改策略");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log
		}
	}
	private void deleteStrategy(ActionEvent e,StrategyInfo item){
		strategyListData.remove(item);
	}
	class StrategyListCell extends ListCell<StrategyInfo>{
		public void updateItem(StrategyInfo item,boolean empty){
			super.updateItem(item, empty);
			if (item!=null){
				//这里要对item的类型做判断，根据类型不同显示不同的值
				GridPane cell = new GridPane();
				Label name = new Label(item.getName());
				name.setFont(new Font("YouYuan",20));
				Label type = new Label(item.getType());
				type.setFont(new Font("YouYuan",12));
				Label time = new Label(((FestivalStrategyInfo) item).getBeginTime().toString()+" - "+((FestivalStrategyInfo) item).getEndTime().toString());
				time.setFont(new Font("YouYuan",8));
				Button change = new Button("change");
				change.setOnAction((ActionEvent e) ->{
					changeStrategy(e,item);
				});
				Button delete = new Button("delete");
				delete.setOnAction((ActionEvent e)->{
					deleteStrategy(e,item);
				});
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
				if (isStrategyListDataSearched.get(strategyListData.indexOf(item))){
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
		//StrategyInfo 也要创建基类
		StrategyRoomInfo itemData = new StrategyRoomInfo("双人房", 5, 69, 200, 100);
		List<StrategyRoomInfo> list = new ArrayList<StrategyRoomInfo>();
		isStrategyListDataSearched = new ArrayList<Boolean>();
		isStrategyListDataSearched.add(false);
		list.add(itemData);
		StrategyInfo item = new FestivalStrategyInfo("节日促销策略", "五折优惠", LocalDate.now(), LocalDate.now(),list);
		strategyListData = FXCollections.observableArrayList();
		strategyListData.add(item);
		strategyList.setCellFactory(e -> new StrategyListCell());
		strategyList.setItems(strategyListData);
		
	}
}
