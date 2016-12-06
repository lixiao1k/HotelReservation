package data.datahelper;

import po.MemberPO;

public interface MemberDataHelper {
	public void insert(MemberPO po);
	public MemberPO getInfo(long userid);
}
