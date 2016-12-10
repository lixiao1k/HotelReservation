

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Presentation.MainUI.HotelWorkerMainUI;
import datacontroller.DataController;
import javafx.application.Application;
import rmi.RemoteHelper;

public class TestRunner{
	private RemoteHelper remoteHelper;
	public TestRunner(String[] args){
		linkToServer();
		DataController.getInstance().put("HotelId",(long)1);
		Application.launch(TestController.class,args);
		
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
		new TestRunner(args);
	}
}
