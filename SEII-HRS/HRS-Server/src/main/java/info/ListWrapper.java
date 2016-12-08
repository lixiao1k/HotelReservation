package info;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ListWrapper<T> extends UnicastRemoteObject{
	private Set<T> data = new HashSet<T>();
	public ListWrapper(Iterator<T> it)throws RemoteException{
		while(it.hasNext()){
			data.add((T) it.next());
		}
	}
	public ListWrapper()throws RemoteException{
		this.data  =new HashSet<>();
	}
	public void add(T element)throws RemoteException{
		data.add(element);
	}
	public void addAll(ListWrapper<T> list)throws RemoteException{
		Iterator<T> it = list.iterator();
		while(it.hasNext()){
			T element = it.next();
			data.add(element);
		}
	}
	public ListWrapper(Collection<T> collection)throws RemoteException{
		this(collection.iterator());
	}
	public Iterator<T> iterator()throws RemoteException{
		return data.iterator();
	}
	public boolean contains(T obj)throws RemoteException{
		Iterator<T> it = data.iterator();
		while(it.hasNext()){
			if (obj.equals(it.next()))
				return true;
		}
		return false;
	}
	public int size()throws RemoteException{
		return data.size();
	}
}
