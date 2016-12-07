package data.dao;


import info.ListWrapper;
import po.MemberPO;

public interface MemberDao {
	public MemberPO getInfo(long userid);
	public MemberPO getInfo(String username);
	public MemberPO getInfoByName(String name);
	public void update(MemberPO po);
	public void add(MemberPO po);
	public ListWrapper<MemberPO> manageInfo(String name);
	public void delete(long userId);
}	
