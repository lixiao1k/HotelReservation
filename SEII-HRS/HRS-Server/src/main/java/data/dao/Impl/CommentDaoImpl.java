package data.dao.Impl;

import java.rmi.RemoteException;
import java.util.List;

import data.dao.CommentDao;
import data.datahelper.CommentDataHelper;
import data.datahelper.impl.DataFactory;
import info.ListWrapper;
import po.CommentPO;

public class CommentDaoImpl implements CommentDao{
	private CommentDataHelper commentDataHelper;
	public CommentDaoImpl() {
		commentDataHelper = DataFactory.getInstance().getCommentDataHelper();
	}
	@Override
	public void insert(CommentPO po) {
		commentDataHelper.insert(po);
	}

	@Override
	public ListWrapper<CommentPO> getUserComment(long userId) throws RemoteException {
		List<CommentPO> userComment = commentDataHelper.getUserComment(userId);
		return new ListWrapper<CommentPO>(userComment);
	}

	@Override
	public ListWrapper<CommentPO> getHotelComment(long hotelId) throws RemoteException {
		List<CommentPO> hotelComment = commentDataHelper.getHotelComment(hotelId);
		return new ListWrapper<CommentPO>(hotelComment);
	}

}
