package data.dao;

public interface DaoManagerService {
	public OrderDao getOrderDao();
	public UserDao getUserDao();
	public CreditDao getCreditDao();
	public StrategyDao getStrategyDao();
	public CommentDao getCommentDao();
	public MemberDao getMemberDao();
	public HotelDao getHotelDao();
}
