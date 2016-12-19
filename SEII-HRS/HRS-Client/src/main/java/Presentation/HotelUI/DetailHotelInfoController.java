package Presentation.HotelUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import datacontroller.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import logic.service.HotelLogicService;
import rmi.RemoteHelper;
import vo.ExtraHotelVO;
import vo.HotelCommentVO;
import vo.HotelVO;
import vo.OrderVO;

public class DetailHotelInfoController implements Initializable{
	private HotelLogicService hotelService;
	private HotelVO hotel;
	private long userId = -1;
	private ExtraHotelVO ehvo;
	private ObservableList<HotelCommentVO> commentData;
	private ObservableList<OrderVO> orderData;
	private ImageView imageView;
	@FXML private ListView<HotelCommentVO> commentListView;
	@FXML private ListView<OrderVO> orderListView;
	
	private void setBaseInfo() throws RemoteException{
		if(hotelService==null)
			hotelService = RemoteHelper.getInstance().getServiceFactory().getHotelLogicService();
		Object o = DataController.getInstance().get("selectHotel");
		if(o!=null)
			hotel = (HotelVO) o;
		o = DataController.getInstance().get("selectUser");
		userId = (long) o;
	}
	private void initialInfo() throws RemoteException{
		if(hotel==null)
			return;
		long hotelId = hotel.getHid();
		ehvo = hotelService.getExtraHotelDetail(hotelId, userId);
		if(ehvo==null){
			Notifications.create().owner(commentListView.getScene().getWindow()).title("初始化").text("初始化错误！").showError();
			return;
		}
		orderData = FXCollections.observableArrayList(ehvo.getBookedOrders());
		commentData = FXCollections.observableArrayList(ehvo.getComments());
		orderListView.setItems(orderData);
		commentListView.setItems(commentData);
		if(ehvo.getImage()==null){
			
		}else{
			
		}
		
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{
			setBaseInfo();
			initialInfo();
		}catch(RemoteException e){
			e.printStackTrace();
		}
	}

}
