package presentation;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;

public class WebManagerMainController {
	@FXML private GridPane webManagerMain;
	@FXML protected void goSetHotelInfo(ActionEvent event){
		try {
			Parent HotelInfo = FXMLLoader.load(getClass().getResource("HotelUI/SetHotelInfo.fxml"));
			HotelInfo.getProperties().put("NAME", "HotelInfoPane");
			ObservableList<Node> list = webManagerMain.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&(value.contains("Pane"))){
					list.remove(node);
					break;
				}
			}
			webManagerMain.add(HotelInfo, 2, 1);
		} catch (IOException e) {
			// log ÈÕÖ¾&&×´Ì¬À¸
		}
	}
}