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

### **Thread safety**

* Thread-safety typically refers to the **ability to safely modify the state of an object when accessed concurrently by multiple threads**.

* **common thread safe classes in java**
     * **Vector**, **Stack** ,**Hashtable**
     * all classes in **`java.util.concurrent`** & **`java.util.concurrent.atomic`**
     * **Stringuffer** (mutable and synchronized)
* String and wrapper classes like Integer, Double, etc., are immutable, and they are thread-safe because their state cannot be modified after creation. 
* Eventhough **immutability** and **synchronization** are two different concepts, In case of immutable classes we might not need synchronization itself. Synchronization is applicable only to class containing mutable fields.
  
---
### **ProducerConsumer problem in Threads**:

```java

public class ProducerConsumerDemo {

    // Shared Queue to store produced items
    private Queue<String> q;

    // Maximum capacity of the queue
    private Integer cap;

    // Constructor to initialize queue and capacity
    public ProducerConsumerDemo(Integer cap) {
        this.cap = cap;
        this.q = new LinkedList<String>();
    }

    // Synchronized method to add an element to the queue (Producer's action)
    public synchronized Boolean add(String element) {
         // If the queue is full, the producer thread needs to wait
        while (q.size() == cap) {
            try {
                // The producer thread releases the lock and waits for the consumer to consume an item
                this.wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Add the item to the queue
        q.add(element);

        // Notify all waiting threads (e.g., consumers) that the queue is no longer empty
        this.notifyAll(); 

        return true;
    }

    // Synchronized method to remove an element from the queue (Consumer's action)
    public synchronized String remove() {
        // If the queue is empty, the consumer thread needs to wait
        while (q.size() == 0) {
            try {
                // The consumer thread releases the lock and waits for the producer to add an item
                this.wait(); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Remove an item from the queue
        String element = q.poll();

        // Notify all waiting threads (e.g., producers) that the queue is no longer full
        this.notifyAll(); 

        return element;
    }

    public static void main(String[] args) {
        ProducerConsumerDemo pc = new ProducerConsumerDemo(5); // Queue with a capacity of 5

        // Producer Thread
        Thread producer = new Thread(() -> {
            int count = 1;
            while (true) {
                try {
                    String item = "Item-" + count;
                    pc.add(item); // Producer adds an item to the queue
                    System.out.println("Produced: " + item);
                    count++;
                    Thread.sleep(500); // Simulate production time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "producer");

        // Consumer Thread
        Thread consumer = new Thread(() -> {
            while (true) {
                try {
                    String item = pc.remove(); // Consumer removes an item from the queue
                    System.out.println("Consumed: " + item);
                    Thread.sleep(1000); // Simulate consumption time
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }, "consumer");

        // Start both threads
        producer.start();
        consumer.start();
    }
}
```
### **Thread lifecycle**

![Thread Lifecycle](https://github.com/Rath2601/main/images/thread_lc.jpg)
