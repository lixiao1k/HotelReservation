package dataservice;

import po.MemberPO;

public interface MemberDataService extends DatabaseService{
	public void insert(MemberPO po);
	public void cancel(MemberPO po);
	public void changeInfo(MemberPO po);
	public MemberPO getInfo(long userid);

}
