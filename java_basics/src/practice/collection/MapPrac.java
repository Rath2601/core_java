package practice.collection;

import java.util.HashMap;

public class MapPrac {
public static void main(String[] args) {
	HashMap<String, Integer> map = new HashMap<>(10, .9f);
	
	map.put("Maths", 100);
	map.put("Science", 100);
	map.put("social science", 100);
	map.put("tamil", 89);
	map.put("english", 96);
	
	int total = 0;
	
	for(Integer i : map.values()) {
		total = total + i;
	}
	
	System.out.println(total);
}
}
