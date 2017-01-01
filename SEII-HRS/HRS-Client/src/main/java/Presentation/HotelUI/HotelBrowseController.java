package Presentation.HotelUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map;
import java.util.Map.Entry;


import Presentation.CreditUI.CreditBrowseController;
import datacontroller.DataController;
import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import info.OrderStrategy;
import info.Rank;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
import vo.SearchHotelVO;
import vo.StrategyItemVO;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.Rating;



public class HotelBrowseController implements Initializable{
	@FXML private TextField searchField;
	@FXML private Button hotelSearchButton;
	@FXML private GridPane mainPane;
	@FXML private ComboBox<String> businessCityBox;
	@FXML private ComboBox<String> circleBox;
	@FXML private CheckComboBox<String> limitBox;
	@FXML private ChoiceBox<String> roomChoiceBox;
	@FXML private ListView<BasicHotelVO> hotelListView;
	@FXML private DatePicker checkin;
	@FXML private DatePicker checkout;
	@FXML private ChoiceBox<String> limitStar;
	@FXML private ChoiceBox<String> limitRank;
	@FXML private ChoiceBox<String> limitPrice;
	@FXML private Button checkHistory;
	@FXML private Button browseDetail;
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
    private ObservableList<String> limitStarList;//为checkcomboBox设置限制条件
    private ObservableList<String> limitRankList;
    private ObservableList<String> limitPriceList;
    private NewOrderVO newOrder;
    private OrderLogicService orderLogic;
    private StrategyLogicService strategyLogic;
    
	public void search(ActionEvent e)
	{
		String searchinfo=searchField.getText();
		String[] aimhotel=searchinfo.split("");
		Map<BasicHotelVO,Integer>map=new HashMap<>();
		List<BasicHotelVO> hotelstextlimit = new ArrayList<BasicHotelVO>();
		try {
			Iterator<BasicHotelVO> it=basicHotel.iterator();
			
			while(it.hasNext())
			{
				BasicHotelVO  bihvo = it.next();
				int count=0;
				for(int i=0;i<aimhotel.length;i++)
				{
					if(bihvo.getHotelName().contains(aimhotel[i]))
					{
						count++;
					}
					
				}
			
				map.put(bihvo, count);
				
			}
			map = sortMapByValue(map);
		    for (Map.Entry<BasicHotelVO, Integer> entry : map.entrySet()) {
		    	hotelstextlimit.add(entry.getKey());
		    }
		    ObservableList<BasicHotelVO> result=FXCollections.observableArrayList(hotelstextlimit);
		    hotelListView.setItems(result);
			
		
		
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
	}
	

	
	  private Map<BasicHotelVO,Integer> sortMapByValue(Map<BasicHotelVO, Integer> map) {
	        List<Map.Entry<BasicHotelVO, Integer>> mapList = new ArrayList<Map.Entry<BasicHotelVO, Integer>>(
	                map.entrySet());
	        Collections.sort(mapList, new Comparator<Map.Entry<BasicHotelVO, Integer>>() {
	            @Override
	            public int compare(Entry<BasicHotelVO, Integer> o1,
	                    Entry<BasicHotelVO, Integer> o2) {
	                return -1*(o1.getValue()-o2.getValue());
	            }
	        });
	        Map<BasicHotelVO,Integer> result = new LinkedHashMap<BasicHotelVO,Integer>();
	        for(Map.Entry<BasicHotelVO, Integer> entry:mapList){
	            result.put(entry.getKey(), entry.getValue());
	        }
	        return result;
	    }
	
	//用组合限制条件搜索
	public void searchInChoice(String value)
	{
		if(hotelListViewData==null){
			System.out.println("datakong");
			return;
		}
		
		if(value==null)
			return;
		else
		{
			String star=limitStar.getSelectionModel().getSelectedItem();
			
			String rank=limitRank.getSelectionModel().getSelectedItem();
		
			String price=limitPrice.getSelectionModel().getSelectedItem();
	
			Rank carank=Rank.NONE;
			switch(star)
			{
			case"一星级":{
				carank=Rank.ONE;break;
			}
			case"二星级":{
				carank=Rank.TWO;break;
			}
			case"三星级":{
				carank=Rank.THREE;break;
			}
			case"四星级":{
				carank=Rank.FOUR;break;
			}
			case"五星级":{
				carank=Rank.FIVE;break;
			}
			case"无":
			{
				carank=Rank.NONE;break;
			}
			}
			double lscore=0;//记录最低评分
			double hscore=0;//记录最高评分
			switch(rank)
			{
			case"4.0~5.0":{
				lscore=4.0;
				hscore=5.0;
				break;
			}
			case"3.0~4.0":{
				lscore=3.0;
				hscore=4.0;
				break;
			}
			case"3.0以下":{
				lscore=0;
				hscore=3.0;
			}
			case"无":{
				lscore=0.0;
				hscore=5.0;
			}
			}
		
			
			double lprice=0;
			double hprice=10000;
			switch(price)
			{
			case"200元以下":{
				lprice=0;
				hprice=200;
				break;
			}
			case"200~500":{
				lprice=200;
				hprice=500;
				break;
			}
			case"500元以上":{
				lprice=500;
				hprice=100000;
				break;
			}
			case"无":{
				lprice=0;
				hprice=100000;
			}
			}
			
			
			try {
				
				Iterator<BasicHotelVO> it=basicHotel.iterator();
				List<BasicHotelVO> hotelslimit = new ArrayList<BasicHotelVO>();
				while(it.hasNext()){
					BasicHotelVO  bihvo = it.next();
					if(bihvo.getRank()==carank&&bihvo.getScore()>lscore&&bihvo.getScore()<hscore)
					{
						double hotelleast=100000;//记录酒店最低价格
				         Set<HotelItemVO> hotIt=bihvo.getRooms();
			                
			                for(HotelItemVO  htlVO:hotIt)
			                {
			                	if(htlVO.getPrice()<hotelleast)
			                	{
			                		hotelleast=htlVO.getPrice();
			                		
			                	}
			                }
			             //   System.out.println(hotelleast);
			                if(hotelleast>lprice&&hotelleast<hprice)
			                {
			                	hotelslimit.add(bihvo);
			                }
					}
					
				}
	
				System.out.println(hotelslimit.size());
				ObservableList<BasicHotelVO> result=FXCollections.observableArrayList(hotelslimit);
			
				hotelListView.setItems(result);
				

				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			
		}
		
	}
	
	//查看预定过的酒店
	public void checkHistory()
	{
		List<BasicHotelVO> hotelsHistory = new ArrayList<BasicHotelVO>();
		
		try {
			Iterator<BasicHotelVO> it=basicHotel.iterator();
			Iterator<Long>historyit=hotelid.iterator();
			BasicHotelVO bavo=null;
			while(it.hasNext())
			{
				bavo=it.next();
				while(historyit.hasNext())
				{
					if(bavo.getHotelId()==historyit.next())
					{
						hotelsHistory.add(bavo);
					}
				}
				
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ObservableList<BasicHotelVO>result=FXCollections.observableArrayList(hotelsHistory);
		hotelListView.setItems(result);
		
	}

	
	public void search(SearchHotelVO vo)
	{
		    try {
		    	if(vo==null)
		    	{
		    		
		    	}
		   // 	System.out.println(vo.getBusinessCircle().getName());
				basicHotel=	hotelbl.getHotels(vo);//根据searchvo返回搜索到的酒店信息
				List<BasicHotelVO> hotels = new ArrayList<BasicHotelVO>();
				System.out.println(basicHotel.size());
				Iterator<BasicHotelVO> it=basicHotel.iterator();
				while(it.hasNext()){
					BasicHotelVO  bihvo = it.next();
					hotels.add(bihvo);
				}
			//	System.out.println("ssss");
				hotelListViewData=FXCollections.observableArrayList(hotels);
				hotelListView.setCellFactory(e->new hotelListCell());
				hotelListView.setItems(hotelListViewData);
				
		    	limitStar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						searchInChoice(newValue);
					}
				});
		    	
		    	limitRank.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						searchInChoice(newValue);
					}
				});
		    	
		    	limitPrice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
						searchInChoice(newValue);
					}
				});
				
				
			//	Object o=(Object)hotelListView.getSelectionModel().getSelectedItem().getHotelId();//得到选中的酒店的id
			//	DataController.getInstance().put("selectHotel", o);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    

	}
	
	class hotelListCell extends ListCell<BasicHotelVO>{
			int star1=0;
			String leastType=null;
			double leastPrice=1000000;
			public void updateItem(BasicHotelVO item,boolean empty)
			{
				super.updateItem(item, empty);
				
				if(item!=null)
				{
	                GridPane cell = new GridPane();
	                cell.prefWidthProperty().bind(hotelListView.widthProperty().subtract(2));
	                Label hotelName = new Label(item.getHotelName()+"/"+item.getScore());//酒店名称+评分
	                hotelName.setFont(new Font("YouYuan",20));
	            //    Label star=new Label(item.getRank().toString());
	            //    star.setFont(new Font("YouYuan",20));
	                if(item.getRank()==Rank.NONE)
	                {
	                
	                	star1=0;
	                }
	                else if(item.getRank()==Rank.ONE)
	                {
	                	star1=1;
	                }
	                else if(item.getRank()==Rank.TWO)
	                {
	                	star1=2;
	                }
	                else if(item.getRank()==Rank.THREE)
	                {
	                	star1=3;
	                }
	                else if(item.getRank()==Rank.FOUR)
	                {
	                	star1=4;
	                }
	                else if(item.getRank()==Rank.FIVE)
	                {
	                	star1=5;
	                }
	                Rating star=new Rating(5,star1);
	            	star.setOnMouseClicked(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							star.setRating(star1);
						}
					});
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
	                	history.setTextFill(Color.AQUAMARINE);
	          
	                }
	                else
	                {
	                	history.setText("未预订");
	                	
	                }
	                Set<HotelItemVO> hotIt=item.getRooms();
	              
	              
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
	                
	                //给下订单按钮加popover界面
	                createOrder.setOnMouseClicked(new EventHandler<MouseEvent>() {
	                   
	                	
	             		@Override
						public void handle(MouseEvent event) {
	          
							createOrder(event, item,leastType,leastPrice);
						}
	               });
	                
	               
	                cell.setHalignment(createOrder, HPos.RIGHT);
	                
	                cell.add(hotelName, 0, 0);
	                cell.add(star, 1, 0);
	                cell.setMargin(star, new Insets(0,0,0,20));
	                cell.add(history, 2, 0);
	                cell.setMargin(history, new Insets(0,0,0,30));
	                cell.add(least, 3, 0);
	                cell.setMargin(least, new Insets(0,0,0,30));
	                cell.add(createOrder, 4, 0);
	                cell.setMargin(createOrder, new Insets(0,0,0,70));
	                setGraphic(cell);
				}
				else
				{
					setGraphic(null);
				//	System.out.println("meiyou");
				}
			}
		
	}
	
	//差一个popover界面
	public void createOrder(MouseEvent e,BasicHotelVO item,String ltype,Double lprice)
	{
		newOrder=new NewOrderVO();
		Set<HotelItemVO>htVO=item.getRooms();
		for(HotelItemVO hteVO:htVO)
		{
			if(hteVO.getRoom().getType().equals(ltype))
			{
				
				newOrder.setRoom(hteVO.getRoom());
			}
		}
	
		long userId=0;
		userId=(long)DataController.getInstance().get("UserId");
		newOrder.setUserId(userId);//传userid
		newOrder.setHotelId(item.getHotelId());//传hotelid
		
		
		PopOver popOver = new PopOver();
		popOver.setDetachable(false);

		popOver.setTitle("下订单");

		GridPane pane=new GridPane();
		
		Label orderInfo=new Label("订单信息");
		orderInfo.setFont(new Font("Youyuan",25));
		
		DatePicker checkin=new DatePicker();
		DatePicker checkout=new DatePicker();

		
		
		Label to=new Label("至");
		to.setFont(new Font("Youyuan",20));;
		Label bookNum=new Label("预定间数:");
		bookNum.setFont(new Font("Youyuan",20));
		
		ComboBox roomNumBox=new ComboBox();
		roomNumBox.setPromptText("房间数量");
		roomNumBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20);
	
		Label contactName=new Label("住客姓名:");
		contactName.setFont(new Font("Youyuan",20));
		TextField contactNameField=new TextField();
		
		
		Label contactWay=new Label("联系方式:");
		contactWay.setFont(new Font("Youyuan",20));
		TextField contactWayField=new TextField();
		
		Label people=new Label("入住人数:");
		people.setFont(new Font("Youyuan",20));
		TextField peopleField=new TextField();
		
		Label child=new Label("有无儿童");
		child.setFont(new Font("Youyuan",20));
		CheckBox ifchild=new CheckBox("有");
		ifchild.setFont(new Font("Youyuan",20));
		
		Label strategy=new Label("所享优惠:");
		strategy.setFont(new Font("Youyuan",20));
		Label strategyText=new Label();
		strategyText.setFont(new Font("Youyuan",20));
		checkout.valueProperty().addListener(new ChangeListener<LocalDate>() {

			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				// TODO Auto-generated method stub
				if(newValue!=null)
				{
					try {
						strategyLogic=serviceFactory.getStrategyLogicService();
						LocalDate localcheckin=checkin.getValue();
						Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				        Date  checkintime=Date.from(instant);
				        
		OrderStrategy	ordervo=new OrderStrategy();
						ordervo.setCheckInTime(checkintime);
						ordervo.setHotelId(item.getHotelId());
						ordervo.setUserId(userid);
						Iterator<BusinessCity> cit = bc.iterator();
						while(cit.hasNext()){
							BusinessCity bcity = cit.next();
							Iterator<BusinessCircle> bcirit = bcity.getCircleIterator();
							boolean flag = true;
							while(bcirit.hasNext()){
								BusinessCircle bcir = bcirit.next();
								if(bcir.getName().equals(circleBox.getSelectionModel().getSelectedItem())){
									ordervo.setBcir(bcir);
									flag = false;
									break;
								}
							}
							if(!flag)
								break;
						}
		ListWrapper<HotelStrategyVO> liststrategy=strategyLogic.getStrategyForOrder(ordervo);
						Iterator<HotelStrategyVO>it=liststrategy.iterator();
						HotelStrategyVO hsVO=null;
						Set<StrategyItemVO> straSet;
						double leastOff=1;
						String strategyDes="";
						while(it.hasNext())
						{
							hsVO=it.next();
							System.out.println("shdadisdaj");
							//有网站针对全部客房的优惠
							if(hsVO.getItems()==null)
							{
								if(hsVO.getOff()<leastOff)
								{
									leastOff=hsVO.getOff();
									strategyDes=hsVO.getName();
									newOrder.setStrategy(hsVO.getId());
									newOrder.setStrategyOff(leastOff);
									
								}
								else
								{
									newOrder.setStrategy(hsVO.getId());
									newOrder.setStrategyOff(leastOff);
								}
							
							}
							else
							{
								straSet=hsVO.getItems();
								for(StrategyItemVO stVO:straSet)
								{
									if(stVO.getRoom().getType().equals(ltype))
									{
										if(stVO.getOff()<leastOff)
										{
											leastOff=stVO.getOff();
											strategyDes=hsVO.getName();
											newOrder.setStrategy(hsVO.getId());
											newOrder.setStrategyOff(leastOff);
											
										}
									}
								}
							}
							
						}
						if(!(it.hasNext()))
						{
							System.out.println("shidaho");
							newOrder.setStrategyOff(leastOff);
							newOrder.setStrategy(-1);
						}
						strategyText.setText(strategyDes);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}
			   
			}
		});
		
		Label order=new Label("订单总价格:");
		order.setFont(new Font("Youyuan",20));
		Label orderTotal=new Label("");
		orderTotal.setFont(new Font("Youyuan",20));
		
   	    roomNumBox.getSelectionModel().selectedIndexProperty().addListener((ObservableValue<? extends Number> ov,Number old_val,Number new_val)->{
	   	 	int num=(int)new_val+1;
			double moneyTotal=0;
			System.out.println(lprice);
			System.out.println(newOrder.getStrategyOff());
			System.out.println(num);
			moneyTotal=num*lprice*newOrder.getStrategyOff();
			orderTotal.setText(String.valueOf(moneyTotal)+"元");
			newOrder.setRoomPrice(lprice);
   	    });
		
		Label empty=new Label();
		Label empty1=new Label();
		
		Button commit=new Button("提交订单");
		commit.setFont(new Font("Youyuan",20));
		commit.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(checkin.getValue()==null||checkout.getValue()==null||roomNumBox.getValue()==null||contactNameField.getText().equals("")
						||contactWayField.getText().equals("")||peopleField.getText().equals(""))
				{
					Notifications.create().owner(hotelSearchButton.getScene().getWindow()).title("错误信息").text("请确认信息完整!").showError();
				}
				else
				{
					System.out.println(1);
					//入住时间
					LocalDate localcheckin=checkin.getValue();
					Instant instant = localcheckin.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			        Date  checkintime=Date.from(instant);
					
			        //预计退房时间
			        LocalDate localcheckout=checkout.getValue();
		    		instant=localcheckout.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		             Date  checkouttime=Date.from(instant);
		             
		             int people=0;
			    	    people=Integer.parseInt(peopleField.getText());
		             
		     	    newOrder.setCheckInTime(checkintime);
		    	    newOrder.setCheckOutTime(checkouttime);
		    	    newOrder.setChild(ifchild.isSelected());
		    	    newOrder.setContactName(contactNameField.getText());
		    	    newOrder.setContactWay(contactWayField.getText());
		    	    newOrder.setPeople(people);
		    	    newOrder.setRoomNum((int)roomNumBox.getValue());
		    	    newOrder.setStrategy(-1);
		    	   try {
		    		   orderLogic=serviceFactory.getOrderLogicService();
		    		   OrderResultMessage result=orderLogic.create(newOrder);
					if(result==OrderResultMessage.SUCCESS)
					   {
						  	popOver.hide();
						  	Notifications.create().owner(commit.getScene().getWindow()).title("成功信息").text("下订单成功").show();
						  	
					   }
					System.out.println(result);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	   
				}
			
			}
			
			
		});
		
		pane.add(orderInfo, 0, 0,2,1);
		pane.setMargin(orderInfo, new Insets(0,0,0,275));
		
		pane.add(checkin, 0, 1);
		pane.setPadding(new Insets(5,5,5,5));
		pane.add(checkout, 2, 1);
		pane.add(to, 1, 1);
		pane.setMargin(to, new Insets(0,50,0,0));
		
		pane.add(bookNum, 0, 2);
		pane.setMargin(bookNum, new Insets(0,0,0,15));
		pane.add(roomNumBox, 0, 2);
		pane.setMargin(roomNumBox, new Insets(0,0,0,120));
		
		pane.add(contactName, 0, 3);
		pane.setMargin(contactName, new Insets(0,0,0,15));
		pane.add(contactNameField, 0, 3, 3, 1);
		pane.setMargin(contactNameField, new Insets(0,50,0,120));
		
		pane.add(contactWay, 0, 4);
		pane.setMargin(contactWay, new Insets(0,0,0,15));
		pane.add(contactWayField, 0, 4, 3, 1);
		pane.setMargin(contactWayField, new Insets(0,50,0,120));
		
		pane.add(people, 0, 5);
		pane.setMargin(people, new Insets(0,0,0,15));
		pane.add(peopleField, 0, 5);
		pane.setMargin(peopleField, new Insets(0,0,0,120));
		
		pane.add(child, 1, 5);
		pane.setMargin(child, new Insets(0,0,0,15));
		pane.add(ifchild, 1, 5,2,1);
		pane.setMargin(ifchild, new Insets(0,0,0,100));
		
		pane.add(strategy, 0, 6);
		pane.setMargin(strategy, new Insets(0,0,0,15));
		pane.add(strategyText, 0, 6,3,1);
		pane.setMargin(strategyText, new Insets(0,0,0,120));
		
		pane.add(empty, 0, 7);
		pane.add(order, 0, 8);
		pane.setMargin(order, new Insets(0,0,0,190));
		pane.add(orderTotal, 1, 8,2,1);
		pane.setMargin(orderTotal, new Insets(0,0,0,0));
		
		pane.add(empty1, 0, 9);
		pane.add(commit, 2, 10);
		pane.setMargin(commit, new Insets(10,0,0,120));
		
		popOver.setContentNode(pane);
		popOver.show(((Node)e.getSource()),e.getScreenX(),e.getScreenY());
		
	}
	
	//浏览酒店详细信息
	public void goHotelDetail()
	{
		//没选中酒店
		if(hotelListView.getSelectionModel().getSelectedItem()==null)
		{
			Notifications.create().owner(searchField.getScene().getWindow()).title("提示信息").text("请选择酒店!").showWarning();
		}
		else
		{
			BasicHotelVO selecthotel;
			selecthotel=hotelListView.getSelectionModel().getSelectedItem();
			DataController.getInstance().putAndUpdate("selectHotel", selecthotel);
			GridPane client=(GridPane)searchField.getScene().getWindow().getScene().getRoot();
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/HotelUI/DetailHotelInfo.fxml"));
			try {
				Parent hoteldetailBrowse = loader.load();
				hoteldetailBrowse.getProperties().put("NAME","HotelDetailPane" );
				ObservableList<Node> list =client.getChildren();
				for(Node node:list){
					String value=(String)node.getProperties().get("NAME");
					if(value!=null&&value.contains("Pane")){
						DataController.getInstance().putAndUpdate("HotelSearchPane", node);
						list.remove(node);
						break;
					}
				}
				client.add(hoteldetailBrowse, 3, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
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

		//	System.out.println("啥也没有");

		}
		else
		{
			selectCircle=circleBox.getSelectionModel().getSelectedItem().toString();
			System.out.println(selectCircle);
		    searchvo.setBusinessCity(city);
		    for(BusinessCircle bcir:setcircle){
		    	if(selectCircle.equals(bcir.getName()))
		    	{
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
	//		hotelSearchButton.getStylesheets().add(getClass().getClassLoader().getResource("Presentation/MainUI/ClientButton.css").toExternalForm());
		
			Iterator<BusinessCity> it = bc.iterator();
			while(it.hasNext())
			{
				BusinessCity bci = it.next();
				set.add(bci.getName());
			}
			ObservableList<String> cities = FXCollections.observableArrayList(set);
			
			businessCityBox.getItems().addAll(cities);
			
			
		/*	limitList=FXCollections.observableArrayList();
			limitList.add("星级");
			limitList.add("评分");
			limitList.add("房间价格");
			limitBox=new CheckComboBox(limitList);

			mainPane.add(limitBox, 0, 0);
			mainPane.setMargin(limitBox, new Insets(150,0,0,150));*/
			limitStarList=FXCollections.observableArrayList();
			limitStarList.add("无");
			limitStarList.add("一星级");
			limitStarList.add("二星级");
			limitStarList.add("三星级");
			limitStarList.add("四星级");
			limitStarList.add("五星级");
			limitStar.setItems(limitStarList);
			limitStar.setValue("三星级");
			
			limitRankList=FXCollections.observableArrayList();
			limitRankList.add("无");
			limitRankList.add("4.0~5.0");
			limitRankList.add("3.0~4.0");
			limitRankList.add("3.0以下");
			limitRank.setItems(limitRankList);
			limitRank.setValue("4.0~5.0");
			
			limitPriceList=FXCollections.observableArrayList();
			limitPriceList.add("无");
			limitPriceList.add("200元以下");
			limitPriceList.add("200~500");
			limitPriceList.add("500元以上");
			limitPrice.setItems(limitPriceList);
			limitPrice.setValue("200元以下");
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
