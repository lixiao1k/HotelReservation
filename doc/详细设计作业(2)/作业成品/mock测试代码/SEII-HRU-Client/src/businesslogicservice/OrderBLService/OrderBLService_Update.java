package businesslogicservice.OrderBLService;

import com.sun.org.apache.xpath.internal.operations.Or;

import businesslogic.OrderBL.OrderList;
import vo.OrderStatus;
import vo.OrderVO;
import vo.StrategyVO;

public interface OrderBLService_Update {
	public OrderList getUserOrderInfo(long userId,OrderStatus status);
	public OrderList getHotelOrderInfo(long hotelId,OrderStatus status);
	public OrderList getWEBOrderInfo();
	public OrderResultMessage create(OrderVO vo);
	public OrderResultMessage abnormal(long orderId);
	public OrderResultMessage userCancel(long orderId);
	public OrderResultMessage execute(long orderId);
	public OrderResultMessage reExecute(long orderId);
	public boolean isUsed(StrategyVO vo);
	public double getTotal(long orderId);
}
