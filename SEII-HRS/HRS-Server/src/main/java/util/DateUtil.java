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
	        c.set(year, month-1, day);
	        result = c.getTime();  
		}
		return result;
	}
}
