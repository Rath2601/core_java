package practice.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPrac {
	public static void main(String[] args) {
		List list = new LinkedList();
		list.add(new Integer(1)); 
//		Integer i =  list.iterator().next(); // Can't convert object to Integer without casting
		
		ArrayList al = new ArrayList<>();
		
		Integer i =  (Integer) list.iterator().next();
		// It can cause type-related runtime errors if a programmer makes a mistake with the explicit casting.
	}
}
