
package com.java8features.opertor;

import java.util.function.BiConsumer;

public class BiConsumerCode {
	public static void main(String[] args) {
       BiConsumer<Integer, String> consumer = (Integer a, String b) -> {
    	  b=  a.toString();
    	  b = b.concat(" suthan");
    	  System.out.println(b);
       };
       
       
       consumer.accept(1, null);
       
	}
}
