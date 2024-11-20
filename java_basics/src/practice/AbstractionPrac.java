package practice;

interface Demo{
	int count = 10;
	private int insideMethod(int count) {
		return count*10;
	}
	
	static String commonMethod() {
		
		return "Demo";
	}
	
	default String defaultMethod() {
	
		System.out.println(insideMethod(this.count));
		return "Default";
		
	}
}

abstract class Demo2{
	
	abstract void sample();
	
	abstract void sample2();
}

public final class AbstractionPrac extends Demo2{

	@Override
	void sample() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void sample2() {
		// TODO Auto-generated method stub
		
	}

}
