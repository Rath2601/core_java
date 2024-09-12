package javaversionfeatures.java17;

public class Test {
	public static double getPermitter(Shape shape) {
		if (shape instanceof Circle c) {
			return 2*Math.PI*c.radius();
		}
		else if (shape instanceof Rectangle r) {
			return 2*r.length()*r.breadth();
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	public static double getPerimeterUsingSwithch(Shape shape) {
		return switch (shape) {
		case Circle c -> 2*Math.PI*c.radius();
		case Rectangle r -> 2*r.length()*r.breadth(); 
		default ->
			throw new IllegalArgumentException("Unexpected value: " + shape);
		};
		
	}
	public  static void  testNull(Object o) {
		switch(o) {
		case String s -> System.out.println(s);
		case null -> System.out.println("unknown");
		default -> System.out.println("default");
		}
	}
	public static void main(String[] args) {
		System.out.println(getPermitter(new Circle(25)));
		System.out.println(getPermitter(new Rectangle(20,10)));
		System.out.println(getPerimeterUsingSwithch(new Circle(25)));
		System.out.println(getPerimeterUsingSwithch(new Rectangle(20,10)));
		testNull(null);
		testNull("suthan");
		testNull(10);
		
		
	}

}
