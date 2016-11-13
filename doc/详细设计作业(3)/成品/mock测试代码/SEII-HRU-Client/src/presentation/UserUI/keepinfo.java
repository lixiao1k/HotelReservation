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
	
	Text ID=new Text("用户ID:");
	grid.add(ID, 0, 0);
	TextField idfield=new TextField();
	grid.add(idfield, 1, 0,2,1);
	Text Phone=new Text("联系方式:");
	grid.add(Phone,0,1);
	TextField phonefield=new TextField();
	grid.add(phonefield, 1,1,2,1);
	Text credit=new Text("信用值:");
	grid.add(credit, 0, 2);
	TextField creditfield=new TextField();
	grid.add(creditfield, 1, 2,2,1);
	Text compony=new Text("企业名称:");
	grid.add(compony, 0, 3);
	TextField componyfield=new TextField();
	grid.add(componyfield, 1, 3,2,1);
	Text birth=new Text("生日:");
	grid.add(birth, 0, 4);
	TextField birthfield=new TextField();
	birthfield.setPromptText("仅限个人会员");
	grid.add(birthfield, 1, 4,2,1);
	return grid;
}
	public static void main(String[] args) {
		launch(args);
	}
}
