package imp_keywords;

public class SuperKeyword extends Demo {
	
	SuperKeyword(){
		super();
	}
	
	void childMethod() {
		
		Demo.demo1(super.name,super.status);
		
	}
	
	public static void main(String[] args) {
		SuperKeyword sk = new SuperKeyword();
		sk.childMethod();
	}
	
}

class Demo {
	
	 String name = "RATH";
	
	 String status = " SUCCESS";
	
	Demo() {
		System.out.println(name);
	}

	static void demo1(String s1, String s2) {
		System.out.println(s1 + s2);
	}
}