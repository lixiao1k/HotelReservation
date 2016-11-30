package logic.service.impl.comment;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import data.dao.CommentDao;
import data.dao.Impl.DaoManager;
import info.ListWrapper;
import po.CommentPO;
import resultmessage.CommentResultMessage;
import util.DozerMappingUtil;
import util.HibernateUtil;
import vo.CommentVO;

public class CommentDO {
	private CommentDao commentDao;
	public CommentDO() {
		commentDao=DaoManager.getInstance().getCommentDao();
	}
	public ListWrapper<CommentVO> getHotelInfo(long hotelId) throws RemoteException {
		HibernateUtil.getCurrentSession().beginTransaction();
		ListWrapper<CommentPO> polist=commentDao.getHotelComment(hotelId);
		List<CommentVO> volist = null;
		while(polist.iterator().hasNext()){
			CommentPO po=polist.iterator().next();
			CommentVO vo=DozerMappingUtil.getInstance().map(po, CommentVO.class);
			volist.add(vo);
		}
		return DozerMappingUtil.getInstance().map(volist, ListWrapper.class);
	}

	public CommentResultMessage review(CommentVO vo) throws RemoteException {
		CommentPO po=DozerMappingUtil.getInstance().map(vo, CommentPO.class);
		HibernateUtil.getCurrentSession().beginTransaction();
		commentDao.insert(po);
		return CommentResultMessage.SUCCESS;
	}
}
