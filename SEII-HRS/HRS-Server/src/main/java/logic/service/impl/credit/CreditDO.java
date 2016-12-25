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
import data.dao.impl.DaoManager;
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
	/**
	 * 信用充值
	 * 网站营销人员进行信用充值操作若调用的方法
	 * @param userId
	 * 需要充值的对象
	 * 用户id
	 * @param delta
	 * 用户所充的钱，实际充值信用为delta*100
	 * @return CreditResultMessage
	 * 充值成功消息
	 */
	public CreditResultMessage excharge(long userId, int delta) {
		try{
			if(delta<=0)
				return CreditResultMessage.FAIL_LESSTHANZERO;
			HibernateUtil.getCurrentSession().beginTransaction();
			MemberPO mpo=DaoManager.getInstance().getMemberDao().getInfo(userId);
			if(mpo==null)
				return CreditResultMessage.FAIL;
			((ClientMemberPO)mpo).setCredit(((ClientMemberPO)mpo).getCredit()+100*delta);
			CreditPO cpo = new CreditPO(mpo, new Date(), 100*delta, ((ClientMemberPO)mpo).getCredit(), "信用充值");
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
	
	/**
	 * 客户查看自身信用记录时调用，返回用户的所有的信用记录
	 * @param userId
	 * 用户id
	 * @return ListWrapper<CreditVO>
	 * 用户的信用记录列表
	 * @throws RemoteException
	 */
	public ListWrapper<CreditVO> getInfo(long userId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<CreditPO> polist=creditDao.getinfo(userId);
			if(polist==null)
				return null;
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
