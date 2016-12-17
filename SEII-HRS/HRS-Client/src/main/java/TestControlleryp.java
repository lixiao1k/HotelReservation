import org.controlsfx.control.NotificationPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class TestControlleryp extends Application{
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello world");
		Parent test = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/CreateOrder.fxml"));
		Scene scene = new Scene(test);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
