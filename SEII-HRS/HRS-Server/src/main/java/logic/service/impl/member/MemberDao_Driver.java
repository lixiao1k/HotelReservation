package logic.service.impl.member;

import java.rmi.RemoteException;

import data.dao.MemberDao;
import po.MemberPO;

public class MemberDao_Driver {
	public void drive(MemberDao service) throws RemoteException{
		service.getInfo(1);
		service.getInfo(2);
		service.getInfo("111");
		service.getInfo("1234");
		service.getInfoByName("111");
		service.getInfoByName("1234");
		service.add(new MemberPO());
		service.update(new MemberPO());
		service.manageInfo(null);
		service.manageInfo("  ");
		service.manageInfo("1234");
		service.delete(1);
		service.delete(2);
	}
}
