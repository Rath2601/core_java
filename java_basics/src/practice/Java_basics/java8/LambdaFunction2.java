package practice.Java_basics.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaFunction2 {
	public static void main(String[] args) {
		// List of sample data
		List<String> names = new ArrayList<>();
		names.add("Alice");
		names.add("Bob");
		names.add("Charlie");
		names.add("David");
		names.add("Eva");

		// Consumer: Print each name in uppercase
		Consumer<String> printUpperCase = name -> System.out.println(name.toUpperCase());
		System.out.println("Consumer (Uppercase names):");
		names.forEach(printUpperCase);

		// Predicate: Check if a name starts with the letter 'A'
		Predicate<String> startsWithA = name -> name.startsWith("A");
		System.out.println("\nPredicate (Names starting with 'A'):");
		names.stream().filter(startsWithA).forEach(System.out::println);

		// Function: Convert each name to its length
		Function<String, Integer> getLength = name -> name.length();
		System.out.println("\nFunction (Name length):");
		names.stream().map(getLength).forEach(length -> System.out.println("Length: " + length));

		// Supplier: Generate a default name when no names exist in the list
		Supplier<String> defaultNameSupplier = () -> "Default Name";
		System.out.println("\nSupplier (Default name):");
		if (names.isEmpty()) {
			System.out.println(defaultNameSupplier.get());
		} else {
			System.out.println("Names list is not empty.");
		}
	}
}
