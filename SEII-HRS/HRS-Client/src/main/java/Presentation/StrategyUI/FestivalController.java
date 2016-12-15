package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

import info.ListWrapper;
import info.StrategyType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
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
		strategyLogic.create(svo);
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
