package Presentation.CreditUI;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import Presentation.MemberUI.KeepPersonInfoController;
import datacontroller.DataController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.service.ServiceFactory;
import vo.CreditVO;

public class CreditBrowseController implements Initializable{
	
	@FXML Label nameLabel;
	@FXML Label creditLabel;
	@FXML ListView<CreditVO> creditListView;
//	KeepPersonInfoController keeppersoninfocontroller;
	private String username;
	private long userid;
	private ObservableList<CreditVO> creditListViewData;
	private ServiceFactory serviceFactory;
	
//	@FXML
//	protected void goBack(ActionEvent e){
//		GridPane clientmain=(GridPane)nameLabel.getScene().getWindow().getScene().getRoot();
//		try {
//			Parent PersonInfo =FXMLLoader.load(getClass().getClassLoader().getResource("Presentation/MemberUI/KeepPersonInfo.fxml"));
//			PersonInfo.getProperties().put("NAME","PersonInfoPane");
//			ObservableList<Node> list = clientmain.getChildren();
//			for(Node node:list){
//				String value=(String)node.getProperties().get("NAME");
//				if(value!=null&&value.contains("Pane")){
//					list.remove(node);
//					break;
//				}
//			}
//			clientmain.add(PersonInfo, 2, 1);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//
//	public void setKeepPersonInfoController(KeepPersonInfoController controller){
//		this.keeppersoninfocontroller=controller;
//	}
//	public void setBaseInfo(long userid){
//		this.userid=userid;
//	}
	private void initialListViewData(Iterator<CreditVO> iterator){
		while(iterator.hasNext()){
			creditListViewData.add(iterator.next());
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		userid=(long)DataController.getInstance().get("UserId");
		creditListViewData=FXCollections.observableArrayList();
	    LocalDate date=LocalDate.now();
		CreditVO credit1=new CreditVO(151250084, date, 26, 398);
		CreditVO credit2=new CreditVO(151250086, date, -26, 226);
		creditListViewData.add(credit1);
		creditListViewData.add(credit2);
		creditListView.setCellFactory(e->new CreditListCell());
		creditListView.setItems(creditListViewData);
	}
	class CreditListCell extends ListCell<CreditVO>{
		public void updateItem(CreditVO item,boolean empty){
			super.updateItem(item, empty);
			if(item!=null){
				GridPane cell=new GridPane();
				Label id=new Label("ID:"+Long.toString(item.getUserId()));
				id.setFont(new Font("KaiTi_GB2312",15));
				id.setPrefWidth(150);
				id.setPrefHeight(50);
				Label time=new Label(item.getDate().toString());
				time.setFont(new Font("KaiTi_GB2312",15));
				Label delta=new Label(Integer.toString(item.getDelta()));
				delta.setFont(new Font("KaiTi_GB2312", 20));
				delta.setPrefWidth(100);
				if(item.getDelta()>0){
					delta.setTextFill(Color.GREEN);
				}else if(item.getDelta()<0){
					delta.setTextFill(Color.RED);
				}else{
					delta.setTextFill(Color.BLACK);
				}
				Label credit=new Label("信用总值:"+Integer.toString(item.getCredit()));
				credit.setFont(new Font("KaiTi_GB2312",20));
				credit.setPrefWidth(200);
				cell.add(id, 0, 0);
				cell.add(time,3,0);
				cell.add(delta, 1, 0);
				cell.add(credit, 2, 0);
				setGraphic(cell);
			}else{
				setGraphic(null);
			}
		}
	}

}
