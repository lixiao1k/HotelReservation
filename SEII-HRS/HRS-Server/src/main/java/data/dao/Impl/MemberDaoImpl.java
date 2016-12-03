package data.dao.Impl;

import data.dao.MemberDao;
import info.ListWrapper;
import po.MemberPO;
import po.VIPPO;

public class MemberDaoImpl implements MemberDao{

	@Override
	public void registerVIP(VIPPO vpo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancel(long userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long userId) {
		// TODO Auto-generated method stub
		
	}


	@Override
<<<<<<< HEAD
	public void delete(long userId) {
		// TODO Auto-generated method stub
=======
	public VIPPO getVIPInfo(long userId) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public MemberPO getInfo(String username) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void update(MemberPO po) {
		// TODO 自动生成的方法存根
>>>>>>> origin/master
		
	}

	@Override
<<<<<<< HEAD
	public VIPPO getVIPInfo(long userId) {
=======
	public ListWrapper<MemberPO> manageInfo(String name) {
>>>>>>> origin/master
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
<<<<<<< HEAD
	public void update(MemberPO po) {
		// TODO Auto-generated method stub
=======
	public void add(MemberPO po) {
		// TODO 自动生成的方法存根
>>>>>>> origin/master
		
	}

	@Override
<<<<<<< HEAD
	public ListWrapper<MemberPO> manageInfo() {
		// TODO Auto-generated method stub
=======
	public MemberPO getInfo(long userid) {
		// TODO 自动生成的方法存根
>>>>>>> origin/master
		return null;
	}

}
