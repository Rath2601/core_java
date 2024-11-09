# **CORE JAVA NOTES**

## **SYNTAX**:

### Class Definition

```java
{public/default} {abstract/final --if needed} class/interface {class_name} {....... }

abstract/final --> not needed for interface

```

### Variable Definition

```java
{public/default/protected/private} {transient - if needed} {volatile/final --if needed} {static --if needed} {datatype} {variable_name} = {value} ;

transient --> marking a variable as transient indicates that it should not be serialized.
             serialization --> persistence, networking, distributed systems, caching, JMS & message brokers.
volatile  --> to indicate that a variable's value will be modified by different threads. (change in one thread is visible to all threads)

```
### Method Definition

```java
{public/default/protected/private} {abstract/final --if needed} {static --if needed} {synchronized -- if needed} {return type --> void & others} {method_name} (parameters ...any number) { ... }
```

## **DATATYPE**:

1. **How bit-level datatype consumes memory.**

2. **Byte has a range of** `2^7 - 1` **to** `-2^7`. 
   - If exceeded, we have to cast explicitly, which may lead to overflow/underflow.

3. **Other data types have their range based on the memory they consume.**
   - `char` has a range of `0` to `65535`.

4. **Data type conversions:**
   - `byte` → `short` → `int` ← `long` (with casting)
   - `int` → `float` → `double`
   - `long` → `float` → `double`

5. **By default:**
   - Whole numbers are treated as `int`.
   - Decimal numbers are treated as `double`.

6. **Use** `f/F`, `d/D`, `l/L` **for proper identification of float, double, and long respectively.**

7. **If the value is out of range, it will roll over to the negative range (like rotation).**

8. **We can use large numbers like** `2_147_483_647`.

## **EXECUTION ORDER** :

**static block** --> **main method** --> **non-static block** (executed at every object creation)--> **non-static method** / **static method** (based on calling order).

**static block** --> initialize static variables or perform one-time actions when the class is loaded

**non -static block** --> called everytime when a new object created.

## **VARIABLE TYPES**:

### **1.Instance Variable**:
     different for each object.
     initialization not mandatory.(default value applied)
     created along with its object and destroyed along with its object.
### **2.Static variable**:
     only difference is static keyword & this is common for all object.
     if changed through one object it'll be reflected in all objects.
### **3.Local/ method variable**:
     initialization is mandatory.
     local to method/constructor/block. cant be accessed outside unless passed/ returned from a method.
     only default AM / final permitted.

if we need to call **NON-STATIC** in  **STATIC** method we need object.

## **AM**:

1. **public** --> visible to all classes.
2. **default** --> visible to classes under same package.
3. **protected** --> visible to only inherited classes.
4. **private** --> visible to only that class.

## **IMPORTANT KEYWORDS STATIC / FINAL / SUPER / THIS**:

### **STATIC : ( class, method , variable , block )**

1. static can be used in nested class (class inside class)
2. If we use static it belongs to the class.
3. We can't use static/final keyword with constructor.
4. we can't override static/final/private method.
5. static can be used in object creation in case of singleton pattern.(static Obj j = new Obj(); )

### **FINAL : (class , method , variable)**

1. final class can't be inherited.
2. final method can't be overridden
3. final variable can't be changed. variable cannot be modified after it has been initialized. (IMMUTABLE)
4. final variable can't have setter method.
5. final doesn't support block.

* final var --> Used for constants that are specific to "each object instance" and remain unchanged after initialization.
              (can be initialized for each object using a constructor. that's why this differs from static final)
* static final var --> Used for constants that are associated with the class itself and are shared across "all instances".

### **SUPER : (INHERITANCE)**

1. can't use inside super in static content/ overridden methods.
2. constructor call must be the first statement in constructor. (using super)

3. can be used to call variable of parent class (super.parentClassVariable .. both static & non-static) (but static should be accessed only with class name)
4. can be used to call method of parent class (super.parentClassMethod .. both static & non-static) (but static should be accessed only with class name)
    (If parent's method not implemented in child class no need to use super)
5. Can call parent's constructor with its param.(can be called only from child constructor)

### **THIS : (ENCAPSULATION)**

1. to refer instance(non-static) variable in current class. (but static should be accessed only with class name)
2. this(....params) to call current class matching constructor.
3. this can be used to refer "current class Instance". (use -> method chaining)
4. the previous one can be passed as argument to another method which have (Class objName) as Parameter.
5. this can be used to call current class method with matching params.
6. this can be used to call current class non static method.
7. constructor call must be the first statement in constructor. (using this)
8. cant use with static content.

## **INHERITANCE: (encourages polymorphism)**

* All classes by default extends Object class.
* types --> single, multilevel, hierarchical.
* 1 Java doesn't support multiple inheritance (DIAMOND PROBLEM) . solved with interface.
* In case of Interface method as private, static, default we can't override it.
* In normal classes , private cant be overrided. other methods can have equal or greater visibility.
  
   1.**protected** --> protected, default, public.
   2.**default** --> default, public.
   3.**public** --> public.

* child class object can access all methods from parent class.
* If A a = new C(); , In this, instance of C is created and with this we can access all methods of the classes (A, B, C)
* If Instance created for A only its method can be accessed. If instance created for B methods of A & B can be accessed.
* IF we create B b =(B) new A(); JVM will throw ClassCastException. B is a subset of A. so it will think B can't be initialized with A object.  
* A final class can't be subclassed.

### **CONSTRUCTOR**:

* Objective to initialize the value.
* name as class and should not have a return type.
* first line of constructor must be either super() or this();
* If we have explicit constructor with param in parent class, we need to use super with req params.
* default super() will be supplied to constructor.


## **POLYMORPHISM**:

* Types : compile-time polymorphism (overloading) | runtime polymorphism (overriding)
* overloading --> same method name & different parameters
* overriding --> class must be inherited, same method signature.
* final/static/private method cant be overrided.
* methods can have equal or greater visibility.

## **ENCAPSULATION**:

* Encapsulation is the mechanism of restricting access to certain components of an object and only exposing a controlled interface to the outside world.

### Benefits of Encapsulation

* **Data Hiding**: Only necessary information is exposed, while the implementation details remain hidden.This reduces the complexity of the code.
* **Security**: Protects an object’s state by controlling the access and modification of its fields.
* **Flexibility and Maintainability**: Changes to the encapsulated code can be made independently without affecting other parts of the program.
* **Improved Code Reusability**: Encapsulated code can be reused more easily as it is modular and self-contained.

### How to Achieve Encapsulation in Java

* **Private Fields**: Declare the class fields (variables) as private to restrict direct access from outside the class.

* **Public Methods**: Provide public getter and setter methods to allow controlled access and modification of the fields.


## **ABSTRACTION**:

### **ABSTRACT CLASS**:

* can't create object. To create object must be inherited. (interface dont have constructor)
* can have both abstract method and concrete method.
* static & final keyword never comes with abstract method.
* abstract methods must be declared not defined.
* abstract methods must be overriden.
* variable can't be abstract.
* can have static and non static block.
* If child class of abstract class is final, then you can't add new abstract method.


### **INTERFACE**:

* Can't have an object. To create object must be inherited. (interface dont have constructor)
* default methods only allowed in interfaces. If it is overridden , it's AM should be public.
* we can use private / static / default method to implement some logic in interface itself.
* all methods are public abstract.
* all variables are public static final.
* can't have static and non static block.

## **INNER CLASSES**:

*uses of inner class*:
1. A member inner class is a class defined within another class and behaves like a member of the outer class. (OuterClass.InnerClass ic =o.new InnerClass();)
2. A member class can be static also. (OuterClass.InnerClass ic =new OuterClass.InnerClass();)
3. class created inside method, can be accessed only within the method.
4. anonymous inner class used when you need a one-off implementation. 
(i.e interface anonymous inner class instantiated | that particular object reference used to call that method alone) 
5. For the above all unimplemented methods will be used for a single object creation using anonymous inner class.


## **INBUILD CLASSES** : 

* By default java.lang package will be imported (implicitly)
* Other packages we're importing it to use them in your code without needing to specify their full package.

1. classes like Math, String, StringBuffer, StringBuilder, all wrapper classes, Arrays.

## **EXCEPTION HANDLING** :

## **WRAPPER CLASSES** : 

1. Wrapper classes -> Byte, Short, Integer, Long, Float, Double, Character, Boolean. (wraps its respective primitive type)
2. Once an object is created, its value cannot be changed. (immutable).  [AtomicInteger, AtomicLong are mutable, suitable for multithreading]
3. Also these classes are also final and can't be subclassed.
4. wrapper classes cache frequently used values to improve performance. [Integer.valueOf(100) will return same object reference]
5. Conversion Utilities to convert between datatypes.
6. Wrapper classes can have null values, when we use Optional values, Database Interaction, working with Collections.

## **STRING MANIPULATION**:

**String** :           Immutable    not synchronized   faster
**StringBuilder**:       mutable    not synchronized   faster
**StringBuffer** :       mutable        synchronized   slower

## **ENUM**:

1. Constants defined this way make the code more readable,
  allow for compile-time checking, 
  document the list of accepted values upfront, 
  and avoid unexpected behavior due to invalid values being passed in.
2. Can only implements interface & its method should be overridden by each ENUM CONSTANTS, Can't extends any class
3. Enum can be used in switch statements
4. Enum should initialize values using constructor and have getter method to retrieve specific field from the enum. (i.e Season.SPRING.getDesc();)
5. enums are implicitly constants, and are seperated by "," and end with ";" and declared only in capitals

## **JAVA 8 FEATURES** :

## **Lambda Expressions** :

1. Lambda expression can be achieved only with functional interface. (i.e interface with @FuntionalInterface annotation / one abstract method interface)
2. FInterface f = () -> {"logic for that abstract method"};
3. Lambda expression can't be used for default/static method. 
4. The reference from lambda expression logic can be used to call default/static method.

### **IMPORTANT FUNCTIONAL INTERFACE***: Predicate/Function/Consumer/Supplier/BinaryOperator/UnaryOperator/BiConsumer

**Predicate**:

1. boolean-valued function of one argument.
2. methods - test, and, negate, or, isEqual, not (methods in Predicate)

test -> to check the output.
or -> 1st one is true, doesn't check the second one.
and -> check both predicate condition.
negate -> change the obtained value.
isEqual -> similar to .equals method of Object. 
[ predicate will be created Predicate p = Predicate.isEqual(obj1); , then check p.test(obj2); ]
not -> change in the predicate obj itself, not in outcome like negate.

**Function**:

1. function that accepts one argument and produces a result.
2. methods - apply, compose, andThen, identity

apply -> get the output.
compose -> add input( the output of the one function (f2) ) for another function (f1).  f1.compose(f2)
andThen -> add input( the output of the one function (f1) ) for another function (f2).  f1.andThen(f2)
identity ->  function that always returns its input argument.

**Consumer**:

1. accepts a single input argument and returns no result.
2. methods - accept, andThen

accept -> get the output
andThen -> add input( the output of the one function (f1) ) for another function (f2).  f1.andThen(f2)

**Supplier**:

1. a new or distinct result be returned each time the supplier is invoked.
2. has only one method get

**BinaryOperator**:

1. an operation upon two operands of the same type, produce result of same type
2. two methods minBy and maxBy
    minBy -> returns the lesser of two elements according to comparator.
    maxBy -> returns the greater of two elements according to comparator.

**UnaryOperator**:

1. single operand that produces result of same type.
2. one method (identity) -> always returns its input argument.

**BiConsumer**:

1. accepts two input arguments and returns no result.
2. methods -> accept, andThen

#### stream API important methods : filter, map, flatMap, reduce, forEach, collect, distinct, sorted, limit, skip.

**map** ->transforms each element of a stream into another object
**flatMap** -> transforms each element of a stream into zero or more elements

#### **NOTE** : 

1. list(fixed-size) created using Arrays.asList() you cannot add or remove elements from the list. ## UnsupportedOperationException
2.

Stream API
Default and Static Methods in Interfaces
Optional Class
New Date and Time API
Nashorn JavaScript Engine
Method References
Repeating and Type Annotations
Parallel Streams
Collectors API

**OBJECT CLASS METHODS** :

**MULTI THREADING**  (Synchronization):

**EXECUTOR FRAMEWORK** :

**FUTURE** :

## **IO STREAM** (**SERIALIZATION** (Serial ID for exception class & other such class)) :

## **GARBAGE COLLECTION** :

## **JAVA 17 feature** :

## **COLLECTION FRAMEWORK**:

The major concepts in Java Memory Management :

* JVM Memory Structure
* Working of Garbage Collector



![Collection Framework](https://github.com/Rath2601/core_java/blob/feature_java8/images/Collections-in-Java.png)

### **Array** :

1. container object that holds a fixed number of values of a single type.
2. Searching an array element is better compared to others.

### **List** :

1. Allow duplicates.
2. Insertion order is maintained.
3. Can have null values.
4. Allows elements to be accessed/inserted/deleted by their position.

### **ArrayList** : (equivalent to vector, but unsynchronized)

1. Resizable-array implementation of the List interface. (stores element in contiguous memory list)
2. can have duplicates and null value.
3. insetion order maintained.
4. methods - add, contains, forEach, get, indexOf, iterator, remove, reversed, set, sort, size, stream, toArray.
5. default capacity of ArrayList is 10, but we can specify in constructor while initializing.

**use**:
* fast random access O(1) but slow insertion and deletion.O(n) 
* traverse is bidirectional (by using listIterator)

### **LinkedList** : 

1. It stores the object and the pointers to next and previous nodes.(inefficient memory)

use:
* allows for fast insertion and deletion O(1) but slow random access.O(n)
* traverse is bidirectional (by using listIterator)

#### Difference between ArrayList and LinkedList
| Feature                     | Array                                    | ArrayList                                 | LinkedList                                   |
|-----------------------------|------------------------------------------|-------------------------------------------|----------------------------------------------|
| **Data Structure**          | Fixed-size array                         | Resizable array                           | Doubly linked list                           |
| **Size**                    | Fixed                                    | Dynamic                                   | Dynamic                                      |
| **Access Time (get)**       | O(1) - Fast                              | O(1) - Fast                               | O(n) - Slower                                |
| **Insertion (add)**         | N/A - Fixed size                         | O(1) - at end, O(n) - at index            | O(1) - Adding elements anywhere              |
| **Deletion (remove)**       | N/A - Fixed size                         | O(n) - Due to shifting elements           | O(1) - Easy to remove from the middle        |
| **Memory Usage**            | Least memory per element                 | Less memory per element                   | More memory per element (due to pointers)    |
| **Traversal**               | Fast with index access                   | Faster with index access (random access)  | Slower for large lists (sequential access)   |
| **Best Suited For**         | Fixed number of elements, fast access    | Frequent access to elements by index      | Frequent insertions/deletions at any position|
| **Resize Capability**       | Not resizable                            | Automatically resizes                     | Automatically resizes                        |
| **Implements**              | N/A                                      | List, RandomAccess, Cloneable, Serializable | List, Deque, Cloneable, Serializable       |
| **Use Case**                | Static data where size is known          | Good for read-heavy operations            | Good for write-heavy operations              |


| Feature                                | ArrayList                                              | Vector                                                |
|----------------------------------------|--------------------------------------------------------|-------------------------------------------------------|
| **Synchronization**                    | ArrayList is not synchronized.                         | Vector is synchronized.                               |
| **Growth Strategy**                    | ArrayList increments 50% of current array size if the number of elements exceeds its capacity. | Vector increments 100%, meaning it doubles the array size if the total number of elements exceeds its capacity. |
| **Legacy Status**                      | ArrayList is not a legacy class. It was introduced in JDK 1.2. | Vector is a legacy class.                             |
| **Performance**                        | ArrayList is fast because it is non-synchronized.       | Vector is slow because it is synchronized. In a multithreading environment, it holds other threads in a runnable or non-runnable state until the current thread releases the lock of the object. |
| **Traversal**                          | ArrayList uses the `Iterator` interface to traverse the elements. | Vector can use the `Iterator` interface or `Enumeration` interface to traverse the elements. |

### **STACK** :

1. class represents a last-in-first-out (LIFO) stack of objects.
2. it has the methods that supports LIFO. but it also have List and Vector methods as it extends them. (it behaves like list as well)
3. synchronized and a thread safe class.

#### uses:

1.undo mechanism (Text Editors), Backtracking Algorithms (Maze solvers), Depth-First Search (DFS), Navigation in Web Browsers (history management).

### **LIST NOTES** :

1. **List Interface**: A general-purpose ordered collection. It doesn't enforce FIFO or LIFO; it's up to the specific implementation or usage.
2. **Stack Class**: Implements LIFO behavior and extends Vector, which implements List.
3. **LinkedList Class**: Implements both List and Deque, allowing for both FIFO and LIFO behaviors depending on the methods used.
