package Presentation.OrderUI;

import java.util.Date;

import org.controlsfx.control.PopOver;

import datacontroller.DataController;
import info.OrderStatus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import util.DateUtil;
import util.DoubleUtil;
import vo.OrderVO;

public class HotelWorkerOrderListCell extends ListCell<OrderVO>{
	private void setColor(OrderStatus status,Label label){
		if(status==OrderStatus.ABNORMAL)
			label.setTextFill(Color.RED);
		else if(status==OrderStatus.EXECUTED||status==OrderStatus.UNEXECUTED)
			label.setTextFill(Color.GREEN);
		else
			label.setTextFill(Color.web("#ffcc66"));
	}
	private void showDetail(OrderVO item,MouseEvent e){
		PopOver popOver = new PopOver();
		popOver.setDetachable(false);
		popOver.setAutoHide(true);
		popOver.setTitle("订单");
		BorderPane mainPane = new BorderPane();
		GridPane basicPane = new GridPane();
		Label name = new Label("下订单人:  "+item.getName());
		Label initialTime = new Label("From "+DateUtil.format(item.getCheckInTime())+" to "+DateUtil.format(item.getCheckOutTime()));
		Label peopleInfo = new Label("入住人数： "+item.getPeople()+"人 ; "+(item.getChild()? "有":"无")+"儿童");
		Label contactName = new Label("联系人： "+item.getContactName());
		Label contactWay = new Label("联系方式： "+item.getContactWay());
		Font font = new Font("YouYuan",14);
		name.setFont(font);
		name.setTextFill(Color.BLACK);
		initialTime.setFont(font);
		initialTime.setTextFill(Color.BLACK);
		peopleInfo.setFont(font);
		peopleInfo.setTextFill(Color.BLACK);
		contactName.setFont(font);
		contactName.setTextFill(Color.BLACK);
		contactWay.setFont(font);
		contactWay.setTextFill(Color.BLACK);
		basicPane.add(name, 0, 0);
		basicPane.add(initialTime, 0, 1);
		basicPane.add(peopleInfo, 0, 3);
		basicPane.add(contactName, 0, 4);
		basicPane.add(contactWay, 0, 5);
		basicPane.setMargin(name, new Insets(2,4,2,4));
		basicPane.setMargin(initialTime, new Insets(2,4,2,4));
		basicPane.setMargin(peopleInfo, new Insets(2,4,2,4));
		basicPane.setMargin(contactName, new Insets(2,4,2,4));
		basicPane.setMargin(contactWay, new Insets(2,4,2,4));
		Separator s = new Separator();
		basicPane.add(s, 0, 6);
		GridPane roomPane = new GridPane();
		Label room = new Label(item.getRoom().getType());
		Label num = new Label(""+item.getRoomNum()+" 间");
		Label strategy = new Label();
		Label price = new Label();
		Label off = new Label();
		room.setFont(new Font("YouYuan",22));
		room.setTextFill(Color.BLACK);
		num.setFont(new Font("YouYuan",10));
		num.setTextFill(Color.BLACK);
		Separator s2 = new Separator();
		s2.setOrientation(Orientation.VERTICAL);
		roomPane.add(room, 0, 0,1,3);
		roomPane.add(s2, 1, 0,1,3);
		roomPane.setMargin(room, new Insets(2,4,2,4));
		roomPane.setHalignment(room, HPos.CENTER);
		roomPane.setValignment(room, VPos.TOP);
		roomPane.add(num, 2, 2,2,1);
		if(item.getStrategy()!=null&&item.getStrategy().length()>0){
			strategy.setText(item.getStrategy());
			price.setText(item.getRoomPrice()+" RMB --> "+DoubleUtil.format(item.getRoomPrice()*item.getStrategyOff())+" RMB");
			off.setText(item.getStrategyOff()*100+" %");
			off.setTextFill(Color.RED);
			price.setFont(new Font("YouYuan",14));
			strategy.setFont(new Font("YouYuan",12));
			off.setFont(new Font("YoutYuan",12));
			strategy.setTextFill(Color.BLACK);
			price.setTextFill(Color.BLACK);
			roomPane.add(price, 2, 0,2,1);
			roomPane.add(strategy, 2, 1,1,1);
			roomPane.add(off, 3, 1,1,1);
			roomPane.setMargin(off, new Insets(2,4,2,4));
		}else{
			price.setText(item.getRoomPrice()+" RMB ");
			price.setFont(new Font("YouYuan",14));
			price.setTextFill(Color.BLACK);
			roomPane.add(price, 2, 0,2,1);
		}
		mainPane.setTop(basicPane);
		mainPane.setBottom(roomPane);
		mainPane.setPadding(new Insets(10,10,10,10));
		popOver.setContentNode(mainPane);
		popOver.show(((Node)e.getSource()),e.getScreenX(),e.getScreenY());
	}
	public void updateItem(OrderVO item,boolean empty){
		super.updateItem(item, empty);
		if(item!=null){
			GridPane gridPane = new GridPane();
			Label orderNum = new Label(item.getOrderNum());
			orderNum.setFont(new Font("YouYuan",18));
			Label orderStatus = new Label(item.getStatus().toString());
			orderStatus.setFont(new Font("YouYuan",13));
			setColor(item.getStatus(), orderStatus);
			Label price = new Label("  "+DoubleUtil.format(item.getPriceAfterStrategy())+"RMB");
			price.setFont(new Font("YouYuan",20));
			Button execute = new Button("执行");
			Button reExecute = new Button("补执行");
			execute.setId("rich-blue");
			reExecute.setId("rich-blue");
			gridPane.add(orderNum, 0, 0);
			gridPane.add(orderStatus, 0	, 1);
			gridPane.add(price, 1, 0,1,2);
			this.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					showDetail(item, event);
				}
			});
			gridPane.setMargin(price, new Insets(2,20,2,0));
			if(item.getStatus()==OrderStatus.UNEXECUTED){
				execute.setOnAction((ActionEvent e)->{
					Object o =DataController.getInstance().get("HotelOrderController");
					((HotelWorkerBrowseOrderListController) o).execute(item,e);
				});
				gridPane.add(execute, 2, 0,1,2);
				gridPane.setHalignment(execute, HPos.RIGHT);
				gridPane.setHgrow(execute, Priority.ALWAYS);
				gridPane.setMargin(execute, new Insets(2,10,2,0));
			}
			if(item.getStatus()==OrderStatus.ABNORMAL
					&&item.getAbnormalTime().before(DateUtil.getFutureDate(new Date(), 6))){
				reExecute.setOnAction((ActionEvent e)->{
					Object o =DataController.getInstance().get("HotelOrderController");
					((HotelWorkerBrowseOrderListController) o).reExecute(item,e);
				});
				gridPane.add(reExecute, 2, 0,1,2);
				gridPane.setHalignment(reExecute, HPos.RIGHT);
				gridPane.setHgrow(reExecute, Priority.ALWAYS);
				gridPane.setMargin(reExecute, new Insets(2,10,2,0));
			}
			setGraphic(gridPane);
		}else{
			setGraphic(null);
		}
	}
}
