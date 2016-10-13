import businesslogic.BrowseBL.BrowseBLService_Stub;
import businesslogic.CommentBL.CommentBLService_Stub;
import businesslogic.CreditBL.CreditBLService_Stub;
import businesslogic.HotelBL.HotelBLService_Stub;
import businesslogic.OrderBL.OrderBLService_Stub;
import businesslogic.UserBL.UserBLService_Stub;
import businesslogicservice.BrowseBLService.BrowseBLService;
import businesslogicservice.CommentBLService.CommentBLService;
import businesslogicservice.CreditBLService.CreditBLService;
import businesslogicservice.HotelBLService.HotelBLService;
import businesslogicservice.OrderBLService.OrderBLService;
import businesslogicservice.UserBLService.UserBLService;
import presentation.BrowseUI.BrowseBLService_Driver;
import presentation.CommentUI.CommentBLService_Driver;
import presentation.CreditUI.CreditBLService_Driver;
import presentation.HotelUI.HotelBLService_Driver;
import presentation.OrderUI.OrderBLService_Driver;
import presentation.UserUI.UserBLService_Driver;

public class Test {
	public static void main(String[] args) {
		BrowseBLService bbl = new BrowseBLService_Stub();
		CommentBLService cobl = new CommentBLService_Stub();
		HotelBLService hbl = new HotelBLService_Stub();
		OrderBLService obl = new OrderBLService_Stub();
		CreditBLService crbl = new CreditBLService_Stub();
		UserBLService ubl = new UserBLService_Stub();
		BrowseBLService_Driver bdriver = new BrowseBLService_Driver();
		CommentBLService_Driver codriver = new CommentBLService_Driver();
		HotelBLService_Driver hdriver = new HotelBLService_Driver();
		OrderBLService_Driver odriver = new OrderBLService_Driver();
		CreditBLService_Driver crdriver = new CreditBLService_Driver();
		UserBLService_Driver udriver = new UserBLService_Driver();
		bdriver.drive(bbl);
		codriver.drive(cobl);
		hdriver.drive(hbl);
		odriver.drive(obl);
		crdriver.drive(crbl);
		udriver.drive(ubl);
	}
}
