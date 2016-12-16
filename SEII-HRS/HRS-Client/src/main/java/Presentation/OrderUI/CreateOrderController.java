package Presentation.OrderUI;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import datacontroller.DataController;
import info.ListWrapper;
import info.OrderStrategy;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.service.ServiceFactory;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.HotelStrategyVO;

public class CreateOrderController implements Initializable{
	@FXML DatePicker checkIntime;
	@FXML DatePicker checkOuttime;
	@FXML TextField roomNumField;
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
	public void commit()
	{
		//入住时间
		
		
			LocalDate localcheckin=checkIntime.getValue();
			Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
	        Date  checkintime=Date.from(instant);
		
		//退房时间
		    LocalDate localcheckout=checkOuttime.getValue();
		    		instant=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		    Date  checkouttime=Date.from(instant);
		    
		//预定间数
		    int roomNum=0;
		 
		    roomNum=Integer.parseInt(roomNumField.getText());
		    
		
		 //住客姓名
		    String customer=null;
		    customer=customerName.getText();
		 //手机号码
		    String contact=null;
		    contact=phoneNumber.getText();
		 //预计入住人数
		    int people=0;
		    people=Integer.parseInt(peopleNum.getText());
		 //是否有儿童
		    boolean child=false;
		    if(Ifchild.isSelected())
		    {
		    	child=true;

		    }
		
		  //判断执行策略
		   if(checkIntime.getValue()!=null&&checkOuttime.getValue()!=null&&roomNumField.getText()!=null)
		   {
			   try {
				strategylogic=serviceFactory.getStrategyLogicService();
				ordervo=new OrderStrategy();
				ordervo.setCheckInTime(checkintime);
				ordervo.setHotelId(hotelid);
				ordervo.setUserId(userid);
				liststrategy=strategylogic.getStrategyForOrder(ordervo);
				Iterator<HotelStrategyVO> it;
				it=liststrategy.iterator();
				boolean flag=false;//标记该酒店有无优惠信息
				while(it.hasNext())
				{
					//所选酒店有优惠
					if(hotelid==it.next().getHotelId())
					{
						flag=true;
						
					}
					
					
				}
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		
	}


    @FXML
	public void setRoom()
	{
		   int num=0;
		   num=roomNumBox.getSelectionModel().getSelectedItem();
		   roomNumField.setText(String.valueOf(num));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		if(serviceFactory==null)
		{
			serviceFactory=RemoteHelper.getInstance().getServiceFactory();
		}
		
		Object o=DataController.getInstance().get("UserId");
		userid=(long)o;
		DataController.getInstance().put("UserId", (Object)userid);
		o=DataController.getInstance().get("selectHotel");
		//hotelid=(long)o;
		//DataController.getInstance().put("selectHotel", (Object)hotelid);
		roomNumBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10);
		
		
	}

}
