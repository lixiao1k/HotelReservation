
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestController extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Hello world");
		Parent test = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/BrowseRoomListUI.fxml"));
		Scene scene = new Scene(test);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
