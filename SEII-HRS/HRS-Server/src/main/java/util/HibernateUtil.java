package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate������
 * @author Whk
 *
 */
public class HibernateUtil {
	private static SessionFactory sessionFactory;
	/*
	 * �����ܱ�����javaӦ��ʹ�õ�sessionFactory
	 * sessionFactory������������ֻ��һ������Դ����ֻ����һ����javaӦ��ʹ��
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
	 * Ϊ�˽�ʡϵͳ��Դ������hibernate���õȼ��������񼶱��������������̣߳���ͨ��getCurrentSession�������ص�ǰ�߳�
	 * �����̰߳�ȫ��
	 * @return Thread-Safety Session
	 */
	public static Session getCurrentSession(){
		//���ص�ǰ��Session����
		return sessionFactory.getCurrentSession();
	}
}
