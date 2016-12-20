package Presentation.MainUI;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ProgressStage {
	private Stage dialogStage;
	private ProgressIndicator progressIndicator;
	public ProgressStage(Task<?> task,Stage primaryStage,String message){
		dialogStage = new Stage();
		progressIndicator = new ProgressIndicator();
		dialogStage.initOwner(primaryStage);
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label(message);
        label.setTextFill(Color.web("#87CEEB"));
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);
        vBox.setMargin(label, new Insets(3,0,0,0));
        vBox.setMargin(progressIndicator, new Insets(130,0,0,0));
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        dialogStage.setScene(scene);
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent event) {
                dialogStage.close();
            }
        });
        Thread inner = new Thread(task);
        inner.start();
	}
	public void close(){
		dialogStage.close();
	}
	public void show(){
		dialogStage.show();
	}
}
