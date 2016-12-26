package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;

import datacontroller.DataController;
import info.ListWrapper;
import info.Room;
import info.StrategyType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.service.HotelLogicService;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import rmi.RemoteHelper;
import vo.HotelItemVO;
import vo.StrategyItemVO;
import vo.StrategyVO;

public class HotelFestivalController implements Initializable{
	@FXML GridPane mainPane;
	DatePicker Time1;
	DatePicker Time2;
	@FXML TextField Name;
	@FXML TextField Off;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	HotelLogicService hotelLogic;
	ListSelectionView<Room> Room;
	long hotelid;
	
	@FXML 
	protected void Create() throws RemoteException{
		StrategyVO svo=new StrategyVO();
		if(Name.getText().equals("")){//�������
			Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("�����������").showWarning();
		}else{
			svo.setName(Name.getText());
			double off=0;
			if(Off.getText().equals("")){//����ۿ�
				Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("�������ۿ�").showWarning();
			}else{
				off=Double.valueOf(Off.getText());
				svo.setOff(off);
				if(Time1.getValue()==null||Time2.getValue()==null){//���ʱ��
					Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("������ʱ��").showWarning();
				}else{
					svo.setExtraInfo(Time1.getValue()+"|"+Time2.getValue());
					svo.setHotelId(hotelid);
					ListWrapper<StrategyType> typelist = strategyLogic.getTypes();
					Iterator<StrategyType> it=typelist.iterator();
					while(it.hasNext()){
						StrategyType type=it.next();
						if(type.getName().equals("HotelFestivalStrategy")){
							svo.setStrategyType(type);
							break;
						}
					}
					ListWrapper<HotelItemVO> volist=hotelLogic.getRoomInfo(hotelid);
					Set<StrategyItemVO> voset=new HashSet<>();
					ObservableList<Room> targetlist=Room.getTargetItems();
					for(Room room:targetlist){
						Iterator<HotelItemVO> itt=volist.iterator();
						while(itt.hasNext()){
							HotelItemVO hvo=itt.next();
							if(hvo.getRoom().getRid()==room.getRid()){
								StrategyItemVO sivo=new StrategyItemVO();
								sivo.setRoom(room);
								sivo.setPriceBefore(hvo.getPrice());
								sivo.setOff(off);
								sivo.setPriceAfter(hvo.getPrice()*(1-off));
								voset.add(sivo);
							}
						}
					}
					svo.setItems(voset);
					StrategyResultMessage m=strategyLogic.create(svo).getResultMessage();
					if(m==StrategyResultMessage.SUCCESS){
						Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("�����ɹ���").showConfirm();
					}
					if(m==StrategyResultMessage.FAIL_WRONGINFO){
						Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("����ʧ�ܣ������ڴ˾Ƶ꣡").showWarning();
					}
					if(m==StrategyResultMessage.FAIL_WRONG){
						Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("����ʧ�ܣ�δ֪����").showWarning();
					}
				}
			}
		}
	}
	
	public void initlistsev() throws RemoteException{
		Room=new ListSelectionView<>();
		ObservableList<Room> sourcelist=FXCollections.observableArrayList();
		ListWrapper<HotelItemVO> volist=hotelLogic.getRoomInfo(hotelid);
		Iterator<HotelItemVO> it=volist.iterator();
		while(it.hasNext()){
			Room room=it.next().getRoom();
			sourcelist.add(room);
		}
		Room.setSourceItems(sourcelist);
		Room.setSourceItems(sourcelist);
		Label sourceheader=new Label("���з��䣺");
		sourceheader.setFont(new Font(14));
		sourceheader.setTextFill(Color.YELLOW);
		Room.setSourceHeader(sourceheader);
		Label targetheader=new Label("ʹ�ò��Եķ��䣺");
		targetheader.setFont(new Font(14));
		targetheader.setTextFill(Color.YELLOW);
		Room.setTargetHeader(targetheader);
		mainPane.add(Room, 0, 1);
		mainPane.setMargin(Room, new Insets(0,0,0,50));
	}
	
	public void setBaseInfo(){
		hotelid=(long)DataController.getInstance().get("HotelId");
		DataController.getInstance().putAndUpdate("HotelId", hotelid);
	}
	
	public void initpicker(){
		Time1=new DatePicker();
		Time1.setPrefWidth(138);
		Time1.setMaxWidth(148);
		mainPane.add(Time1, 0, 2);
		mainPane.setMargin(Time1, new Insets(0, 0, 0, 130));
		Time2=new DatePicker();
		Time2.setPrefWidth(138);
		Time2.setMaxWidth(148);
		mainPane.add(Time2, 0, 2);
		mainPane.setMargin(Time2, new Insets(0, 0, 0, 320));
	}
	
	//��ʼ��css
	public void initcss(){
		mainPane.getStylesheets().add(getClass().getResource("alltype.css").toExternalForm());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setBaseInfo();
		initcss();
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
			hotelLogic=RemoteHelper.getInstance().getServiceFactory().getHotelLogicService();
			initlistsev();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initpicker();
	}
}
