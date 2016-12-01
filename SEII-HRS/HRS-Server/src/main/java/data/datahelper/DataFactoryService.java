package data.datahelper;

public interface DataFactoryService {
	public OrderDataHelper getOrderDataHelper();
	public UserDataHelper getUserDataHelper();
	public CreditDataHelper getCreditDataHelper();
	public StrategyDataHelper getStrategyDataHelper();
	public HotelDataHelper getHotelDataHelper();
	public CommentDataHelper getCommentDataHelper();
	public MemberDataHelper getMemberDataHelper();
}
