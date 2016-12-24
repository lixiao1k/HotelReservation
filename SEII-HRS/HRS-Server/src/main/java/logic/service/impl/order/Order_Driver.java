package logic.service.impl.order;

import java.rmi.RemoteException;
import java.util.Date;

import info.Room;
import logic.service.OrderLogicService;
import vo.NewOrderVO;

public class Order_Driver {
	public void drive(OrderLogicService service) throws RemoteException{
		service.getUserOrderInfo(1);
		service.getUserOrderInfo(2);
		service.getHotelOrderInfo(1);
		service.getHotelOrderInfo(2);
		service.getWEBOrderInfo();
		NewOrderVO vo = new NewOrderVO(1, 1, true, 1, "1", "1", new Date(), new Date(), new Room(), 0, 0, 0, 0);
		service.create(vo);
		vo.setRoomNum(1);
		service.create(vo);
		service.abnormal(1);
		service.abnormal(2);
		service.abnormal(3);
		service.abnormal(4);
		service.userRevoke(1);
		service.userRevoke(2);
		service.userRevoke(3);
		service.userRevoke(4);
		service.execute(1);
		service.execute(2);
		service.execute(3);
		service.execute(4);
		service.reExecute(1);
		service.reExecute(2);
		service.reExecute(3);
		service.reExecute(4);
		service.webRevoke(1,2);
		service.webRevoke(2,2);
		service.webRevoke(3,2);
		service.webRevoke(4,2);
	}
}
