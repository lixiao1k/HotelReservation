package Presentation.UserUI;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.MemberLogicService;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import vo.ManageClientVO;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import Presentation.UserUI.ManageUserController;
import datacontroller.DataController;
public class ManageClientController implements Initializable{
	@FXML  private TextField searchField;
    @FXML  private GridPane ClientPane;
    private String Birth="���¶�ʮ";
    private String Company="�Ͼ���ѧ";
    private ServiceFactory servicefactory;
    private MemberLogicService memberlogic;
    private ManageClientVO clientvo;
	public void Search(ActionEvent e)
	{
		String username=searchField.getText();
		try {
			memberlogic=servicefactory.getMemberLogicService();
			clientvo=memberlogic.getClient(username);
			setBaseinfo();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(clientvo.getCompanyname()!=null)
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
		return Birth;//�����ݿ��еõ�birth��Ϣ
	}
	
	public String getCompany()
	{
		return Company;//�����ݿ��еõ�company��Ϣ
	}

	public void setBaseinfo()
	{
		DataController.getInstance().put("searchClient", clientvo);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(servicefactory==null)
		{
			servicefactory=RemoteHelper.getInstance().getServiceFactory();
			
		}
		
		
	}
}
