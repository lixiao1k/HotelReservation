package data.datahelper;

import java.util.List;

import po.MemberPO;

public interface MemberDataHelper {
	public void insert(MemberPO po);
	public MemberPO getInfo(long userid);
	public MemberPO getInfo(String username);
	public MemberPO getInfoByName(String name);
	public List<MemberPO> getHotelWorkerInfo(String hotelname);
	public void update(MemberPO po);
	public void delete(long userid);
}
