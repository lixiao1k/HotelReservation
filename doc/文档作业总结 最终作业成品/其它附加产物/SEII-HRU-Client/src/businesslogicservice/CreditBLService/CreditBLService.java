package businesslogicservice.CreditBLService;

import java.util.List;

import vo.CreditVO;

public interface CreditBLService {
	public List<CreditVO> getInfo(long userID);
	public CreditResultMessage recharge(int value);
	public void add(long userid,int value);
	public void decrease(long userid,int value);
}
