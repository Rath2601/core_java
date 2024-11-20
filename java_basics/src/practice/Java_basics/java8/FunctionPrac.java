package practice.Java_basics.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionPrac {
public static void main(String[] args) {
	List<String> names = Arrays.asList("Naveen","Sudhan","Sedhu","Rathna","Nishanth");
	
	
	Predicate<String> p = s -> s.length() > 5;
	
	Predicate<String> p1 = s -> s.startsWith("R");
	
	Predicate<String> p2 = p1.negate();
	
	List<String> filteredNames = names.stream().filter(p.and(p2)).toList();
	
	System.out.println(filteredNames);
}
}
