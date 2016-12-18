package Presentation.OrderUI;

import java.awt.event.ActionEvent;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.service.HotelLogicService;
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
	@FXML DatePicker checkIntime;
	@FXML DatePicker checkOuttime;

	@FXML ComboBox<Integer> roomNumBox;
	@FXML TextField customerName;
	@FXML TextField phoneNumber;
	
	@FXML CheckBox Ifchild;
	@FXML Label strategyType;
	@FXML Label totalMoney;
	@FXML Label orderDes;
	@FXML Label lateCheck;
	@FXML TextField peopleNum;
	private long userid;
	private long hotelid;
	private ServiceFactory  serviceFactory;
	private StrategyLogicService  strategylogic;
	private OrderStrategy ordervo;
	private ListWrapper<HotelStrategyVO> liststrategy;
	private NewOrderVO  neworder;
	private HotelLogicService  hotellogic;
	private  double leastPrice;
	public void commit()
	{
		
		    

		    
		
		 //住客姓名
		    String customer=null;
		    customer=customerName.getText();
		    neworder.setContactName(customer);
		 //手机号码
		    String contact=null;
		    contact=phoneNumber.getText();
		    neworder.setContactWay(contact);
		 //预计入住人数
		    int people=0;
		    people=Integer.parseInt(peopleNum.getText());
		    neworder.setPeople(people);
		 //是否有儿童
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
    	//入住时间
		
		
		LocalDate localcheckin=checkIntime.getValue();
		Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date  checkintime=Date.from(instant);
	  
	//退房时间
	    LocalDate localcheckout=checkOuttime.getValue();
	    		instant=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	    Date  checkouttime=Date.from(instant);
	    
	    neworder.setCheckInTime(checkintime);
	    neworder.setCheckOutTime(checkouttime);
	    
	    //检查策略
	    ListWrapper<HotelItemVO> hotelitemList;//根据hotelid得到该酒店全部房间基本类型
	
			   try {
				 HotelStrategyVO hotelstrategyvo;
				 HotelItemVO    hotelroomvo;//根据hotelid得到该酒店全部房间类型信息
				 
				 
				strategylogic=serviceFactory.getStrategyLogicService();
				ordervo=new OrderStrategy();
				ordervo.setCheckInTime(checkintime);
				ordervo.setHotelId(hotelid);
				ordervo.setUserId(userid);
				
				
				liststrategy=strategylogic.getStrategyForOrder(ordervo);
				Iterator<HotelStrategyVO> it;
				it=liststrategy.iterator();
				
				Set<StrategyItemVO> hotelstrategySet;
				String roomType=(String)DataController.getInstance().get("selectRoomType");//得到所选酒店房型
				
				
				Object o=DataController.getInstance().get("selectHotel");//得到所选酒店的基本信息
				BasicHotelVO bhvo=(BasicHotelVO)o;
				
				Set<HotelItemVO> hotelroom=bhvo.getRooms();
				for(HotelItemVO hivo:hotelroom)
				{
					if(hivo.getRoom().getType()==roomType)
					{
						
						neworder.setRoom(hivo.getRoom());//给订单传房间信息
					}
				}
			
				
				
		
			    hotellogic=serviceFactory.getHotelLogicService();
			    hotelitemList=hotellogic.getRoomInfo(hotelid);
			    
			    Iterator<HotelItemVO> ithotel;
			    ithotel=hotelitemList.iterator();
			    
			    //遍历酒店房间类型得到房间初始价格
			    while(ithotel.hasNext())
			    {
			    	hotelroomvo=ithotel.next();
			    	if(roomType.equals(hotelroomvo.getRoom().getType()))
			    	{
			    		leastPrice=hotelroomvo.getPrice();
			    	
			    	}
			    }
			    
			    
				String strategyName="  ";
				
				boolean flag=false;//标记该酒店有无优惠信息
				double offinfo=1;
				while(it.hasNext())
				{
					hotelstrategyvo=it.next();
					//所选酒店有优惠
					if(hotelid==hotelstrategyvo.getHotelId())
					{
						
						hotelstrategySet=hotelstrategyvo.getItems();
						
						for(StrategyItemVO  sta:hotelstrategySet)
						{
							
							//所选房间类型有优惠
							if(roomType.equals(sta.getRoom().getType()))
							{
								neworder.setStrategy(hotelstrategyvo.getId());//设置strategyid
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
					strategyType.setText("无");
				}
				//最晚入住时间

                SimpleDateFormat sdf=new SimpleDateFormat("yy-MM-dd HH:mm:ss ");
				String lastCheckin;
				Calendar lastCal = Calendar.getInstance();
				lastCal.setTime(checkintime);
				lastCal.add(Calendar.AM_PM, 18);
				Date lastCheck=lastCal.getTime();
				lastCheckin=sdf.format(lastCheck);
				lateCheck.setText("订单最晚执行时间:"+lastCheckin);
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
		DataController.getInstance().put("UserId", (Object)userid);
	

		//hotelid=(long)o;
		//DataController.getInstance().put("selectHotel", (Object)hotelid);
		neworder.setUserId(userid);
		neworder.setHotelId(hotelid);

		roomNumBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
		
		
	}

}
