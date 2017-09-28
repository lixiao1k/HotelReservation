package businesslogic.CreditBL;

import java.util.ArrayList;
import java.util.List;

import businesslogic.MemberBL.MemberController;
import businesslogicservice.CreditBLService.CreditResultMessage;
import businesslogicservice.MemberBLService.MemberResultMessage;
import vo.CreditVO;

public class Credit {
	List<CreditInfo> creditInfoList;
	MemberController memberController;
	public void setMemberController(MemberController memberController){
		this.memberController = memberController;
	}
	public void setCreditInfoList(List<CreditInfo> list){
		this.creditInfoList = list;
	}
	public CreditList getInfo(long userId) {
		// TODO Auto-generated method stub
		List<CreditInfo> result = new ArrayList<>();
		for (CreditInfo info:creditInfoList){
			if (info.getUserId()==userId)
				result.add(info);
		}
		return new CreditList(result);
	}

	public CreditResultMessage update(long userId, int value) {
		// TODO Auto-generated method stub
		memberController.getInfo(userId).updateCredit(value);
		return CreditResultMessage.SUCCESS;
	}

	public CreditResultMessage insert(CreditVO vo) {
		// TODO Auto-generated method stub
		CreditInfo credit = new CreditInfo(vo);
		this.creditInfoList.add(credit);
		return CreditResultMessage.SUCCESS;
	}
}
