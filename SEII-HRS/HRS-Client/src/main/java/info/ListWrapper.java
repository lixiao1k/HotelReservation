package info;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListWrapper<T> {
	private Set<T> data = new HashSet<T>();
	public ListWrapper(Iterator<T> it){
		while(it.hasNext()){
			data.add((T) it.next());
		}
	}
	public ListWrapper(Collection<T> collection){
		this(collection.iterator());
	}
	public Iterator<T> iterator(){
		return data.iterator();
	}
	public boolean contains(T obj){
		Iterator<T> it = data.iterator();
		while(it.hasNext()){
			if (obj.equals(it.next()))
				return true;
		}
		return false;
	}
}
