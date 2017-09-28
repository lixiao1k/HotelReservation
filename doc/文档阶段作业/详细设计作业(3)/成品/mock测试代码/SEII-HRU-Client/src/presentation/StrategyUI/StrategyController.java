package presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
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
	private boolean saveOption;
	private StrategyFormController controller;
	private BrowseStrategyListController browseController;
	public void initial(StrategyInfo item){
		controller.initial(item);
	}
	@FXML
	protected void cancel(ActionEvent e){
		Stage stage = (Stage) stratrgyPane.getScene().getWindow();
		stage.close();
	}
	public void initial(){
		controller.initial();
	}
	public void setSaveOption(boolean option){
		this.saveOption = option;
	}
	@FXML
	protected void save(ActionEvent e){
		if (saveOption){
			if (strategyTypeChoice.getSelectionModel().getSelectedItem().equals("���մ�������")){
				Object[] temp = controller.getStrategyRoomListViewData().toArray();
				List<StrategyRoomInfo> list = new ArrayList<StrategyRoomInfo>();
				for (int i=0;i<temp.length;i++){
					list.add((StrategyRoomInfo)temp[i]);
				}
				FestivalStrategyFormController festivalController = (FestivalStrategyFormController) controller;
				StrategyInfo newItem = new FestivalStrategyInfo(strategyTypeChoice.getSelectionModel().getSelectedItem(),strategyName.getText(), festivalController.getBeginTime().getValue(), festivalController.getEndTime().getValue(),list);
				browseController.update(newItem, "CREATE");
			}
			if (strategyTypeChoice.getSelectionModel().getSelectedItem().equals("���մ�������")){
				Object[] temp = controller.getStrategyRoomListViewData().toArray();
				List<StrategyRoomInfo> list = new ArrayList<StrategyRoomInfo>();
				for (int i=0;i<temp.length;i++){
					list.add((StrategyRoomInfo)temp[i]);
				}
				BirthdayStrategyFormController birthdayController = (BirthdayStrategyFormController) controller;
				StrategyInfo newItem = new BirthdayStrategyInfo(strategyTypeChoice.getSelectionModel().getSelectedItem(), strategyName.getText(), list);
				browseController.update(newItem, "CREATE");
			}
			if (strategyTypeChoice.getSelectionModel().equals("������ҵ��������")){
				
			}
		}
		else {
			Object[] temp = controller.getStrategyRoomListViewData().toArray();
			List<StrategyRoomInfo> list = new ArrayList<StrategyRoomInfo>();
			for (int i=0;i<temp.length;i++){
				list.add((StrategyRoomInfo)temp[i]);
			}
			if (strategyTypeChoice.getSelectionModel().getSelectedItem() .equals("���մ�������")){
				FestivalStrategyFormController festivalController = (FestivalStrategyFormController) controller;
				StrategyInfo newItem = new FestivalStrategyInfo(strategyTypeChoice.getSelectionModel().getSelectedItem(),strategyName.getText(), festivalController.getBeginTime().getValue(), festivalController.getEndTime().getValue(),list);
				browseController.update(newItem, "CHANGE");
			}
			if (strategyTypeChoice.getSelectionModel().getSelectedItem().equals("���մ�������")){
				BirthdayStrategyFormController birthdayController = (BirthdayStrategyFormController) controller;
				StrategyInfo newItem = new BirthdayStrategyInfo(strategyTypeChoice.getSelectionModel().getSelectedItem(), strategyName.getText(), list);
				browseController.update(newItem, "CHANGE");
			}
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
		List<String> list = new ArrayList<String>();
		list.add("���մ�������");
		list.add("���մ�������");
		list.add("VIP��Ȧ��������");
		list.add("����Ԥ����������");
		list.add("������ҵ��������");
		strategyTypeChoice.setItems(FXCollections.observableArrayList(list));
		strategyTypeChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				ObservableList<Node> list = stratrgyPane.getChildren();
				String newNode=null;
			
				if(newValue!=null){
					switch(newValue){
						case "���մ�������" : newNode="FestivalStrategyForm.fxml";
										
										  break;
						case "���մ�������" : newNode="BirthdayStrategyForm.fxml";
										  
										  break;
						case "������ҵ��������" : newNode="CompanyStrategyForm.fxml";
											
											break;
						case "����Ԥ����������" : newNode="RoomPrebookStrategyForm.fxml";
											
											break;
						case "VIP��Ȧ��������" : newNode="VIPTradeStrategyForm.fxml";
											
											break;
					}
				}
				for (Node node:list){
					String value = (String) node.getProperties().get("NAME");
					if (value!=null&&value.equals(oldValue)){
						list.remove(node);
						break;
					}
				}
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(newNode));
					Parent newStrategyForm = loader.load();
					newStrategyForm.getProperties().put("NAME", newValue);
					StrategyController.this.controller = loader.getController();
					stratrgyPane.add(newStrategyForm, 0,1,4,2);
					controller.initial();
				} catch (IOException e) {
					//��־
					System.out.println(e.getCause()+e.getMessage());
				}
				
			}

			
		});
	}
}
