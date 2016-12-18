package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.CommentDataHelper;
import po.CommentPO;
import po.HotelPO;
import po.MemberPO;
import util.HibernateUtil;

public class CommentDataHelperMysqlImpl implements CommentDataHelper{
	private static final String getUserComment = "from CommentPO as c where c.member=:MEMBER";
	private static final String getHotelComment = "from CommentPO as c where c.hotel=:HOTEL";
	@Override
	public void insert(CommentPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public List<CommentPO> getUserComment(long userId) {
		MemberPO po = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class, userId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getUserComment);
		if(po!=null)
			query.setEntity("MEMBER", po);
		else
			return null;
		return query.list();
	}

	@Override
	public List<CommentPO> getHotelComment(long hotelId) {
		HotelPO po = (HotelPO) HibernateUtil.getCurrentSession().get(HotelPO.class, hotelId);
		Query query = HibernateUtil.getCurrentSession().createQuery(getHotelComment);
		if(po!=null)
			query.setEntity("HOTEL", po);
		else
			return null;
		return query.list();
	}

}
