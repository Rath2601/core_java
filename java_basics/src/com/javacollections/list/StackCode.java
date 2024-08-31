package com.javacollections.list;

import java.util.Stack;

public class StackCode {
public static void main(String[] args) {
	Stack s = new Stack<>();
	
	s.add(1);
	s.add("sample");
	s.add(true);
	
	s.addFirst(3);
	
	System.out.println(s);
	
	System.out.println(s.getClass());
}
}
