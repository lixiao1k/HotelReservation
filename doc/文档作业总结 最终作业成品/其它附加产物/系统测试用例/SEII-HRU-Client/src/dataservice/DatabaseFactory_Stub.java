package dataservice;

public class DatabaseFactory_Stub implements DatabaseFactory{

	@Override
	public DatabaseService getUserData() {
			UserDataService userData = new UserDataService_Stub();
			return userData;
	}
	
}
