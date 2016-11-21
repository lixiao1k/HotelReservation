package list;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListCache<T> {
	//先进行初始化，防止出现空指针错误，提高程序健壮性
	private Set<T> datas = new HashSet<T>();
	private final int MAX_SIZE = 20;
	//cache的替换策略
	private CacheStrategy<T> strategy = new FIFOCacheStrategy();
	public ListCache(){}
	public ListCache(Set<T> datas,CacheStrategy<T> strategy){
		this.datas = datas;
	}
	public ListCache(Set<T> datas){
		this.datas = datas;
	}
	public Iterator<T> getData(){
		return this.datas.iterator();
	}
	public void setDatas(Set<T> datas){
		this.datas = datas;
	}
	public void store(T data){
		if (datas.size()<MAX_SIZE){
			datas.add(data);
		}else{
			strategy.replace(datas, data);
		}
	}
}
