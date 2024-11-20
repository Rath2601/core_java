package imp_keywords;

public class StaticKeyword {
	
	 class NestedClass {
		static void m1() {
			System.out.println("nested class static method");
		}

		void m2() {
			System.out.println("nested class non static method");
		}
	}
	
	static class StaticNestedClass {
		static void m1() {
			System.out.println("nested class static method");
		}

		void m2() {
			System.out.println("nested class non static method");
		}
	}
	
	
	public static void main(String[] args) {
		NestedClass nc = new StaticKeyword().new NestedClass();
		nc.m2();
		NestedClass.m1();
		StaticNestedClass snc = new StaticNestedClass();
		snc.m2();
		StaticNestedClass.m1();
	}
}


//static void m1() {
//	System.out.println("This is static method");
//}
//
//static StaticKeyword sk = new StaticKeyword();
//
//public static void main(String[] args) {
////NestedClass nc =sk.new NestedClass();
//NestedClass.demo();
//}
//
//void m1(String one) {
//
//}
//
//class NestedClass extends StaticKeyword {
//
//	static void demo() {
//		System.out.println("non-static demo method inside nested class");
//	}
//}