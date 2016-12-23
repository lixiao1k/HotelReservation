package runner;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Presentation.MainUI.HotelWorkerMainUI;
import Presentation.MainUI.LoginMainUI;
import Presentation.MainUI.WebSalerMainUI;
import datacontroller.DataController;
import javafx.application.Application;
import rmi.RemoteHelper;

public class ClientRunner{
	private RemoteHelper remoteHelper;
	public ClientRunner(String[] args){
		linkToServer();
		DataController.getInstance().put("HotelId", (long)1);
		Application.launch(HotelWorkerMainUI.class,args);
	}
	private void linkToServer() {
		try {
			remoteHelper = RemoteHelper.getInstance();
			remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/ServiceFactory"));
			System.out.println("linked");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ClientRunner(args);
	}
}
