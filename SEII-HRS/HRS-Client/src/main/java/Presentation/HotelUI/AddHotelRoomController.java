package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import info.ListWrapper;
import info.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.HotelLogicService;
import logic.service.ServiceFactory;
import resultmessage.HotelResultMessage;
import rmi.RemoteHelper;
import vo.AddHotelVO;
import vo.HotelItemVO;

public class AddHotelRoomController implements Initializable{
	@FXML TextField num1;
	@FXML TextField num2;
	@FXML TextField num3;
	@FXML ComboBox<String> type1;
	@FXML ComboBox<String> type2;
	@FXML ComboBox<String> type3;
	@FXML TextField price1;
	@FXML TextField price2;
	@FXML TextField price3;
	
	private ServiceFactory service;
	private HotelLogicService hotelLogic;
	private Set<HotelItemVO> roomSet;
	private ListWrapper<Room>roomType;
	private Map<String,Long>map;
	private AddHotelVO ahvo;
	
	public void goBack() throws IOException
	{
		 GridPane mainpane=(GridPane)num1.getScene().getWindow().getScene().getRoot();
		  Parent pane=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/AddHotelInfo.fxml"));
			pane.getProperties().put("NAME", "addHotelInfoPane");
			ObservableList<Node> list=mainpane.getChildren();
			for (Node node:list){
				String value = (String) node.getProperties().get("NAME");
				if (value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			mainpane.add(pane, 3, 1);
	}
	
	public void commit() throws RemoteException
	{
		if(type1.getValue()!=null)
		{
			HotelItemVO hlV1=new HotelItemVO();
			Room room1=new Room();
			System.out.println(type1.getValue());
			room1.setType(type1.getValue());
			room1.setRid(map.get(type1.getValue()));
			hlV1.setRoom(room1);

			hlV1.setNum(Integer.parseInt(num1.getText()));
			hlV1.setPrice(Double.parseDouble(price1.getText()));
			roomSet.add(hlV1);

			
		}
		if(type2.getValue()!=null)
			
		{
			HotelItemVO hlV2=new HotelItemVO();
			Room room2=new Room();
			room2.setType(type2.getValue());
			room2.setRid(map.get(type2.getValue()));
			hlV2.setRoom(room2);

			hlV2.setNum(Integer.parseInt(num2.getText()));
			hlV2.setPrice(Double.parseDouble(price2.getText()));
			roomSet.add(hlV2);

			
			
		}
		if(type3.getValue()!=null)
		{
			HotelItemVO hlV3=new HotelItemVO();
			Room room3=new Room();
			room3.setType(type3.getValue());
			room3.setRid(map.get(type3.getValue()));
			hlV3.setRoom(room3);
			hlV3.setNum(Integer.parseInt(num3.getText()));
			hlV3.setPrice(Double.parseDouble(price3.getText()));
			roomSet.add(hlV3);

		}
	
		ahvo.setItems(roomSet);
		System.out.println(ahvo.getUsername());
		HotelResultMessage m=hotelLogic.addHotel(ahvo).getResultMessage();
		if(	m==HotelResultMessage.SUCCESS)
		{
			Notifications.create().owner(num3.getScene().getWindow()).title("提示信息").text("添加成功！").showConfirm();
		}
		else if(m==HotelResultMessage.FAIL)
		{
			Notifications.create().owner(num3.getScene().getWindow()).title("错误信息").text("添加失败，未知错误！").showError();

		}
		else if(m==HotelResultMessage.FAIL_NOTENOUGHINFO)
		{
			Notifications.create().owner(num3.getScene().getWindow()).title("错误信息").text("添加失败，信息不全！").showError();

		}
	}
	

	

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(service==null)
			service=RemoteHelper.getInstance().getServiceFactory();
			try {
				
				hotelLogic=service.getHotelLogicService();
				List<String>type=new ArrayList<String>();
				roomType=hotelLogic.getRoomTypes();
				Iterator<Room>it=roomType.iterator();
				map=new HashMap<>();
				roomSet=new HashSet<>();
				Room room;
				while(it.hasNext())
				{
					room=it.next();
					type.add(room.getType());
					map.put(room.getType(), room.getRid());
					
				}
				ObservableList<String>roomTypeList=FXCollections.observableArrayList(type);
				type1.getItems().addAll(roomTypeList);
				type2.getItems().addAll(roomTypeList);
				type3.getItems().addAll(roomTypeList);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 ahvo=(AddHotelVO)DataController.getInstance().get("addHotelInfo");
		 System.out.println(ahvo.getUsername());
	}
	
	

}
