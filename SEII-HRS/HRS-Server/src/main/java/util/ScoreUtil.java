package util;

import java.util.Set;

import po.CommentPO;
import po.HotelPO;

public class ScoreUtil {
	public static double[] rankScore = {31.47,39.67,56.06,74.09,88.85};
	public static double calculate(HotelPO po){
		double result = 0.0;
		switch(po.getRank()){
			case ONE:
				result += rankScore[0];
				break;
			case TWO:
				result += rankScore[1];
				break;
			case THREE:
				result += rankScore[2];
				break;
			case FOUR:
				result += rankScore[3];
				break;
			case FIVE:
				result += rankScore[4];
				break;
		}
		Set<CommentPO> comments = po.getComments();
		if(comments==null) 
			return result;
		double temp = 0.0;
		int count = 0;
		for (CommentPO com:comments){
			temp+=com.getGrade()*2.25;
			count++;
		}
		result += temp/(1.0*count);
		return result;
	}
}
