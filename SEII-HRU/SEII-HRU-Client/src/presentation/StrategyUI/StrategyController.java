package presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import presentation.BrowseUI.BrowseStrategyListController;
import vo.StrategyRoomInfo;

public class StrategyController implements Initializable{
	@FXML private ChoiceBox<String> strategyTypeChoice;
	@FXML private GridPane stratrgyPane;
	@FXML private TextField strategyName;
	private StrategyFormController controller;
	private BrowseStrategyListController browseController;
	@FXML
	protected void cancel(ActionEvent e){
		Stage stage = (Stage) stratrgyPane.getScene().getWindow();
		stage.close();
	}
	@FXML
	protected void save(ActionEvent e){
		if (!strategyTypeChoice.isDisabled()){
			if (strategyTypeChoice.getSelectionModel().getSelectedItem() .equals("节日促销策略")){
				Object[] temp = controller.getStrategyRoomListViewData().toArray();
				List<StrategyRoomInfo> list = new ArrayList<StrategyRoomInfo>();
				for (int i=0;i<temp.length;i++){
					list.add((StrategyRoomInfo)temp[i]);
				}
				FestivalStrategyFormController festivalController = (FestivalStrategyFormController) controller;
				StrategyInfo newItem = new StrategyInfo(strategyTypeChoice.getSelectionModel().getSelectedItem(),strategyName.getText(), festivalController.getBeginTime().getValue(), festivalController.getEndTime().getValue(),list);
				browseController.update(newItem, "CREATE");
				
			}
		}
		else {
			
		}
		Stage stage = (Stage) stratrgyPane.getScene().getWindow();
		stage.close();
	}
	public void setBrowseController(BrowseStrategyListController controller){
		this.browseController = controller;
	}
	public void setFormController(StrategyFormController controller){
		this.controller = controller;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		strategyTypeChoice.getSelectionModel().selectFirst();
		strategyTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				ObservableList<Node> list = stratrgyPane.getChildren();
				String oldkey = null,newNode=null,newkey=null;
				if(oldValue!=null){
					switch(oldValue){
						case "节日促销策略" : oldkey="Festival";
										  break;
						case "生日促销策略" : oldkey="Birthday";
										  break;
						case "合作企业促销策略" : oldkey="Company";
											break;
						case "房间预订促销策略" : oldkey="Room";
											break;
						case "VIP商圈促销策略" : oldkey="VIP";
											break;
					}
				}
				
				if(newValue!=null){
					switch(newValue){
						case "节日促销策略" : newNode="FestivalStrategyForm.fxml";
										  newkey="Festival";
										  break;
						case "生日促销策略" : newNode="BirthdayStrategyForm.fxml";
										  newkey="Birthday";
										  break;
						case "合作企业促销策略" : newNode="CompanyStrategyForm.fxml";
											newkey="Company";
											break;
						case "房间预订促销策略" : newNode="RoomPrebookStrategyForm.fxml";
											newkey="Room";
											break;
						case "VIP商圈促销策略" : newNode="VIPTradeStrategyForm.fxml";
											newkey="VIP";
											break;
					}
				}
				for (Node node:list){
					String value = (String) node.getProperties().get("NAME");
					if (value!=null&&value.equals(oldkey)){
						list.remove(node);
						break;
					}
				}
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(newNode));
					Parent newStrategyForm = loader.load();
					newStrategyForm.getProperties().put("NAME", newkey);
					stratrgyPane.add(newStrategyForm, 0,1,4,2);
					StrategyController.this.controller = loader.getController();
				} catch (IOException e) {
					//日志
				}
				
			}

			
		});
	}
}
