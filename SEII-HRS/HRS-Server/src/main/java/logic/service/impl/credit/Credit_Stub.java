package logic.service.impl.credit;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.CreditLogicService;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public class Credit_Stub implements CreditLogicService{

	@Override
	public CreditResultMessage excharge(long userId, int delta) throws RemoteException {
		if(delta<=0){
			System.out.println("Credit.excharge  :  delta<=0|return CreditResultMessage.FAIL_LESSTHANZERO");
			return CreditResultMessage.FAIL_LESSTHANZERO;
		}
		if(userId==1){
			System.out.println("Credit.excharge  :  userId==1|return CreditResultMessage.FAIL");
			return CreditResultMessage.FAIL;
		}
		return CreditResultMessage.SUCCESS;
	}

	@Override
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		CreditVO cvo1 = new CreditVO();
		CreditVO cvo2 = new CreditVO();
		ListWrapper<CreditVO> result = new ListWrapper<>();
		result.add(cvo2);
		result.add(cvo1);
		System.out.println("Credit.getInfo  :  return normal result");
		return result;
	}

}
