package practice.Java_basics;

public class StringManipulation2 {
	public static void main(String [] args) {
	   String s1 = "Hello";
	   String s2 = s1 + " World";
	   
	   System.out.println(s1);
	   System.out.println(s2);
	   
	   StringBuffer sb1 = new StringBuffer("Hello");
	   StringBuffer sb2 = sb1.append(" World");
	   
	   System.out.println(sb1);
	   System.out.println(sb2);
		
	}
}
