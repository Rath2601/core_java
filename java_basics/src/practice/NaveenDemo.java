package practice;

public class NaveenDemo {
	
	
	Inter i; // InterA .InterB, InterC
	

	public String name;

	public NaveenDemo(Inter i, String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		Inter s = new Sample();
		s.m1();
	}
}

interface Inter {
	void m1();
}

class Sample implements Inter {

	@Override
	public void m1() {
		System.out.println("overridden method");
	}

}