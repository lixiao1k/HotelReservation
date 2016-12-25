package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
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
	
	public void commit()
	{
		if(type1.getValue()!=null)
		{
			HotelItemVO hlV1=null;
			Room room1=null;
			room1.setType(type1.getValue());
			room1.setRid(map.get(type1.getValue()));
			hlV1.setRoom(room1);
			if(num1.getText().equals("")||price1.getText().equals(""))
			{
				Notifications.create().owner(num1.getScene().getWindow()).title("错误信息").text("填写内容不能为空!").showError();
			}
			else
			{
				hlV1.setNum(Integer.parseInt(num1.getText()));
				hlV1.setPrice(Double.parseDouble(price1.getText()));
				roomSet.add(hlV1);
				Notifications.create().owner(num1.getScene().getWindow()).title("提示信息").text("提交成功").showConfirm();;

			}
		}
		if(type2.getValue()!=null)
			
		{
			HotelItemVO hlV2=null;
			Room room2=null;
			room2.setType(type2.getValue());
			room2.setRid(map.get(type2.getValue()));
			hlV2.setRoom(room2);
			if(num2.getText().equals("")||price2.getText().equals(""))
			{
				Notifications.create().owner(num2.getScene().getWindow()).title("错误信息").text("填写内容不能为空!").showError();
			}
			else
			{
				hlV2.setNum(Integer.parseInt(num2.getText()));
				hlV2.setPrice(Double.parseDouble(price2.getText()));
				roomSet.add(hlV2);
				Notifications.create().owner(num2.getScene().getWindow()).title("提示信息").text("提交成功").showConfirm();;

			}
			
		}
		if(type3.getValue()!=null)
		{
			HotelItemVO hlV3=null;
			Room room3=null;
			room3.setType(type3.getValue());
			room3.setRid(map.get(type3.getValue()));
			hlV3.setRoom(room3);
			if(num3.getText().equals("")||price3.getText().equals(""))
			{
				Notifications.create().owner(num3.getScene().getWindow()).title("错误信息").text("填写内容不能为空!").showError();
			}
			else
			{
				hlV3.setNum(Integer.parseInt(num3.getText()));
				hlV3.setPrice(Double.parseDouble(price3.getText()));
				roomSet.add(hlV3);
				Notifications.create().owner(num3.getScene().getWindow()).title("提示信息").text("提交成功").showConfirm();;

			}
			
		}
	
		ahvo.setItems(roomSet);
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
		
	}
	
	

}
