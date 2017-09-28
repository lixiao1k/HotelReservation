package dataservice;

import java.util.List;

import po.BrowsePO;

public interface BrowseDataService extends DatabaseService{
	public void insert(BrowsePO po);
	public List<BrowsePO> getInfo(long userid);
	public void clear(long userid);

}
