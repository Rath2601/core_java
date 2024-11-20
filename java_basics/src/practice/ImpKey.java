package practice;

public class ImpKey {
	static final double constant = 3.14d;
	
	int a;
	
	ImpKey() {
//		System.out.println("constructor without param");
	}

	ImpKey(String s) {
		System.out.println("constructor with param");
	}

	static void m1() {
		System.out.println("parent static method");
	}

	void m2() {
		System.out.println("parent non static method");
	}

	public static void main(String[] args) {

	}
}
