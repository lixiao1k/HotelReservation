package businesslogicservice.OrderBLService;

import java.util.List;

import vo.OrderType;
import vo.OrderVO;

public interface OrderBLService {
	public List<OrderVO> getuserInfo(long userid,OrderType type);
	public List<OrderVO> getHotelOrdersInfo(long hotelid,OrderType type);
	public List<OrderVO> getWEBOrdersInfo();
	public OrderResultMessage create(OrderVO vo);
	public void abnormal(OrderVO vo);
	public OrderResultMessage userCancel(OrderVO vo);
	public OrderResultMessage execute(OrderVO vo);
	public OrderResultMessage WEBCancel(OrderVO vo);
	public double getTotal(OrderVO vo);
}
