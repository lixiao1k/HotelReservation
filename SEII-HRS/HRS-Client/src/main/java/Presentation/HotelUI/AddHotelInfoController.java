package Presentation.HotelUI;


import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.Notifications;

import com.sun.javafx.geom.Rectangle;

import datacontroller.DataController;
import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.Rank;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import logic.service.HotelLogicService;
import logic.service.ServiceFactory;
import resultmessage.HotelResultMessage;
import rmi.RemoteHelper;
import vo.AddHotelResultVO;
import vo.AddHotelVO;
import vo.HotelItemVO;

public class AddHotelInfoController implements Initializable{
@FXML private TextField addressField;

@FXML private ChoiceBox starChoice;
@FXML private TextArea summaryArea;
@FXML private TextArea institutionArea;
@FXML private TextArea serveArea;
@FXML private TextField hotelName;
@FXML private PasswordField password;
@FXML private TextField addHotelField;
@FXML  private ComboBox<String> businessCity;
@FXML  private ComboBox<String>businessCircle;
@FXML private StackPane stack;
@FXML private Button next;

 private static String star=null;
 private AddHotelVO addHotel;
 private ServiceFactory service;
 private HotelLogicService hotelLogic;
 private ListWrapper<BusinessCity> bc;
 private BusinessCity  city;
 private BusinessCircle circle;
 private Set<BusinessCircle> setcircle;
 private AddHotelResultVO hresult;


  
  @FXML
  //閿熸枻鎷烽敓楗衡槄鎷烽敓锟� 閿熸枻鎷烽敓鏂ゆ嫹鍐欓敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
  public void cancel(ActionEvent e)
  {
	  addressField.clear();
	  
	  
	  summaryArea.clear();
	  institutionArea.clear();
	  serveArea.clear();
	  addHotelField.clear();
  }
  
  //璺宠浆鍒板綍鍏ラ厭搴楀鎴夸俊鎭晫闈�
  public void goNext() throws IOException
  {
	  boolean area=(summaryArea.getText().equals(""))||(institutionArea.getText().equals(""))||(serveArea.getText().equals(""));
		boolean field=(addressField.getText().equals(""))||(addHotelField.getText().equals("")
				        ||hotelName.getText().equals("")||password.getText().equals(""));
	   
		  if(area||field)
		  {
			  
				Notifications.create().owner(addHotelField.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();
		  }

		  else
		  {
			 
			  Rank rank=Rank.NONE;
			  addHotel.setAddress(addressField.getText());
			  addHotel.setDescription(summaryArea.getText());
			  addHotel.setFacility(institutionArea.getText());
			  addHotel.setName(addHotelField.getText());
			  addHotel.setMemberName(hotelName.getText());
			  addHotel.setPassword(password.getText());
			  addHotel.setService(serveArea.getText());
			  switch(star)
			  {
			  case"无":{
				  break;
			  }
			  case"一星级":
			  {
				  rank=Rank.ONE;break;
			  }
			  case"二星级":
			  {
				  rank=Rank.TWO;break;
			  }
			  case"三星级":
			  {
				  rank=Rank.THREE;break;
			  }
			  case"四星级":
			  {
				  rank=Rank.FOUR;break;
			  }
			  case"五星级":
			  {
				  rank=Rank.FIVE;break;
			  }
			  
				  
			  }
			  
			  addHotel.setRank(rank);


		  }
	  
		DataController.getInstance().putAndUpdate("addHotelInfo", addHotel);
	  GridPane mainpane=(GridPane)addressField.getScene().getWindow().getScene().getRoot();
	  Parent pane=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/AddHotelRoom.fxml"));
		pane.getProperties().put("NAME", "addHotelRoomPane");
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
  
  
	public void selectCity()
	{
		Iterator<BusinessCity> it;
		try {
			it = bc.iterator();
			String selectCity=businessCity.getSelectionModel().getSelectedItem().toString();
				   
		    Set<String> circle=new HashSet<>();//瀵版鍩岄崺搴＄鐎电懓绨查崗銊╁劥閸熷棗婀�娣団剝浼�
			while(it.hasNext())
			{
				BusinessCity bci=it.next();
				if(bci.getName().equals(selectCity))
				{
					city=bci;
					System.out.println(city.toString());
					Set<BusinessCircle> bcirs = bci.getCircles();
										setcircle=bcirs;
					for(BusinessCircle bcir:bcirs){
							circle.add(bcir.getName());
					}
					break;
				}
			}
			
			ObservableList<String> circles = FXCollections.observableArrayList(circle);

				businessCircle.getItems().clear();
	

				businessCircle.getItems().addAll(circles);
			
		

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void selectCircle()
	{
		
	
		String selectCircle=null;
		if(businessCircle .getSelectionModel().getSelectedItem()==null)
		{

		//	System.out.println("鍟ヤ篃娌℃湁");

		}
		else
		{

			selectCircle=businessCircle.getSelectionModel().getSelectedItem();
			System.out.println(selectCircle);
	
		    addHotel.setBusinessCity(city);
		    for(BusinessCircle bcir:setcircle){
		    	if(selectCircle.equals(bcir.getName()))
		    	{
		    		circle=bcir;
		    		circle.setBcircleId(bcir.getBcircleId());
		    		break;
		    	}
		}
		    addHotel.setBusinessCircle(circle);
		    
		  
		}

		
	}
  

@Override
public void initialize(URL location, ResourceBundle resources) {
	
	if(service==null)
	{
		service=RemoteHelper.getInstance().getServiceFactory();
		
	}
	try {
		addHotel=new AddHotelVO();
		DataController.getInstance().putAndUpdate("addHotelInfo", addHotel);
		hotelLogic=service.getHotelLogicService();
		bc=hotelLogic.getCity();
		Set<String> set = new HashSet<>();//瀵版鍩岄崗銊╁劥閸╁骸绔舵穱鈩冧紖

		Iterator<BusinessCity> it = bc.iterator();
		while(it.hasNext())
		{
			BusinessCity bci = it.next();
			
			set.add(bci.getName());
		}
		
		ObservableList<String> cities = FXCollections.observableArrayList(set);
		System.out.println(cities.size());
		businessCity.getItems().addAll(cities);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	
	// TODO Auto-generated method stub
	starChoice.setItems(FXCollections.observableArrayList("无","一星级","二星级","三星级","四星级","五星级"));
	starChoice.setValue("无");
	star=(String) starChoice.getValue();
	starChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// TODO Auto-generated method stub

			if(newValue!=null)
			{
				
			    star=newValue;
			}
		
		
		}
		
	});
}
}
