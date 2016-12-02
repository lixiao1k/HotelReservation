package data.dao;


import info.ListWrapper;
import po.MemberPO;
import po.UserPO;
import po.VIPPO;

public interface MemberDao {
	public void registerVIP(VIPPO vpo);
	public VIPPO getVIPInfo(long userId);
	public MemberPO getInfo(long userId);
	public void cancel(long userId);
	public void update(MemberPO po);
	public ListWrapper<MemberPO> manageInfo();
	public void delete(long userId);
}
