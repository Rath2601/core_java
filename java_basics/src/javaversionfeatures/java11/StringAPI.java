package javaversionfeatures.java11;

import java.util.List;

public class StringAPI {
public static void main(String[] args) {
	String s = "  suthan   test \n I am super";
//	System.out.println(s.isBlank());
//	
//	System.out.println(s.repeat(2));
//	
//	System.out.println(s.strip());
	
	 s.lines().forEach(e -> System.out.println(e));
	
}
}
