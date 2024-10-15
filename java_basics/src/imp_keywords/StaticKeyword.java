package imp_keywords;

public class StaticKeyword {

	static void m1() {
		System.out.println("This is static method");
	}

	static StaticKeyword sk = new StaticKeyword();
	
	public static void main(String[] args) {
		
	}

	 void m1(String one) {
		
	}
}

class NestedClass extends StaticKeyword {

	static void demo() {
		System.out.println("non-static demo method inside nested class");
	}
}
