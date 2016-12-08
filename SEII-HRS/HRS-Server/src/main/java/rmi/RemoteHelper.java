package rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import logic.service.ServiceFactory;
import logic.service.impl.ServiceFactoryImpl;

public class RemoteHelper {
	public RemoteHelper(){
		initServer();
	}
	
	/*
	 * ��ʼ����������ע��Զ�̶���
	 */
	
	private void initServer(){
		ServiceFactory serviceFactory;
		try{
			serviceFactory = ServiceFactoryImpl.getInstance();
			LocateRegistry.createRegistry(8888);
			Naming.bind("rmi://localhost:8888/ServiceFactory",
					serviceFactory);
			System.out.println("success");
		}catch(RemoteException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}
	}
}
