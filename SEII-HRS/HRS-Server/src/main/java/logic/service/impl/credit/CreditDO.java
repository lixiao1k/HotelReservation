package logic.service.impl.credit;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import data.dao.CreditDao;
import data.dao.UserDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.ClientMemberPO;
import po.CreditPO;
import po.MemberPO;
import po.UserPO;
import resultmessage.CreditResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.CreditVO;

public class CreditDO {
	private CreditDao creditDao;
	public CreditDO() {
		creditDao=DaoManager.getInstance().getCreditDao();
	}
	public CreditResultMessage excharge(long userId, int delta) {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			MemberPO mpo=DaoManager.getInstance().getMemberDao().getInfo(userId);
			if(mpo==null)
				return CreditResultMessage.FAIL;
			((ClientMemberPO)mpo).setCredit(((ClientMemberPO)mpo).getCredit()+100*delta);
			CreditPO cpo = new CreditPO(mpo, new Date(), 100*delta, ((ClientMemberPO)mpo).getCredit(), "–≈”√≥‰÷µ");
			creditDao.insert(cpo);
			DaoManager.getInstance().getMemberDao().update(mpo);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return CreditResultMessage.SUCCESS;
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
				return CreditResultMessage.FAIL;
			}catch(RuntimeException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
	
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<CreditPO> polist=creditDao.getinfo(userId);
			List<CreditVO> volist=new ArrayList<CreditVO>();
			Iterator<CreditPO> it = polist.iterator();
			while(it.hasNext()){
				CreditPO po=it.next();
				CreditVO vo=DozerMappingUtil.getInstance().map(po,CreditVO.class);
				volist.add(vo);
			}
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return new ListWrapper<>(volist);
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
				return null;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
