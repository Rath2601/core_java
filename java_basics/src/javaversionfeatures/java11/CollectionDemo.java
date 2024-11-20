package javaversionfeatures.java11;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CollectionDemo {
	public static void main(String[] args) {
		List<String> nameList = Arrays.asList("suthan", "naveen", "sedhu");

        Object[] names = nameList.toArray(String[] :: new);	
        
        Object[] names2 = nameList.toArray(new String[nameList.size()]);	
        
        for(Object o : names2) {
        	System.out.println(o);
        }
	}
}
