package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.StrategyDataHelper;
import po.StrategyPO;
import util.HibernateUtil;

public class StrategyDataHelperMysqlImpl implements StrategyDataHelper{
	private static final String hotelStrategyList = "from Strategy as s where s.hotel=:HOTEL";
	private static final String webStrategyList = "from Strategy as s where s.type=1";
	@Override
	public void insert(StrategyPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public void delete(StrategyPO po) {
		HibernateUtil.getCurrentSession().delete(po);
	}

	@Override
	public StrategyPO getInfo(long strategyId) {
		return (StrategyPO) HibernateUtil.getCurrentSession().get(StrategyPO.class, strategyId);
	}

	@Override
	public List<StrategyPO> getHotelStrategyList(long hotelId) {
		Query query = HibernateUtil.getCurrentSession().createQuery(hotelStrategyList);
		query.setLong("HOTEL", hotelId);
		return query.list();
	}

	@Override
	public List<StrategyPO> getWEBStrategyList() {
		Query query = HibernateUtil.getCurrentSession().createQuery(webStrategyList);
		return query.list();
	}

}
