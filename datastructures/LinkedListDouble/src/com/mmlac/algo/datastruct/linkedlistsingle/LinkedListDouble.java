package com.mmlac.algo.datastruct.linkedlistsingle;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Basic Singly Linked List that only knows the next element
 * 
 * @author Markus Lachinger - business@mmlac.com - mmlac.com
 *
 * @param <E> What type of Elements can be stored in the list
 */
public class LinkedListDouble<E> implements Iterator<E> 
{
	
	public LinkedListDouble() {
		this.head 	= null;
		this.size	= 0;	
	}
	
	public LinkedListDouble(E element) {
		this.head = null;
		this.size = 0;
		this.add(element);
	}
	
	/**
	 * This is a single entry of the linked list
	 * 
	 * @param <E>
	 */
	public static class Entry<E> {
		public E element;
		public Entry<E> next;
		public Entry<E> prev;
		
		public Entry(E e, Entry<E> p, Entry<E> n) {
			this.element = e;
			this.prev = p;
			this.next = n;
		}
		
	}
	
	
	//Variables
	
	private Entry<E> head = null;
	private int size = 0;


	public boolean add(E e) {
		
		if (this.size == 0) {
			this.head = new Entry<E>(e, null, null);
		} else {
			Entry<E> last = this.getLast();
			last.next = new Entry<E>(e, last, this.head);
			this.head.prev = last.next;
		}
		
		this.size++;
		return true;
	}




	public void clear() {
		this.head = null;
		this.size = 0;
		
	}


	public boolean contains(Object o) {
		if (size == 0) return false;
		
		return indexOf(o) != -1;
		
	}
	
	public int indexOf(Object a) {
		int 		pos = -1;
		
		if (a != null) {
			Entry<E> 	e 	= this.head;
			int 		i 	=  0;
			
			while (i < this.size && pos == -1) {
				if(e.element == a) pos = i;
				i++;
				e = e.next;
			}
		}
		
		return pos;
	}


	
	public boolean isEmpty() {
		return this.size == 0;
	}


	
	public boolean remove(Object o) {
		if (this.size == 0) return false;
		int io = this.indexOf(o);
		if (io != -1) {
			if (io == 0) {
				if (size == 1) {
					this.head = null;
				} else {
					this.head.prev.next = this.head.next;
					this.head.next.prev = this.head.prev;
					this.head = this.head.next;
				}
			} else {
				Entry<E> prev = this.get(io-1);
				Entry<E> next = prev.next.next;
				prev.next = next;
				next.prev = prev;
			}
			this.size--;
			return true;
		}
		return false;
	}
	
	public Entry<E> get(int i) {
		if (i < 0 || i > this.size-1) throw new IndexOutOfBoundsException();
		if (i == 0) return this.head;
		Entry<E> e = this.head;
		for (int cur = 0; cur < this.size-1; cur++) {
			if (cur == i) break;
			e = e.next;
		}	
		return e;
	}



	
	public int size() {
		return this.size;
	}



	public Entry<E> getLast() throws NoSuchElementException {
		if (this.size == 0) throw new NoSuchElementException();
		if (size == 1) return this.head;
		return this.head.prev;		
	}

	
	
	/**
	 * Iterator implementation
	 */
	Entry<E> currentIterator;
	
	public Iterator<E> iterator() {
		this.currentIterator = this.head;
		return this;
	}	


	@Override
	public boolean hasNext() {
		return this.currentIterator != null;
	}



	@Override
	public E next() {
		E result = this.currentIterator.element;
		if (this.currentIterator.next == this.head) { 
			this.currentIterator = null;
		} else {
			this.currentIterator = this.currentIterator.next;
		}
		return result;
	}




	@Override
	public void remove() {
		this.remove(this.currentIterator.element);
		
	}
	
	
}
