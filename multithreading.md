## **Multitasking in a system**
* **Process based** -> multiple threads in multiple process (ex: using paint and word at same time)
* **Thread based** -> multitasking within a program (ex: printing and autocorrecting text at same time)
  
## **Thread**
* Independent sequential path of execution.
* Many threads can run concurrently.
* At runtime THREADS in a program exist in **common memory space** and share both data & code.
* Lightweight compared to process.
* **share the process** running the program.
  
### **Concept in multithreading**
* Main thread(also user thread) is initial point where we can spawn multiple user and deamon threads.
* Program stops executing as soon as user thread finished (dont wait for deamon execution)
* we set a thread as deamon by using Thread.setDeamon(true)
* to create a Thread on our own
  * We create a runnable type and override run method.(implements Runnable) (preferred as multiple interface supported in java)
  * We create a Thread type and overrider run method. (extends Thread)
    
### **synchronization** [makes a particular code thread safe]

* **prevents race conditions by allowing only one thread to execute** a block of code or a method at a time for a given object or class.
* **Method Synchronization**:
  * **Instance level** 
  * **Class level**
* If a synchronized method in the parent class is overridden in a child class, the synchronization behavior depends on the child class. If not explicitly synchronized, it won't be synchronized by default.
* You **can use synchronized blocks inside methods** to synchronize specific code blocks (rather than the entire method).
```java
  synchronized (lock) { // lock --> any object can be passed as lock object, usually the instance that is calling this method
     // If the lock object is null, it will result in a NullPointerException.
  }

  synchronized (MyClass.class) {  // In case of static we have to use the class in the synchronized block
     
  }
```
```java
public class Stack {
	private int[] array;
	private int stackTop;
	private Object lock;

	public Stack(int capacity) {
		array = new int[capacity];
		stackTop = -1;
		lock = new Object();
	}
	
	public static synchronized void method() { // here locked on class level.
		//Can run independently from synchronized block of an instance.
		synchronized(Stack.class) {
			
		}
	}

	// t1, t2, t3 (threads) t1 locked with s Object
	public synchronized boolean push(int element) {
		synchronized (lock) { // if the lock is null , we will get null pointer exception.
			if (isFull())
				return false;
			++stackTop;
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			array[stackTop] = element;
			return true;
		}

	}

	// t1, t2, t3 (threads) all has to wait since the lock is on push method by same
	// instance
	// we can use any object to be used as a lock for synchronized block.
	public synchronized int pop() { // synchronized at method level is like using synchronized(this) {} for whole
									// method content.
		synchronized (lock) {
			if (isEmpty())
				return Integer.MIN_VALUE;
			int obj = array[stackTop];
			array[stackTop] = Integer.MIN_VALUE;
			try {
				Thread.sleep(1000);// increases the chances for the state of the stack being corrupted by other thread
			} catch (Exception e) {
			}
			stackTop--;
			return obj;
		}
	}

	public boolean isEmpty() {
		return stackTop < 0;
	}

	public boolean isFull() {
		return stackTop >= array.length - 1;
	}

	public static void main(String[] args) {
		
		// If I override the synchronized method, it is the doscretion of the developer to make it synchronized also.

		Stack s = new Stack(5);// same object is used for push and pop by two different threads

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.push(i);
				// While this executes, the `pop` thread cannot access the `pop` method
				// because the lock is held by the current thread (`pusher`) on the shared
				// `Stack` instance
			}
		}, "pusher").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				s.pop();
				// While this executes, the `pusher` thread cannot access the `push` method
				// because the lock is held by the current thread (`pop`) on the shared `Stack`
				// instance
			}
		}, "pop").start();
		
		//Order is not guaranteed, whichever thread can run.

	}

}
```
