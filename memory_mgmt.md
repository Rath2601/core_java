## Memory area allocated by JVM

The major concepts in Java Memory Management :
* **Loading Metadata**
* **JVM Memory Structure**
* **Working of Garbage Collector**

### **Loading Metadata**:

* **CLASSLOADER** -> used to load class into memory.
  * **Load classes in hierarchical fashion**
  * **convert from .class to class obj** (compiler converts from .java to .class)
  * First class in app is loaded using static main() invoked by JVM. other classes are loaded as required by this particular class.
  
1. BootStrap Classloader ->
   * Bootstrap CL serves as the parent of all the other CL instances. 
   * core class loader loads all classes in jre library (only classes belong to **java.base**) (**loads the classloader itself**)
   * not a java class (written in native c/c++) 
2. Extension Classloader ->
   * child of the bootstrap class loader.
   * all classes under ext folder of jre. (all other modules than java.base)
3. System Classloader    ->
   * loads everything on the classpath (jdbc jar, hibernate jar , spring jar)
   * loads the userdefined classes also.
4. Custom Classloader    -> 
   * application server dev write their own custom CL for jboss, weblogic

to dynamically load a class at runtime , 
```
Class driver = Class.forName("com.mysql.jdbc.Driver"): // can throws ClassNotFoundException
driver.method() // we can invoke the methods in the class using reflection API 
```

#### **Functioning of classloader**:

* **a ClassLoader instance will delegate the search for the class or resource to its parent class loader before attempting to find the class or resource itself.**
  * CL are part of JRE, whenever JVM request a class the CL checks to load the class. 
  * If it doesn't able to load it, this'll delegate the loading task to its parent class (a type of Bootstrap ClassLoader)
  * Still not found --> delegate to its child (a type of ExtClassLoader)
  * Still not found --> delegate to its child (AppClassLoader)
  * Still not found --> then only throw the error. 

---
#### **Uses of CustomClassLoader** :
  * Load classes from sources other than the file system or JARs, such as databases, networks, or custom file systems.
  * Dynamically load and unload plugins at runtime. (new functionalities can be added without restarting the system.)
  * enabling different versions of classes to coexist.
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
