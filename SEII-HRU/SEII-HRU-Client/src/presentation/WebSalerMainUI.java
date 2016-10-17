package presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class WebSalerMainUI extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("WebSalerMainUI.fxml"));
		Parent HotelInfo = FXMLLoader.load(getClass().getResource("StrategyUI/CreateNewStrategy.fxml"));
		HotelInfo.getProperties().put("NAME", "HotelInfo");
		GridPane pane = (GridPane) root.lookup("#hotelmain");
		pane.add(HotelInfo, 2, 1);
		Scene scene = new Scene(root,900,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}