package Presentation.OrderUI;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import datacontroller.DataController;
import info.ListWrapper;
import info.OrderStrategy;
import info.Room;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.HotelLogicService;
import logic.service.OrderLogicService;
import logic.service.ServiceFactory;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.BasicHotelVO;
import vo.HotelItemVO;
import vo.HotelStrategyVO;
import vo.NewOrderVO;
import vo.OrderVO;
import vo.StrategyItemVO;

public class CreateOrderController implements Initializable{
	@FXML private DatePicker checkIntime;
	@FXML private DatePicker checkOuttime;

	@FXML private ComboBox<Integer> roomNumBox;
	@FXML private TextField customerName;
	@FXML private TextField phoneNumber;
	
	@FXML private CheckBox Ifchild;
	@FXML private Label strategyType;
	@FXML private Label totalMoney;
	@FXML private Label orderDes;
	@FXML private Label lateCheck;
	@FXML private TextField peopleNum;
	@FXML private GridPane mainPane;
	private long userid;
	private long hotelid;
	private ServiceFactory  serviceFactory;
	private StrategyLogicService  strategylogic;
	private OrderStrategy ordervo;
	private ListWrapper<HotelStrategyVO> liststrategy;
	private NewOrderVO  neworder;
	private HotelLogicService  hotellogic;
	private OrderLogicService orderLogic;
	private  double leastPrice;
	public void commit() throws IOException
	{
		
		    

		    
		
		 //浣忓濮撳悕
		    String customer=null;
		    customer=customerName.getText();
		    neworder.setContactName(customer);
		 //鎵嬫満鍙风爜
		    String contact=null;
		    contact=phoneNumber.getText();
		    neworder.setContactWay(contact);
		 //棰勮鍏ヤ綇浜烘暟
		    int people=0;
		    people=Integer.parseInt(peopleNum.getText());
		    neworder.setPeople(people);
		 //鏄惁鏈夊効绔�
		    boolean child=false;
		    if(Ifchild.isSelected())
		    {
		    	child=true;

		    }
		    neworder.setChild(child);
		
		    double totalNum=0;
		    totalNum=neworder.getRoomNum()*leastPrice;
		    neworder.setRoomPrice(totalNum);
		    totalMoney.setText(String.valueOf(totalNum));
		
		    try {
				orderLogic=serviceFactory.getOrderLogicService();
		    	orderLogic.create(neworder);
		    	GridPane client=(GridPane)peopleNum.getScene().getWindow().getScene().getRoot();
				FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/OrderUI/ClientBrowseOrderListUI.fxml"));
				
				Parent hoteldetailBrowse = loader.load();
				hoteldetailBrowse.getProperties().put("NAME","HotelDetailPane" );
				ObservableList<Node> list =client.getChildren();
				for(Node node:list){
					String value=(String)node.getProperties().get("NAME");
					if(value!=null&&value.contains("Pane")){
						list.remove(node);
						break;
					}
				}
				client.add(hoteldetailBrowse, 3, 1);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
	}


    @FXML
	public void setRoom()
	{
		   int num=0;
		   num=roomNumBox.getSelectionModel().getSelectedItem();
		   roomNumBox.setValue(num);
		  
		   neworder.setRoomNum(num);
	}
	
    public void checkStrategy()
    {
    	//鍏ヤ綇鏃堕棿
		
		
		LocalDate localcheckin=checkIntime.getValue();
		Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date  checkintime=Date.from(instant);
	  
	//閫�鎴挎椂闂�
	    LocalDate localcheckout=checkOuttime.getValue();
	    		instant=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	    Date  checkouttime=Date.from(instant);
	    
	    neworder.setCheckInTime(checkintime);
	    neworder.setCheckOutTime(checkouttime);
	    
	    //妫�鏌ョ瓥鐣�
	    ListWrapper<HotelItemVO> hotelitemList;//鏍规嵁hotelid寰楀埌璇ラ厭搴楀叏閮ㄦ埧闂村熀鏈被鍨�
	
			   try {
				 HotelStrategyVO hotelstrategyvo;
				 HotelItemVO    hotelroomvo;//鏍规嵁hotelid寰楀埌璇ラ厭搴楀叏閮ㄦ埧闂寸被鍨嬩俊鎭�
				 
				 
				strategylogic=serviceFactory.getStrategyLogicService();
				ordervo=new OrderStrategy();
				ordervo.setCheckInTime(checkintime);
				ordervo.setHotelId(hotelid);
				ordervo.setUserId(userid);
				
				
				liststrategy=strategylogic.getStrategyForOrder(ordervo);
				Iterator<HotelStrategyVO> it;
				it=liststrategy.iterator();
				
				Set<StrategyItemVO> hotelstrategySet;
				String roomType=(String)DataController.getInstance().get("selectRoomType");//寰楀埌鎵�閫夐厭搴楁埧鍨�
				
				
				Object o=DataController.getInstance().get("selectHotel");//寰楀埌鎵�閫夐厭搴楃殑鍩烘湰淇℃伅
				BasicHotelVO bhvo=(BasicHotelVO)o;
				
				Set<HotelItemVO> hotelroom=bhvo.getRooms();
				for(HotelItemVO hivo:hotelroom)
				{
					if(hivo.getRoom().getType()==roomType)
					{
						
						neworder.setRoom(hivo.getRoom());//缁欒鍗曚紶鎴块棿淇℃伅
					}
				}
			
				
				
		
			    hotellogic=serviceFactory.getHotelLogicService();
			    hotelitemList=hotellogic.getRoomInfo(hotelid);
			    
			    Iterator<HotelItemVO> ithotel;
			    ithotel=hotelitemList.iterator();
			    
			    //閬嶅巻閰掑簵鎴块棿绫诲瀷寰楀埌鎴块棿鍒濆浠锋牸
			    while(ithotel.hasNext())
			    {
			    	hotelroomvo=ithotel.next();
			    	if(roomType.equals(hotelroomvo.getRoom().getType()))
			    	{
			    		leastPrice=hotelroomvo.getPrice();
			    	
			    	}
			    }
			    
			    
				String strategyName="  ";
				
				boolean flag=false;//鏍囪璇ラ厭搴楁湁鏃犱紭鎯犱俊鎭�
				double offinfo=1;
				while(it.hasNext())
				{
					hotelstrategyvo=it.next();
					//鎵�閫夐厭搴楁湁浼樻儬
					if(hotelid==hotelstrategyvo.getHotelId())
					{
						
						hotelstrategySet=hotelstrategyvo.getItems();
						
						for(StrategyItemVO  sta:hotelstrategySet)
						{
							
							//鎵�閫夋埧闂寸被鍨嬫湁浼樻儬
							if(roomType.equals(sta.getRoom().getType()))
							{
								neworder.setStrategy(hotelstrategyvo.getId());//璁剧疆strategyid
								flag=true;
								if(sta.getPriceAfter()<leastPrice)
								{
									leastPrice=sta.getPriceAfter();
									offinfo=sta.getOff();
									neworder.setStrategyOff(offinfo);
									
								}
							}
						}
						strategyName=hotelstrategyvo.getName();
					}
					
					
				}
				
				if(flag)
				{
					strategyType.setText(strategyName);
					
					
				}
				else
				{
					strategyType.setText("鏃�");
				}
				//鏈�鏅氬叆浣忔椂闂�

                SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss ");
				String lastCheckin;
				Calendar lastCal = Calendar.getInstance();
				lastCal.setTime(checkintime);
				lastCal.add(Calendar.AM_PM, 18);
				Date lastCheck=lastCal.getTime();
				lastCheckin=sdf.format(lastCheck);
				lateCheck.setText("璁㈠崟鏈�鏅氭墽琛屾椂闂�:"+lastCheckin);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
			   
			   
	    
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		if(serviceFactory==null)
		{
			serviceFactory=RemoteHelper.getInstance().getServiceFactory();
		}
		neworder=new NewOrderVO();
		Object o=DataController.getInstance().get("UserId");
		userid=(long)o;
		DataController.getInstance().putAndUpdate("UserId", (Object)userid);
		
		DataController.getInstance().putAndUpdate("creatOrderPane", mainPane);// for hotelbrowse's popover

		o=DataController.getInstance().get("HotelID");
		hotelid=(long)o;
		//DataController.getInstance().put("selectHotel", (Object)hotelid);
		neworder.setUserId(userid);
		neworder.setHotelId(hotelid);

		roomNumBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		
		
	}

}
