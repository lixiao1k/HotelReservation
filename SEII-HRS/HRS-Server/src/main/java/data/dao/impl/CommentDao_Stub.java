package data.dao.impl;

import java.rmi.RemoteException;

import data.dao.CommentDao;
import info.ListWrapper;
import po.CommentPO;

public class CommentDao_Stub implements CommentDao{

	@Override
	public void insert(CommentPO po) {
		System.out.println("CommentDao.insert  :  insert success!");
	}

	@Override
	public ListWrapper<CommentPO> getUserComment(long userId) throws RemoteException {
		if(userId==1){
			System.out.println("CommentDao.getUserComment  :  userId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(userId==0){
			System.out.println("CommentDao.getUserComment  :  userId=0|return null");
			return null;
		}
		ListWrapper<CommentPO> list = new ListWrapper<>();
		list.add(new CommentPO());
		System.out.println("CommentDao.getUserComment  :  userId=other number|return normal result");
		return list;
	}

	@Override
	public ListWrapper<CommentPO> getHotelComment(long hotelId) throws RemoteException {
		if(hotelId==1){
			System.out.println("CommentDao.getHotelComment  :  hotelId=1|return empty ListWrapper");
			return new ListWrapper<>();
		}
		if(hotelId==0){
			System.out.println("CommentDao.getHotelComment  :  hotelId=0|return null");
			return null;
		}
		ListWrapper<CommentPO> list = new ListWrapper<>();
		list.add(new CommentPO());
		System.out.println("CommentDao.getHotelComment  :  hotelId=other number|return normal result");
		return list;
	}

}
