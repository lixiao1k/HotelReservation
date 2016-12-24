package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.MemberDao;
import info.ListWrapper;
import po.HotelPO;
import po.MemberPO;

public class MemberDao_Stub implements MemberDao{

	@Override
	public MemberPO getInfo(long userid) {
		if(userid==1){
			System.out.println("MemberDao.getInfo  :  userid=1|return NULL");
			return null;
		}
		System.out.println("MemberDao.getInfo  :  userid=other number|return normal result");
		return new MemberPO();
	}

	@Override
	public MemberPO getInfo(String username) {
		if(username.equals("111")){
			System.out.println("MemberDao.getInfo  :  username=111|return NULL");
			return null;
		}
		System.out.println("MemberDao.getInfo  :  username=other string|return normal result");
		return new MemberPO();
	}

	@Override
	public MemberPO getInfoByName(String name) {
		if(name.equals("111")){
			System.out.println("MemberDao.getInfoByName  :  name=111|return NULL");
			return null;
		}
		System.out.println("MemberDao.getInfoByName  :  name=other string|return normal result");
		return new MemberPO();
	}

	@Override
	public void update(MemberPO po) {
		System.out.println("MemberDao.update  :  update success");
	}

	@Override
	public void add(MemberPO po) {

		System.out.println("MemberDao.update  :  add success");
	}

	@Override
	public ListWrapper<MemberPO> manageInfo(String name) throws RemoteException {
		if(name==null){
			System.out.println("MemberDao.manageInfo  :  name=null|return null");
			return null;
		}
		if(name.equals("\\s")){
			System.out.println("MemberDao.manageInfo  :  name info wrong|return empty ListWrapper");
			return new ListWrapper<>();
		}
		ListWrapper<MemberPO> list = new ListWrapper<>();
		list.add(new MemberPO());
		System.out.println("MemberDao.manageInfo  :  name normal|return normal result");
		return list;
	}

	@Override
	public void delete(long userId) {
		if(userId==1){
			System.out.println("MemberDao.delete  :  userId==1|delete fail");
			return;
		}
		System.out.println("MemberDao.delete  :  userId==other number|delete success");
		
		
	}

}
