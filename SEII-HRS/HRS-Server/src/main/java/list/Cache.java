package list;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Cache<T> {
	private Map<Object,CacheEntry<T>> map = new HashMap<Object,CacheEntry<T>>();
	private TreeSet<CacheKey> accessQueue = new TreeSet<CacheKey>(new AccessTimeComparator());
	private int size = 20;
	public Cache(int size){
		if (size<0)
			throw new IllegalArgumentException("size less than 0!");
		this.size = size;
	}
	public Iterator<Object> getKeys(){
		Set<Object> keys;
		synchronized (map) {
			keys = map.keySet();
		}
		return keys.iterator();
	}
	public T get(Object key){
		if (key==null)
			throw new IllegalArgumentException("key is null");
		CacheEntry<T> entry = map.get(key);
		if (entry==null)
			return null;
		CacheKey cacheKey = entry.getKey();
		accessQueue.remove(cacheKey);
		cacheKey.setAccessTime(System.currentTimeMillis());
		accessQueue.add(cacheKey);
		return entry.getEntry();
	}
	private void removeKey(CacheKey key){
		accessQueue.remove(key);
		map.remove(key.getKey());
	}
	public void put(Object key,T data){
		if (key==null)
			throw new IllegalArgumentException("key is null!");
		if (size==0)
			return;
		CacheEntry<T> entry = map.get(key);
		CacheKey cacheKey = null;
		synchronized (this.map) {
			if (entry==null){
				cacheKey = new CacheKey(key);
				if(map.size()>=size){
					CacheKey next = accessQueue.first();
					if (!key.equals(next.getKey())){
						this.removeKey(next);
					}
				}
			}else{
				cacheKey = entry.getKey();
				this.accessQueue.remove(cacheKey);
				cacheKey.setAccessTime(System.currentTimeMillis());
			}
			this.accessQueue.add(cacheKey);
			this.map.put(key, new CacheEntry<T>(data,cacheKey));
		}
	}
	/*
	 * 弹出并删除相应的键
	 */
	public T remove(Object key){
		if(key==null)
			throw new IllegalArgumentException("key is null!");
		synchronized (map) {
			CacheEntry<T> entry = map.get(key);
			if (entry==null)
				return null;
			this.removeKey(entry.getKey());
			return entry.getEntry();
		}
	}
	public boolean contains(Object key){
		if (key==null)
			throw new IllegalArgumentException("key is null");
		return map.containsKey(key);
	}
	private class AccessTimeComparator implements Comparator<CacheKey> {

		@Override
		public int compare(CacheKey o1, CacheKey o2) {
			if (o1.getAccessTime()>o2.getAccessTime()){
				return 1;
			}else if (o1.getAccessTime()==o2.getAccessTime())
				return 0;
			else return -1;
		}

		
		
	}
}
