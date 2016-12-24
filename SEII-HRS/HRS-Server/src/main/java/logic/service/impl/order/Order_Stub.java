package logic.service.impl.order;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.OrderLogicService;
import resultmessage.OrderResultMessage;
import vo.NewOrderVO;
import vo.OrderVO;

public class Order_Stub implements OrderLogicService{

	@Override
	public ListWrapper<OrderVO> getUserOrderInfo(long userId) throws RemoteException {
		if(userId==1){
			System.out.println("Order.getUserOrderInfo  :  userId==1|return null");
			return null;
		}
		ListWrapper<OrderVO> result = new ListWrapper<>();
		result.add(new OrderVO());
		System.out.println("Order.getUserOrderInfo  :  normal info|return normal result");
		return result;
	}

	@Override
	public ListWrapper<OrderVO> getHotelOrderInfo(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("Order.getHotelOrderInfo  :  hotelId==1|return null");
			return null;
		}
		ListWrapper<OrderVO> result = new ListWrapper<>();
		result.add(new OrderVO());
		System.out.println("Order.getHotelOrderInfo  :  normal info|return normal result");
		return result;
	}

	@Override
	public ListWrapper<OrderVO> getWEBOrderInfo() throws RemoteException {
		ListWrapper<OrderVO> result = new ListWrapper<>();
		result.add(new OrderVO());
		System.out.println("Order.getWEBOrderInfo  :  return normal result");
		return result;
	}

	@Override
	public OrderResultMessage create(NewOrderVO vo) throws RemoteException {
		if(vo.getRoomNum()<=0){
			System.out.println("Order.create  :  vo.getRoomNum()<=0|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		System.out.println("Order.create  :  normal info|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage abnormal(long orderId) throws RemoteException {
		if(orderId==1){
			System.out.println("Order.abnormal  :  orderId==1|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		if(orderId==2){
			System.out.println("Order.abnormal  :  orderId==2|return OrderResultMessage.FAIL_WRONGID");
			return OrderResultMessage.FAIL_WRONGID;
		}
		if(orderId==3){
			System.out.println("Order.abnormal  :  orderId==3|return OrderResultMessage.FAIL_WRONGSTATUS");
			return OrderResultMessage.FAIL_WRONGSTATUS;
		}
		System.out.println("Order.abnormal  :  orderId==other number|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage userRevoke(long orderId) throws RemoteException {
		if(orderId==1){
			System.out.println("Order.userRevoke  :  orderId==1|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		if(orderId==2){
			System.out.println("Order.userRevoke  :  orderId==2|return OrderResultMessage.FAIL_WRONGID");
			return OrderResultMessage.FAIL_WRONGID;
		}
		if(orderId==3){
			System.out.println("Order.userRevoke  :  orderId==3|return OrderResultMessage.FAIL_WRONGSTATUS");
			return OrderResultMessage.FAIL_WRONGSTATUS;
		}
		System.out.println("Order.userRevoke  :  orderId==other number|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage execute(long orderId) throws RemoteException {
		if(orderId==1){
			System.out.println("Order.execute  :  orderId==1|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		if(orderId==2){
			System.out.println("Order.execute  :  orderId==2|return OrderResultMessage.FAIL_WRONGID");
			return OrderResultMessage.FAIL_WRONGID;
		}
		if(orderId==3){
			System.out.println("Order.execute  :  orderId==3|return OrderResultMessage.FAIL_WRONGSTATUS");
			return OrderResultMessage.FAIL_WRONGSTATUS;
		}
		System.out.println("Order.execute  :  orderId==other number|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage reExecute(long orderId) throws RemoteException {
		if(orderId==1){
			System.out.println("Order.reExecute  :  orderId==1|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		if(orderId==2){
			System.out.println("Order.reExecute  :  orderId==2|return OrderResultMessage.FAIL_WRONGID");
			return OrderResultMessage.FAIL_WRONGID;
		}
		if(orderId==3){
			System.out.println("Order.reExecute  :  orderId==3|return OrderResultMessage.FAIL_WRONGSTATUS");
			return OrderResultMessage.FAIL_WRONGSTATUS;
		}
		System.out.println("Order.reExecute  :  orderId==other number|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

	@Override
	public OrderResultMessage webRevoke(long orderId, int rank) throws RemoteException {
		if(orderId==1){
			System.out.println("Order.webRevoke  :  orderId==1|return OrderResultMessage.FAIL_WRONGORDERINFO");
			return OrderResultMessage.FAIL_WRONGORDERINFO;
		}
		if(orderId==2){
			System.out.println("Order.webRevoke  :  orderId==2|return OrderResultMessage.FAIL_WRONGID");
			return OrderResultMessage.FAIL_WRONGID;
		}
		if(orderId==3){
			System.out.println("Order.webRevoke  :  orderId==3|return OrderResultMessage.FAIL_WRONGSTATUS");
			return OrderResultMessage.FAIL_WRONGSTATUS;
		}
		System.out.println("Order.webRevoke  :  orderId==other number|return OrderResultMessage.SUCCESS");
		return OrderResultMessage.SUCCESS;
	}

}
