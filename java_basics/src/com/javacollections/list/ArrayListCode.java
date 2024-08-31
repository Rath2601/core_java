package com.javacollections.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListCode {
	public static void main(String[] args) {
          List<Object> al = new ArrayList<>(100);
           
           al.add(1);
           al.add("test");
           al.add(true);
           al.add(23.45f);
           al.add(1);
           al.add(null);
           al.add(true);
           al.add(null);
           
           
          Iterator i = al.iterator();
          
          while(i.hasNext()) {
        		
        	  if(i.next()==null) {
//        		  al.add("fresh");
        	  }
          }

          al.add("fresh");
           System.out.println(al);
	}
}
