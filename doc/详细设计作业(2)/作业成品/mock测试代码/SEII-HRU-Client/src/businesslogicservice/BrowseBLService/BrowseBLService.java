package businesslogicservice.BrowseBLService;

import java.util.List;

import vo.BrowseVO;
import vo.CreditVO;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderType;
import vo.OrderVO;
import vo.RuleVO;
import vo.StrategyVO;

public interface BrowseBLService {
	public List<OrderVO> getOrdersInfo(long userid,OrderType type);
	public List<OrderVO> getHotelOrdersInfo(long hotelid,OrderType type);
	public List<OrderVO> getWEBOrdersInfo();
	public List<CreditVO> getCreditInfo(long userid);
	public List<HotelVO> getHotels(RuleVO vo);
	public HotelVO getHotel(long hotelid);
	public StrategyVO getStrategyInfo(int orderid);
	public MemberVO getMemberInfo(long memberid);
	public List<BrowseVO> getBrowseInfo(long userid);
	public void clear(long userid);
}
