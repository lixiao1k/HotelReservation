package presentation.BrowseUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BrowseStrategyListController {
	@FXML
	public void createNewStrategy(ActionEvent e){
		Stage stage = new Stage();
		try {
			Parent createNewStrategy = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/StrategyUI/CreateNewStrategy.fxml"));
			GridPane pane = (GridPane) createNewStrategy.lookup("#createStratrgyPane");
			Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("presentation/StrategyUI/FestivalStrategyForm.fxml"));
			content.getProperties().put("NAME", "Festival");
			pane.add(content, 0, 2,4,1);
			Scene scene = new Scene(createNewStrategy,450,600);
			stage.setTitle("创建新策略");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e1) {
			//log 日志&&状态栏显示
		}
	}
}
