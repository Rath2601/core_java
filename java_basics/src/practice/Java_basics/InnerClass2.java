package practice.Java_basics;

public class InnerClass2 {
	
	public InnerClass2() {
		this("sample");
	}
	public InnerClass2(String string) {
		// TODO Auto-generated constructor stub
	}
	void m2() {
		
	}
	static class InnerClass{
		void m3() {
			
		}
		void m4() {
			
		}
	}
	public static void main(String[] args) {
//		InnerClass2 o = new InnerClass2();
//		InnerClass2.InnerClass ic =o.new InnerClass();
		
		InnerClass2.InnerClass ic =new InnerClass2.InnerClass();
	}
	
}
