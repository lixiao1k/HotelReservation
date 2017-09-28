package data.datahelper.impl;

import data.datahelper.CommentDataHelper;
import data.datahelper.CreditDataHelper;
import data.datahelper.DataFactoryService;
import data.datahelper.HotelDataHelper;
import data.datahelper.MemberDataHelper;
import data.datahelper.OrderDataHelper;
import data.datahelper.StrategyDataHelper;
import data.datahelper.UserDataHelper;

public class DataFactory implements DataFactoryService{
	private OrderDataHelper orderDataHelper;
	private UserDataHelper userDataHelper;
	private CreditDataHelper creditDataHelper;
	private StrategyDataHelper strategyDataHelper;
	private HotelDataHelper hotelDataHelper;
	private CommentDataHelper commentDataHelper;
	private MemberDataHelper memberDataHelper;
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
	@Override
	public HotelDataHelper getHotelDataHelper() {
		if(hotelDataHelper==null)
			hotelDataHelper = new HotelDataHelperMysqlImpl();
		return hotelDataHelper;
	}
	@Override
	public CommentDataHelper getCommentDataHelper() {
		if(commentDataHelper==null)
			commentDataHelper = new CommentDataHelperMysqlImpl();
		return commentDataHelper;
	}
	@Override
	public MemberDataHelper getMemberDataHelper() {
		if(memberDataHelper==null)
			memberDataHelper = new MemberDataHelperMysqlImpl();
		return memberDataHelper;
	}

}
