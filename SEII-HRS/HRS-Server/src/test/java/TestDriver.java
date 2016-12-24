import java.rmi.RemoteException;

import org.junit.Test;

import data.dao.impl.CommentDao_Stub;
import data.dao.impl.CreditDao_Stub;
import data.dao.impl.HotelDao_Stub;
import data.dao.impl.MemberDao_Stub;
import data.dao.impl.OrderDao_Stub;
import data.dao.impl.StrategyDao_Stub;
import data.dao.impl.UserDao_Stub;
import logic.service.impl.comment.CommentDao_Driver;
import logic.service.impl.comment.Comment_Driver;
import logic.service.impl.comment.Comment_Stub;
import logic.service.impl.credit.CreditDao_Driver;
import logic.service.impl.credit.Credit_Driver;
import logic.service.impl.credit.Credit_Stub;
import logic.service.impl.hotel.HotelDao_Driver;
import logic.service.impl.hotel.Hotel_Driver;
import logic.service.impl.hotel.Hotel_Stub;
import logic.service.impl.member.MemberDao_Driver;
import logic.service.impl.member.Member_Driver;
import logic.service.impl.member.Member_Stub;
import logic.service.impl.order.OrderDao_Driver;
import logic.service.impl.order.Order_Driver;
import logic.service.impl.order.Order_Stub;
import logic.service.impl.strategy.StrategyDao_Driver;
import logic.service.impl.strategy.Strategy_Driver;
import logic.service.impl.strategy.Strategy_Stub;
import logic.service.impl.user.UserDao_Driver;
import logic.service.impl.user.User_Driver;
import logic.service.impl.user.User_Stub;

public class TestDriver {
	@Test
	public void testAllDao() throws RemoteException{
		CommentDao_Stub cstub = new CommentDao_Stub();
		CommentDao_Driver cdriver = new CommentDao_Driver();
		cdriver.drive(cstub);
		CreditDao_Stub crstub = new CreditDao_Stub();
		CreditDao_Driver crdriver = new CreditDao_Driver();
		crdriver.drive(crstub);
		HotelDao_Stub hstub = new HotelDao_Stub();
		HotelDao_Driver hdriver = new HotelDao_Driver();
		hdriver.drive(hstub);
		MemberDao_Stub mstub = new MemberDao_Stub();
		MemberDao_Driver mdriver = new MemberDao_Driver();
		mdriver.drive(mstub);
		OrderDao_Stub ostub = new OrderDao_Stub();
		OrderDao_Driver odriver = new OrderDao_Driver();
		odriver.drive(ostub);
		StrategyDao_Stub sstub = new StrategyDao_Stub();
		StrategyDao_Driver sdriver = new StrategyDao_Driver();
		sdriver.drive(sstub);
		UserDao_Stub ustub = new UserDao_Stub();
		UserDao_Driver udriver = new UserDao_Driver();
		udriver.drive(ustub);	
	}
	@Test
	public void testAllLogic() throws RemoteException{
		Comment_Stub cs = new Comment_Stub();
		Comment_Driver cd = new Comment_Driver();
		cd.drive(cs);
		Credit_Stub crs = new Credit_Stub();
		Credit_Driver crd = new Credit_Driver();
		crd.drive(crs);
		Hotel_Stub hs = new Hotel_Stub();
		Hotel_Driver hd = new Hotel_Driver();
		hd.drive(hs);
		Member_Stub ms= new Member_Stub();
		Member_Driver md = new Member_Driver();
		md.drive(ms);
		Order_Stub os = new Order_Stub();
		Order_Driver od = new Order_Driver();
		od.drive(os);
		Strategy_Stub ss = new Strategy_Stub();
		Strategy_Driver sd = new Strategy_Driver();
		sd.drive(ss);
		User_Stub us = new User_Stub();
		User_Driver ud = new User_Driver();
		ud.drive(us);
	}
}
