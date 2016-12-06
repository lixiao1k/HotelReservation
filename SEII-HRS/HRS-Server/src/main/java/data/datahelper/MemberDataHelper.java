package data.datahelper;

import po.MemberPO;

public interface MemberDataHelper {
	public void insert(MemberPO po);
	public MemberPO getInfo(long userid);
	public MemberPO getInfo(String username);
	public void update(MemberPO po);
	public void delete(long userid);
}
