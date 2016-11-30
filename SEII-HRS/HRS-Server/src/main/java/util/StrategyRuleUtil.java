package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import info.ListWrapper;


public class StrategyRuleUtil {
	private Map<String, String> types = new HashMap<String,String>();
	private static StrategyRuleUtil instance;
	static{
		try{
			instance = new StrategyRuleUtil();
			instance.initial();
		}catch(ExceptionInInitializerError e){
			e.printStackTrace();
		}
	}
	public static StrategyRuleUtil getInstance(){
		return instance;
	}
	private void initial(){
		File file = new File("StrategyMapping.properties");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line=reader.readLine())!=null){
				String[] data = line.split("=");
				types.put(data[0], data[1]);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getClassName(String key){
		if (key==null)
			throw new IllegalArgumentException("wrong argument");
		return instance.types.get(key);
	}
	public static ListWrapper<String> getTypes(){
		Set<String> result = instance.types.keySet();
		return new ListWrapper<>(result);
	}
}
