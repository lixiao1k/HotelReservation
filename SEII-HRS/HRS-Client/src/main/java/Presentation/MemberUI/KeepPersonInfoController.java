package Presentation.MemberUI;

import java.awt.Button;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Presentation.MainUI.ClientMainUIController;
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

public class KeepPersonInfoController implements Initializable{
	@FXML Label IDLabel;
	@FXML TextField nameTextField;
	@FXML DatePicker birthdayPicker;
	@FXML TextField companyNameTextField;
	@FXML ComboBox<String> contactComboBox;
	@FXML TextField creditTextField;
	
	GridPane clientmain;
	
	@FXML 
	protected void add(ActionEvent e){
		
	}
	
	@FXML
	protected void goCreditBrowse(ActionEvent e){
//		try {
//			Parent creditBrowse = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/CreditUI/CreditBrowse.fxml"));
//			creditBrowse.getProperties().put("NAME","CreditBrowsePane" );
//			ObservableList<Node> list =clientmain.getChildren();
//			for(Node node:list){
//				String value=(String)node.getProperties().get("NAME");
//				if(value!=null&&value.contains("pane")){
//					list.remove(node);
//					break;
//				}
//			}
//			clientmain.add(creditBrowse, 2, 1);
//			} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		
		
	}
	
	@FXML
	protected void edit(ActionEvent e){
		
	}

	@FXML
	protected void ensure(ActionEvent e){
		
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		
	}

}
