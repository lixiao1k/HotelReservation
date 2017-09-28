package data.datahelper.impl;

import java.util.List;

import org.hibernate.Query;

import data.datahelper.StrategyDataHelper;
import info.StrategyType;
import po.HotelPO;
import po.StrategyPO;
import util.HibernateUtil;

public class StrategyDataHelperMysqlImpl implements StrategyDataHelper{
	private static final String hotelStrategyList = "from StrategyPO as s where s.hotel=:HOTEL and s.status='false'";
	private static final String webStrategyList = "from StrategyPO as s where s.type=1 and s.status='false'";
	private static final String getTypesList = "from StrategyType";
	@Override
	public void insert(StrategyPO po) {
		HibernateUtil.getCurrentSession().save(po);
	}

	@Override
	public void update(StrategyPO po) {
		HibernateUtil.getCurrentSession().update(po);
	}

	@Override
	public StrategyPO getInfo(long strategyId) {
		return (StrategyPO) HibernateUtil.getCurrentSession().get(StrategyPO.class, strategyId);
	}

	@Override
	public List<StrategyPO> getHotelStrategyList(long hotelId) {
		HotelPO hotel = (HotelPO) HibernateUtil.getCurrentSession().get(HotelPO.class, hotelId);
		Query query = HibernateUtil.getCurrentSession().createQuery(hotelStrategyList);
		query.setEntity("HOTEL", hotel);
		return query.list();
	}

	@Override
	public List<StrategyPO> getWEBStrategyList() {
		Query query = HibernateUtil.getCurrentSession().createQuery(webStrategyList);
		return query.list();
	}

	@Override
	public List<StrategyType> getTypes() {
		Query query = HibernateUtil.getCurrentSession().createQuery(webStrategyList);
		return query.list();
	}

}
