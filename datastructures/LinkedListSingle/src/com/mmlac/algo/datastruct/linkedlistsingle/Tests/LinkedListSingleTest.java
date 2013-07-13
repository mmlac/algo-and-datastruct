package com.mmlac.algo.datastruct.linkedlistsingle.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import com.mmlac.algo.datastruct.linkedlistsingle.LinkedListSingle;

public class LinkedListSingleTest {
	
	@Test
	public void testInit() {
		LinkedListSingle<String> ll = new LinkedListSingle<String>();
		assertNotNull("LinkedList is null after initialization!", ll);
		
		LinkedListSingle<String> l2 = new LinkedListSingle<String>("FIRST");
		assertEquals("Length should be one after initializing with an element", 
					 l2.size(), 1);
		assertTrue("Element should be in LinkedList after initializing with it",
					l2.contains("FIRST"));	
	}
	
	@Test
	public void testAdd() {
		LinkedListSingle<String> ll = new LinkedListSingle<String>();
		assertEquals("List size is 0 after empty initialization", ll.size(), 0);
		assertTrue("List is empty after initialization", ll.isEmpty());
		
		ll.add("BAR");
		ll.add("FOO");
		assertEquals("After adding two elements the size is 2", ll.size(), 2);
		assertFalse("List is no longer empty after inserting to elements", ll.isEmpty());
		
		//Could be improved to return the element and use an internal method to get the 
		//Entry object for the other classes. #lazy
		assertEquals("First Entry added is Element 0 on get", ll.get(0).element, "BAR");
		assertEquals("GetLast returns last element", ll.getLast().element, "FOO");
	}
	
	@Test
	public void testRemove() {
		LinkedListSingle<String> ll = new LinkedListSingle<String>();
		assertFalse("removeing an object from an empty list should return false",
				ll.remove("FOOBAR"));
		ll.add("FOO");
		assertEquals(ll.size(), 1);
		assertFalse("removing a non-existing element should return false",
				ll.remove("FOOBAR"));
		
		assertEquals("removing a non-existing element should leave the element inside",
				ll.size(), 1);
		
		ll.add("Barrr");
		ll.add("TEST");
		ll.add("BAzzz");
		assertTrue("added Element is in LinkedList", ll.contains("TEST"));
		ll.remove("TEST");
		assertFalse("correct Element is removed", ll.contains("TEST"));
				
	}
	
	@Test
	public void testIterator() {
		LinkedListSingle<String> ll = new LinkedListSingle<String>();
		ll.add("FOO");
		ll.add("BAR");
		ll.add("BAZ");
		ll.add("FOOBAR");
		ll.add("FOOBAZ");
		
		String expected = "FOO BAR BAZ FOOBAR FOOBAZ ";
		String result = "";
		Iterator<String> it = ll.iterator();
		while(it.hasNext()) {
			result += it.next();
			result += " ";
		}
		
		assertEquals("Using an iterator should return the elements in the right order",
				expected, result);
	}

}
