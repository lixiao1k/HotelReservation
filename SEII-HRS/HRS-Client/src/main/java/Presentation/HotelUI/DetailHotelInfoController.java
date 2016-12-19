package Presentation.HotelUI;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import org.controlsfx.control.SegmentedButton;

import datacontroller.DataController;
import info.OrderStatus;
import info.Rank;
import info.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.service.HotelLogicService;
import rmi.RemoteHelper;
import util.DoubleUtil;
import vo.BasicHotelVO;
import vo.ExtraHotelVO;
import vo.HotelCommentVO;
import vo.HotelItemVO;
import vo.OrderVO;

public class DetailHotelInfoController implements Initializable{
	private HotelLogicService hotelService;
	private BasicHotelVO hotel;
	private long userId = -1;
	private ExtraHotelVO ehvo;
	private ListView<HotelItemVO> roomListView;
	private ListView<HotelCommentVO> commentListView;
	private ObservableList<HotelItemVO> roomData;
	private ObservableList<HotelCommentVO> commentData;
	private ObservableList<OrderVO> orderData;
	private SegmentedButton sbutton;
	private ObservableList<ToggleButton> buttons;
	private Rating rating;
	private Map<Rank,Integer> rankMap;
	@FXML private GridPane root;
	@FXML private ImageView imageView;
	@FXML private Text hotelName;
	@FXML private Text address;
	@FXML private Label score;
	@FXML private VBox rankVBox;
	@FXML private Button returnButton;
	private void setBaseInfo() throws RemoteException{
		if(hotelService==null)
			hotelService = RemoteHelper.getInstance().getServiceFactory().getHotelLogicService();
		Object o = DataController.getInstance().get("selectHotel");
		if(o!=null)
			hotel = (BasicHotelVO) o;
		o = DataController.getInstance().get("selectUser");
		if(o!=null)
			userId = (long) o;
		buttons = FXCollections.observableArrayList();
		ToggleButton b1 = new ToggleButton("酒店详情");
		ToggleButton b2 = new ToggleButton("客房信息");
		ToggleButton b3 = new ToggleButton("酒店评论");
		ToggleButton b4 = new ToggleButton("订单信息");
		roomListView = new ListView<HotelItemVO>();
		commentListView = new ListView<HotelCommentVO>();
		b1.setOnAction((ActionEvent e)->{
			try {
				DataController.getInstance().put("description", hotel.getDescription());
				DataController.getInstance().put("service", hotel.getService());
				DataController.getInstance().put("facility", hotel.getFacility());
				Parent p = FXMLLoader.load(getClass().getResource("DetailHotelDetailInfo.fxml"));
				ObservableList<Node> nodes = root.getChildren();
				for(Node node:nodes){
					String t = (String) node.getProperties().get("NAME");
					if(t!=null&&t.contains("Data")){
						root.getChildren().remove(node);
						break;
					}
				}
				p.getProperties().put("NAME", "Data");
				root.add(p, 0, 2,2,1);
			} catch (Exception e1) {
				e1.printStackTrace();
				Notifications.create().owner(root.getScene().getWindow()).title("初始化").text("初始化失败").showError();
			}
		});
		b2.setOnAction((ActionEvent e)->{
			ObservableList<Node> nodes = root.getChildren();
			for(Node node:nodes){
				String t = (String) node.getProperties().get("NAME");
				if(t!=null&&t.contains("Data")){
					root.getChildren().remove(node);
					break;
				}
			}
			root.add(roomListView, 0, 2,2,1);
		});
		b3.setOnAction((ActionEvent e)->{
			ObservableList<Node> nodes = root.getChildren();
			for(Node node:nodes){
				String t = (String) node.getProperties().get("NAME");
				if(t!=null&&t.contains("Data")){
					root.getChildren().remove(node);
					break;
				}
			}
			root.add(commentListView, 0, 2,2,1);
		});
		b4.setOnAction((ActionEvent e)->{
			
			try {
				DataController.getInstance().putAndUpdate("OrderData", orderData);
				Parent p = FXMLLoader.load(getClass().getResource("DetailHotelOrderInfo.fxml"));
				ObservableList<Node> nodes = root.getChildren();
				for(Node node:nodes){
					String t = (String) node.getProperties().get("NAME");
					if(t!=null&&t.contains("Data")){
						root.getChildren().remove(node);
						break;
					}
				}
				p.getProperties().put("NAME", "Data");
				root.add(p, 0, 2,2,1);
			} catch (Exception e1) {
				Notifications.create().owner(root.getScene().getWindow()).title("初始化").text("初始化失败").showError();
				e1.printStackTrace();
			}
			
		});
		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);
		sbutton = new SegmentedButton();
		sbutton.getButtons().addAll(buttons);
		sbutton.getStyleClass().add(SegmentedButton.STYLE_CLASS_DARK);
		root.add(sbutton, 0, 1,2,1);
		root.setHalignment(sbutton, HPos.CENTER);
		root.setHgrow(sbutton, Priority.ALWAYS);
		Image returnPic = new Image(getClass().getClassLoader().getResourceAsStream("Presentation/MainUI/return40x40.png"));
		returnButton.setGraphic(new ImageView(returnPic));
		returnButton.setBorder(null);
		returnButton.setBackground(null);
		returnButton.setOnAction((ActionEvent e)->{
			
		});
		rankMap = new HashMap<>();
		rankMap.put(Rank.ONE, 1);
		rankMap.put(Rank.TWO, 2);
		rankMap.put(Rank.THREE, 3);
		rankMap.put(Rank.FOUR, 4);
		rankMap.put(Rank.FIVE, 5);
		commentListView.setCellFactory(e-> new HotelCommentListCell());
		roomListView.setCellFactory(e->new HotelRoomClientListCell());
		commentListView.getProperties().put("NAME", "Data");
		roomListView.getProperties().put("NAME", "Data");
		if(hotel.getImage()==null){
			System.out.println(1);
			Image image = new Image(getClass().getClassLoader().getResourceAsStream("Presentation/HotelUI/DefaultHotelPic.jpg"));
			imageView.setImage(image);
		}else{
			WritableImage pic = null;
			imageView.setImage(SwingFXUtils.toFXImage((BufferedImage) ehvo.getImage(), pic));
		}
		hotelName.setText(hotel.getHotelName());
		address.setText(hotel.getAddress());
		score.setText(DoubleUtil.format(hotel.getScore())+"");
		rating = new Rating(5,rankMap.get(hotel.getRank()));
		rating.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				rating.setRating(rankMap.get(hotel.getRank()));
			}
		});
		rankVBox.getChildren().add(rating);
		rankVBox.setMargin(rating, new Insets(25,0,0,0));
		//DataController.getInstance().putAndUpdate("Root", root.getScene().getRoot());
		//test
		HotelCommentVO hcvo = new HotelCommentVO();
		hcvo.setComment("1111");
		hcvo.setDate(new Date());
		hcvo.setGrade(3);
		hcvo.setHide(true);
		hcvo.setName(null);
		Room room = new Room();
		room.setType("大床房");
		hcvo.setRoom(room);
		List<HotelCommentVO> vo = new ArrayList<>();
		vo.add(hcvo);
		commentListView.setItems(FXCollections.observableArrayList(vo));
		HotelItemVO hivo = new HotelItemVO();
		hivo.setDate(new Date());
		hivo.setNum(200);
		hivo.setTotal(344);
		hivo.setPrice(36.7);
		hivo.setRoom(room);
		List<HotelItemVO> vo2 = new ArrayList<>();
		vo2.add(hivo);
		roomListView.setItems(FXCollections.observableArrayList(vo2));
		OrderVO orderVO = new OrderVO();
		orderVO.setOrderNum("SE-12345");
		orderVO.setStatus(OrderStatus.EXECUTED);
		List<OrderVO> vo3 = new ArrayList<>();
		vo3.add(orderVO);
		ObservableList<OrderVO> olist = FXCollections.observableArrayList(vo3);
		DataController.getInstance().putAndUpdate("OrderData", olist);
		DataController.getInstance().putAndUpdate("Root", root.getScene().getWindow().getScene().getRoot());
	}
	private void initialInfo() throws RemoteException{
		if(hotel==null)
			return;
		long hotelId = hotel.getHotelId();
		ehvo = hotelService.getExtraHotelDetail(hotelId, userId);
		if(ehvo==null){
			return;
		}
		orderData = FXCollections.observableArrayList(ehvo.getBookedOrders());
		commentData = FXCollections.observableArrayList(ehvo.getComments());
		roomData = FXCollections.observableArrayList(hotel.getRooms());
		commentListView.setItems(commentData);
		roomListView.setItems(roomData);

	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{	
			//test
			hotel=new BasicHotelVO();
			hotel.setDescription("上海铂睿全套房酒店位于上海市静安寺南京西路的顶级商务区域，周围众多顶级奢侈品牌汇集，甲级办公楼林立，恒隆广场、中信泰富广场、梅龙镇广场也都近在咫尺。得天独厚的地理位置，便捷的地铁2号和7号线，可以方便快捷地往返上海浦东、虹桥两个国际机场及国家会议中心。出门即可快速前往上海的各个区域。");
			hotel.setService("上海铂睿全套房酒店每间房间配有无线上网服务，设施配备齐全。");
			hotel.setFacility("免费旅游交通图(可赠送)有可无线上网的公共区域 免费中餐厅收费停车场");
			hotel.setRank(Rank.FIVE);
			hotel.setAddress("江苏省南京市");
			hotel.setHotelName("如家");
			hotel.setScore(75.222222);
			//
			setBaseInfo();
			initialInfo();
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
