package datacontroller;

import java.util.HashMap;
import java.util.Map;

public class DataController {
	private Map<String, Object> map = new HashMap<>();
	private static DataController instance;
	public static DataController getInstance(){
		if(instance==null)
			instance = new DataController();
		return instance;
	}
	public void put(String name,Object o){
		map.put(name, o);
	}
	public Object get(String name){
		return map.get(name);
	}
}
