package Presentation.MainUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WebManagerMainUI extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("WebManagerMainUI.fxml"));
		Parent HotelInfo = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/AddHotelInfo.fxml"));
		HotelInfo.getProperties().put("NAME", "HotelInfoPane");
		GridPane pane = (GridPane) root.lookup("#webManagerMain");
		pane.add(HotelInfo, 2, 1);
		Scene scene = new Scene(root,900,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}