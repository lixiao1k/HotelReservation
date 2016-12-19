package Presentation.HotelUI;

import java.io.IOException;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.DateUtil;
import vo.HotelCommentVO;

public class HotelCommentListCell extends ListCell<HotelCommentVO> {
	public void updateItem(HotelCommentVO item,boolean empty){
		super.updateItem(item, empty);
		if(item!=null){
			try {
				GridPane cell = FXMLLoader.load(getClass().getResource("CommentListCell.fxml"));
				Label pic = (Label) cell.lookup("#pic");
				Text name = (Text) cell.lookup("#name");
				VBox rankVBox = (VBox) cell.lookup("#rankVBox");
				Text room = (Text) cell.lookup("#room");
				Text content = (Text) cell.lookup("#content");
				Text date = (Text) cell.lookup("#date");
				date.setText(DateUtil.format(item.getDate()));
				content.setText(item.getComment());
				if(item.getRoom()!=null)
					room.setText("所订房间：  "+item.getRoom().getType());
				Rating rating = new Rating(5, item.getGrade());
				rating.setOnMouseClicked(new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent event) {
						rating.setRating(item.getGrade());
					}
				});
				rankVBox.getChildren().add(rating);
				if(item.isHide())
					name.setText("匿名用户");
				else
					name.setText(item.getName());
				Image image = new Image(getClass().getClassLoader().getResourceAsStream("Presentation/MainUI/user50x50.png"));
				pic.setText("");
				pic.setGraphic(new ImageView(image));
				setGraphic(cell);
			} catch (IOException e) {
				Notifications.create().title("初始化").text("初始化失败！").showError();
				e.printStackTrace();
			}
		}else{
			setGraphic(null);
		}
	}
}
