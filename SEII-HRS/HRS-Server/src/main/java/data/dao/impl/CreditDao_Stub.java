package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.CreditDao;
import info.ListWrapper;
import po.CommentPO;
import po.CreditPO;

public class CreditDao_Stub implements CreditDao{

	@Override
	public void insert(CreditPO po) {
		System.out.println("CreditDao.insert  :  insert success");
	}

	@Override
	public ListWrapper<CreditPO> getinfo(long userId) throws RemoteException {
		if(userId==1){
			System.out.println("CreditPO.getinfo  :  userId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(userId==0){
			System.out.println("CreditPO.getinfo  :  userId=0|return null");
			return null;
		}
		ListWrapper<CreditPO> list = new ListWrapper<>();
		list.add(new CreditPO());
		System.out.println("CreditPO.getinfo  :  userId=other number|return normal result");
		return list;
	}

}
