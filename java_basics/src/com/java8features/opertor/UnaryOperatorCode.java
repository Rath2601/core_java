package com.java8features.opertor;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UnaryOperatorCode {
	public static void main(String[] args) {
        UnaryOperator<String> trial = i -> i.toUpperCase();
        
        List<String> names = Arrays.asList("suthan","rathna");
       names =  names.stream().map(trial).collect(Collectors.toList());
        
        System.out.println(names);
	}
}
