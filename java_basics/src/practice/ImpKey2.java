package practice;

public class ImpKey2 extends ImpKey {
	int a ;
	public ImpKey2() {
//		super("star");
		
	}
	

	public void m3(ImpKey2 obj) {
		System.out.println("method to use super and this");

		System.out.println(obj.a);
//		System.out.println(super.a);
		
//		super.m2();
//		ImpKey.m1();
		
		m4(this.a, this.a);
	}
	
	void m2() {
		System.out.println("m2 method in child");
	}
	
	void m4(int a, int b)
	{
		super.m2();
		System.out.println((a+b)+""+this);
	}
	@Override
	public String toString() {
		return "ImpKey2 []";
	}

	public static void main(String[] args) {
		ImpKey2 i2 = new ImpKey2();
		i2.a = 10;
		i2.m3(i2);
		
		ImpKey2 i3 = new ImpKey2();
		i3.a = 15;
		i3.m3(i3);
	}
}
