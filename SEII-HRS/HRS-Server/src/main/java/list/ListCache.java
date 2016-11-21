package list;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListCache<T> {
	//�Ƚ��г�ʼ������ֹ���ֿ�ָ�������߳���׳��
	private Set<T> datas = new HashSet<T>();
	private final int MAX_SIZE = 20;
	//cache���滻����
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
