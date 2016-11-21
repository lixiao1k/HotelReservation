package list;

public class CacheKey {
	private int accessTimes;
	private Object key;
	public CacheKey(Object key,int accessTimes){
		if (key==null) 
			throw new IllegalArgumentException("null key!");
		this.accessTimes = accessTimes;
		this.key = key;
	}
	public CacheKey(Object key){
		this(key,0);
	}
	public int getAccessTimes(){
		return this.accessTimes;
	}
	public Object getKey(){
		return this.key;
	}
	public void setAccessTimes(int accessTimes){
		this.accessTimes = accessTimes;
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
