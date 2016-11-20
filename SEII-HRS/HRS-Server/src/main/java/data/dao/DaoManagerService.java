package data.dao;

public interface DaoManagerService {
	public OrderDao getOrderDao();
	public UserDao getUserDao();
	public CreditDao getCreditDao();
}
