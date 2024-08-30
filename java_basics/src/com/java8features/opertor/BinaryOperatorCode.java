package com.java8features.opertor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class BinaryOperatorCode {

	static List<String> names = new ArrayList<>();

	public static void demo() {
		names.add("suthan");
		names.add("rathna");
		names.add("sedhu");
		names.add("naveen");
		
		String nameTotal = names.stream().reduce(
				(x,y) ->
				{System.out.println(x+y);
				return x+y;
				}).get();
		
		System.out.println(nameTotal);
		BinaryOperator<String> bo = (x,y) -> {
			System.out.println(x+y);
			return x+y;
			};
			
		bo.apply("sample", " test");
	}

	public static void main(String[] args) {
		demo();
	}
}
