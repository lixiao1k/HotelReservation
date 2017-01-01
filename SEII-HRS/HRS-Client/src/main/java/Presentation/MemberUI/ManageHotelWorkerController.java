package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import info.BusinessCircle;
import info.BusinessCity;
import info.ListWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.service.HotelLogicService;
import logic.service.MemberLogicService;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.BasicHotelVO;
import vo.ManageHotelVO;
import vo.ManageHotelWorkerVO;

public class ManageHotelWorkerController implements Initializable {
	@FXML private TextField searchHotel;
	@FXML private ListView<ManageHotelVO> hotellist;
	@FXML private TextField workerName;
	@FXML private TextField password;
	@FXML private TableColumn addressCol;
	@FXML private TableColumn companyCol;
	private ServiceFactory servicefactory;
	private MemberLogicService memberlogic;
	private ListWrapper<ManageHotelVO> allhotel;
	private ObservableList<ManageHotelVO>hotelData;
	String[] worker={"xiaoa","xiao b"};
	String[] workerpassword={"sss","sdasd"};
	public void Search()
	{
		
		String hotel=searchHotel.getText();
		try {
			memberlogic=servicefactory.getMemberLogicService();
			allhotel=memberlogic.getAllHotelWorker(hotel);//得到相同名字的全部酒店信息
			List<ManageHotelVO> hotels = new ArrayList<ManageHotelVO>();
			Iterator<ManageHotelVO> it;
			it=allhotel.iterator();
			while(it.hasNext())
			{
				hotels.add(it.next());
			}
			hotelData=FXCollections.observableArrayList(hotels);
			hotellist.setCellFactory(e->new hotellistCell());
			hotellist.setItems(hotelData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	/*	ObservableList<hotelInfo> hotellist=FXCollections.observableArrayList(
				new hotelInfo(worker[0],workerpassword[0]),
				new hotelInfo(worker[1],workerpassword[1])
				);
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
		hotelData.setItems(hotellist);*/
		show();

	}
	
	class hotellistCell extends ListCell<ManageHotelVO>
	{
		public void updateItem(ManageHotelVO item,boolean empty)
		{
			super.updateItem(item, empty);
			if(item!=null)
			{
				GridPane cell=new GridPane();
			     cell.prefWidthProperty().bind(hotellist.widthProperty().subtract(2));
			     Label address=new Label(item.getBussinesscity().getName());//得到城市名称
			     address.setFont(new Font("Youyuan",20));
			     Label circle=new Label(item.getBussinesscircle().getName());//得到商圈名称
			     circle.setFont(new Font("Youyuan",20));
			     Label hotelName=new Label(item.getHotelname());//得到酒店名称
			     hotelName.setFont(new Font("Youyuan",20));
			     
			     cell.add(address,0,0);
			     cell.add(circle, 1, 0);
			     cell.add(hotelName, 2, 0);
			     setGraphic(cell);
			}
			else
			{
				setGraphic(null);
			}
		}
	}
	
	public void Cancel()
	{
		workerName.clear();
		password.clear();
	}
	
	public void Commit() throws IOException
	{
		boolean empty=workerName.getText().equals("")||password.getText().equals("");
		if(empty)
		{
			Notifications.create().owner(searchHotel.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();

		}
		else
		{
			ManageHotelVO vo;
			ManageHotelWorkerVO commitvo=new ManageHotelWorkerVO(0, 0, "", "", "");
			vo=hotellist.getSelectionModel().getSelectedItem();
			long hotelid=vo.getHotelid();
			commitvo.setHotelid(hotelid);
			String nameup=workerName.getText();
			commitvo.setName(nameup);
			String passup=password.getText();
			commitvo.setPassword(passup);
			MemberResultMessage result=memberlogic.updateHotelWorker(commitvo);
			if(MemberResultMessage.SUCCESS==result)
			{
				Notifications.create().owner(searchHotel.getScene().getWindow()).title("提示信息").text("提交成功").show();

				  workerName.clear();
				  password.clear();
			}
			else if(MemberResultMessage.FAIL_PASSWORDLENGTH==result)
			{
				Notifications.create().owner(searchHotel.getScene().getWindow()).title("错误信息").text("密码不符合格式").showError();
				
			}
			
	
		}
	}
	
	public void show()
	{
		 	ManageHotelVO selecvo;
		 	selecvo=hotellist.getSelectionModel().getSelectedItem();
			if(selecvo==null)
			{
				
			}
			else
			{
				workerName.setText(selecvo.getName());
				password.setText(selecvo.getPassword());
			}
	
	
	}
/*	public static class hotelInfo
	{
		private SimpleStringProperty address;
		private SimpleStringProperty company;
		
		private hotelInfo(String address,String company)
		{
			this.address=new SimpleStringProperty(address);
			this.company=new SimpleStringProperty(company);
			
		}
		
		public String getAddress()
		{
			return address.get();
		}
		
		public String getCompany()
		{
			return company.get();
		}
		
		public void setAddress(String add)
		{
			address.set(add);
		}
		
		public void setCompany(String com)
		{
			company.set(com);
		}
	}*/


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(servicefactory==null)
		{
			servicefactory=RemoteHelper.getInstance().getServiceFactory();
		}
		
	}
	
}
