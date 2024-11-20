package practice.Java_basics.java8;


@FunctionalInterface
interface Demo{
	
	String msg(String a , String b);
	
	default String process(String s) {
		return String.join(s,"This", "is");
	}
	
}

public class LambdaFunctions {
	public static void main(String[] args) {
		Demo d = (a,b) ->{
			
		return	a.strip()+" "+b.strip();
			
		};
		
		System.out.println(d.process(" "));
		System.out.println(d.msg("hey", "google"));
	}
}


