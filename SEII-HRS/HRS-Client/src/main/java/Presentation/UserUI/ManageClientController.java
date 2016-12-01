package Presentation.UserUI;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

import Presentation.UserUI.ManageUserController;
public class ManageClientController {
	@FXML TextField searchField;
    @FXML GridPane ClientPane;
	public void Search(ActionEvent e)
	{
		String search=searchField.getText();
		
		if(search.equals("¸öÈË"))
		{
			try {
				
				Parent personVIPBrowse = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/UserUI/ManagePVIPClient.fxml"));
				personVIPBrowse.getProperties().put("NAME","PVIPClient" );
				ObservableList<Node> list =ClientPane.getChildren();
				for(Node node:list){
					String value=(String)node.getProperties().get("NAME");
					if(value!=null&&value.contains("CVIPClient")){
						list.remove(node);
						break;
					}
				}
				ClientPane.add(personVIPBrowse, 0,1);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
			try {
				
				Parent personVIPBrowse = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/UserUI/ManageCVIPClient.fxml"));
				personVIPBrowse.getProperties().put("NAME","CVIPClient" );
				ObservableList<Node> list =ClientPane.getChildren();
				for(Node node:list){
					String value=(String)node.getProperties().get("NAME");
					if(value!=null&&value.contains("PVIPClient")){
						list.remove(node);
						break;
					}
				}
				ClientPane.add(personVIPBrowse, 0,1);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}
	
	
}
