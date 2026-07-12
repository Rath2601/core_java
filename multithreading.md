### Multitasking in a System (Process & Thread)

- **Process vs. Thread:** A process is an isolated sandbox container; a thread is a lightweight worker executing code inside it.
- **Execution Minimum:** Every process requires at least one "main" thread to actually run the application.
- **Isolation:** Processes are completely blind to each other; threads inside a process share everything (code, data, and memory).
- **Communication:** Process-to-process communication (IPC) is expensive and slow; thread-to-thread communication is direct and fast.
- **Switching Overhead:** Swapping processes forces the OS to reload memory maps (slow); swapping threads keeps the same map (fast).
- **Blast Radius:** A process crash is contained to itself; a single thread crash destroys the entire parent process and all other threads.
- **Process-Based multitasking:** Running separate apps concurrently (e.g., using Paint and Word at the same time).
- **Thread-Based multitasking:** Running concurrent tasks inside one app (e.g., printing and autocorrecting simultaneously in Word).

---

### Multithreading Basics in Java

- **Main thread** (also a user thread) is the initial point where we can spawn multiple **user** and **daemon** threads.
- Program stops executing as soon as **user threads** finish (does **not** wait for daemon execution).
- We set a thread as daemon using `Thread.setDaemon(true)`.
1. **Implement `Runnable`** and override `run()` — *(preferred, as multiple interfaces are supported in Java)*.
   - `Runnable` is just the **task** | `Thread` is the **task + utility methods**.
2. **Extend `Thread`** and override `run()`.

> **ExecutorService insight:** `ExecutorService` already has its own set of pre-warmed, highly optimized worker threads sitting in a pool waiting for work. We can only **assign tasks** rather than giving more workers — we pass only a `Runnable` (task) to the executor service.

---

### Thread Methods

| Method | What it does |
|---|---|
| `start()` | Creates a new thread and runs concurrently. |
| `run()` | Calling `run()` directly runs in the **main thread**, not concurrently. |
| `sleep()` | Pauses the thread for a specified time. Useful to delay execution or simulate time-based tasks. |
| `join()` | Waits for this thread to terminate. *(Only works on a running thread — otherwise just moves to the next line.)* |
| `yield()` | Hints to the thread scheduler that the current thread is willing to yield its current use of CPU to let other threads execute. *(Totally dependent upon the OS.)* |
| `interrupt()` | Signals that the thread should stop its current operation. |

---

### Synchronization

**Makes a particular piece of code thread-safe** — prevents race conditions by allowing only one thread to execute a block of code or a method at a time for a given object or class.

- **Class level:** Blocks all threads across the whole application, regardless of the object they use.
- **Object level:** Threads having different objects run completely in parallel without blocking each other. Different threads with different objects can run concurrently.
- You can use synchronized blocks inside methods to synchronize specific code blocks (rather than the entire method):

```java
synchronized (lock) { // lock --> any object can be passed as the lock object,
      // usually the instance that is calling this method
    // If the lock object is null, it will result in a NullPointerException.
}

synchronized (MyClass.class) { // In case of static, we must use the class in the synchronized block
}
```
- **You cannot release a lock you do not own** → you need to acquire the lock using `synchronized` before calling `wait()` / `notify()`.
- **Synchronization should be at the same level while using wait/notify otherwise IllegalMonitorStateException** (either class-level everywhere or object-level everywhere) — mixing levels means threads are locking on different monitors and won't block each other.

---

### wait() / notify() with synchronized  vs  ReentrantLock with Condition

- `wait()` and `notify()` are **hardcoded** to look only for native `synchronized` monitor locks.
- You need **at least two threads** for wait/notify coordination to make sense.
- `ReentrantLock` and `Condition` are far more powerful than `synchronized` because **a single lock can have multiple conditions** (e.g., separate `notFull` and `notEmpty` conditions on one queue lock).

#### Side-by-side API Comparison

| `synchronized` / Object Framework | `ReentrantLock` Framework | What it actually does |
|---|---|---|
| `synchronized(lock)` | `lock.lock()` | Acquires the lock. |
| `lock.wait()` | `condition.await()` | Releases the lock, drops the thread to `WAITING`. |
| `lock.notify()` | `condition.signal()` | Wakes up **one** waiting thread. |
| `lock.notifyAll()` | `condition.signalAll()` | Wakes up **all** waiting threads. |

#### Producer–Consumer Problem

- The basic **educational** solution is `wait()` and `notify()`.
- In a **real-world scenario we would never use these methods** — we would use utility classes like `ArrayBlockingQueue` or `LinkedBlockingQueue`, which use **separate locks for reading and writing to maximize throughput**. We would also use `ReentrantLock` where finer control is needed.

---

### Thread-Safe Classes & the Loophole Associated with Them

#### Common thread-safe classes in Java

| Class / Group | Mechanism |
|---|---|
| `Vector`, `Stack`, `Hashtable` | Use locking — **mutual exclusion** |
| All classes in `java.util.concurrent` & `java.util.concurrent.atomic` | **Hardware-level atomicity** (CAS) |
| `StringBuffer` | Mutable **and** synchronized — every single one of its public methods is explicitly marked with the `synchronized` keyword |
| `String` and wrapper classes (`Integer`, `Double`, etc.) | **Immutable** — thread-safe because their state cannot be modified after creation |

> **Mutability** → the state of the data | **Synchronization** → the mechanism of access control.

#### The Loophole

> **Just because a class is thread-safe doesn't mean your logic is.**

When you chain multiple thread-safe methods together (compound actions like *check-then-act* or *read-modify-write*), you have to wrap them in a `synchronized` block so they are treated as a **single, uninterrupted transaction**.

- `Vector`, `Stack` → mutual exclusion (all methods are synced with single lock & one method executes at a time)
- Atomic classes → maintain atomicity (CPU-level Compare-And-Swap (CAS) read and write only if nobody changes it meanwhile)

#### Immutability vs. Synchronization

Even though immutability and synchronization are two different concepts, **immutable classes might not need synchronization at all**. Synchronization is applicable only to classes containing **mutable fields**.

---

### Immutability & Singleton Reference

*(Cross-linked concept — added here because it's easy to confuse while studying multithreading.)*

- **Singleton** → could be / could not be mutable. Singleton only guarantees *one instance*, not *immutable state* — a mutable singleton still needs thread-safety handling.

#### A truly immutable class requires:

1. `final` class (no subclassing).
2. `private final` fields (assigned once, hidden from the outside).
3. **Defensive copying in the constructor** for all incoming mutable parameters.
4. **Defensive copying in getters** for all outgoing mutable internal objects.
5. **No `this` reference escaping** during the construction phase.
---

![Thread Lifecycle](https://github.com/Rath2601/core_java/blob/main/images/thread_lc.png)

### Thread States — Triggers, Production Meaning & the Real-World Replacement

| Thread State | Primary Triggers (Basic & JUC Methods) | Key Production Meaning / Behavior | Real-World Proper Solution (instead of raw basics) |
|---|---|---|---|
| **NEW** | `new Thread()` | Object allocated in memory; not started. Pools manage this lazily or eagerly. | Never `new Thread()` manually in production → use **`ExecutorService` / thread pools** (`Executors.newFixedThreadPool`, etc.), which pre-create and reuse threads. |
| **RUNNABLE** | `t.start()`, `Thread.yield()`, user's I/O completion | Actively executing or waiting for a CPU time slice. The JVM makes no distinction between the two. | Submit tasks via **`ExecutorService.submit()`** / **`CompletableFuture.supplyAsync()`** instead of `start()`; never rely on `yield()` — leave scheduling to the pool + OS. |
| **BLOCKED** | Waiting for a `synchronized` block/method; post-`notify()` lock re-acquisition | Waiting **strictly for an intrinsic monitor lock**. Does **not** apply to `ReentrantLock` (it's a class which keeps the thread in the WAITING state instead). | Replace long/contended `synchronized` sections with **`ReentrantLock`** (`tryLock`, fairness, interruptibility) or lock-free **atomic classes** / **concurrent collections**. |
| **WAITING** | `obj.wait()`, `t.join()`, `ReentrantLock.lock()` | Waiting **indefinitely** for another thread to signal/finish. Uses `LockSupport.park()`. | Replace `wait()/notify()` with **`Condition.await()/signal()`**, or better, **`BlockingQueue`** for producer–consumer; replace `join()` with **`CompletableFuture.thenApply/thenCombine/allOf`** or `Future.get()`. |
| **TIMED_WAITING** | `Thread.sleep(ms)`, `obj.wait(ms)`, `t.join(ms)`, `lock.tryLock(time)` | Waiting with a **hard upper-bound timeout**. `sleep()` **retains** locks; `wait()` **releases** them. | Replace `sleep()`-based polling with **`ScheduledExecutorService`** (scheduled/periodic tasks), **`lock.tryLock(timeout)`**, **`future.get(timeout)`**, or **`CompletableFuture.orTimeout()`**. |
| **TERMINATED** | Run completion, uncaught exception | Dead. Cannot be restarted. **Deadlocks or leaks show up here in thread dumps.** | Use **`ExecutorService.shutdown()/awaitTermination()`** for graceful lifecycle; handle failures via `Future`/`CompletableFuture.exceptionally()` and `UncaughtExceptionHandler` instead of letting threads die silently. |

---

### Benefits of Real-World Concurrency Classes

| Class / Utility | Replaces (educational primitive) | Key Benefits in Production |
|---|---|---|
| **`ExecutorService`** (thread pools) | `new Thread()` + `start()` | Pre-warmed, reusable worker threads (no per-task thread creation cost); bounded resource usage; task-vs-worker separation; built-in lifecycle (`shutdown`, `awaitTermination`); returns `Future` for results. |
| **`ReentrantLock`** | `synchronized` | Explicit lock control across method boundaries; `tryLock()` (avoid deadlock by backing off); `tryLock(timeout)`; **interruptible** lock acquisition (`lockInterruptibly()`); optional **fairness** policy; one lock → **multiple `Condition`s**. |
| **`Condition`** | `wait()` / `notify()` / `notifyAll()` | Multiple wait-sets per lock (e.g., `notFull` + `notEmpty`), so you signal exactly the threads that care — no "wake everyone and re-check" thundering herd. |
| **`CompletableFuture`** | `join()`, manual result-passing between threads | Non-blocking async composition (`thenApply`, `thenCompose`, `thenCombine`, `allOf/anyOf`); declarative error handling (`exceptionally`, `handle`); timeouts (`orTimeout`); chains pipelines instead of blocking threads. |
| **`ArrayBlockingQueue` / `LinkedBlockingQueue`** | `wait()`/`notify()` producer–consumer code | Producer–consumer solved out-of-the-box; blocking `put()`/`take()` handle all signaling internally; `LinkedBlockingQueue` uses **separate locks for reading and writing to maximize throughput**. |
| **`java.util.concurrent.atomic` classes** (`AtomicInteger`, etc.) | `synchronized` counters/flags | **Hardware-level atomicity** (CAS) — lock-free, no BLOCKED threads, far higher throughput for simple read-modify-write operations. |
| **Concurrent collections** (`ConcurrentHashMap`, etc.) | `Hashtable`, `Vector`, `Collections.synchronizedX` | Fine-grained/striped locking or lock-free reads instead of one big monitor; atomic compound operations (`computeIfAbsent`, `putIfAbsent`) that close the "thread-safe class ≠ thread-safe logic" loophole. |
| **`ScheduledExecutorService`** | `Thread.sleep()` loops | Precise delayed & periodic scheduling without burning a thread on `sleep()`; survives task exceptions per policy. |

---

*Rule of thumb: the `Thread`/`wait`/`notify`/`sleep`/`join`/`yield` layer is for **understanding the JVM's threading model**; the `java.util.concurrent` layer is what you actually **ship**.*

---
### **synchronization demo with stack** : 

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
---
### **Join Demo**:
```java
public class JoinDemo {
	public static void main(String[] args) {

		Thread t1 = new Thread(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, "Thread-1");

		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, "Thread-2");

		Thread t3 = new Thread(() -> {
			try {
				Thread.sleep(2000);
				try {
					t2.join(18000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName());
		}, "Thread-3");

		t1.start();
		t2.start();
		t3.start();

		System.out.println("Thread-1 : " + t1.getState());
		System.out.println("Thread-2 : " + t2.getState()); // Thread-2 TIMED_WAITING state it is sleeping for 20 seconds
		System.out.println("Thread-3 : " + t3.getState()); // Thread-3 TIMED_WAITING state it is waiting for Thread-2 to
															// finish (18s)

	}
}
```
---
### **Dead lock**

```java

public class DeadLockDemo {
	public static void main(String[] args) {
        String lock_one = "boom";
        String lock_two = new String("boom");
        
        System.out.println(lock_one==lock_two); // If this is true, We can't able to acquire deadlock
        System.out.println(lock_one.equals(lock_two));
        
        Thread t1 = new Thread(() -> {
        	synchronized(lock_one) {
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		synchronized(lock_two) {
        			System.out.println("successfully acquired lock");
        		}
        	}
        },"Thread1");
        
        Thread t2 = new Thread(() -> {
        	synchronized(lock_two) {
        		try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		synchronized(lock_one) {
        			System.out.println("successfully acquired lock");
        		}
        	}
        },"Thread2");
        
        t1.start();
        t2.start();
	}
}
```
---
