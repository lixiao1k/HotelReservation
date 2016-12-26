package Presentation.MemberUI;
/*
 * @author Shelton Lee 151250084
 */
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.plaf.ToolTipUI;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import logic.service.ServiceFactory;
import resultmessage.MemberResultMessage;
import rmi.RemoteHelper;
import vo.BasicMemberVO;
import vo.MemberVO;
import vo.VIPVO;

public class KeepPersonInfoController implements Initializable{

	@FXML TextField nameTextField;
	@FXML TextField phoneTextField;
	@FXML Label creditLabel;
	@FXML Label VIPLabel;
	@FXML Button editButton;
	@FXML Button saveButton;
	@FXML Button browseButton;
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
			creditBrowse.getProperties().put("NAME","CreditBrowsePane" );
			ObservableList<Node> list =clientmain.getChildren();//remove this pane and add the CreditUI's pane
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
		Notifications.create().title("INFO").text("Now can edit").showConfirm();
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
		if(phoneTextField.getText().length()!=11||nameTextField.getText().length()<1){
			return;
		}
		BasicMemberVO vo =new BasicMemberVO(userid, phoneTextField.getText(), nameTextField.getText());
		MemberResultMessage result=serviceFactory.getMemberLogicService().changeInfo(vo);
		if(result==MemberResultMessage.SUCCESS){
		    Notifications.create().title("INFO").text("SUCCESS").showConfirm();
		}else{
			Notifications.create().title("INFO").text("FAIL").showConfirm();
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
		    final Tooltip tip = new Tooltip();
		    tip.setText("Please press the edit button first");
		    nameTextField.setTooltip(tip);
		    phoneTextField.setTooltip(tip);
		    if(membervo.isVIP()){
		        Image image=new Image(getClass().getResourceAsStream("VIP.png"));
		        VIPLabel.setGraphic(new ImageView(image));
		        final Tooltip tooltip=new Tooltip();
		        tooltip.setText("VIP user"+membervo.getName()+"Welcome!"+"\n"+
		                        "Your credit deposit is:"+membervo.getCredit());
		        VIPLabel.setTooltip(tooltip);
		    }
		    //UI beauty
		    Image imageEdit=new Image(getClass().getResourceAsStream("edit.png"));
		    editButton.setGraphic(new ImageView(imageEdit));
		    editButton.getStylesheets().add(getClass().getResource("buttonFile.css").toExternalForm());
		    Image imageSave = new Image(getClass().getResourceAsStream("save.png"));
		    saveButton.setGraphic(new ImageView(imageSave));
		    saveButton.getStylesheets().add(getClass().getResource("buttonFile.css").toExternalForm());
		    Image imageBrowse = new Image(getClass().getResourceAsStream("browse.png"));
		    browseButton.setGraphic(new ImageView(imageBrowse));
		    browseButton.getStylesheets().add(getClass().getResource("buttonFile.css").toExternalForm());
		}catch (RemoteException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
