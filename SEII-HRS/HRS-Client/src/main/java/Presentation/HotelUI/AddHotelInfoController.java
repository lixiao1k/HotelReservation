package Presentation.HotelUI;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import vo.AddHotelVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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


private static String star=null;

@FXML
  public void Submit(ActionEvent e)throws IOException
  {
	boolean area=(summaryArea.getText().equals(""))||(institutionArea.getText().equals(""))||(serveArea.getText().equals(""));
	boolean field=(addressField.getText().equals(""))||(companyField.getText().equals(""))||(addHotelField.getText().equals("")
			        ||hotelName.getText().equals("")||password.getText().equals(""));
   

	  if(area||field)
	  {
		  
		  Stage clickCheck=new Stage();
		  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheckFalse.fxml"));
		  Scene scene=new Scene(root,275,125);
		  clickCheck.setScene(scene);
		  clickCheck.show();
		  //��Ϣ��д������
	  }
	  else
	  {
		 
		  //��װaddHotelVO
		  AddHotelVO add=new AddHotelVO(addressField.getText(),companyField.getText(),addHotelField.getText(),summaryArea.getText(),institutionArea.getText(),serveArea.getText(),star);
		  Stage clickCheck=new Stage();
		  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/FeedbackUI/clickCheck.fxml"));
		  Scene scene=new Scene(root,275,125);
		  clickCheck.setScene(scene);
		  clickCheck.show();
		  //�ύ�ɹ�  
		  //��һ��addHotelVO
	  }
	  
  }
  
  @FXML
  //���ȡ�� ����д�������
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
	starChoice.setItems(FXCollections.observableArrayList("��","���Ǽ�","���Ǽ�","���Ǽ�","���Ǽ�"));
	starChoice.setValue("��");
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
