package Presentation.CreditUI;
/*
 * @author Shelton Lee 151250084
 */
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.ResourceBundle;

import Presentation.MemberUI.KeepPersonInfoController;
import datacontroller.DataController;
import info.ListWrapper;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.service.ServiceFactory;
import rmi.RemoteHelper;
import vo.CreditVO;

public class CreditBrowseController implements Initializable{
	
	@FXML Label nameLabel;
	@FXML Label creditLabel;
	@FXML ListView<CreditVO> creditListView;
	@FXML Label creditImageLabel;
	@FXML Label peopleImageLabel;
	private String username;
	private long userid;
	private ObservableList<CreditVO> creditListViewData;
	private ServiceFactory serviceFactory;
	private Iterator<CreditVO> it;
	private int credit;
	
	private void initialListViewData(Iterator<CreditVO> iterator){
		while(iterator.hasNext()){
			creditListViewData.add(iterator.next());
		}
	}
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		if(serviceFactory==null)
			serviceFactory=RemoteHelper.getInstance().getServiceFactory();
		try{
			userid=(long)DataController.getInstance().get("UserId");
			ListWrapper<CreditVO> list=serviceFactory.getCreditLogicService().getInfo(userid);
			if(list==null){
				System.out.println("null");
			}
			if(list.iterator()==null){
				System.out.println("listnull");
			}
			it=list.iterator();
			creditListViewData=FXCollections.observableArrayList();
			initialListViewData(it);		
			creditListView.setCellFactory(e->new CreditListCell());
			creditListView.setItems(creditListViewData);
			username=serviceFactory.getMemberLogicService().getInfo(userid).getName();
			credit=serviceFactory.getMemberLogicService().getInfo(userid).getCredit();
			nameLabel.setText(username);
			creditLabel.setText(Integer.toString(credit));
			Image imagecredit =new Image(getClass().getResourceAsStream("credit.png"));
		    creditImageLabel.setGraphic(new ImageView(imagecredit));
			Image userImage =new Image(getClass().getResourceAsStream("user.png"));
		    peopleImageLabel.setGraphic(new ImageView(userImage));
		}catch(RemoteException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

	}
	class CreditListCell extends ListCell<CreditVO>{
		public void updateItem(CreditVO item,boolean empty){
			super.updateItem(item, empty);
			if(item!=null){
				GridPane cell=new GridPane();
				final Tooltip tooltip=new Tooltip();//to show the cause of the credit's change
				tooltip.setText(item.getReason());
				Label time=new Label(item.getDate().toString());
				time.setFont(new Font("KaiTi_GB2312",15));
				Label delta=new Label(Integer.toString(item.getDelta()));
				delta.setFont(new Font("KaiTi_GB2312", 15));
				delta.setPrefWidth(100);
				delta.setTooltip(tooltip);
				if(item.getDelta()>0){
					delta.setTextFill(Color.GREEN);
				}else if(item.getDelta()<0){
					delta.setTextFill(Color.RED);
				}else{
					delta.setTextFill(Color.BLACK);
				}
				Label credit=new Label("Credit Deposit:"+Integer.toString(item.getCredit()));
				credit.setFont(new Font("KaiTi_GB2312",15));
				credit.setPrefWidth(200);
				cell.add(time,2,0);
				cell.add(delta, 0, 0);
				cell.add(credit, 1, 0);
				setGraphic(cell);
			}else{
				setGraphic(null);
			}
		}
	}

}
