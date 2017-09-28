package businesslogic.CreditBL;

import java.util.ArrayList;
import java.util.List;

import businesslogicservice.CreditBLService.CreditBLService;
import businesslogicservice.CreditBLService.CreditResultMessage;
import vo.CreditVO;


public class CreditBLService_Stub implements CreditBLService{

	@Override
	public List<CreditVO> getInfo(long userID) {
		// TODO Auto-generated method stub
		// ������Ӧ�û������ü�¼�������漰��database�Ĳ��������½�һ�����б����Թ�
		CreditVO vo = new CreditVO();
		List<CreditVO> list = new ArrayList<CreditVO>();
		list.add(vo);
		return list;
	}

	@Override
	public CreditResultMessage recharge(int value) {
		// TODO Auto-generated method stub
		if (value>0) return CreditResultMessage.SUCCESS;
		return CreditResultMessage.FAIL_LESSTHANZERO;
	}

	@Override
	public void add(long userid, int value) {
		// TODO Auto-generated method stub
		//add value to userid's credit
	}

	@Override
	public void decrease(long userid, int value) {
		// TODO Auto-generated method stub
		//decrease value to userid's credit
	}

}
