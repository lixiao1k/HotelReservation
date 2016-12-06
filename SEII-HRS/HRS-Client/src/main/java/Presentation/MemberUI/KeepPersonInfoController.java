package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import test.TestManageClientVO;

public class KeepPersonInfoController implements Initializable{
	@FXML Label IDLabel;
	@FXML TextField nameTextField;
	@FXML TextField birthdayTextField;
	@FXML TextField companyNameTextField;
	@FXML ComboBox<String> contactComboBox;
	@FXML Label creditLabel;
	TestManageClientVO testmanageclientvo = new TestManageClientVO();
	
	private boolean isEdit=false;
	private long userid;
	
	
	@FXML 
	protected void add(ActionEvent e){
		if(isEdit){
			contactComboBox.setEditable(true);
		}
	}
	
	@FXML
	protected void goCreditBrowse(ActionEvent e){
		try {
			GridPane clientmain=(GridPane)IDLabel.getScene().getWindow().getScene().getRoot();
			Parent creditBrowse = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/CreditUI/CreditBrowse.fxml"));
			creditBrowse.getProperties().put("NAME","CreditBrowsePane" );
			ObservableList<Node> list =clientmain.getChildren();
			for(Node node:list){
				String value=(String)node.getProperties().get("NAME");
				if(value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(creditBrowse, 2, 1);
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	}
	
	@FXML
	protected void edit(ActionEvent e){
		isEdit=true;
		nameTextField.setEditable(true);
		companyNameTextField.setEditable(true);
//		contactComboBox.setEditable(true);
	}

	@FXML
	protected void ensure(ActionEvent e){
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO Auto-generated method stub
		nameTextField.setText(testmanageclientvo.returnManageClientVO().getUsername());
		companyNameTextField.setText(testmanageclientvo.returnManageClientVO().getCompanyname());
		birthdayTextField.setText(testmanageclientvo.returnManageClientVO().getBirthday().toString());
		IDLabel.setText(Long.toString(testmanageclientvo.returnManageClientVO().getUserid()));
		nameTextField.setEditable(false);
		companyNameTextField.setEditable(false);
		contactComboBox.setEditable(false);
		birthdayTextField.setEditable(false);
		
	}
}
