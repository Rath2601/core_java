package practice;

public class Syntax {
    int a = 0;
	static int b = 10;
	static final int c = 20;
	static transient final int d = 25; // all three exist in pair with its own purpose
	
	public void m1() {
		System.out.println("sample non static method");
	}
	public static void m2() {
		System.out.println("sample static method");
	}
	{
		System.out.println("sample non static block");
	}
	
	static {
		System.out.println("sample static block");
	}
	
	public static void main(String[] args) {
		Syntax s = new Syntax();
		s.m1();
		m2();
		System.out.println("test without public class");
	}
}

final class Main{ // can't be extended
	
}

abstract class Sample{
	
}

interface Test{
	
}