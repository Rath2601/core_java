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
### **JAVA MEMORY MANAGEMENT**:

![Memory management](https://github.com/Rath2601/core_java/blob/main/images/Java_Memory_Model.png)

##### Java Memory Model

A specification that defines how threads in Java interact with memory, ensuring consistent and predictable behavior in multithreaded environments across different hardware and JVM implementations.

It establishes rules for visibility, ordering, and atomicity, preventing issues like data races caused by CPU caching or instruction reordering.

##### GC Roots

An object on the Java Heap is a GC Root if and only if its liveness is determined by an execution context outside of the general garbage-collected heap itself.

**GC root examples:**

- **Stack:** Local variables and method parameters
- **Metaspace:** References to static variables of loaded classes
- **OS / Native Layer (JNI):** JNI Handles (Global/Local) used by external native C/C++ code.
- **JVM System Internal Layer:** Core JVM engine references (like GC threads, system ClassLoaders, and fundamental system exceptions) living in Native Process Memory and Metaspace.

##### Java Memory Structure

At runtime, the JVM creates a set of runtime data areas. These include:

- Heap Memory
- Stack Memory
- Method Area (Implemented as Metaspace in Java 8+)
- Program Counter (PC) Register
- Native Method Stack

###### Heap Memory

- primary area for dynamic memory allocation
- shared memory space accessed by all threads
- JVM regions: Young Generation and the Old Generation (To optimize this process and reduce application pause times)

###### Young Generation

- optimised for fast allocation and frequent garbage collection (newly created objects begins lifecycle)
- objects are collected often using Minor GCs which are fast and efficient (e.g., method-local objects, temporary buffers small objects)
- **Eden Space:** When Eden fills up, a Minor GC triggered (JVM pauses briefly, hence to optimize multithreading used)
- **Survivor Spaces (S0/S1):** After each Minor GC, reachable objects from Eden are moved into one of the survivor spaces
- if object age exceeds `-XX:MaxTenuringThreshold` promoted to old generation / forced promotion when S0/S1 fills up
- tuning of the Young Generation: size of the Young Generation (`-Xmn`), Eden-to-Survivor space ratio (`-XX:SurvivorRatio`), promotion age threshold (`-XX:MaxTenuringThreshold`)
- tuning useful for applications with high allocation rates, such as REST APIs, streaming pipelines, or real-time event processing systems

###### Old Generation

- designed to hold long-lived objects, those that have survived multiple Minor GCs.
- examples: Persistent application-level caches, Large object graphs such as sessions or user data, Static or shared data structures that are retained across requests
- Major GC run here. when both generations are collected together, the process is known as a Full GC.
- A full stop-the-world pause, Tracing all reachable objects starting from the GC roots and Compacting memory to eliminate fragmentation
- tune maximum heap size using (`-Xmx`) and initial heap size (`-Xms`), ratio btw old/young (`-XX:NewRatio`)

###### Stack Memory

- Thread local and stack exist for each thread. it has many frames.
- Last-in, first-out (LIFO) order.
- each frame has:
  - **Local variable array:** Holds method parameters, primitives, reference to objects declared within the method.
  - **Operand stack:** Used internally by the JVM to evaluate expressions and store intermediate computations.
  - **Return value slot:** Stores the result of the method call, if any, before passing it back to the calling method.
  - **Reference to the runtime constant pool:** Allows the method to resolve field names, method names, and literals.
- lightweight and quick. No Garbage collector required
- stack size can be tuned (`-Xss`)
- we would get stackOverflowError if we had too many nested methods without tail call optimization / recurses too deeply with no base case or improper termination condition

###### Metaspace

- class metadata (whole blueprint including constructor code, field and method metadata), runtime constant pool, reference to static variables
- majorly used by polymorphism, reflections
- this is replacement for permgem after java 8 which is allocated from native memory (grow dynamically as needed) (in this static content part of permgem)
- can size to prevent native memory exhausion (`-XX:MetaspaceSize` `-XX:MaxMetaspaceSize`)
- GC same as heap (major GC) —> identifies classes that are loaded by classloader are unreachable and also classloader is not reachable (`-XX:+ClassUnloading` to make JVM unload classes if not used and no object exist of that class)

###### Program Counter Register

- has bytecode instruction a thread could execute next
- each thread will have its own private PC register (to have multithreaded independent execution for threads)
- native methods are handled by JNI and implemented in C.
- decode and execute as per OS
- PC register does not require garbage collection or tuning, and it has no configurable size or visibility at the language level

###### Native Method Stack

- used for native (C/C++) methods via JNI (acts as bridge between JVM and native libraries)
- unlike java stack, this is managed by OS
- each thread has it own native method stack
- No GC manually managed in native code.
- **Common Causes of Native Memory Leaks:**
  - Missing free() or delete calls in JNI code
  - Improper use of DirectByteBuffer without cleanup
  - Unreleased file handles or sockets
  - Repeated class loading without proper unloading
  - Native libraries with poor memory hygiene

##### Garbage Collector

- its efficiency has a direct impact on application performance, latency, and scalability
- Frees up memory occupied by unreachable objects
- Prevents memory leaks and heap exhaustion
- Ensures long-running applications continue to operate without manual intervention

| Collector | Pause Time | Concurrency | Compaction | Best Use Cases | Platform & JDK Support |
|---|---|---|---|---|---|
| Serial GC | High (stop-the-world) | None | Yes | Small apps, single-threaded or embedded systems | All platforms, all JDKs `-XX:+UseSerialGC` |
| Parallel GC | Moderate–High | Parallel stop-the-world | Yes | Batch jobs, compute-heavy services, large heaps with loose latency requirements | All platforms, all JDKs `-XX:+UseParallelGC` |
| CMS (Deprecated) | Low (some phases concurrent) | Mark & sweep are concurrent | No (leads to fragmentation) | Legacy low-latency systems (Java 8 only) | Removed in Java 14 `-XX:+UseConcMarkSweepGC` |
| G1 GC | Low–Moderate | Concurrent marking, mixed mode | Partial (region-based) | General-purpose apps, moderate-latency SLAs | Default in Java 9+ `-XX:+UseG1GC` |
| ZGC | Very low (<10ms) | Fully concurrent | No (uses colored pointers) | Real-time, large-heap, low-latency systems | Linux, Windows, macOS (x86_64, AArch64), Java 15+ `-XX:+UseZGC` |
| Shenandoah | Very low (heap size independent) | Fully concurrent | Yes (concurrent compaction) | Interactive, real-time systems needing low pause and compaction | Linux, Windows (x86_64, AArch64), Java 15+ `-XX:+UseShenandoahGC` |

Regardless of the algorithm used, most GC implementations follow a variation of the Mark-Sweep-Compact process:

1. **Mark:** The collector scans through live references and marks all reachable objects by tracing from the GC roots.
2. **Sweep:** Once marking is complete, the collector reclaims memory occupied by objects that were not marked (i.e., unreachable).
3. **Compact (optional):** To reduce fragmentation, some collectors move live objects into contiguous memory regions and update references.

To make references we can have three options Strong references, weak references, soft references.

1. **Strong reference** -> Regular object references.
2. **Weak reference** -> Caches where objects can be discarded if memory is tight.
3. **Soft reference** -> Memory-sensitive caches or applications.

##### Memory Leak

A memory leak occurs when objects that are no longer needed remain reachable and are not collected by the garbage collector. This usually results in gradual heap growth and eventual OutOfMemoryError

- **Collection growth leak:** Singleton instance (spring bean) which has an instance variable collection and we add to this list.
- **static field leak:** if we assign massive object graph to static field
- **Thread leak:** Anything reachable from a running thread's stack cannot be garbage collected. failed to call shutdown() on ExecutorService / we didn't exit from infinite looped Thread. anything referred via this thread will not be removed from heap
- **ClassLoader Leaks:** Failure to isolate dynamic runtime environments. (JVM metaspace infra)

**particularly in heap:**

- Failure to properly release resources like database connections
- Inefficient caching mechanisms without proper evict / expire mechanism
- Loading large files or large numbers of objects into memory

**particularly in metaspace:**

- Excessive class loading (e.g., repeated redeployment in application servers)
- Classloader memory leaks in frameworks or custom classloaders
- Lack of a defined Metaspace limit

**due to GC:**

- High object allocation rate with insufficient heap
- Memory leaks causing the heap to remain full
- Inefficient GC configuration

##### Best Practices

- **Minimise unnecessary object creation** (Reuse objects where possible (e.g., use StringBuilder instead of concatenating immutable strings), Prefer primitive types over boxed types (e.g., int instead of Integer) when autoboxing is not needed, Use object pools only when profiling shows significant performance gain.)
- **Avoid memory leaks:** Use weak references (WeakReference, WeakHashMap) for caches or listeners, Deregister event listeners and callbacks explicitly, Close all resources in finally blocks or use try-with-resources)
- **Choose the right Data structure:** (Prefer ArrayList over LinkedList unless insert/remove operations dominate, Use EnumSet or EnumMap instead of general-purpose collections for enums, Avoid using synchronized collections when not needed, Pre-size collections if the final size is known to avoid resizing overhead)
- **Be mindful of object retention in collection:** Clear collections when they are no longer needed, Limit the scope of long-lived caches or queues, Use bounded collections where applicable (e.g., LinkedBlockingQueue with capacity)
- **Understand application specific memory pattern:** Web servers may have many short-lived objects (benefits from efficient young generation tuning), Stream processors often maintain stateful objects (heap size and compaction become critical), GUI applications are sensitive to GC pauses (low-latency GC collectors may be required)
