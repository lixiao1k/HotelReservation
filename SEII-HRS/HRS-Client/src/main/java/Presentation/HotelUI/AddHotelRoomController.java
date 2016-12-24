package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import vo.AddHotelVO;

public class AddHotelRoomController implements Initializable{
	@FXML TextField bigBed;
	@FXML TextField doubleBed;
	@FXML TextField familyRoom;
	
	
	public void goBack() throws IOException
	{
		 GridPane mainpane=(GridPane)bigBed.getScene().getWindow().getScene().getRoot();
		  Parent pane=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/AddHotelInfo.fxml"));
			pane.getProperties().put("NAME", "addHotelInfoPane");
			ObservableList<Node> list=mainpane.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			mainpane.add(pane, 3, 1);
	}
	
	public void commit()
	{
	boolean empty=bigBed.getText().equals("")||doubleBed.getText().equals("")||familyRoom.getText().equals("");
	if(empty)
	{
		Notifications.create().owner(bigBed.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();
	}
	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		AddHotelVO ahvo=(AddHotelVO)DataController.getInstance().get("addHotelInfo");
		
	}
	
	

}
