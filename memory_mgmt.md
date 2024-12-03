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
  
1. BootStrap Classloader -> core class loader loads all classes in jre library
2. Extension Classloader -> all classes under ext folder of jre. (3rd party jar)
3. System Classloader    -> loads everything on the classpath (jdbc jar, hibernate jar , spring jar)
4. Custom Classloader    -> application server dev write their own custom CL for jboss, weblogic

to dynamically load a class at runtime , 
```
Class driver = Class.forName("com.mysql.jdbc.Driver"): // can throws ClassNotFoundException
driver.method() // we can invoke the methods in the class using reflection API 
```

* **Class(Method) Area** : Stores class metadata, static variables, and code for methods and constructors.
* **Heap** : Stores objects, arrays and other data structure.
* **Stack** : Stores method call frames, local variables, and primitive data types.
* **Program Counter Register** :  Stores the address of the next instruction to be executed for each thread.
* **Native Method Stack** : Stores native method calls (if any).
