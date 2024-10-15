package practice;

public class Car implements vehicle {

	public void speed() {
	}

	public void weight() {
	}

}

class Bike implements vehicle {

	public void speed() {
	}

	public void weight() {
	}

}

interface vehicle {

	void speed();
	void weight();
}