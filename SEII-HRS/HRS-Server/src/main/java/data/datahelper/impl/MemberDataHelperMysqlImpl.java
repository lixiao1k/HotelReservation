package data.datahelper.impl;


import java.util.List;

import org.hibernate.Query;

import data.datahelper.MemberDataHelper;
import po.MemberPO;
import util.HibernateUtil;

public class MemberDataHelperMysqlImpl implements MemberDataHelper{
	private static final String getInfoByString = "select m from MemberPO as m, UserPO as u where u.username=:USERNAME";
	@Override
	public void insert(MemberPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public MemberPO getInfo(long userid) {
		return (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class, userid);
	}

	@Override
	public MemberPO getInfo(String username) {
		Query query = HibernateUtil.getCurrentSession().createQuery(getInfoByString);
		query.setString("USERNAME", username);
		List<MemberPO> list = query.list();
		if(list!=null&&list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void update(MemberPO po) {
		HibernateUtil.getCurrentSession().update(po);
	}

	@Override
	public void delete(long userid) {
		MemberPO po = (MemberPO) HibernateUtil.getCurrentSession().get(MemberPO.class, userid);
		HibernateUtil.getCurrentSession().delete(po);
	}
	
}
