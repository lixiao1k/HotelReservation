package logic.service.impl.credit;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.CreditDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.CreditPO;
import resultmessage.CreditResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.CreditVO;

public class CreditDO {
	private CreditDao creditDao;
	public CreditDO() {
		creditDao=DaoManager.getInstance().getCreditDao();
	}
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		ListWrapper<CreditPO> polist=creditDao.getinfo(userId);
		List<CreditVO> volist=null;
		while(polist.iterator().hasNext()){
			CreditPO po=polist.iterator().next();
			CreditVO vo=DozerMappingUtil.getInstance().map(po,CreditVO.class);
			volist.add(vo);
		}
		return DozerMappingUtil.getInstance().map(volist, ListWrapper.class);
	}

	public CreditResultMessage insert(CreditVO vo) throws RemoteException {
		CreditPO po=DozerMappingUtil.getInstance().map(vo, CreditPO.class);
		if(po.getCredit()<0){
			return CreditResultMessage.FAIL_LESSTHANZERO;
		}else{
			HibernateUtil.getCurrentSession().beginTransaction();
			creditDao.insert(po);
			return CreditResultMessage.SUCCESS;
		}
	}
}
