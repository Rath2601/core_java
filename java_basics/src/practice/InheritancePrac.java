package practice;

class A {
	static void m1() {
		System.out.println("A static method");
	}

	void m2() {
		System.out.println("A non static method");
	}
	
	void m3() {
		
	}
}

class B extends A {
	static void m1() {
		System.out.println("B static method");
	}

	void m2() {
		System.out.println("B non static method");
	}
	
	void m4() {
		
	}
}

public class InheritancePrac extends B {

	public static void main(String[] args) {
		InheritancePrac ip =(InheritancePrac) new A(); //class cast exception
		ip.m2();
		ip.m3();
	}
}
