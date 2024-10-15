package practice.Java_basics;

public class EnumDemo2 {
	public static void main(String[] args) {
		TrafficLight light = TrafficLight.RED;
		light.display();
	}
}

interface Displayable {
	void display();

	void desc();
}

enum TrafficLight implements Displayable {
	RED {
		@Override
		public void display() {
			System.out.println("Stop!");
		}

		@Override
		public void desc() {
			System.out.println("you need to stop");

		}
	},
	GREEN {
		@Override
		public void display() {
			System.out.println("Go!");
		}

		@Override
		public void desc() {
			System.out.println("you may go");

		}
	},
	YELLOW {
		@Override
		public void display() {
			System.out.println("Caution!");
		}

		@Override
		public void desc() {
			System.out.println("you need to wait");

		}
	};
}

enum AccountTypes{
	SAVINGS("savings account",5000);

	AccountTypes(String string, int i) {
	}
}
