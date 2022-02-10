package ga.mmbh.cfgs.modules;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

import ga.mmbh.cfgs.exceptions.ColaExceededSizeException;
import ga.mmbh.cfgs.exceptions.ElementBlockedException;
import ga.mmbh.cfgs.exceptions.LlevateTuNullDeAquiException;

public class ColaDelCastigo<T> implements Comparator<T>{

	private final TreeSet<T> list;
	private final Comparator<T> comparator;
	
	public ColaDelCastigo(Comparator<T> comparator) {
		this.comparator = comparator;
		this.list = new TreeSet<T>(comparator);
	}
	
	public ColaDelCastigo() {
		this.list = new TreeSet<T>();
		this.comparator = (Comparator<T>) list.comparator();
	}


	public int size() {
		return list.size();
	}


	public boolean isEmpty() {
		return list.isEmpty();
	}


	public boolean contains(Object o) {
		return list.contains(o);
	}


	public Iterator<T> iterator() {
		return list.iterator();
	}


	public Object[] toArray() {
		return list.toArray();
	}


	public T[] toArray(T[] a) {
		return list.toArray(a);
	}


	public boolean remove(Object o) throws ElementBlockedException {
		if (list.size() == 1) throw new ElementBlockedException();
		return list.remove(o);
	}


	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}


	public boolean addAll(Collection<? extends T> c) throws Exception {
		if (list.size() + c.size() > 10) throw new ElementBlockedException();
		return list.addAll(c);
	}


	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}


	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}


	public boolean add(T e) throws LlevateTuNullDeAquiException, ColaExceededSizeException {
		if (list.size() >= 10) throw new ColaExceededSizeException();
		if (e == null) throw new LlevateTuNullDeAquiException();
		return list.add(e);
	}


	public T pollFirst() {
		return list.pollFirst();
	}
	
	public T pollLast() {
		return list.pollLast();
	}

	public boolean offer(T e) {
		return list.add(e);
	}

	public T poll() {
		T element = list.last();
		list.remove(element);
		return element;
	}

	public T peek() {
		return null;
	}

	@Override
	public int compare(T o1, T o2) {
		return list.comparator().compare(o1, o2);
	}
}
