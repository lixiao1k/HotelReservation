package logic.service.impl.credit;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import data.dao.CreditDao;
import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.CreditPO;
import po.UserPO;
import resultmessage.CreditResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.CreditVO;

public class CreditDO {
	private CreditDao creditDao;
	private UserDao userDao;
	public CreditDO() {
		creditDao=DaoManager.getInstance().getCreditDao();
		userDao=DaoManager.getInstance().getUserDao();
	}
	public CreditResultMessage excharge(long userId, int delta) {
		HibernateUtil.getCurrentSession().beginTransaction();
		UserPO upo=userDao.getInfo(userId);
		if(upo.getCredit()+delta<0){
			return CreditResultMessage.FAIL_LESSTHANZERO;
		}else{
			LocalDate time=LocalDate.now();
			CreditPO po=new CreditPO(userId, time, delta, upo.getCredit());
			creditDao.insert(po);
			upo.setCredit(upo.getCredit()+delta);
			userDao.update(upo);
			return CreditResultMessage.SUCCESS;
		}
		
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
}
