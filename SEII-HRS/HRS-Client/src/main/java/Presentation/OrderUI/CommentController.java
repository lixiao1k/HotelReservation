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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import vo.CommentVO;
import vo.OrderVO;

public class CommentController implements Initializable{
    @FXML ComboBox<String> degreeComboBox;
    @FXML TextArea commentTextArea;
    ObservableList<String> degreeData=FXCollections.observableArrayList("1","2","3","4","5");
    private OrderVO vo; 
    private long userid;
    private ServiceFactory servicefactory;
    @FXML
    protected void save(ActionEvent e) throws RemoteException{
    	int grade=Integer.parseInt(degreeComboBox.getValue());
    	System.out.println(grade);
    	System.out.println(commentTextArea.getText());
    	System.out.println(userid);
    	long orderid=vo.getOrderId();
    	System.out.println(orderid);
    	CommentVO vo=new CommentVO(grade, commentTextArea.getText(), userid, (long)3,orderid);
    	servicefactory.getCommentLogicService().review(vo);
    	Notifications.create().title("提示").text("订单评价成功").showConfirm();
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
	}

}