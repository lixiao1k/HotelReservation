package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Presentation.FeedbackUI.AddContactController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import test.TestManageClientVO;
import test.TestMemberVO;

public class KeepPersonInfoController implements Initializable{
	@FXML Label IDLabel;
	@FXML TextField nameTextField;
	@FXML TextField birthdayTextField;
	@FXML TextField companyNameTextField;
	@FXML ComboBox<String> contactComboBox;
	@FXML Label creditLabel;
	TestManageClientVO testmanageclientvo = new TestManageClientVO();
	TestMemberVO testmembervo =new TestMemberVO();
	
	private boolean isEdit=false;
	private long userid;
	private ObservableList<String> contact =FXCollections.observableArrayList();
	
	
	@FXML 
	protected void add(ActionEvent e){
		if(isEdit){
		    try {
			    FXMLLoader loader =new FXMLLoader(getClass().getClassLoader().getResource("Presentation/FeedbackUI/addContactUI.fxml"));
	            Parent root=loader.load();
			    AddContactController contactcontroller=loader.getController();
	            contactcontroller.setController(this);
			    Scene sence=new Scene(root);
			    Stage stage=new Stage();
			    stage.setScene(sence);
			    stage.show();
		    } catch (IOException e1) {
			    // TODO Auto-generated catch block
			    e1.printStackTrace();
		    }
		}

	}
	@FXML
	protected void delete(ActionEvent e){
		try{
			if(isEdit&&contact.size()!=0){
		        contact.remove(contactComboBox.getValue());		
		        contactComboBox.setItems(contact);
		        contactComboBox.setPromptText(contact.get(0));
		    }
		}catch(IndexOutOfBoundsException er){
			contactComboBox.setPromptText("No Contact");
			
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
	
	//编辑按钮点击后置为可编辑
	@FXML
	protected void edit(ActionEvent e){
		isEdit=true;
		nameTextField.setEditable(true);
		companyNameTextField.setEditable(true);
	}

	@FXML
	protected void ensure(ActionEvent e){
		updateClient();
	}
	
	private void comboinitial(){
		List<String> contactOfList =new ArrayList<String>();
		contactOfList=testmanageclientvo.returnManageClientVO().getPhonenumber();
		for(String temp:contactOfList){
			contact.add(temp);
		}
		
	}
	
	public void addPhoneNumber(String phoneNumber){
		this.contact.add(phoneNumber);
	}
	//存储用户信息
	private void updateClient(){
		
		
	}
	private void updateView(){
		nameTextField.setText(testmanageclientvo.returnManageClientVO().getUsername());
		companyNameTextField.setText(testmanageclientvo.returnManageClientVO().getCompanyname());
		birthdayTextField.setText(testmanageclientvo.returnManageClientVO().getBirthday().toString());
		comboinitial();
		contactComboBox.setItems(contact);
		contactComboBox.setItems(contact);
		contactComboBox.setItems(contact);
		contactComboBox.setPromptText(contact.get(0));
		creditLabel.setText(Integer.toString(testmembervo.returnMemberVO().getCredit()));
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		updateView();
		IDLabel.setText(Long.toString(testmanageclientvo.returnManageClientVO().getUserid()));
		nameTextField.setEditable(false);
		companyNameTextField.setEditable(false);
		contactComboBox.setEditable(false);
		birthdayTextField.setEditable(false);
	}
}
