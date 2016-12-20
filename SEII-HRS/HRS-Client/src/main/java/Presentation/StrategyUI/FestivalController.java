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
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import resultmessage.StrategyResultMessage;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class FestivalController implements Initializable{
	@FXML GridPane mainPane;
	DatePicker Time1;
	DatePicker Time2;
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
				if(Time1.getValue()==null||Time2.getValue()==null){//检测时间
					Notifications.create().owner(mainPane.getScene().getWindow()).title("创建策略").text("请输入时间").showWarning();
				}else{
					svo.setExtraInfo(Time1.getValue()+"|"+Time2.getValue());
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
	
	public void initpicker(){
		Time1=new DatePicker();
		Time1.setPrefWidth(138);
		Time1.setMaxWidth(148);
		mainPane.add(Time1, 0, 1);
		mainPane.setMargin(Time1, new Insets(0, 0, 0, 130));
		Time2=new DatePicker();
		Time2.setPrefWidth(138);
		Time2.setMaxWidth(148);
		mainPane.add(Time2, 0, 1);
		mainPane.setMargin(Time2, new Insets(0, 0, 0, 320));
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			strategyLogic=RemoteHelper.getInstance().getServiceFactory().getStrategyLogicService();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		initpicker();
	}
}
