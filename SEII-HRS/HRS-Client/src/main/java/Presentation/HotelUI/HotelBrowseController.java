package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import Presentation.CreditUI.CreditBrowseController;
import datacontroller.DataController;
import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import vo.SearchHotelVO;

public class HotelBrowseController implements Initializable{
	@FXML TextField searchField;
	@FXML Button hotelSearchButton;
	@FXML ComboBox businessCityBox;
	@FXML ComboBox circleBox;
	@FXML TableColumn hotelName;
	@FXML TableColumn history;
	@FXML TableColumn rank;
	@FXML TableColumn star;
	@FXML TableColumn roomType;
	@FXML TableColumn leastPrice;
	@FXML ChoiceBox<String> roomChoiceBox;
	@FXML ListView<HotelInfo> listView;
	@FXML DatePicker checkin;
	@FXML DatePicker checkout;
	private ServiceFactory serviceFactory;
	private ListWrapper<BusinessCity> bc;
	private long userid;
    private BusinessCity  city;
    private BusinessCircle circle;
    private Set<BusinessCircle> setcircle;
	public void search(ActionEvent e)
	{
		
	}
	
	public void search(SearchHotelVO vo)
	{
		System.out.println("chenggong");
	}
	
	public void createOrder(ActionEvent e)
	{
		try {
			GridPane clientmain=(GridPane)searchField.getScene().getWindow().getScene().getRoot();
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/OrderUI/CreateOrder.fxml"));
			Parent createOrder = loader.load();
//			CreditBrowseController creditcontroller=loader.getController();
//			creditcontroller.setKeepPersonInfoController(this);
//			creditcontroller.setBaseInfo(this.userid);
			createOrder.getProperties().put("NAME","CreateOrderPane" );
			ObservableList<Node> list =clientmain.getChildren();
			for(Node node:list){
				String value=(String)node.getProperties().get("NAME");
				if(value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(createOrder, 2, 1);
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	
	public void selectCity()
	{
		Iterator<BusinessCity> it;
		try {
			it = bc.iterator();
			String selectCity=businessCityBox.getSelectionModel().getSelectedItem().toString();
				   
		    Set<String> circle=new HashSet<>();//得到城市对应全部商圈信息
			while(it.hasNext())
			{
				BusinessCity bci=it.next();
				if(bci.getName().equals(selectCity))
				{
					city=bci;
					Set<BusinessCircle> bcirs = bci.getCircles();
										setcircle=bcirs;
					for(BusinessCircle bcir:bcirs){
							circle.add(bcir.getName());
					}
					break;
				}
			}
			
			ObservableList<String> circles = FXCollections.observableArrayList(circle);

				circleBox.getItems().clear();
	

				circleBox.getItems().addAll(circles);
			
		

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void selectCircle()
	{
		
		SearchHotelVO searchvo=new SearchHotelVO();
		String selectCircle=null;
		if(circleBox.getSelectionModel().getSelectedItem()==null)
		{
			
		}
		else
		{
	
			selectCircle=circleBox.getSelectionModel().getSelectedItem().toString();
		    searchvo.setBusinessCity(city);
		    for(BusinessCircle bcir:setcircle){
		    	if(selectCircle.equals(bcir.getName()))
		    	{
		    		bcir.setBcircleId(1);//先自己定一个编号
		    		circle.setBcircleId(bcir.getBcircleId());
		    		break;
		    	}
		}
		    searchvo.setBusinessCircle(circle);
	
			
		}
		
		
		if(checkin.getValue()!=null)
		{
			LocalDate localcheckin=checkin.getValue();
			Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date  checkintime=new Date();
	              checkintime.from(instant);
	              searchvo.setCheckInTime(checkintime);
		}

		
		if(checkout.getValue()!=null)
		{
			LocalDate localcheckout=checkout.getValue();
	        Instant instantout=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date  checkouttime=new Date();
	        	  checkouttime.from(instantout);
	        	  searchvo.setCheckOutTime(checkouttime);
		}

			search(searchvo);  

	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		try {
			userid=(long)DataController.getInstance().get("UserId");
			bc = serviceFactory.getHotelLogicService().getCity();
			Set<String> set = new HashSet<>();//得到全部城市信息

			Iterator<BusinessCity> it = bc.iterator();
			while(it.hasNext())
			{
				BusinessCity bci = it.next();
				set.add(bci.getName());
			}
			ObservableList<String> cities = FXCollections.observableArrayList(set);
			
			businessCityBox.getItems().addAll(cities);
			

			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
