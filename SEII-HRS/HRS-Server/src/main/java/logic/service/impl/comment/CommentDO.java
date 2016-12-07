package logic.service.impl.comment;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.hibernate.Hibernate;

import data.dao.CommentDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.CommentPO;
import po.HotelPO;
import po.MemberPO;
import po.OrderPO;
import resultmessage.CommentResultMessage;
import resultmessage.OrderResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.CommentVO;
import vo.HotelCommentVO;

public class CommentDO {
	private CommentDao commentDao;
	public CommentDO() {
		commentDao=DaoManager.getInstance().getCommentDao();
	}
	public ListWrapper<HotelCommentVO> getHotelInfo(long hotelId) throws RemoteException {
		try{
			HibernateUtil.getCurrentSession().beginTransaction();
			ListWrapper<CommentPO> polist=commentDao.getHotelComment(hotelId);
			List<HotelCommentVO> volist = new ArrayList<HotelCommentVO>();
			Iterator<CommentPO> it = polist.iterator();
			while(it.hasNext()){
				CommentPO po=it.next();
				Hibernate.initialize(po.getMember());
				HotelCommentVO vo=DozerMappingUtil.getInstance().map(po, HotelCommentVO.class);
				volist.add(vo);
			}
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return new ListWrapper<HotelCommentVO>(volist);
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

	public CommentResultMessage review(CommentVO vo) throws RemoteException {
		try{
			CommentPO po=DozerMappingUtil.getInstance().map(vo, CommentPO.class);
			HibernateUtil.getCurrentSession().beginTransaction();
			MemberPO mpo = (MemberPO) HibernateUtil.getCurrentSession().load(MemberPO.class, vo.getUserId());
			OrderPO opo = (OrderPO) HibernateUtil.getCurrentSession().load(OrderPO.class, vo.getOrderId());
			if(mpo==null||opo==null)
				return CommentResultMessage.FAIL;
			po.setHotel(opo.getHotel());
			po.setMember(mpo);
			po.setOrder(opo);
			po.setRoom(opo.getRoom());
			commentDao.insert(po);
			HibernateUtil.getCurrentSession().getTransaction().commit();
			return CommentResultMessage.SUCCESS;
		}catch(RuntimeException e){
			e.printStackTrace();
			try{
				HibernateUtil.getCurrentSession().getTransaction().rollback();
				return CommentResultMessage.FAIL_HAVEREVIEWED;
			}catch(RuntimeErrorException ex){
				ex.printStackTrace();
			}
			throw e;
		}
	}
}
