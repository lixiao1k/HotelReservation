package data.dao;


import info.ListWrapper;
import po.MemberPO;
import po.VIPPO;

public interface MemberDao {
	public void registerVIP(VIPPO vpo);

	public VIPPO getVIPInfo(long userid);
	public MemberPO getInfo(long userid);
	public MemberPO getInfo(String username);
	public void cancel(long userid);
	public void update(MemberPO po);
	public void add(MemberPO po);
	public ListWrapper<MemberPO> manageInfo(String name);
	public void delete(long userId);
}
