### JVM ClassLoaders

#### 1. Core Definition
* **ClassLoader:** JVM subsystem that dynamically reads raw `.class` bytecode on-demand and transforms it into live `java.lang.Class` objects inside **Metaspace** memory upon first code reference.

#### Class Loading Triggers: Lazy vs. Manual Eager

By default, the JVM loads classes **lazily** (only when a line of code explicitly mentions the class type or creates an instance). You can force **explicit/eager** loading via:

1. **`Class.forName(String)`**: Forces immediate loading, linking, and static initialization.
2. **Reflection API**: Calling methods like `loadClass()` or investigating class metadata forces immediate loading.
3. **Framework Bootstrapping**: Spring/Hibernate eagerly scan and load target entities/beans during startup to fail-fast if classes are missing.
   
---

#### 2. Modern Hierarchy (Java 9+)

| ClassLoader | Implementation | Scope / Packages Covered |
| :--- | :--- | :--- |
| **Bootstrap** | Native C/C++ | Core runtime modules (`java.base` e.g., `java.lang.*`, `java.util.*`). Returns `null` in Java code. |
| **Platform** | Java-based | Non-core SE platform modules/extensions (`java.sql.*`, `java.xml.*`). Replaced Java 8 Extension loader. |
| **Application** | Java-based | Application classpath/module-path (User classes, Spring, Hibernate, third-party JARs). |

---

#### 3. Parent-First Delegation Flow
1. **Trigger:** JVM targets **Application ClassLoader** for a class.
2. **Upward Delegation:** Application CL pauses $\rightarrow$ delegates to **Platform CL** $\rightarrow$ delegates to **Bootstrap CL**.
3. **Downward Execution:** Bootstrap CL gets "first right of refusal". 
   * *Found:* Loaded instantly (Guarantees security, prevents **Class Hijacking** of core API).
   * *Missing:* Trickles downward to Platform, then Application.
   * *Nowhere found:* Throws `ClassNotFoundException`.



---

#### 4. Custom ClassLoaders & Real-World Use

##### Definition
* Extends `java.lang.ClassLoader` and overrides `findClass(String name)`. 
* **Mechanics:** Customizes *how to fetch raw bytes* (`byte[]`) from non-standard sources, then passes them to the final native JVM method `defineClass()` to build the memory object.

##### Primary Use Cases
* **Non-standard Sources:** Loading bytecode from encrypted disk files, remote HTTP streams, or Database Binary Large Objects (BLOBs).
* **Hot-Swapping:** Loading/unloading modules or runtime plugins without restarting the JVM process.

##### Production Example: Spring Boot `LaunchedURLClassLoader`
* **The Problem:** Standard Java Application ClassLoader cannot read nested JARs (e.g., `app.jar -> /lib/dependency.jar`).
* **The Solution:** Spring Boot uses `LaunchedURLClassLoader` to scan, index, and programmatically load classes packaged deep inside a single executable **Fat JAR** (`BOOT-INF/lib/`).
---
### **Memory areas in Java**:

![Memory management](https://github.com/Rath2601/core_java/blob/main/images/memory_mgmt.png)

* **Class(Method) Area** : Stores class metadata, static variables, and code for methods and constructors.
* **Heap** : Stores objects, arrays and other data structure.
* **Stack** : Stores method call frames, local variables, and primitive data types.
* **Program Counter Register** :  Stores the address of the next instruction to be executed for each thread.
* **Native Method Stack** : Stores native method calls (if any).
---
### **Garbage collector**:

The garbage collector is primarily applicable for **heap memory**.

Heap divided into Young generation (Eden, survivors) , old generation, and permanent generation (static and non static method metadata , static and non static block metadata, static variables)

1. First when we run GC , the unreferenced objects marked and moved out of the heap and referenced are sweep to survivor 0.
2. the second time GC executes, the unreferenced objects marked and moved out of heap and referenced are sweep to survivor 1. 
(each time the age of an object is increased and we can set the threshold value in VM argument)
3. If an object survived till it reaches the threshold value then it'll be promoted to old generation . In the old generation the execution of GC is less frequent. Also the objects present in old generation are quiet big one.
4. the GC executed in young generation is called minor GC. GC executed in old generation is called major GC. 

The garbage collector has two **strategies of execution**, They are 

1. Mark and sweep 
2. Mark and sweep and compaction
  * **Prevents Fragmentation**: Fragmentation occurs when free memory is scattered across the heap, making it difficult to allocate large objects even if enough memory is available overall.
  * **Improves Allocation Efficiency**: With compacted memory, allocating memory for new objects is faster because the JVM doesn’t need to search for fragmented free spaces.

Also the garbage collector has **many versions** along many versions of Java. They are

versions of GC ->  because of the enhancement of GC (throughput will increase and latency will decrease) (*Here provide me pros and cons for each*) 

 1. **serial**  -> single thread (app thread will pause)
 2. **parallel** -> multiple thread will work (app thread will pause)
 3. **concurrent mark & sweep(CMS)** -> while app thread working , concurrently GC threads also working (100% not guaranteed) (no compaction happens)
 4. **G1 garbage collector** (like CMS with compaction)

**Serial Collector**:
* Use -XX:+UseSerialGC if:
* The application has a small data set (up to approximately 100 MB).
* The application will run on a single processor with no pause-time requirements.

**Parallel Collector**:
* Use -XX:+UseParallelGC if:
* Peak application performance is the first priority.
* There are no strict pause-time requirements, or pauses of 1 second or longer are acceptable.

**G1 or CMS Collector**:
* Use -XX:+UseG1GC or -XX:+UseConcMarkSweepGC if:
* Response time is more important than overall throughput.
* Garbage collection pauses must be kept shorter than 1 second.

**ZGC (Fully Concurrent Collector)**:
* Use -XX:+UseZGC if:
* Response time is a high priority.
* The application uses a very large heap.

To make references we can have three options Strong references, weak references, soft references.

 1. **Strong reference** -> Regular object references.
 2. **Weak reference** -> Caches where objects can be discarded if memory is tight.
 3. **Soft reference** -> Memory-sensitive caches or applications.

---
### **Memory Leak**:

**Memory leak is a situation where where there are objects present in the heap that are no longer used, but the garbage collector is unable to remove them from memory.**

The **potential areas of memory leak** are
1. Static References
   (note : A static field, by itself, is not a memory leak.It's only a memory leak if memory is allocated but not released when no longer needed.If you need a list of doubles for the lifetime of the program, then keeping the data in a static field is an appropriate design.)
3. listeners and callback
4. cached objects
5. Improper use of collections
6. unclosed resources
7. Inner class.

**impact of memory leak** can be
1. Decreased Application Performance
2. Increasing Memory Consumption Over Time
3. Frequent Garbage Collection Activities
4. OutOfMemoryError Exceptions

#### **Analyzing and Diagnosing Memory Leaks**

* **heap dump** -> snapshot of all the objects in memory at a particular moment
* **heap walker** -> tool for inspecting objects, references, and memory usage in the Java heap to identify memory leaks.

##### **VisualVM**
* Pricing: Free (Included in Oracle JDK).
* Features: Real-time memory monitoring, heap dump analysis, and built-in heap walker.
* Usage: Detect leaks by observing heap size growth and ineffective garbage collection.

##### **Eclipse Memory Analyzer (MAT)**
* Pricing: Free (Open-source).
* Features: Large heap dump analysis and automatic memory leak detection.
* Usage: Analyze heap dumps to locate memory leak suspects and large object allocations.

##### **JProfiler**
* Pricing: €449 (per user, perpetual license).
* Features: Real-time profiling, detailed heap analysis, and memory allocation tracking.
* Usage: Monitor memory usage in real-time and identify high-memory-consuming code paths.

##### **YourKit Java Profiler**
* Pricing: $499 (per user, perpetual license).
* Features: Comprehensive profiling for memory, CPU, and garbage collection.
* Usage: Analyze memory allocations and investigate inefficient garbage collection.

##### **Java Flight Recorder (JFR) and Java Mission Control (JMC)**
* Pricing: Free (Bundled with Oracle JDK).
* Features: Low-overhead data recording and advanced analysis of memory patterns.
* Usage: Collect runtime data and analyze memory usage in development or production.
  
#### **strategies to prevent memory leak**
* use local variables inside methods wherever possible
* Avoid static collections that grow indefinitely. (If a static collection is necessary, consider implementing a cleanup strategy that periodically removes unnecessary entries.) 
* Always unregister listeners and callbacks when they are no longer needed
* Use caching wisely with an eviction policy in place. Limit the size of caches and use soft or weak references.
* Be vigilant with collections. Remove objects from collections when they are no longer needed.
* Use static inner classes if an instance of the inner class can outlive its outer class instance.
* Always close resources (files, streams, connections) after use. Use try-with-resources statements for automatic resource management.
* Regularly profile your application for memory usage, especially after adding new features or making significant changes.
* Regular code reviews and pair programming sessions can help identify potential memory leak issues early.
* Write unit and integration tests to check for memory leaks, particularly in critical parts of the application.
