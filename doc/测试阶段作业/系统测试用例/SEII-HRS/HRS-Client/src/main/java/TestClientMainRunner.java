import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import Presentation.MainUI.ClientMainUI;
import datacontroller.DataController;
import javafx.application.Application;
import rmi.RemoteHelper;
import Presentation.MainUI.ClientMainUI;

public class TestClientMainRunner {
    private RemoteHelper remoteHelper;
    public TestClientMainRunner(String[] args){
    	linkToService();
    	DataController.getInstance().put("UserId", (long)2);
    	Application.launch(ClientMainUI.class,args);
    }
    private void linkToService(){
    	try{
    		remoteHelper = RemoteHelper.getInstance();
    		remoteHelper.setRemote(Naming.lookup("rmi://localhost:8888/ServiceFactory"));
    		System.out.println("linked");
    	}catch(MalformedURLException e){
    		e.printStackTrace();
    	}catch(RemoteException e){
    		e.printStackTrace();
    	}catch(NotBoundException e){
    		e.printStackTrace();
    	}
    }
    public static void main(String[] args) {
		new TestClientMainRunner(args);
	}
}
