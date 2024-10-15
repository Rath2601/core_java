package practice;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Test {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		try {
			System.out.println("test");
			throw new FileNotFoundException();
			
			
		} 
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("catched");
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("catched");
		} catch (IndexOutOfBoundsException e) {

		}
		catch (IOException e) {
			
		}

	}

//	private static void methodA() throws Exception {
//		methodB();
//	}
//
//	private static void methodB() throws Exception {
//		throw new CustomCException("test");
//	}
}