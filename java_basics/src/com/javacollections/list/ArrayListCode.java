package com.javacollections.list;

import java.util.ArrayList;

public class ArrayListCode {
	public static void main(String[] args) {
           ArrayList al = new ArrayList<>();
           
           al.add(1);
           al.add("test");
           al.add(true);
           al.add(23.45f);
           al.add(1);
           al.add(null);
           al.add(true);
           al.add(null);

           System.out.println(al);
	}
}
