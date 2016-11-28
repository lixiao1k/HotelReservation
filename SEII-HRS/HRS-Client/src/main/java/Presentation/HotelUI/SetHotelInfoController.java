package Presentation.HotelUI;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetHotelInfoController {
@FXML TextField addressField;
@FXML TextField companyField;
@FXML TextField starField;
@FXML TextArea summaryArea;
@FXML TextArea institutionArea;
@FXML TextArea serveArea;
@FXML Button submitButton;


@FXML
  public void Submit(ActionEvent e)throws IOException
  {
	boolean area=(summaryArea.getText().equals(""))||(institutionArea.getText().equals(""))||(serveArea.getText().equals(""));
	boolean field=(addressField.getText().equals(""))||(companyField.getText().equals(""))||(starField.getText().equals(""));
	  if(area||field)
	  {
		  Stage clickCheck=new Stage();
		  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/clickCheckFalse.fxml"));
		  Scene scene=new Scene(root,275,125);
		  clickCheck.setScene(scene);
		  clickCheck.show();
		  //信息填写不完整
	  }
	  else
	  {
		  Stage clickCheck=new Stage();
		  Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/HotelUI/clickCheck.fxml"));
		  Scene scene=new Scene(root,275,125);
		  clickCheck.setScene(scene);
		  clickCheck.show();
		  //提交成功
	  }
	  
  }
  
  @FXML
  //点击取消 将填写内容清除
  public void cancel(ActionEvent e)
  {
	  addressField.clear();
	  companyField.clear();
	  starField.clear();
	  summaryArea.clear();
	  institutionArea.clear();
	  serveArea.clear();
	  
  }
}
