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
