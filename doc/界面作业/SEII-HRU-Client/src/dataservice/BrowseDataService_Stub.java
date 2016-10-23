package dataservice;

import java.util.ArrayList;
import java.util.List;

import po.BrowsePO;

public class BrowseDataService_Stub implements BrowseDataService{

	@Override
	public void insert(BrowsePO po) {
		// TODO Auto-generated method stub
		System.out.println("Insert SUCCESS");
	}

	@Override
	public List<BrowsePO> getInfo(long userid) {
		// TODO Auto-generated method stub
		List<BrowsePO> list = new ArrayList<BrowsePO>();
		return list;
	}

	@Override
	public void clear(long userid) {
		// TODO Auto-generated method stub
		System.out.println("Clear SUCCESS");
	}

}
