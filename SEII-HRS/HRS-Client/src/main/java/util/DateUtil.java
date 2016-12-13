package util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
	public static boolean compare(Date date1,Date date2){ 
	    Calendar aCalendar = Calendar.getInstance();
	    aCalendar.setTime(date1);
	    int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
	    aCalendar.setTime(date2);
	    int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
		int d = day2 - day1;
		return (d==0)? true:false;
	}
	public static String format(Date date){
		return df.format(date);
	}
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
