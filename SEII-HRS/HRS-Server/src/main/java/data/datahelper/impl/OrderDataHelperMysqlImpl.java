package data.datahelper.impl;

import data.datahelper.HibernateUtil;
import data.datahelper.OrderDataHelper;
import po.OrderPO;

public class OrderDataHelperMysqlImpl implements OrderDataHelper{

	@Override
	public void insert(OrderPO po) {
		HibernateUtil.getCurrentSession()
						.save(po);
	}

	@Override
	public void update(OrderPO po) {
		HibernateUtil.getCurrentSession()
						.saveOrUpdate(po);
		
	}

	@Override
	public OrderPO getInfo(long orderId) {

		OrderPO po = (OrderPO)HibernateUtil.getCurrentSession()
								.get(OrderPO.class, orderId);
	
		return po;
	}

}
