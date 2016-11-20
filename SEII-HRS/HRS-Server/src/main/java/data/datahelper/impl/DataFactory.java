package data.datahelper.impl;

import data.datahelper.DataFactoryService;
import data.datahelper.OrderDataHelper;

public class DataFactory implements DataFactoryService{
	private OrderDataHelper orderDataHelper;
	private static DataFactory instance;

	static{
		try{
		instance = new DataFactory();
		}catch(Throwable e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DataFactory getInstance(){
		return instance;
	}
	@Override
	public OrderDataHelper getOrderDataHelper() {
		if(orderDataHelper==null)
			orderDataHelper = new OrderDataHelperMysqlImpl();
		return orderDataHelper;
	}

}
