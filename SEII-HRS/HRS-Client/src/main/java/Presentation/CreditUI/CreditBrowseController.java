package Presentation.CreditUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import vo.CreditVO;

public class CreditBrowseController implements Initializable{
	
	@FXML Label nameLabel;
	@FXML Label creditLabel;
	@FXML ListView<CreditVO> creditListView;
	@FXML
	protected void goBack(ActionEvent e){
		GridPane clientmain=(GridPane)nameLabel.getScene().getWindow().getScene().getRoot();
		try {
			Parent PersonInfo =FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/KeepPersonInfo.fxml"));
			PersonInfo.getProperties().put("NAME","PersonInfoPane");
			ObservableList<Node> list = clientmain.getChildren();
			for(Node node:list){
				String value=(String)node.getProperties().get("NAME");
				if(value!=null&&value.contains("Pane")){
					list.remove(node);
					break;
				}
			}
			clientmain.add(PersonInfo, 2, 1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
