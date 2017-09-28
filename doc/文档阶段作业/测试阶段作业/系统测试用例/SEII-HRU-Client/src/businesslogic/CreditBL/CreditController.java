package businesslogic.CreditBL;

import java.time.LocalDate;
import java.util.List;

import businesslogic.MemberBL.MemberController;
import businesslogicservice.CreditBLService.CreditBLService_Update;
import businesslogicservice.CreditBLService.CreditResultMessage;
import vo.CreditVO;

public class CreditController implements CreditBLService_Update{
	private Credit creditObject = new Credit();
	@Override
	public CreditList getInfo(long userId) {
		// TODO Auto-generated method stub
		return creditObject.getInfo(userId);
	}

	@Override
	public CreditResultMessage update(long userId, int value) {
		// TODO Auto-generated method stub
		CreditResultMessage result = creditObject.update(userId, value);
		CreditVO vo = new CreditVO(userId,LocalDate.now(),value,123);
		insert(vo);
		return result;
	}
	public void setMemberController(MemberController memberController){
		creditObject.setMemberController(memberController);
	}
	@Override
	public CreditResultMessage insert(CreditVO vo) {
		// TODO Auto-generated method stub
		return creditObject.insert(vo);
	}
	public void setList(List<CreditInfo> list){
		creditObject.setCreditInfoList(list);
	}
}
