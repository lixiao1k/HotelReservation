package businesslogicservice.CreditBLService;

import businesslogic.CreditBL.CreditList;
import vo.CreditVO;

public interface CreditBLService_Update {
	public CreditList getInfo(long userId);
	public CreditResultMessage update(long userId,int value);
	public CreditResultMessage insert(CreditVO vo);
}
