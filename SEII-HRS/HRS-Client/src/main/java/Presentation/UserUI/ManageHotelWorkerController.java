package Presentation.UserUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.service.HotelLogicService;
import rmi.RemoteHelper;

public class ManageHotelWorkerController implements Initializable {
	@FXML TextField searchHotel;
	@FXML TableView<hotelInfo> hotelData;
	@FXML TextField workerName;
	@FXML TextField password;
	@FXML TableColumn addressCol;
	@FXML TableColumn companyCol;
	
	String[] worker={"xiaoa","xiao b"};
	String[] workerpassword={"sss","sdasd"};
	public void Search()
	{
		
		String hotel=searchHotel.getText();
		//����Member.getAllHotelWorker�ӿ� �õ�info  
		ObservableList<hotelInfo> hotellist=FXCollections.observableArrayList(
				new hotelInfo(worker[0],workerpassword[0]),
				new hotelInfo(worker[1],workerpassword[1])
				);
		addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
		companyCol.setCellValueFactory(new PropertyValueFactory<>("company"));
		hotelData.setItems(hotellist);
		show();//���÷����Ӳ��� ��workerinfo����ȥ

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
			 Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheckFalse.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
		}
		else
		{
			String nameup=workerName.getText();
			String passup=password.getText();
			//װ��ManageHotelWorkerVO
			Stage clickCheck=new Stage();
			  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
			  Scene scene=new Scene(root,275,125);
			  clickCheck.setScene(scene);
			  clickCheck.show();
			  workerName.clear();
			  password.clear();
		}
	}
	
	public void show()
	{
		int num=hotelData.getSelectionModel().getSelectedIndex();
			if(num==-1)
			{
				
			}
			else
			{
				workerName.setText(worker[num]);
				password.setText(workerpassword[num]);
			}
	
	
	}
	public static class hotelInfo
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
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			System.out.println(RemoteHelper.getInstance().getServiceFactory().getHotelLogicService());
			ListWrapper<BusinessCity> bc = RemoteHelper.getInstance().getServiceFactory().getHotelLogicService().getCity();
			System.out.println(2);
			if(bc==null)
				System.out.println("wrong");
			Iterator<BusinessCity> it = bc.iterator();
			while(it.hasNext()){
				BusinessCity bcc = it.next();
				System.out.println(bcc.getName());
				Iterator<BusinessCircle> bci = bcc.getCircleIterator();
				while(bci.hasNext()){
					BusinessCircle bcci = bci.next();
					System.out.println(bcci.getName());
				}
				System.out.println("----------------------");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
}
