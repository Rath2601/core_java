package imp_keywords;

public class ThisKeyword {
	
	private int a  ;
	private static int b ;
	
	long c = 15;
	float d = c;
	
	ThisKeyword(){
		this(10, 20);
	}
	
	void show() {
		System.out.println("The sum is "+(a+b));
	}
	
	public ThisKeyword(int a, int b) {
		this.a = a;
		this.b = b;  // to refer instance variable
	}
	
	
	public static void main(String[] args) {
          ThisKeyword tk = new ThisKeyword();
          tk.show();
          System.out.println(tk.d);
	}
	
}
