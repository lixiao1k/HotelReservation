package businesslogic.MemberBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.MemberVO;
import vo.VIPVO;

public class TestMemberBL {
	MemberController controller;
	public TestMemberBL(){
		controller = new MemberController();
		
	}
	public void test(){
		MemberInfo member1 = new MemberInfo("13312345678", "hyj");
		MemberInfo member2 = new MemberInfo("13587654321", "lyp");
		List<MemberInfo> members = new ArrayList<MemberInfo>();
		members.add(member1);
		members.add(member2);
		MemberResultMessage result;
		controller.setMembers(members);
		VIPVO vipvo = new VIPVO(0, "1997-06-06",member1.getId());
		result = controller.registerVIP(vipvo);
		System.out.println(result);
		member1.updateCredit(30);
		result = controller.registerVIP(vipvo);
		System.out.println(result);
		result = controller.registerVIP(vipvo);
		System.out.println(result);
		vipvo = new VIPVO(0, "1997-06-06",12314);
		result = controller.registerVIP(vipvo);
		System.out.println(result);
		result = controller.cancel(member2.getId());
		System.out.println(result);
		result = controller.cancel(1234);
		System.out.println(result);
		result = controller.cancel(member1.getId());
		System.out.println(result);
		MemberVO membervo = controller.getInfo(member1.getId());
		System.out.println(membervo);
		member1.updateCredit(-1);
		result = controller.cancel(member1.getId());
		System.out.println(result);
		membervo = controller.getInfo(member2.getId());
		System.out.println(membervo);
		MemberVO memberVO1 = new MemberVO(member1);
		memberVO1.setName("whk");
		result = controller.changeInfo(memberVO1);
		System.out.println(result);
		membervo = controller.getInfo(member1.getId());
		System.out.println(membervo);
		MemberVO newVO = new MemberVO("1234","1234");
		result = controller.changeInfo(newVO);
		System.out.println(result);
		MemberInfoList list = controller.manageInfo();
		List<MemberVO> listm = list.getList();
		for (int i=0;i<listm.size();i++){
			System.out.println(listm);
		}
	}
	public static void main(String[] args) {
		TestMemberBL test = new TestMemberBL();
		test.test();
	}
}
