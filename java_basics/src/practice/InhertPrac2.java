package practice;

@FunctionalInterface
interface sam{

	 void sum();
	
	default void demo() {
		System.out.println("interface method");
	}
	
	static void demo2() {
		System.out.println("static in demo");
	}
}


class Dem{
	void m1() {
		System.out.println("Parent method");
	}
}

abstract class Em{
	Em(){
		System.out.println("parent constructor");
	}
}
public class InhertPrac2 implements sam{
//	public void demo() {
//		sam.super.demo();
//		System.out.println("overidden interface method");
//	}
	
	void m1() {
		
		System.out.println("Child method");
	}
	
	public static void main(String[] args) {
		InhertPrac2 ip2 = new InhertPrac2();
		ip2.demo();
//		sam s = new InhertPrac2();
		sam.demo2();
		
	}

	@Override
	public void sum() {
		
	}
}
