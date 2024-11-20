package imp_keywords;

public class FinalKeyword {
	
	static final StringBuffer test = new StringBuffer("RATH");
	
	 final  StringBuffer sample = new StringBuffer("RATH");
	 
	 void method1() {
		System.out.println("test for final");
	}
	
	final void method2() {
		System.out.println("test for final");
	}
	
	public static void main(String[] args) {
            FinalKeyword fk = new FinalKeyword();
            fk.sample.append(" SUCCESS");
            System.out.println(fk.sample);
            
            FinalKeyword fk2 = new FinalKeyword();
            fk2.sample.append(" SUCCESS CORRECT");
            System.out.println(fk2.sample);
            
            System.out.println(fk.sample);
            
            System.out.println(fk2.sample);
	}
}


