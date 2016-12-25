package util;

/**
 * double 工具类，主要用来格式化可能出现的精度
 * @author Whk
 *
 */
public class DoubleUtil {
	public static double format(double x){
		return ((int)x*100)*1.0/100;
	}
}
