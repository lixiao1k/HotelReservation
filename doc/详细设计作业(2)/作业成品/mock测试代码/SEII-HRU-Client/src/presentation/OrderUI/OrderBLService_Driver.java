package presentation.OrderUI;

import java.util.List;

import businesslogicservice.OrderBLService.OrderBLService;
import businesslogicservice.OrderBLService.OrderResultMessage;
import vo.OrderType;
import vo.OrderVO;

public class OrderBLService_Driver {
	public void drive(OrderBLService service){
		List list = service.getHotelOrdersInfo(1234, OrderType.GOOD);
		if (list!=null) System.out.println("OrderBLService.getHotelOrdersInfo SUCCESS");
		list = service.getuserInfo(1234, OrderType.GOOD);
		if (list!=null) System.out.println("OrderBLService.getuserInfo SUCCESS");
		list = service.getWEBOrdersInfo();
		if (list!=null) System.out.println("OrderBLService.getWEBOrdersInfo SUCCESS");
		OrderVO vo = new OrderVO();
		OrderResultMessage result = service.create(vo);
		if (result==OrderResultMessage.SUCCESS) System.out.println("OrderBLService.create SUCCESS");
		result = service.execute(vo);
		if (result==OrderResultMessage.SUCCESS) System.out.println("OrderBLService.execute SUCCESS");
		result = service.userCancel(vo);
		if (result==OrderResultMessage.SUCCESS) System.out.println("OrderBLService.userCancel SUCCESS");
		result = service.WEBCancel(vo);
		if (result==OrderResultMessage.SUCCESS) System.out.println("OrderBLService.WEBCancel SUCCESS");
		double total = service.getTotal(vo);
		if ((total-1000)<0.001)  System.out.println("OrderBLService.getTotal SUCCESS");
	}
}
