package practice.collection;

public class Generic<T> {

	private T val;
	
	public Generic() {
	}
	
	public Generic(T value) {
		this.val = value;
	}

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
	
}
