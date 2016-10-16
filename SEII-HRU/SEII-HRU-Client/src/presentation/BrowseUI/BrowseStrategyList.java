package presentation.BrowseUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BrowseStrategyList extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = (Parent)FXMLLoader.load(getClass().getResource("BrowseStrategyListUI.fxml"));
		Parent child = (Parent)FXMLLoader.load(getClass().getResource("AnotherFXMLForTest.fxml"));
		GridPane pane = (GridPane) root.lookup("#mainPane");
		pane.add(child, 3, 3);
		Scene scene = new Scene(root,900,600);
		primaryStage.setTitle("æ∆µÍ‘§∂©œµÕ≥-HRU");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	public static void main(String[] args) {
		launch(args);
	}

}
