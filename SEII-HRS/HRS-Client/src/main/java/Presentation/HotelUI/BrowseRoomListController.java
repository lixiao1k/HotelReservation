package Presentation.HotelUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.management.Notification;

import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import datacontroller.DataController;
import info.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import logic.service.ServiceFactory;
import resultmessage.HotelResultMessage;
import rmi.RemoteHelper;
import vo.CheckInRoomInfoVO;
import vo.HotelItemVO;

public class BrowseRoomListController implements Initializable{
	private ServiceFactory serviceFactory;
	private long hotelId;
	@FXML private ListView<HotelItemVO> roomListView;
	private ObservableList<HotelItemVO> roomListViewData;
	@FXML
	public void createNewRoom(ActionEvent e){
		
	}
	public void updateRoom(String type,int num,int total,double price,String operation){
		
	}
	public void changeRoomInfo(ActionEvent e,String roomType,String roomNum,String roomTotal,String roomPrice){
		
	}
	public void deleteItem(ActionEvent e,String type){
	}
	public void lineCheck(MouseEvent e,HotelItemVO hivo){
		PopOver popOver = new PopOver();
		popOver.setDetachable(false);
		popOver.setDetached(true);
		popOver.setDetachedTitle("线下入住信息");
		GridPane pane = new GridPane();
		Label label = new Label("入住数量");
		label.setFont(new Font("YouYuan",15));
		TextField field = new TextField();
		Button btn = new Button("确定");
		btn.setFont(new Font("YouYuan",15));

		btn.setOnAction((ActionEvent e3)->{
			try{
				int num = Integer.parseInt(field.getText());
				popOver.hide();
				lineCheckAction(hivo,num);
			}catch(NumberFormatException e4){
				Notifications.create().title("线下入住").text("请输入数字！").showWarning();
				popOver.hide();
			}
		});
		Button btn2 = new Button("取消");
		btn2.setFont(new Font("YouYuan",15));
		btn2.setOnAction((ActionEvent e2)->{
			popOver.hide();
		});
		pane.add(label, 0, 0);
		pane.add(field, 1, 0);
		pane.add(btn, 1, 1);
		pane.add(btn2, 1, 1);
		pane.setHalignment(btn, HPos.RIGHT);
		pane.setMargin(btn, new Insets(5,5,5,0));
		pane.setHalignment(btn2, HPos.RIGHT);
		pane.setMargin(btn2, new Insets(5,65,5,0));
		pane.setMargin(field, new Insets(5,5,0,5));
		pane.setMargin(label, new Insets(5,5,0,5));
		popOver.setContentNode(pane);
		popOver.show(((Node)e.getSource()),e.getScreenX(),e.getScreenY());
	}
	private void lineCheckAction(HotelItemVO room,int roomNum){
		HotelResultMessage result;
		if(roomNum>room.getNum()){
			Notifications.create().title("线下入住").text("请输入当前房间数以内数目！").showWarning();
			return;
		}
			
		try {
			CheckInRoomInfoVO cirivo = new CheckInRoomInfoVO();
			cirivo.setHotelId(hotelId);
			cirivo.setRoom(room.getRoom());
			cirivo.setRoomNum(roomNum);
			cirivo.setCheckInTime(new Date());
			result = serviceFactory.getHotelLogicService().roomCheckIn(cirivo);
			System.out.println(result);
			if(result==HotelResultMessage.SUCCESS){
				room.setNum(room.getNum()-roomNum);
				roomListViewData.add(room);
				roomListViewData.remove(room);
				Notifications.create().title("线下入住").text("更新成功").showConfirm();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		Object o = DataController.getInstance().get("HotelId");
		hotelId = (long) o;
		ListWrapper<HotelItemVO> list;
		try {
			list = serviceFactory.getHotelLogicService().getRoomInfo(hotelId);
			Iterator<HotelItemVO> it = list.iterator();
			List<HotelItemVO> rooms = new ArrayList<HotelItemVO>();
			while(it.hasNext()){
				HotelItemVO hivo = it.next();
				rooms.add(hivo);
			}
			roomListViewData = FXCollections.observableArrayList(rooms);
			roomListView.setCellFactory(e -> new RoomListCell());
			roomListView.setItems(roomListViewData);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	class RoomListCell extends ListCell<HotelItemVO>{

		public void updateItem(HotelItemVO item,boolean empty){
			super.updateItem(item, empty);

            if (item != null) {
                GridPane cell = new GridPane();
                Label type = new Label(item.getRoom().getType());
                type.setFont(new Font("YouYuan",20));
                Label avaliableNum = new Label(item.getNum()+"/"+item.getTotal());
                avaliableNum.setFont(new Font("YouYuan",8));
                Label price = new Label(item.getPrice()+"RMB");
                
                price.setFont(new Font("YouYuan",13));
                Button change = new Button("更改");
                change.getProperties().put("NAME", item.getRoom().getType()+" "+item.getNum()+" "+item.getTotal()+" "+item.getPrice());
                change.setOnAction((ActionEvent e)->{
                	try {
						Parent roomInfo = FXMLLoader.load(getClass().getResource("RoomInfo.fxml"));
						PopOver pop = new PopOver();
	                	pop.show(change, change.getLayoutX(), change.getLayoutY());
	                	pop.setContentNode(roomInfo);
	                	String[] temp = ((String)change.getProperties().get("NAME")).split(" ");
	                	changeRoomInfo(e,temp[0],temp[1],temp[2],temp[3]);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
                
                });
                Button lineCheck = new Button("线下入住");
                lineCheck.setOnMouseClicked(new EventHandler<MouseEvent>() {
              
             		@Override
					public void handle(MouseEvent event) {
						lineCheck(event, item);
					}
               });
                cell.add(type, 0, 0);
                cell.add(avaliableNum, 0, 1);
                cell.add(price, 1, 0);
                cell.add(change, 2, 0);
                cell.add(lineCheck, 3, 0);
                cell.setHalignment(price, HPos.RIGHT);
                cell.setMargin(price, new Insets(2,25,2,0));
                cell.setHalignment(lineCheck, HPos.RIGHT);
                cell.setMargin(lineCheck, new Insets(2,10,2,0));
                setGraphic(cell);
            } else {

                setGraphic(null);
            }
        }
	}
}
