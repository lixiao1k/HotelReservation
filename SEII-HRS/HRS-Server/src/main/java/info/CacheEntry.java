package info;

public class CacheEntry<T> {
	private final T entry;
	private final CacheKey key;
	public CacheEntry(T entry,CacheKey key){
		this.entry = entry;
		this.key = key;
	}
	public CacheKey getKey(){
		return this.key;
	}
	public T getEntry(){
		return this.entry;
	}
}
