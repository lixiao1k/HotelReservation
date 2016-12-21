package Presentation.HotelUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.AddHotelVO;

public class AddHotelInfoController implements Initializable{
@FXML TextField addressField;
@FXML TextField companyField;
@FXML ChoiceBox starChoice;
@FXML TextArea summaryArea;
@FXML TextArea institutionArea;
@FXML TextArea serveArea;
@FXML TextField hotelName;
@FXML TextField password;
@FXML TextField addHotelField;
@FXML ChoiceBox provinceChoice;
@FXML ChoiceBox cityChoice;

 private static String star=null;
 private AddHotelVO addHotel;

@FXML
  public void Submit(ActionEvent e)throws IOException
  {
	boolean area=(summaryArea.getText().equals(""))||(institutionArea.getText().equals(""))||(serveArea.getText().equals(""));
	boolean field=(addressField.getText().equals(""))||(companyField.getText().equals(""))||(addHotelField.getText().equals("")
			        ||hotelName.getText().equals("")||password.getText().equals(""));
   

	  if(area||field)
	  {
		  
			Notifications.create().owner(addHotelField.getScene().getWindow()).title("错误信息").text("填写内容不能为空").showError();

		 
	  }
	  else
	  {
		 
		
		  addHotel.setAddress(addressField.getText());
		  addHotel.setDescription(summaryArea.getText());
		  addHotel.setFacility(institutionArea.getText());
		  addHotel.setName(addHotelField.getText());
		  addHotel.setMemberName(hotelName.getText());
		  addHotel.setPassword(password.getText());

		  Stage clickCheck=new Stage();
		  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
		  Scene scene=new Scene(root,275,125);
		  clickCheck.setScene(scene);
		  clickCheck.show();
		  //锟结交锟缴癸拷  
		  //锟斤拷一锟斤拷addHotelVO
	  }
	  
  }
  
  @FXML
  //锟斤拷锟饺★拷锟� 锟斤拷锟斤拷写锟斤拷锟斤拷锟斤拷锟�
  public void cancel(ActionEvent e)
  {
	  addressField.clear();
	  companyField.clear();
	  
	  summaryArea.clear();
	  institutionArea.clear();
	  serveArea.clear();
	  addHotelField.clear();
  }

@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	starChoice.setItems(FXCollections.observableArrayList("无","一星级","二星级","三星级","四星级","五星级"));
	starChoice.setValue("无");
	star=(String) starChoice.getValue();
	starChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			// TODO Auto-generated method stub

			if(newValue!=null)
			{
				
			    star=newValue;
			}
		
		
		}
		
	});
}
}
