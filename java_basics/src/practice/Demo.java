package practice;

public class Demo extends Saga {
	
	final String s;
	
	Demo(String s){
		this.s = s;
	}
//	
//
//	public Demo(int i) {
//		System.out.println("constructor 2");
//	}
//
//
//	public void m1() {
//		
//	}
//	
	void m2() {
		System.out.println();
		super.m2();
	}
//	
	public static void main(String[] args) {
		Demo s = (Demo) new Saga();
		
		s.m2();
		
		System.out.println(s);
		
	}
}

//interface InterDemo{
//
//	private void m4() {
//		
//	}
//	static void m3() {
//		System.out.println("");
//	}
//	default void m1() {
//		System.out.println("default interface method");
//	}
//}


 class Saga{

	{
		System.out.println("abstract non static block");
	}
	
	
	
	 void m2() {
		System.out.println("method in a class");
	}
}
