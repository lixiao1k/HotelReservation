package Presentation.StrategyUI;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.controlsfx.control.ListSelectionView;

import info.ListWrapper;
import info.StrategyType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import logic.service.StrategyLogicService;
import rmi.RemoteHelper;
import vo.StrategyVO;

public class BirthController implements Initializable{
	@FXML GridPane mainPane;
	@FXML TextField Name;
	@FXML TextField Off;
	GridPane clientmain;
	StrategyLogicService strategyLogic;
	ListSelectionView<String> Room;
	
	@FXML 
	protected void Create() throws RemoteException{
		StrategyVO svo=new StrategyVO();
		svo.setName(Name.getText());
		svo.setOff(Double.valueOf(Off.getText()));
		ListWrapper<StrategyType> typelist = strategyLogic.getTypes();;
		Iterator<StrategyType> it=typelist.iterator();
		while(it.hasNext()){
			StrategyType type=it.next();
			if(type.getName().equals("Birth"))
				svo.setStrategyType(type);
				break;
		}
		svo.setExtraInfo("");
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
