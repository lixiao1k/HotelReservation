package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import info.ListWrapper;
import info.StrategyType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class FestivalController implements Initializable{
	@FXML GridPane mainPane;
	@FXML TextField Time1;
	@FXML TextField Time2;
	@FXML TextField Name;
	@FXML TextField Off;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	
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
				if(Time1.getText().equals("")||Time2.getText().equals("")){//���ʱ��
					Notifications.create().owner(mainPane.getScene().getWindow()).title("��������").text("������ʱ��").showWarning();
				}else{
					svo.setExtraInfo(Time1.getText()+"|"+Time2.getText());
					svo.setHotelId(-1);
					ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
					Iterator<StrategyType> it=typelist.iterator();
					while(it.hasNext()){
						StrategyType type=it.next();
						if(type.getName().equals("Festival"))
							svo.setStrategyType(type);
							break;
					}
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
