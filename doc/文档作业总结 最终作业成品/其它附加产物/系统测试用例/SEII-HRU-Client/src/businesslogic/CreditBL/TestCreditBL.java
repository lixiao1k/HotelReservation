package businesslogic.CreditBL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import businesslogic.MemberBL.MemberController;
import businesslogic.MemberBL.MemberInfo;
import businesslogicservice.CreditBLService.CreditResultMessage;
import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.CreditVO;

public class TestCreditBL {
	CreditController controller;
	public TestCreditBL(){
		controller = new CreditController();
	}
	public void test(){
		MemberInfo member1 = new MemberInfo("13312345678", "hyj");
		MemberInfo member2 = new MemberInfo("13587654321", "lyp");
		List<MemberInfo> members = new ArrayList<MemberInfo>();
		members.add(member1);
		members.add(member2);
		MemberController memberController = new MemberController();
		memberController.setMembers(members);
		controller.setMemberController(memberController);
		CreditInfo info1 = new CreditInfo(12344321, LocalDate.now(), -5, 65);
		CreditInfo info2 = new CreditInfo(12344321, LocalDate.now().minusDays(1),+5,75);
		CreditInfo info3 = new CreditInfo(member1.getId(), LocalDate.now(), +30,123);
		List<CreditInfo> credits = new ArrayList<>();
		credits.add(info1);
		credits.add(info2);
		credits.add(info3);
		controller.setList(credits);
		CreditList list;
		List<CreditInfo> cinfo;
		list= controller.getInfo(12344321);
		cinfo = list.getList();
		for (int i=0;i<cinfo.size();i++)
			System.out.println(cinfo.get(i));
		CreditResultMessage result;
		result = controller.update(member1.getId(), +3);
		System.out.println(result);
		list= controller.getInfo(12344321);
		cinfo = list.getList();
		for (int i=0;i<cinfo.size();i++)
			System.out.println(cinfo.get(i));
		result = controller.insert(new CreditVO(12342222,LocalDate.now(),+3,78));
		System.out.println(result);
	}
	public static void main(String[] args) {
		TestCreditBL test = new TestCreditBL();
		test.test();
	}
}
