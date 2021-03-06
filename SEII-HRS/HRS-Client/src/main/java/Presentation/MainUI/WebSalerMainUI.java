package Presentation.MainUI;

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
		Parent NewStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/StrategyUI/WebSalerBrowseStrategyListUI.fxml"));
		NewStrategy.getProperties().put("NAME", "NewStrategyPane");
		GridPane pane = (GridPane) root;
		pane.add(NewStrategy, 3, 1);
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}