package Presentation.HotelUI;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import util.DoubleUtil;
import vo.HotelItemVO;

public class HotelRoomClientListCell extends ListCell<HotelItemVO>{
	public void updateItem(HotelItemVO item,boolean empty){
		super.updateItem(item, empty);
		if(item!=null){
			GridPane cell = new GridPane();
			Font font = new Font("YouYuan",20);
			Label type = new Label(item.getRoom().getType());
            Label avaliableNum = new Label(item.getNum()+"/"+item.getTotal());
            Label price = new Label(DoubleUtil.format(item.getPrice())+"RMB");
            type.setFont(font);
            avaliableNum.setFont(new Font("YouYuan",8));
            price.setFont(new Font("YouYuan",13));
            Button btn = new Button("下订单");
            btn.setId("shiny-orange");
            btn.setOnAction((ActionEvent e)->{
            	try {
					Parent p = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/OrderUI/CreateOrder.fxml"));
					Object o = DataController.getInstance().get("Root");
					if(o==null){
						Notifications.create().title("初始化").text("初始化失败！").showError();
						return;
					}
					DataController.getInstance().putAndUpdate("selectRoomType", item.getRoom());
					GridPane root = (GridPane) o;
					ObservableList<Node> nodes = root.getChildren();
					for(Node node:nodes){
						String t = (String) node.getProperties().get("NAME");
						if(t!=null&&t.contains("Pane")){
							nodes.remove(node);
							break;
						}
					}
					p.getProperties().put("NAME", "Pane");
					root.add(p, 3, 1);
            	} catch (Exception e1) {
            		e1.printStackTrace();
					Notifications.create().title("初始化").text("初始化失败！").showError();
					
				}
            	
            });
            cell.add(type, 0, 0);
            cell.add(avaliableNum, 0, 1);
            cell.add(price, 1, 0);
            cell.add(btn, 2, 0);
            cell.setHalignment(btn, HPos.RIGHT);
            cell.setHgrow(btn, Priority.ALWAYS);
            cell.setMargin(btn, new Insets(2,10,2,0));
            cell.setMargin(price, new Insets(2,10,2,10));
			setGraphic(cell);
		}else{
			setGraphic(null);
		}
	}
}
