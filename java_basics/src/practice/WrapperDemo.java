package practice;

public class WrapperDemo {
	public static void main(String[] args) {

		int i = 10; 
		
		Integer i1 = i; //P-O
		
		String s = String.valueOf(i1); //O-S
		
		Integer i2 = Integer.valueOf(s); //S-O
		
		int i3 = i2.intValue(); //O-P
		
		String s2 = String.valueOf(i3); //P-S
		
		int i4 = Integer.parseInt(s2); //S-P
		 
		System.out.println(i2);
		System.out.println(i3);
		System.out.println(i4);
		
		
		
	}
}
