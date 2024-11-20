package practice;

import java.io.IOException;

public class CustomCException2 extends IOException{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CustomCException2(String message){
		 System.out.println("Idhu compiletime custom exception vandha print aagum");
	 }
}
