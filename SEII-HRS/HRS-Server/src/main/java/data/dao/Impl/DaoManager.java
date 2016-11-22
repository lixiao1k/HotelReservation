package data.dao.Impl;

import data.dao.CommentDao;
import data.dao.CreditDao;
import data.dao.DaoManagerService;
import data.dao.HotelDao;
import data.dao.MemberDao;
import data.dao.OrderDao;
import data.dao.StrategyDao;
import data.dao.UserDao;

public class DaoManager implements DaoManagerService{
	private static DaoManager instance;
	private OrderDao orderDao;
	private UserDao userDao;
	private CreditDao creditDao;
	private StrategyDao strategyDao;
	private CommentDao commentDao;
	private MemberDao memberDao;
	private HotelDao hotelDao;
	static{
		try{
		instance = new DaoManager();
		}catch(Throwable e){
			throw new ExceptionInInitializerError(e);
		}
	}
	public static DaoManager getInstance(){
		return instance;
	}
	@Override
	public OrderDao getOrderDao() {
		if (orderDao==null)
			orderDao = new OrderDaoImpl();
		return orderDao;
	}
	@Override
	public UserDao getUserDao() {
		if (userDao==null)
			userDao = new UserDaoImpl();
		return userDao;
	}
	@Override
	public CreditDao getCreditDao() {
		if (creditDao==null)
			creditDao = new CreditDaoImpl();
		return creditDao;
	}
	@Override
	public StrategyDao getStrategyDao() {
		if (strategyDao==null)
			strategyDao = new StrategyDaoImpl();
		return strategyDao;
	}
	@Override
	public CommentDao getCommentDao() {
		if (commentDao==null)
			commentDao = new CommentDaoImpl();
		return commentDao;
	}
	@Override
	public MemberDao getMemberDao() {
		if (memberDao==null)
			memberDao = new MemberDaoImpl();
		return memberDao;
	}
	@Override
	public HotelDao getHotelDao() {
		if (hotelDao==null)
			hotelDao = new HotelDaoImpl();
		return hotelDao;
	}
	
}
