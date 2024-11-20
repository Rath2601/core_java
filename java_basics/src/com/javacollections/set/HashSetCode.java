package com.javacollections.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class HashSetCode{
	public static void main(String[] args) {
		HashSet<String> s = new HashSet<>();
	
		s.add("sedhu");
		s.add("sathish");
		s.add("suriya");
		s.add("suthan");
		s.add("naveen");
		s.add("kicha");
		List<String> test = s.stream().takeWhile( e -> (e.charAt(0)=='s')).toList();
		
		List<String> test1 = s.stream().dropWhile( e -> (e.charAt(0)=='s')).toList();
		
		Iterator<String> i = s.stream().iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		
		System.out.println(s);
		System.out.println(test);
		System.out.println(test1);
		
		

	}
	

}


