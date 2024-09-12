package javaversionfeatures.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RandomNumberDemo {
	public static void main(String[] args) {
        RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create();
        
        int i=1; 
        
       while(i<=10) {
    	   System.out.println(randomGenerator.nextInt(2300000, 2400000));
    	   i++;
       }
    	  
	}
}
