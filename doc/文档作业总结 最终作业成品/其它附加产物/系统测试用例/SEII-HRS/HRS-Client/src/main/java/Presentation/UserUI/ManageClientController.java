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
	@FXML  private TextField searchField;
    @FXML  private GridPane ClientPane;
    private String Birth="五月二十";
    private String Company="南京大学";
	public void Search(ActionEvent e)
	{
		String username=searchField.getText();
		//调用Member.getAllClient接口  将username传过去 得到ManageClientVO
		if(username.equals("个人"))
		{
			try {
				
				Parent personVIPBrowse = FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/UserUI/ManagePeVIPClient.fxml"));
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
	
	public String getBirth()
	{
		return Birth;//从数据库中得到birth信息
	}
	
	public String getCompany()
	{
		return Company;//从数据库中得到company信息
	}
}
