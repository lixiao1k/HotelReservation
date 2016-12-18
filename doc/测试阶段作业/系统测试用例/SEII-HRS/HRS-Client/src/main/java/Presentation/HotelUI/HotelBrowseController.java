package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import logic.service.HotelLogicService;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import vo.BasicHotelVO;
import vo.HotelItemVO;
import vo.SearchHotelVO;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;


public class HotelBrowseController implements Initializable{
	@FXML TextField searchField;
	@FXML Button hotelSearchButton;

	@FXML ComboBox<String> businessCityBox;
	@FXML ComboBox<String> circleBox;

	@FXML ChoiceBox<String> roomChoiceBox;
	@FXML ListView<BasicHotelVO> hotelListView;
	@FXML DatePicker checkin;
	@FXML DatePicker checkout;
	private ServiceFactory serviceFactory;
	private ListWrapper<BusinessCity> bc;
	private long userid;
    private BusinessCity  city;
    private BusinessCircle circle;
    private Set<BusinessCircle> setcircle;
    private SearchHotelVO searchvo;
    private ListWrapper<Long>  hotelid;//预定过的酒店ID
    private HotelLogicService hotelbl;
    private ListWrapper<BasicHotelVO>  basicHotel;
    private ObservableList<BasicHotelVO> hotelListViewData;
	public void search(ActionEvent e)
	{
		
	}
	
	public void search(SearchHotelVO vo)
	{
		    try {
		    	if(vo==null)
		    	{
		    		System.out.println("vo有问题");
		    	}
				basicHotel=	hotelbl.getHotels(vo);//根据searchvo返回搜索到的酒店信息
				List<BasicHotelVO> hotels = new ArrayList<BasicHotelVO>();
				System.out.println(basicHotel.size());
				Iterator<BasicHotelVO> it=basicHotel.iterator();
				while(it.hasNext()){
					BasicHotelVO  bihvo = it.next();
					hotels.add(bihvo);
				}
				System.out.println("ssss");
				hotelListViewData=FXCollections.observableArrayList(hotels);
				hotelListView.setCellFactory(e->new hotelListCell());
				hotelListView.setItems(hotelListViewData);
			//	Object o=(Object)hotelListView.getSelectionModel().getSelectedItem().getHotelId();//得到选中的酒店的id
			//	DataController.getInstance().put("selectHotel", o);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    

	}
	
	class hotelListCell extends ListCell<BasicHotelVO>{
			public void updateItem(BasicHotelVO item,boolean empty)
			{
				super.updateItem(item, empty);
				
				if(item!=null)
				{
	                GridPane cell = new GridPane();
	                cell.prefWidthProperty().bind(hotelListView.widthProperty().subtract(2));
	                Label hotelName = new Label(item.getHotelName()+"/"+item.getScore());//酒店名称+评分
	                hotelName.setFont(new Font("YouYuan",20));
	                Label star=new Label(item.getRank().toString());
	                star.setFont(new Font("YouYuan",20));
	                long theHotelID=item.getHotelId();//得到ID
	                boolean flag=false;
	                try {
						Iterator<Long> it=hotelid.iterator();
						
						while(it.hasNext())
						{
							if(theHotelID==it.next())
							{
							flag=true;
							}
							else
							{

							}
						}
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	               Label history=new Label();
	                if(flag)
	                {
	                	history.setText("预定过");
	                	history.setFont(new Font("Youyuan",20));
	          
	                }
	                else
	                {
	                	history.setText("    ");
	                	
	                }
	                Set<HotelItemVO> hotIt=item.getRooms();
	                double leastPrice=1000000;
	                String leastType=null;
	                for(HotelItemVO  htlVO:hotIt)
	                {
	                	if(htlVO.getPrice()<leastPrice)
	                	{
	                		leastPrice=htlVO.getPrice();
	                		leastType=htlVO.getRoom().getType();
	                	}
	                }
	                
	                Label least=new Label(leastPrice+"("+leastType+")");
	                Button createOrder=new  Button("下订单");
	                createOrder.setFont(new Font("Youyuan",20));
	                //涓轰笅璁㈠崟娣诲姞鐣岄潰
	                createOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
	                    
	             		@Override
						public void handle(MouseEvent event) {
							createOrder(event, item);
						}
	               });
	                
	                
	                
	                cell.add(hotelName, 0, 0);
	                cell.add(star, 1, 0);
	                cell.add(history, 2, 0);
	                cell.add(least, 3, 0);
	                cell.add(createOrder, 4, 0);
	                setGraphic(cell);
				}
				else
				{
					setGraphic(null);
					System.out.println("meiyou");
				}
			}
		
		
		
	}
	
	public void createOrder(MouseEvent e,BasicHotelVO item)
	{
		PopOver popOver = new PopOver();
		popOver.setDetachable(false);

		popOver.setTitle("下订单");


		GridPane pane=new GridPane();
		
		
	}
	
	
	public void selectCity()
	{
		Iterator<BusinessCity> it;
		try {
			it = bc.iterator();
			String selectCity=businessCityBox.getSelectionModel().getSelectedItem().toString();
				   
		    Set<String> circle=new HashSet<>();//寰楀埌鍩庡競瀵瑰簲鍏ㄩ儴鍟嗗湀淇℃伅
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

				circleBox.getItems().clear();
	

				circleBox.getItems().addAll(circles);
			
		

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void selectCircle()
	{
		
	
		String selectCircle=null;
		if(circleBox.getSelectionModel().getSelectedItem()==null)
		{

			System.out.println("啥也没有");

		}
		else
		{

			selectCircle=circleBox.getSelectionModel().getSelectedItem().toString();
			System.out.println(selectCircle);
		    searchvo.setBusinessCity(city);
		    for(BusinessCircle bcir:setcircle){
		    	if(selectCircle.equals(bcir.getName()))
		    	{
		    		bcir.setBcircleId(1);//先自己设一个id
		    		circle=bcir;
		    		circle.setBcircleId(bcir.getBcircleId());
		    		break;
		    	}
		}
		    searchvo.setBusinessCircle(circle);
			search(searchvo);  
		}

		
	}
	
	
	public void detailCheck()
	{
		if(checkin.getValue()!=null)
		{
			LocalDate localcheckin=checkin.getValue();
			System.out.println(localcheckin);
			Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date  checkintime=Date.from(instant);
	          
	              searchvo.setCheckInTime(checkintime);
		}
		
		if(checkout.getValue()!=null)
		{
			LocalDate localcheckout=checkout.getValue();
	        Instant instantout=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date  checkouttime=Date.from(instantout);
	        	
	        	  searchvo.setCheckOutTime(checkouttime);
		}
		
		search(searchvo);

	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		try {
			searchvo=new SearchHotelVO();
			userid=(long)DataController.getInstance().get("UserId");
			hotelbl=serviceFactory.getHotelLogicService();
			hotelid=hotelbl.getBookHotel(userid);//得到预定历史
			bc = serviceFactory.getHotelLogicService().getCity();
			Set<String> set = new HashSet<>();//寰楀埌鍏ㄩ儴鍩庡競淇℃伅

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
