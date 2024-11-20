package com.javacollections.list;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListCode {
public static void main(String[] args) {
	LinkedList ll = new LinkedList<>();
	
	 ll.add(1);
     ll.add("test");
     ll.add(true);
     ll.add(23.45f);
     ll.add(1);
     ll.add(null);
     ll.add(true);
     ll.add(null);
     
     Iterator i = ll.iterator();
     
     while(i.hasNext()) {
   		
   	  if(i.next()==null) {
   		  ll.add("test1");
   	  }
     }
     
     System.out.println(ll);
}
}
