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
		svo.setName(Name.getText());
		svo.setOff(Double.valueOf(Off.getText()));
		ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
		Iterator<StrategyType> it=typelist.iterator();
		while(it.hasNext()){
			StrategyType type=it.next();
			if(type.getName().equals("VIPCircle"))
				svo.setStrategyType(type);
				break;
		}
		svo.setExtraInfo(Time1.getText()+"|"+Time2.getText());
		svo.setHotelId(-1);
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
