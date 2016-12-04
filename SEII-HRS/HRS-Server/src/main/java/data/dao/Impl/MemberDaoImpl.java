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
	public void registerVIP(VIPPO vpo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VIPPO getVIPInfo(long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberPO getInfo(long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberPO getInfo(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel(long userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(MemberPO po) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
