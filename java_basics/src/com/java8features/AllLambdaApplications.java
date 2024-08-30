package com.java8features;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class AllLambdaApplications {

    public static void main(String[] args) {
        // 1. Runnable Example - Lambda for Multithreading
    	
        Runnable runnable = () -> System.out.println("Running in a separate thread");
        new Thread(runnable).start();

        // 2. Comparator Example - Lambda for Sorting
        
        List<String> names = Arrays.asList("John", "Jane", "Tom", "Jerry");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println("Sorted names: " + names);

        // 3. Predicate Example - Filtering a Collection
        
        Predicate<String> startsWithJ = s -> s.startsWith("J");
        List<String> filteredNames = names.stream()
                                          .filter(startsWithJ)
                                          .collect(Collectors.toList());
        System.out.println("Names starting with J: " + filteredNames);

        // 4. Function Example - Mapping a Collection
        
        Function<String, Integer> lengthMapper = s -> s.length();
        List<Integer> nameLengths = names.stream()
                                         .map(lengthMapper)
                                         .collect(Collectors.toList());
        System.out.println("Lengths of names: " + nameLengths);

        // 5. Consumer Example - Iterating a Collection
        
        Consumer<String> printer = s -> System.out.println(s);
        names.forEach(printer);

        // 6. Supplier Example - Lazy Initialization
        
        Supplier<String> supplier = () -> "Hello, Supplier!";
        System.out.println(supplier.get());

        // 7. BinaryOperator Example - Reducing a Collection
        
        BinaryOperator<Integer> sumOperator = (a, b) -> a + b;
        Optional<Integer> sum = nameLengths.stream()
                                           .reduce(sumOperator);
        sum.ifPresent(total -> System.out.println("Sum of name lengths: " + total));

        // 8. UnaryOperator Example - Modifying a Collection
        
        UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
        List<String> uppercasedNames = names.stream()
                                            .map(toUpperCase)
                                            .collect(Collectors.toList());
        System.out.println("Uppercased names: " + uppercasedNames);

        // 9. BiConsumer Example - Key-Value Processing
        
        Map<String, Integer> nameLengthMap = new HashMap<>();
        names.forEach(name -> nameLengthMap.put(name, name.length()));
        BiConsumer<String, Integer> biConsumer = (name, length) -> 
            System.out.println(name + " has length " + length);
        nameLengthMap.forEach(biConsumer);

        // 10. Custom Functional Interface Example
        
        FInterface customFunction = (user, nickName) -> user + " aka " + nickName;
        System.out.println(customFunction.show("John", "Johnny"));
    }

}
