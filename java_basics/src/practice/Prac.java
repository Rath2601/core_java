package practice;

public class Prac extends Parent{
public static void main(String[] args) throws ClassNotFoundException {
	Parent p = new Prac();
	Prac p1 = (Prac) new Parent();
	
	Class s = Class.forName("Prac");
	
}
}

class Parent {
	Parent() {
		System.out.println("tests");
		m1();
	}

	private void m1() {
		
		System.out.println("method in parent");
	}

}