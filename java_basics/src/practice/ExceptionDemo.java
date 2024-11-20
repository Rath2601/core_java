package practice;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionDemo {
	static int a = 10;
static int b = 0;
	public static void main(String[] args) throws CustomCException, FileNotFoundException {
		try {
			int c = a/b;
			throw new FileNotFoundException();
			
		}
		catch(ArithmeticException e) {
			
		}catch(RuntimeException e) {
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


//try {
//	if (a > 0) {
//		throw new CustomRException("summa try pannu");
//	} else if(a<0){
//		throw new CustomCException2("summa try ");
//	}else {
//		throw new CustomCException("summa try ");
//	}
//}
// catch (CustomCException ex) {
//
//}
//catch (CustomCException2 ex) {
//
//}
//finally {
//	System.out.println("Finally will be printed");
//}