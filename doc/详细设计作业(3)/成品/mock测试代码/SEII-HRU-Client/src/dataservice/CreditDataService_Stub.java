package dataservice;

import po.CreditPO;

public class CreditDataService_Stub implements CreditDataService{

	@Override
	public void add(long userid, int value) {
		// TODO Auto-generated method stub
		System.out.println("CreditDataService.add SUCCESS");
	}

	@Override
	public void decrease(long userid, int value) {
		// TODO Auto-generated method stub
		System.out.println("CreditDataService.decrease SUCCESS");
	}

	@Override
	public void insert(CreditPO po) {
		// TODO Auto-generated method stub
		System.out.println("CreditDataService.insert SUCCESS");
	}

}
