package Presentation.MemberUI;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import org.controlsfx.control.Notifications;
import Presentation.CreditUI.CreditBrowseController;
import datacontroller.DataController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.BasicMemberVO;
import vo.MemberVO;

public class KeepPersonInfoController implements Initializable{

	@FXML TextField nameTextField;
	@FXML TextField phoneTextField;
	@FXML Label creditLabel;
	private ServiceFactory serviceFactory;
	private long userid;
	private String username;
	private int credit;
	private String phone;
	private MemberVO membervo;
		
	
	@FXML
	protected void goCreditBrowse(ActionEvent e){
		try {
			GridPane clientmain=(GridPane)nameTextField.getScene().getWindow().getScene().getRoot();
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Presentation/CreditUI/CreditBrowse.fxml"));
			Parent creditBrowse = loader.load();
//			CreditBrowseController creditcontroller=loader.getController();
//			creditcontroller.setKeepPersonInfoController(this);
//			creditcontroller.setBaseInfo(this.userid);
			creditBrowse.getProperties().put("NAME","CreditBrowsePane" );
			ObservableList<Node> list =clientmain.getChildren();
			for(Node node:list){
				String value=(String)node.getProperties().get("NAME");
				if(value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(creditBrowse, 3, 1);
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@FXML
	protected void edit(ActionEvent e){
		Notifications.create().title("提示").text("可以编辑").showConfirm();
		nameTextField.setEditable(true);
		phoneTextField.setEditable(true);
	}
	@FXML
	protected void save(ActionEvent e) throws RemoteException{
		updateClient();
		nameTextField.setEditable(false);
		phoneTextField.setEditable(false);
	}
	private void updateClient() throws RemoteException{
		BasicMemberVO vo =new BasicMemberVO(userid, phoneTextField.getText(), nameTextField.getText());
		MemberResultMessage result=serviceFactory.getMemberLogicService().changeInfo(vo);
		if(result==MemberResultMessage.SUCCESS){
		    Notifications.create().title("提示").text("保存成功").showConfirm();
		}else{
			Notifications.create().title("提示").text("保存失败").showConfirm();
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(serviceFactory==null)
			serviceFactory = RemoteHelper.getInstance().getServiceFactory();
		try{
			userid=(long) DataController.getInstance().get("UserId");
			membervo=serviceFactory.getMemberLogicService().getInfo(userid);
			username=membervo.getName();
			credit=membervo.getCredit();
			phone=membervo.getPhone();
		    nameTextField.setText(username);
		    phoneTextField.setText(phone);	
		    creditLabel.setText(Integer.toString(credit));
		    nameTextField.setEditable(false);
		    phoneTextField.setEditable(false);
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
