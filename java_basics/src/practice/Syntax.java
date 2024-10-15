package practice;

public abstract class Syntax {

	int a = 10;

	 static int b = 20;

	abstract void m1();

	void m2() {
		System.out.println("method m2");
	}

	public static void main(String[] args) {
       Main m = new Main();
       m.speed();
	}
}

class Main extends Syntax implements Dummy {

	@Override
	public void speed() {
		System.out.println("overridden method from abstract class");
		Syntax s = new Main();
		s.m2();
	}

	@Override
	void m1() {
		Dummy d = new Main();
		d.m5();
	}
}

abstract interface Dummy {
	void speed();

	static void m4() {
		System.out.println("static inside interface");
	}

	default void m5() {
		System.out.println("default inside interface");
	}
}
