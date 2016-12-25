package Presentation.OrderUI;


import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import logic.service.ServiceFactory;
import resultmessage.CommentResultMessage;
import rmi.RemoteHelper;
import vo.CommentVO;
import vo.OrderVO;

public class CommentController implements Initializable{
    @FXML ComboBox<String> degreeComboBox;
    @FXML TextArea commentTextArea;
    @FXML RadioButton anonymousRadioButton;
    @FXML GridPane commentPane;
    @FXML Button updateButton;
    ObservableList<String> degreeData=FXCollections.observableArrayList("1","2","3","4","5");
    private OrderVO vo; 
    private long userid;
    private ServiceFactory servicefactory;
    @FXML
    protected void save(ActionEvent e) throws RemoteException{
    	if(degreeComboBox.getValue()==null){
    		return;
    	}
    	int grade=Integer.parseInt(degreeComboBox.getValue());
    	long orderid=vo.getOrderId();
    	CommentVO vo=new CommentVO(grade, commentTextArea.getText(), userid, (long)3,orderid);
    	if(anonymousRadioButton.isSelected()){
    		vo.setHide(true);
    	}else{
    		vo.setHide(false);
    	}
        CommentResultMessage resultmessage=servicefactory.getCommentLogicService().review(vo);
        if(resultmessage==CommentResultMessage.SUCCESS){
        	Notifications.create().title("��ʾ").text("���۳ɹ�").showConfirm();

        }else if(resultmessage==CommentResultMessage.FAIL){
        	Notifications.create().title("��ʾ").text("����ʧ��").showConfirm();
        }else if(resultmessage==CommentResultMessage.FAIL_HAVEREVIEWED){
        	Notifications.create().title("��ʾ").text("�Ѿ�����").showConfirm();
        }
    }
    private void initialComboBox(){
    	degreeComboBox.setItems(degreeData);
    }
    public void setOrderVO(OrderVO vo){
    	this.vo=vo;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		userid=(long)DataController.getInstance().get("UserId");
		servicefactory=RemoteHelper.getInstance().getServiceFactory();
		initialComboBox();
		Image imageupdate = new Image(getClass().getResourceAsStream("update.png"));
		updateButton.setGraphic(new ImageView(imageupdate));
		updateButton.getStylesheets().add(getClass().getResource("commentFile.css").toExternalForm());
	}

}