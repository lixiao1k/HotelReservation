package data.datahelper;

import java.util.List;

import info.BusinessCircle;
import info.BusinessCity;
import info.HotelItem;
import info.ListWrapper;
import info.Rule;
import po.HotelPO;

public interface HotelDataHelper {
	public void insert(HotelPO po);
	public void update(HotelPO po);
	public HotelPO getInfo(long hotelId);
	public ListWrapper<HotelItem> getHotelListByRule(Rule rule);
	public ListWrapper<HotelItem> getHotelListByString(String rule);
	public List<BusinessCity> getAllCity();
	public List<HotelPO> getHotelListByCityAndCircle(BusinessCity city, BusinessCircle circle);
}
