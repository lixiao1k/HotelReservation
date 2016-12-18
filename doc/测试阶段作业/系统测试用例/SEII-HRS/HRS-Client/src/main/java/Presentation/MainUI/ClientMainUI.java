package Presentation.MainUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ClientMainUI extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root=FXMLLoader.load(getClass().getResource("ClientMainUI.fxml"));
		Parent personInfo=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/PersonInfo.fxml"));
		personInfo.getProperties().put("NAME","KeepPersonInfoPane");
		GridPane pane=(GridPane) root;
		pane.add(personInfo, 3,1);
		
		Scene scene = new Scene(root,900,600);
		scene.getStylesheets().add(getClass().getResource("ClientButton.css").toExternalForm());
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
