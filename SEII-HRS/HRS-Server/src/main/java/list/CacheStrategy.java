package list;

import java.util.Set;

public interface CacheStrategy<T> {
	public void replace(Set<T> datas,T data);
}
