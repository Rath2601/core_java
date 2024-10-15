package imp_keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreamUtilClass {
	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("India");
		names.add("Indonesia");
		names.add("Europe");
		names.add("China");

		List<String> filteredNames = names.stream().filter(i -> i.startsWith("I")).toList();
		
		System.out.println(filteredNames);
	}
}
