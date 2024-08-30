package com.java8features.consumerAndsupplier;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ConsumerCode {
	static Consumer<String> c2 =  x -> {
		if (!x.isEmpty()) {
			System.out.println("valid email "+x);
		}else {
			System.out.println("not valid "+x);
		}
	};

	static Consumer<String> c1 = x -> {
		if (x.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$")) {
			System.out.println("valid email :"+x);
		}else {
			System.out.println("not valid :"+x);
		}
	};
	
	
	static Supplier<Map<Integer, String>> s1 = () -> Map.of(2349838, "Rathnasabapathy");
	
	
	

	public static void main(String[] args) {
		c1.accept("star@gmail.com");
		c1.accept("sample");
		Consumer c3 = c1.andThen(c2);
		c3.accept("test");
		
		System.out.println(s1.get());

	}
}
