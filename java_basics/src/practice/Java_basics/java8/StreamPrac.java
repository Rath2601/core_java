package practice.Java_basics.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class StreamPrac {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");
		names.add("David");
		names.add("Eva");
		names.add("Aravind");
		names.add("Nishanth");
		names.add("Aravind");
		names.add("Siva");

		Set<String> s = names.stream().distinct().collect(Collectors.toSet());
		
		Consumer<String> c = name -> name.endsWith("e");
		
		List<String> initials = names.stream()
	            .flatMap(name -> Arrays.stream(new String[]{name.substring(0, 1)})) // Get the first letter as an initial
	            .distinct() // Optional: Remove duplicates if you only want unique initials
	            .collect(Collectors.toList());
		
//		List<String> sortedNames = 	names.stream().sorted().collect(Collectors.)
		
		
//		System.out.println(sortedNames);
		
//		System.out.println(s);

	} 
}
