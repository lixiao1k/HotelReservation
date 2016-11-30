package data.datahelper.impl;

import data.datahelper.CreditDataHelper;
import data.datahelper.DataFactoryService;
import data.datahelper.OrderDataHelper;
import data.datahelper.StrategyDataHelper;
import data.datahelper.UserDataHelper;

public class DataFactory implements DataFactoryService{
	private OrderDataHelper orderDataHelper;
	private UserDataHelper userDataHelper;
	private CreditDataHelper creditDataHelper;
	private StrategyDataHelper strategyDataHelper;
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
	@Override
	public UserDataHelper getUserDataHelper() {
		if(userDataHelper==null)
			userDataHelper = new UserDataHelperMysqlImpl();
		return userDataHelper;
	}
	@Override
	public CreditDataHelper getCreditDataHelper() {
		if(creditDataHelper==null)
			creditDataHelper = new CreditDataHelperMysqlImpl();
		return creditDataHelper;
	}
	@Override
	public StrategyDataHelper getStrategyDataHelper() {
		if(strategyDataHelper==null)
			strategyDataHelper = new StrategyDataHelperMysqlImpl();
		return strategyDataHelper;
	}

}
