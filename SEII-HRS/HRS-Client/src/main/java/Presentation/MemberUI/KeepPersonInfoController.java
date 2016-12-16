package Presentation.MemberUI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.management.Notification;

import org.controlsfx.control.Notifications;

import Presentation.CreditUI.CreditBrowseController;
import Presentation.FeedbackUI.AddContactController;
import Presentation.MainUI.ClientMainUIController;
import datacontroller.DataController;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import test.TestManageClientVO;
import test.TestMemberVO;
import vo.ManageClientVO;
import vo.MemberVO;

public class KeepPersonInfoController implements Initializable{

	@FXML Label IDLabel;
	@FXML TextField nameTextField;
	@FXML TextField birthdayTextField;
	@FXML TextField companyNameTextField;
	@FXML ComboBox<String> contactComboBox;
	@FXML Label creditLabel;
	private ServiceFactory serviceFactory;
	private boolean isEdit=false;
	private long userid;
	private String username;
	private int credit;
	private String phone;
	private MemberVO membervo;
	private ManageClientVO clientvo;
	private ObservableList<String> contact =FXCollections.observableArrayList();
	private ClientMainUIController clientmainuicontroller;
	
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
		}else{
			Notifications.create().title("閿熸枻鎷烽敓鏂ゆ嫹").text("閿熸枻鎷烽敓楗虹鎷烽敓鏂ゆ嫹鍡夘叏鎷烽敓鑴氾拷").showWarning();
		}

	}
	@FXML
	protected void delete(ActionEvent e){
//		try{
//			if(isEdit&&contact.size()!=0){
//				phonenumber.remove(contactComboBox.getValue());
//		        contact.remove(contactComboBox.getValue());		
//		        contactComboBox.setItems(contact);
//		        contactComboBox.setPromptText(contact.get(0));
//		    }
//		}catch(IndexOutOfBoundsException er){
//			contactComboBox.setPromptText("No Contact");
//			
//		}


	}
	
	
	
	@FXML
	protected void goCreditBrowse(ActionEvent e){
		try {
			GridPane clientmain=(GridPane)IDLabel.getScene().getWindow().getScene().getRoot();
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/CreditUI/CreditBrowse.fxml"));
			Parent creditBrowse = loader.load();
			CreditBrowseController creditcontroller=loader.getController();
			creditcontroller.setKeepPersonInfoController(this);
			creditcontroller.setBaseInfo(this.userid);
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
	
	//閿熸磥杈戦敓鏂ゆ嫹閽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熻娇顏庢嫹鏉�鍡夛拷
	@FXML
	protected void edit(ActionEvent e){
		Notifications.create().title("閿熸磥杈�").text("閿熸枻鎷烽敓鏂ゆ嫹鍡夛拷").showConfirm();
		isEdit=true;
		nameTextField.setEditable(true);
		companyNameTextField.setEditable(true);
	}

	@FXML
	protected void save(ActionEvent e){
		updateClient();
	}
	
	private void comboinitial(){
//		for(String temp:phonenumber){
//			contact.add(temp);
//		}
		
	}
	
	public void addPhoneNumber(String phoneNumber){
		System.out.println("get");
//		this.phonenumber.add(phoneNumber);
		this.contact.add(phoneNumber);
	}
	//閿熻姤鍌ㄩ敓鐭紮鎷烽敓鏂ゆ嫹鎭�
	private void updateClient(){
		
		
	}
	private void updateView(){
		nameTextField.setText(username);
		companyNameTextField.setText(clientvo.getCompanyname());
		birthdayTextField.setText(clientvo.getBirthday().toString());
		comboinitial();
		contactComboBox.setItems(contact);
		contactComboBox.setPromptText(contact.get(0));
		creditLabel.setText(Integer.toString(credit));
	}
	
	public void setClientMainUIController(ClientMainUIController controller){
		this.clientmainuicontroller=controller;
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		try{
			userid=(long) DataController.getInstance().get("UserId");
			membervo=serviceFactory.getMemberLogicService().getInfo((long)2);
			username=membervo.getName();
			credit=membervo.getCredit();
			clientvo=serviceFactory.getMemberLogicService().getClient(username);
			phone =clientvo.getPhonenumber();
		updateView();
		IDLabel.setText(Long.toString(userid));
		nameTextField.setEditable(false);
		companyNameTextField.setEditable(false);
		contactComboBox.setEditable(false);
		birthdayTextField.setEditable(false);
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
