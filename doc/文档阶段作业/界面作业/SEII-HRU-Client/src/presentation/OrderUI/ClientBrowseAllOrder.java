package presentation.OrderUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ClientBrowseAllOrder extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		BorderPane root=new BorderPane();
		
		Scene scene=new Scene(root,762,504);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
