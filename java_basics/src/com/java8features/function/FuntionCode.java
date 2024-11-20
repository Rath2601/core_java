package com.java8features.function;

import java.util.function.Function;

public class FuntionCode {

	static void functionDemo() {
		Function<String, String> f1 = s -> "function applied " + s;

		Function<String, String> f2 = s -> "second function applied " + s;

		StringBuffer s = new StringBuffer();
		Function<Integer, StringBuffer> f3 = i -> {
			for (int j = 0; j < i; j++) {
				char a = (char) (j + 65);
				s.append(a);
			}
			return s;
		};

		Function mergedFunction = f1.andThen(f2);
		
		Function composedFunction =f1.compose(f2);
		
		Function constant = Function.identity();
		
		System.out.println(constant.apply("sample"));
		
		System.out.println(mergedFunction.apply("test"));
		
		System.out.println(composedFunction.apply("test"));

//		System.out.println(f3.apply(5));

//		System.out.println(f1.apply("String1"));
	}

	public static void main(String[] args) {
		functionDemo();
	}
}
