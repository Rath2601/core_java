package practice;

public class CustomRException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CustomRException(String message){
		System.out.println("Idhu runtime custom exception vandha print aagum");
	}

}
