package javaversionfeatures.java17;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RandomNoDemo {
	public static void main(String[] args) {
		RandomGenerator randomGenerator = RandomGeneratorFactory.getDefault().create(); //getting random alogorithm use getDefault() method
		System.out.println(randomGenerator.getClass());
		
		RandomGeneratorFactory.all().map(name -> name.group()+":"+name.name()).sorted()
		.forEach(System.out::println); // here we can get all inbuild algorithm for random no generation
		
		// here we can use one build algorithm
		RandomGenerator randomGenerator1 =RandomGeneratorFactory.of("Xoshiro256PlusPlus").create();
		
		int i =0;
		while(i<=10) {
			int num=randomGenerator1.nextInt(80); 
			System.out.println(num);
			i++;
			
		}
	}

}
