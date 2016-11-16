package logic.service;

import list.CreditList;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public interface CreditLogicService {
	public CreditList getInfo(long userId);
	public CreditResultMessage update(long userId,int value);
	public CreditResultMessage insert(CreditVO vo);
}
