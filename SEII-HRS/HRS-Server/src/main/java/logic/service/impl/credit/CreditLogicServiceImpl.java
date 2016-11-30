package logic.service.impl.credit;

import java.rmi.RemoteException;

import info.ListWrapper;
import logic.service.CreditLogicService;
import resultmessage.CreditResultMessage;
import vo.CreditVO;

public class CreditLogicServiceImpl implements CreditLogicService{
	CreditDO creditDO;
	public CreditLogicServiceImpl() {
		creditDO=new CreditDO();
	}
	@Override
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		return creditDO.getInfo(userId);
	}

	@Override
	public CreditResultMessage insert(CreditVO vo) throws RemoteException {
		return creditDO.insert(vo);
	}
	
}
