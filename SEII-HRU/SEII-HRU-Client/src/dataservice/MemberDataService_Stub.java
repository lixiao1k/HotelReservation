package dataservice;

import po.MemberPO;

public class MemberDataService_Stub implements MemberDataService{

	@Override
	public void insert(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("MemberDataService.Insert SUCCESS");
	}

	@Override
	public void cancel(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("MemberDataService.cancel SUCCESS");
	}

	@Override
	public void changeInfo(MemberPO po) {
		// TODO Auto-generated method stub
		System.out.println("MemberDataService.changeInfo SUCCESS");
	}

	@Override
	public MemberPO getInfo(long userid) {
		// TODO Auto-generated method stub

		return new MemberPO();
	}

}
