package practice.Java_basics;

enum Demo {
	VIJAY(07), AJITH(05), SURYA(07);

	int ratings;

	Demo(int ratings) {
		this.ratings = ratings;
	}

	public int getRatings() {
		return ratings;
	}
}

public class EnumDemo {
	public static void main(String[] args) {
		System.out.println(Demo.AJITH.getRatings());
	}
}
