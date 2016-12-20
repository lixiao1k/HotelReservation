package Presentation.StrategyUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;

public class WebSalerCreateStrategyController implements Initializable{
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Type;
	GridPane clientmain;
	
	public void initType(){
		ObservableList<String> typelist=FXCollections.observableArrayList();
		typelist.addAll("VIP商圈优惠策略","节日优惠策略");
		Type.setItems(typelist);
	}
	
	//界面跳转
	public void swift(int i){
		String name[]={
				"VIPCircle",
				"Festival"};
    	try {
			Parent Strategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/"+name[i]+".fxml"));
			Strategy.getProperties().put("NAME", name[i]+"Strategy");
			ObservableList<Node> list = mainPane.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Strategy")){
					list.remove(node);
					break;
				}
			}
			mainPane.add(Strategy, 0, 1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//增加监听
	public void addchoiceboxlistener(){
		Type.getSelectionModel().selectedItemProperty().addListener((ov,oldvalue,newvalue)->{
			if(newvalue.equals("VIP商圈优惠策略")){
				swift(0);
			}
			if(newvalue.equals("节日优惠策略")){
				swift(1);
			}
        });
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initType();
		addchoiceboxlistener();
	}
}
