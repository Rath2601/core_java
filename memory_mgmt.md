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
* **Class(Method) Area** : Stores class metadata, static variables, and code for methods and constructors.
* **Heap** : Stores objects, arrays and other data structure.
* **Stack** : Stores method call frames, local variables, and primitive data types.
* **Program Counter Register** :  Stores the address of the next instruction to be executed for each thread.
* **Native Method Stack** : Stores native method calls (if any).
---
### **Memory Leak**:

**Memory leak is a situation where where there are objects present in the heap that are no longer used, but the garbage collector is unable to remove them from memory.**

The **potential areas of memory leak** are
1. Static References
2. listeners and callback
3. cached objects
4. Improper use of collections
5. unclosed resources
6. Inner class.

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
