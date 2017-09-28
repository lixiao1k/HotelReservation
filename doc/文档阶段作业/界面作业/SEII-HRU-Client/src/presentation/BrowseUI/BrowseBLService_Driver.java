package presentation.BrowseUI;

import java.util.List;

import businesslogicservice.BrowseBLService.BrowseBLService;
import businesslogicservice.HotelBLService.HotelBLService;
import vo.HotelVO;
import vo.MemberVO;
import vo.OrderType;
import vo.RuleVO;
import vo.StrategyVO;

public class BrowseBLService_Driver {
	public void drive(BrowseBLService service){
		List list = service.getBrowseInfo(1234);
		if (list!=null) System.out.println("BrowseBLService.getBrowseInfo SUCCESS");
		list = service.getCreditInfo(1234);
		if (list!=null) System.out.println("BrowseBLService.getCreditInfo SUCCESS");
		list = service.getHotelOrdersInfo(1234,OrderType.GOOD);
		if (list!=null) System.out.println("BrowseBLService.getHotelOrdersInfo SUCCESS");
		RuleVO rvo = new RuleVO();
		list = service.getHotels(rvo);
		if (list!=null) System.out.println("BrowseBLService.getHotels SUCCESS");
		list = service.getOrdersInfo(1234, OrderType.GOOD);
		if (list!=null) System.out.println("BrowseBLService.getOrdersInfo SUCCESS");
		list = service.getWEBOrdersInfo();
		if (list!=null) System.out.println("BrowseBLService.getWEBOrdersInfo SUCCESS");
		HotelVO hvo = service.getHotel(1234);
		if (hvo!=null) System.out.println("BrowseBLService.getHotel SUCCESS");
		MemberVO mvo = service.getMemberInfo(1234);
		if (mvo!=null) System.out.println("BrowseBLService.getMemberInfo SUCCESS");
		StrategyVO svo = service.getStrategyInfo(1234);
		if (svo!=null) System.out.println("BrowseBLService.getStrategyInfo SUCCESS");
	}
}
