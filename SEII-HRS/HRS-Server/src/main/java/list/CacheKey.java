package list;

public class CacheKey {
	private long accessTime;
	private Object key;
	public CacheKey(Object key,int accessTimes){
		if (key==null) 
			throw new IllegalArgumentException("null key!");
		this.accessTime = accessTimes;
		this.key = key;
	}
	public CacheKey(Object key){
		this(key,0);
	}
	public long getAccessTime(){
		return this.accessTime;
	}
	public Object getKey(){
		return this.key;
	}
	public void setAccessTime(long accessTime){
		this.accessTime = accessTime;
	}
	public boolean equals(Object obj){
		if(!(obj instanceof CacheKey)){
			return false;
		}
		return this.key.equals(((CacheKey)obj).key);
	}
	public int hashCode(){
		return this.key.hashCode();
	}
}
