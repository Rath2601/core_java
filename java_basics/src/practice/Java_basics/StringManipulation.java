package practice.Java_basics;

public class StringManipulation {
	public static void main(String [] args) {
		String s1 = new String("suthan");
		String s2 = "suthan";
		String s3 = new String(new char[]{'r','a','t','h'},1,3); //ath 
		String s4 = new String(new int[]{97,98,99,100},1,3); // print using charset
		//same for byte inplace of int
//		System.out.println(s3);
//		System.out.println(s4);
		
		
		StringBuffer sb = new StringBuffer("suthan"); //synchronized
		StringBuilder sbi = new StringBuilder("sample");
		
		String s5 = new String(sb);
		String s6 = new String(sbi);
		
		
	}
}
