package Presentation.StrategyUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;

public class HotelWorkerCreateStrategyController implements Initializable{
	@FXML Button Back;
	@FXML GridPane mainPane;
	@FXML ChoiceBox<String> Type;
	GridPane clientmain;
	
	@FXML
	protected void Back(){
	   	try {
				Parent browseStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/HotelWorkerBrowseStrategyListUI.fxml"));
				browseStrategy.getProperties().put("NAME", "BrowseStrategyPane");
				clientmain=(GridPane) mainPane.getScene().getWindow().getScene().getRoot();
				ObservableList<Node> list = clientmain.getChildren();
				for (Node node:list){
					String value = (String) node.getProperties().get("NAME");
					if (value!=null&&value.contains("Pane")){
						list.remove(node);
						break;
					}
				}
				clientmain.add(browseStrategy, 3, 1);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	}
	
	@FXML
	protected void Enter(){
		Image image2=new Image(getClass().getResourceAsStream("undo2.png"));
		Back.setBackground(new Background(new BackgroundImage(image2, null, null, null, null)));
	}
	
	@FXML
	protected void Exit(){
		Image image1=new Image(getClass().getResourceAsStream("undo.png"));
		Back.setBackground(new Background(new BackgroundImage(image1, null, null, null, null)));
	}
	
	public void initBack(){
		Image image1=new Image(getClass().getResourceAsStream("undo.png"));
		Back.setBackground(new Background(new BackgroundImage(image1, null, null, null, null)));
	}
	
	public void initType(){
		ObservableList<String> typelist=FXCollections.observableArrayList();
		typelist.addAll("�����Żݲ���","����Ԥ���Żݲ���","������ҵ�Żݲ���","�����Żݲ���");
		Type.setItems(typelist);
	}
	
	//������ת
	public void swift(int i){
		String name[]={
				"Birth",
				"Room",
				"Company",
				"HotelFestival"};
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
	
	//���Ӽ���
	public void addchoiceboxlistener(){
		Type.getSelectionModel().selectedItemProperty().addListener((ov,oldvalue,newvalue)->{
			if(newvalue.equals("�����Żݲ���")){
				swift(0);
			}
			if(newvalue.equals("����Ԥ���Żݲ���")){
				swift(1);
			}
			if(newvalue.equals("������ҵ�Żݲ���")){
				swift(2);
			}
			if(newvalue.equals("�����Żݲ���")){
				swift(3);
			}
        });
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initType();
		initBack();
		addchoiceboxlistener();
	}
}
