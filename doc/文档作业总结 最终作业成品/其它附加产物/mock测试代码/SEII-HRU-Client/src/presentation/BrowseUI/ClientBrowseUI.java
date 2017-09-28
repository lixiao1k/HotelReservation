package presentation.BrowseUI;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientBrowseUI extends Application {

   @Override
   public void start(Stage primaryStage) {

       BorderPane root = new BorderPane();
      
       HBox hbox=addHBox();
       root.setTop(hbox);
  
       Scene scene = new Scene(root ,768 , 564);

       primaryStage.setTitle("客户");
       primaryStage.setScene(scene);
       primaryStage.show();
   }

   
   public HBox addHBox(){
	   HBox hbox=new HBox();
	   hbox.setPadding(new Insets(15,12,15,12));
	   hbox.setSpacing(10);
	   hbox.setStyle("-fx-background-color: #336699;");
	   
	   Button allOrderButton=new Button("所有订单");
	   allOrderButton.setPrefSize(80, 20); 
	   
	   Button completeOrderButton=new Button("已完成订单");
	   completeOrderButton.setPrefSize(80, 20);
	   
	   Button nonexcutedOrderButton=new Button("未执行订单");
	   nonexcutedOrderButton.setPrefSize(80, 20);

	   Button abnormalOrderButton=new Button("异常订单");
	   abnormalOrderButton.setPrefSize(80, 20);
	   
	   Button canceledOrderButton=new Button("已撤销订单");
	   canceledOrderButton.setPrefSize(80,20);
	   
	   hbox.getChildren().addAll(allOrderButton,completeOrderButton,nonexcutedOrderButton,abnormalOrderButton,canceledOrderButton);
	   
	   return hbox;
   }
   
//   public static void main(String[] args) {
//       launch(args);
//   }
}