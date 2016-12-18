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
		if(Name.getText().equals("")){//检测名字
			Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("请输入策略名").showWarning();
		}else{
			svo.setName(Name.getText());
			double off=0;
			if(Off.getText().equals("")){//检测折扣
				Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("请输入折扣").showWarning();
			}else{
				off=Double.valueOf(Off.getText());
				svo.setOff(off);
				if(Time1.getText().equals("")||Time2.getText().equals("")){//检测时间
					Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("请输入时间").showWarning();
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
						Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("创建成功！").showConfirm();
					}
					if(m==StrategyResultMessage.FAIL_WRONGINFO){
						Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("创建失败！不存在此酒店！").showWarning();
					}
					if(m==StrategyResultMessage.FAIL_WRONG){
						Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("创建失败！未知错误！").showWarning();
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
