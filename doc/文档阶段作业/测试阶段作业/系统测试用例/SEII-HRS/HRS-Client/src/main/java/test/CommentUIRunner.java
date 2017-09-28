package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CommentUIRunner extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation.OrderUI.Comment.fxml"));
		Parent pane=loader.load();
		Scene scene=new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
    public static void main(String[] args) {
		launch();
	}
}
