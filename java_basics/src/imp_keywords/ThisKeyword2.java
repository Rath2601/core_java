package imp_keywords;

public class ThisKeyword2 {

	ThisKeyword2 m1() {
		// extra logic can be written here
		return this;
	}

	void demo() {
		System.out.println("method to be called from "
				+ "this as a keyword for class instance");
	}

	public static void main(String[] args) {
          new ThisKeyword2().m1().demo();
	}
}
