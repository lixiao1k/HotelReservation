package presentation.UserUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class keepinfo extends Application {

	@Override
	public void start(Stage primaryStage) {
		BorderPane p=new BorderPane();
		HBox hbox=addHBox();
		GridPane grid=addGridPane();
		p.setCenter(grid);
		p.setTop(hbox);
		primaryStage.setScene(new Scene(p,768,564));
		primaryStage.show();
	}
public HBox addHBox(){
	HBox hbox=new HBox();
	hbox.setPadding(new Insets(15,12,15,295));
	hbox.setStyle("-fx-background-color:#336699;");
	Text keepinfo=new Text("维护基本信息");
	keepinfo.setFont(Font.font("Arial",30));
	hbox.getChildren().add(keepinfo);
	return hbox;
	
}
public GridPane addGridPane(){
	GridPane grid=new GridPane();
	grid.setVgap(10);
	grid.setHgap(10);
	grid.setPadding(new Insets(10,10,0,10));
	
	Text ID=new Text("用户ID");
	grid.add(ID, 0, 0);
	TextField idfield=new TextField("XXX");
	grid.add(idfield, 1, 0,2,1);
	
	return grid;
}
	public static void main(String[] args) {
		launch(args);
	}
}
