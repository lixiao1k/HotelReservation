package data.dao.Impl;

import data.dao.MemberDao;
import data.datahelper.MemberDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
import po.MemberPO;
import po.VIPPO;

public class MemberDaoImpl implements MemberDao{
	private MemberDataHelper memberDataHelper;
	public MemberDaoImpl() {
		memberDataHelper = DataFactory.getInstance().getMemberDataHelper();
	}

	@Override
	public MemberPO getInfo(long userid) {
		// TODO Auto-generated method stub
		return memberDataHelper.getInfo(userid);
	}

	@Override
	public MemberPO getInfo(String username) {
		// TODO Auto-generated method stub
		return memberDataHelper.getInfo(username);
	}
	@Override
	public void update(MemberPO po) {
		memberDataHelper.update(po);
	}

	@Override
	public void add(MemberPO po) {
		memberDataHelper.insert(po);
	}

	@Override
	public ListWrapper<MemberPO> manageInfo(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long userId) {
		memberDataHelper.delete(userId);
		DataFactory.getInstance().getUserDataHelper().delete(userId);
	}

}
