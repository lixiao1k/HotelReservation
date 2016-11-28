package Presentation.CreditUI;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import vo.CreditVO;

public class CreditBrowseController implements Initializable{
	
	@FXML Label nameLabel;
	@FXML Label creditLabel;
	@FXML ListView<CreditVO> creditListView;
	@FXML
	protected void goBack(ActionEvent e){
		
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
