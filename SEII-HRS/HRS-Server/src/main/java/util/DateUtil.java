package util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	// date format:yyyy-mm-dd
	public static Date transform(String date){
		String[] data = date.split("-");
		Date result;
		if(data.length!=3)
			throw new IllegalArgumentException();
		else{
			int year = Integer.parseInt(data [0].trim());
			//ȥ���ո�  
	        int month = Integer.parseInt(data [1].trim());  
	        int day = Integer.parseInt(data [2].trim());  
	        //��ȡһ������ʵ��  
	        Calendar c = Calendar.getInstance();
	        //�趨����������  
	        c.set(year, month-1, day,0,0,0);
	        result = c.getTime();  
		}
		return result;
	}
	//���ز���ʱ����hour��Сʱ��Date
	public static Date getFutureDate(Date date,int hour){
		long time = date.getTime();
		time+=hour*60*60*1000;
		Date result = new Date(time);
		return result;
	}
	//���ز���ʱ��ǰhourСʱʱ��
	public static Date getBeforeDate(Date date,int hour){
		long time = date.getTime();
		time-=hour*60*60*1000;
		Date result = new Date(time);
		return result;
	}
}
