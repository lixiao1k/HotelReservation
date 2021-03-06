import businesslogic.BrowseBL.BrowseBLService_Stub;
import businesslogic.CommentBL.CommentBLService_Stub;
import businesslogic.CommentBL.CommentDataService_Driver;
import businesslogic.CreditBL.CreditBLService_Stub;
import businesslogic.CreditBL.CreditDataService_Driver;
import businesslogic.HotelBL.HotelBLService_Stub;
import businesslogic.MemberBL.MemberBLService_Stub;
import businesslogic.OrderBL.OrderBLService_Stub;
import businesslogic.StrategyBL.StrategyBLService_Stub;
import businesslogic.UserBL.UserBLService_Stub;
import businesslogicservice.BrowseBLService.BrowseBLService;
import businesslogicservice.BrowseBLService.BrowseDataService_Driver;
import businesslogicservice.CommentBLService.CommentBLService;
import businesslogicservice.CreditBLService.CreditBLService;
import businesslogicservice.HotelBLService.HotelBLService;
import businesslogicservice.HotelBLService.HotelDataService_Driver;
import businesslogicservice.MemberBLService.MemberBLService;
import businesslogicservice.MemberBLService.MemberDataService_Driver;
import businesslogicservice.OrderBLService.OrderBLService;
import businesslogicservice.OrderBLService.OrderDataService_Driver;
import businesslogicservice.StrategyBLService.StrategyBLService;
import businesslogicservice.StrategyBLService.StrategyDataService_Driver;
import businesslogicservice.UserBLService.UserBLService;
import businesslogicservice.UserBLService.UserDataService_Driver;
import dataservice.*;
import presentation.BrowseUI.BrowseBLService_Driver;
import presentation.CommentUI.CommentBLService_Driver;
import presentation.CreditUI.CreditBLService_Driver;
import presentation.HotelUI.HotelBLService_Driver;
import presentation.MemberUI.MemberBLService_Driver;
import presentation.OrderUI.OrderBLService_Driver;
import presentation.StrategyUI.StrategyBLService_Driver;
import presentation.UserUI.UserBLService_Driver;

public class Test {
	public static void main(String[] args) {
		BrowseBLService bbl = new BrowseBLService_Stub();
		CommentBLService cobl = new CommentBLService_Stub();
		HotelBLService hbl = new HotelBLService_Stub();
		OrderBLService obl = new OrderBLService_Stub();
		CreditBLService crbl = new CreditBLService_Stub();
		UserBLService ubl = new UserBLService_Stub();
		MemberBLService mbl = new MemberBLService_Stub();
		StrategyBLService sbl = new StrategyBLService_Stub();
		BrowseBLService_Driver bdriver = new BrowseBLService_Driver();
		CommentBLService_Driver codriver = new CommentBLService_Driver();
		HotelBLService_Driver hdriver = new HotelBLService_Driver();
		OrderBLService_Driver odriver = new OrderBLService_Driver();
		CreditBLService_Driver crdriver = new CreditBLService_Driver();
		UserBLService_Driver udriver = new UserBLService_Driver();
		MemberBLService_Driver mdriver = new MemberBLService_Driver();
		StrategyBLService_Driver sdriver = new StrategyBLService_Driver();
		bdriver.drive(bbl);
		codriver.drive(cobl);
		hdriver.drive(hbl);
		odriver.drive(obl);
		crdriver.drive(crbl);
		udriver.drive(ubl);
		mdriver.drive(mbl);
		sdriver.drive(sbl);
		// data ��
		BrowseDataService_Stub bd = new BrowseDataService_Stub();
		OrderDataService_Stub od = new OrderDataService_Stub();
		CreditDataService_Stub crd = new CreditDataService_Stub();
		CommentDataService_Stub cod = new CommentDataService_Stub();
		UserDataService_Stub ud = new UserDataService_Stub();
		MemberDataService_Stub md = new MemberDataService_Stub();
		HotelDataService_Stub hd = new HotelDataService_Stub();
		StrategyDataService_Stub sd = new StrategyDataService_Stub();
		BrowseDataService_Driver bddriver = new BrowseDataService_Driver();
		OrderDataService_Driver oddriver = new OrderDataService_Driver();
		CreditDataService_Driver crddriver = new CreditDataService_Driver();
		CommentDataService_Driver coddriver = new CommentDataService_Driver();
		UserDataService_Driver uddriver = new UserDataService_Driver();
		MemberDataService_Driver mddriver = new MemberDataService_Driver();
		HotelDataService_Driver hddriver = new HotelDataService_Driver();
		StrategyDataService_Driver sddriver = new StrategyDataService_Driver();
		bddriver.drive(bd);
		oddriver.drive(od);
		crddriver.drive(crd);
		coddriver.drive(cod);
		uddriver.drive(ud);
		mddriver.driver(md);
		hddriver.drive(hd);
		sddriver.drive(sd);
		
	}
}
