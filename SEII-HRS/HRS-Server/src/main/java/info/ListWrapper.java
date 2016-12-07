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
	public ListWrapper(){
		this.data  =new HashSet<>();
	}
	public void add(T element){
		data.add(element);
	}
	public void addAll(ListWrapper<T> list){
		Iterator<T> it = list.iterator();
		while(it.hasNext()){
			T element = it.next();
			data.add(element);
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
	public int size(){
		return data.size();
	}
}
