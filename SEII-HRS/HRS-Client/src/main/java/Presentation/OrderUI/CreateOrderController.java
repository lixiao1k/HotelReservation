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

import org.controlsfx.control.Notifications;

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
import resultmessage.OrderResultMessage;
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
	private StrategyLogicService  strategyLogic;
	private OrderStrategy ordervo;
	private ListWrapper<HotelStrategyVO> liststrategy;
	private NewOrderVO  newOrder;
	private HotelLogicService  hotelLogic;
	private OrderLogicService orderLogic;
	
	private  double leastPrice;
	public void commit() throws IOException
	{
		
		
		 //住客姓名
		    String customer=null;
		    customer=customerName.getText();
		    newOrder.setContactName(customer);
		 //手机号码
		    String contact=null;
		    contact=phoneNumber.getText();
		    newOrder.setContactWay(contact);
		 //预计入住人数
		    int people=0;
		    people=Integer.parseInt(peopleNum.getText());
		    newOrder.setPeople(people);
		 //是否有儿童
		    boolean child=false;
		    if(Ifchild.isSelected())
		    {
		    	child=true;

		    }
		    newOrder.setChild(child);
		    double roompri=0;
		    roompri=leastPrice*newOrder.getStrategyOff();
		    newOrder.setRoomPrice(roompri);
		   
		
		    try {
				orderLogic=serviceFactory.getOrderLogicService();
		    	
		    	if(	orderLogic.create(newOrder)==OrderResultMessage.SUCCESS)
		    	{
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
		    	}
		    	else if(orderLogic.create(newOrder)==OrderResultMessage.FAIL_NOTENOUGHCREDIT)
		    	{
		    		Notifications.create().owner(phoneNumber.getScene().getWindow()).title("错误信息").text("信用不足!").showError();
		    	}

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
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
	    
	    newOrder.setCheckInTime(checkintime);
	    newOrder.setCheckOutTime(checkouttime);
	    
	    //妫�鏌ョ瓥鐣�
	    ListWrapper<HotelItemVO> hotelitemList;//鏍规嵁hotelid寰楀埌璇ラ厭搴楀叏閮ㄦ埧闂村熀鏈被鍨�
	
			   try {
				 HotelStrategyVO hotelstrategyvo;
				 HotelItemVO    hotelroomvo;//鏍规嵁hotelid寰楀埌璇ラ厭搴楀叏閮ㄦ埧闂寸被鍨嬩俊鎭�
				 
				 
				strategyLogic=serviceFactory.getStrategyLogicService();
				ordervo=new OrderStrategy();
				ordervo.setCheckInTime(checkintime);
				ordervo.setHotelId(hotelid);
				ordervo.setUserId(userid);
				
				
				liststrategy=strategyLogic.getStrategyForOrder(ordervo);
				Iterator<HotelStrategyVO> it;
				it=liststrategy.iterator();
				
				Set<StrategyItemVO> hotelstrategySet;
				String roomType=(String)DataController.getInstance().get("selectRoomType");//寰楀埌鎵�閫夐厭搴楁埧鍨�
				
				
				Object o=DataController.getInstance().get("selectHotel");//寰楀埌鎵�閫夐厭搴楃殑鍩烘湰淇℃伅
				BasicHotelVO bhvo=(BasicHotelVO)o;
				
				Set<HotelItemVO> hotelroom=bhvo.getRooms();
				for(HotelItemVO hivo:hotelroom)
				{
					if(hivo.getRoom().getType().equals(roomType))
					{

						newOrder.setRoom(hivo.getRoom());//给订单传房间信息

					}
				}
			
				
				
		
			    hotelLogic=serviceFactory.getHotelLogicService();
			    hotelitemList=hotelLogic.getRoomInfo(hotelid);
			    
			    Iterator<HotelItemVO> ithotel;
			    ithotel=hotelitemList.iterator();
			    
			    //閬嶅巻閰掑簵鎴块棿绫诲瀷寰楀埌鎴块棿鍒濆浠锋牸
			    while(ithotel.hasNext())
			    {
			    	hotelroomvo=ithotel.next();
			    	if(roomType.equals(hotelroomvo.getRoom().getType()))
			    	{
			    		leastPrice=hotelroomvo.getPrice();
			    		System.out.println(leastPrice);
			    	}
			    }
			    
			    System.out.println("aaaaa");
				String strategyName="  ";
				
				boolean flag=false;//鏍囪璇ラ厭搴楁湁鏃犱紭鎯犱俊鎭�
				double offinfo=1;
				if(!(it.hasNext()))
				{
					System.out.println("shidaho");
					newOrder.setStrategyOff(offinfo);
					newOrder.setStrategy(-1);
				}
				else
				{
					System.out.println("bbbbbb");
				}
				while(it.hasNext())
				{
					System.out.println("y偶东西");
					hotelstrategyvo=it.next();
					//鎵�閫夐厭搴楁湁浼樻儬
					if(hotelid==hotelstrategyvo.getHotelId())
					{
						
						hotelstrategySet=hotelstrategyvo.getItems();
						if(hotelstrategySet==null)
						{
							if(hotelstrategyvo.getOff()<offinfo)
							{
								offinfo=hotelstrategyvo.getOff();
						
								newOrder.setStrategy(hotelstrategyvo.getId());
								newOrder.setStrategyOff(offinfo);
								
							}
							else
							{
								newOrder.setStrategy(hotelstrategyvo.getId());
								newOrder.setStrategyOff(offinfo);
							}
						}
						for(StrategyItemVO  sta:hotelstrategySet)
						{
							
							//鎵�閫夋埧闂寸被鍨嬫湁浼樻儬
							if(roomType.equals(sta.getRoom().getType()))
							{

								newOrder.setStrategy(hotelstrategyvo.getId());//设置strategyid

								flag=true;
								if(sta.getPriceAfter()<leastPrice)
								{
									leastPrice=sta.getPriceAfter();
									offinfo=sta.getOff();
									newOrder.setStrategyOff(offinfo);
									
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
    
    @FXML
	public void setRoom()
	{
		   int num=0;
		   num=roomNumBox.getSelectionModel().getSelectedItem();
		   roomNumBox.setValue(num);
		  
		   newOrder.setRoomNum(num);
		   double totalNum=0;
		   System.out.println(num);
		   System.out.println(leastPrice);
		   System.out.println(newOrder.getStrategyOff());
		    totalNum=num*leastPrice*newOrder.getStrategyOff();
		    totalMoney.setText(String.valueOf(totalNum));
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		if(serviceFactory==null)
		{
			serviceFactory=RemoteHelper.getInstance().getServiceFactory();
		}
		newOrder=new NewOrderVO();
		Object o=DataController.getInstance().get("UserId");
		userid=(long)o;
		DataController.getInstance().putAndUpdate("UserId", (Object)userid);
		
		DataController.getInstance().putAndUpdate("creatOrderPane", mainPane);// for hotelbrowse's popover
		
		o=DataController.getInstance().get("selectHotel");//得到一个BasicHotelVO
		BasicHotelVO basichotel=(BasicHotelVO)o;
		hotelid=basichotel.getHotelId();
		//DataController.getInstance().put("selectHotel", (Object)hotelid);
		newOrder.setUserId(userid);
		newOrder.setHotelId(hotelid);

		roomNumBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		
		
	}

}
