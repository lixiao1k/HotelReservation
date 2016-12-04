package Presentation.UserUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
		// TODO Auto-generated method stub
		
		
	}
	
}
