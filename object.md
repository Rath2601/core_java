### **OBJECT CLASS**:

#### **equals(Object obj)**:

* Default: Checks **reference equality** (whether two references point to the same object) and **the Values** are same.
* Purpose: Override to define logical equality based on object content.
* Common usage: Necessary for comparing objects logically, especially in collections.

#### **hashCode()**:

* Default: Returns a unique integer for each object instance.
* Purpose: Override when equals is overridden to ensure objects that are equal have the same hash code.
* Common usage: Used in hash-based collections (like HashMap, HashSet) to group objects.

#### **toString()**:

* Default: Returns a string with the class name + "@" + hash code in hexadecimal.
* Purpose: Override to provide a readable, custom representation of an object’s content.
* Common usage: Useful for debugging and logging.

#### **wait()**: (refer multithreading)

* Causes the current thread to wait until it is awakened, typicallyby being notified or interrupted.
* IllegalMonitorStateException - if the current thread is notthe owner of the object's monitor
* InterruptedException - if any thread interrupted the current thread before orwhile the current thread was waiting.
  The interrupted status of thecurrent thread is cleared when this exception is thrown.

#### **notify()**: (refer multithreading)

* notify() only wakes up one thread that is waiting on the monitor of the same object instance.
* The JVM picks an arbitrary thread from the waiting queue of the object to resume. It does not affect threads waiting on other object instances.

#### **notifyAll()**: (refer multithreading)

* notifyAll() wakes up all threads waiting on the monitor of the same object instance.
* It will notify all threads in the waiting queue for that specific object.

#### **NOTE**:
**wait(), notify(), and notifyAll() are only applicable to threads that currently hold the synchronized lock on the object they are invoking these methods on.**

```java
public class ObjectMethodPrac {

	public synchronized void waitMethod() {
		System.out.println(Thread.currentThread().getName() + " is waiting...");
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " is resumed...");
	}

	public synchronized void notifyMethod() {
		System.out.println(Thread.currentThread().getName() + " is notifying...");
		this.notifyAll();

	}

	public static void main(String[] args) throws InterruptedException {
		ObjectMethodPrac resource1 = new ObjectMethodPrac();

		Thread t1 = new Thread(() -> resource1.waitMethod(), "Thread-1");

		// Thread T2 will notify on resource1 (same object)
		Thread t2 = new Thread(() -> resource1.notifyMethod(), "Thread-2");

		// Thread T3 will wait on resource2 (same object)
		Thread t3 = new Thread(() -> resource1.waitMethod(), "Thread-3");

		
		t3.start();
		Thread.sleep(100); // Ensure T3 waits before T2 notifies

		t1.start();
		Thread.sleep(100); // Ensure T1 waits before T2 notifies

		t2.start(); // Notify all threads in waiting state of resource1 object.
		Thread.sleep(100);

	}
}
```
