package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate工具类
 * @author Whk
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	/*
	 * 创建能被整个java应用使用的sessionFactory
	 * sessionFactory是重量级对象，只有一个数据源，故只创建一个供java应用使用
	 */
	static{
		try{
			sessionFactory = new Configuration()
									.configure()
									.buildSessionFactory();
		}catch(Throwable e){
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	/*
	 * 为了节省系统资源，这里hibernate采用等级二的事务级别，其事务依赖于线程，故通过getCurrentSession方法返回当前线程
	 * 其是线程安全的
	 * @return Thread-Safety Session
	 */
	public static Session getCurrentSession(){
		//返回当前的Session对象
		return sessionFactory.getCurrentSession();
	}
}
