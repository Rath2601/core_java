# **CORE JAVA NOTES**

## **SYNTAX**:

### Class Definition

```java

Top-level Class
---------------
{public/default} {abstract/final/strictfp} class ClassName

Notes:
- abstract OR final (not both)
- strictfp optional

Top-level Interface
-------------------
{public/default} interface InterfaceName

Notes:
- implicitly abstract
- cannot be final

Nested Class
------------
{public/protected/private/default} {abstract/final/static} class ClassName

```

### Variable Definition

#### 1. Correct Syntax Order

```java
[Access_Modifier] [static] [final/volatile] [transient] [Data_Type] [variable_name] = [value];
```
`final` and `volatile` are mutually exclusive. You cannot use both on the same variable.

---

#### 2. Invalid Variable Modifiers

The following keywords cannot be applied to variables:

| Keyword | Valid Usage |
|----------|-------------|
| `synchronized` | Methods / Blocks only |
| `abstract` | Classes / Interfaces / Methods only |
| `native` | Methods only |
| `strictfp` | Classes / Interfaces / Methods / Expressions only |

---

#### 3. Core Keywords Reference

##### `static`
Class-level shared state. Belongs to the class metadata, not object instances.

###### Native Java Serialization
- Skipped automatically.
- Represents class state, not instance state.

###### Jackson / Gson
- Not ignored by default.
- If a static field has a public getter, Jackson may serialize it into JSON.
- Use `@JsonIgnore` or custom visibility configuration to exclude it.

###### JPA / Hibernate / MyBatis
- Ignored for database mapping.
- ORMs map instance fields to database rows.

---

##### `final` 
Assigned exactly once. The primitive value or object reference cannot be changed after initialization.

###### Jackson / Gson
- Works well for immutable DTOs.
- Constructor injection using `@JsonCreator` is commonly used.

###### JPA / Hibernate
- Generally unsupported or problematic.
- JPA requires:
  - A no-argument constructor.
  - Reflection/proxy-based field population.
- `final` fields interfere with this lifecycle.

---

##### `volatile`
Guarantees visibility across threads. Reads and writes occur directly against main memory.

`volatile` does **not** provide: Atomicity & Mutual exclusion

Example:

```java
count++;
```
This remains a read-modify-write operation and can still suffer from race conditions.

---

##### `transient`
Marks a field to be skipped during state persistence/serialization.

###### behaviour on framework
Skip Native Java, Jackson, JPA, Gson Serializations. MyBatis has no effect


---

#### 4. Keyword Combinations

##### `static final` : Compile-time or runtime constants.

---

##### `static volatile` : Global thread-visible state.

Examples:
- Feature flags
- Shutdown indicators
- Shared JVM-wide status variables

---

##### `transient volatile` : Runtime-only, thread-visible state.
- Cache status flags
- Session runtime state
- In-memory synchronization indicators - Fields remain thread-safe for visibility but are excluded from serialization.

---

##### `transient final` : Runtime-only immutable fields.
- Assigned during object construction.
- Excluded from serialization mechanisms that honor `transient`.

---

#### 5. Redundant Combinations

##### `static transient`
- `static` fields are already excluded from native Java serialization.
- Adding `transient` provides no additional benefit.

---

##### `static transient volatile`
- `static` already excludes the field from native serialization.
- `volatile` still affects thread visibility.
- `transient` contributes no additional behavior.

---

### Method Definition

```java
{public/default/protected/private} {abstract/final --if needed} {static --if needed} {synchronized -- if needed} {return type --> void & others} {method_name} (parameters ...any number) { ... }

synchronized keyword --> used in static methods, instance methods, and code blocks.
- instance method  → locks on current object (this)
synchronized(this) { } --> explicitly locks the current object (this) 
                           but only for the specific block of code
- static method    → class-level locking
synchronized(Sample.class) { } --> explicitly locks on class level (this) 
                           but only for the specific block of code.
NOTE : this behaves equivalent to pessimistic lock in DB

```

## **DATATYPE**:

1. **How bit-level datatype consumes memory.**

| Type      | Bits            | Range |
| --------- | --------------- | ------------------ |
| `boolean` | JVM-dependent |       |
| `byte`    | 8 bits          |  -2⁷ → 2⁷-1        |
| `short`   | 16 bits         | -2¹⁵ → 2¹⁵-1       |
| `int`     | 32 bits         | -2³¹ → 2³¹-1       |
| `long`    | 64 bits         | -2⁶³ → 2⁶³-1       |
| `char`    | 16 bits         | 0 → 65535        |
| `float`   | 32 bits         | 7 decimal digits precision      |
| `double`  | 64 bits         | 15-16 decimal digits precision       |

2. Sign
* Signed   : byte, short, int, long, float, double
* Unsigned : char (0 → 65535)
* N/A      : boolean

3. Type Conversion
* **Implicit** (Widening)
  - byte → short → int → long → float → double
  - char → int → long → float → double

* **Explicit** (Narrowing)
  - double → float → long → int → short → byte
  - int → char
- **(bit truncation)** -> If the value is out of range, it will roll over to the negative range (like rotation).
- if float & double values are casted to int, fractional part is truncated & if value > Integer.MAX_VALUE → result is Integer.MAX_VALUE if value < Integer.MIN_VALUE → result is Integer.MIN_VALUE.**(range saturation)**

4. **By default:**
   - Whole numbers are treated as `int`.
   - Decimal numbers are treated as `double`.
5. **Use** `f/F`, `d/D`, `l/L` **for proper identification of float, double, and long respectively.**
6. **We can use large numbers like** `2_147_483_647`.

## **EXECUTION ORDER** :

* **static block** --> **main method** --> **non-static block** (executed at every object creation) --> constructor --> **non-static method** / **static method** (based on calling order).
* **static block** --> initialize static variables or perform one-time actions when the class is loaded 
* **non-static block** --> called everytime when a new object created.
* **static block & non-static block** called based on code order

* Static blocks of parent class execute first because the compiler loads parent class before child class.

## **VARIABLE TYPES**:

### **1.Instance Variable**:
     different for each object.
     initialization not mandatory.(default value applied)
     created along with its object and destroyed along with its object.
     Stored in Heap (inside object) - Until object is garbage collected
### **2.Static variable**:
     only difference is static keyword & this is common for all object.
     if changed through one object it'll be reflected in all objects.
     Stored in Method Area / Metaspace (class level) - Until class is unloaded
### **3.Local/ method variable**:
     initialization is mandatory.
     local to method/constructor/block. cant be accessed outside unless passed/ returned from a method.
     only default AM / final permitted.
     Stored in Stack Frame - Until method/block execution ends

if we need to call **NON-STATIC** in  **STATIC** method we need object.

## **AM**:

1. **public** --> visible to all classes.
2. **default** --> visible to classes under same package.
3. **protected** --> visible to only inherited classes.
4. **private** --> visible to only that class.

* Nested class can have any access modifier.

## **IMPORTANT KEYWORDS STATIC / FINAL / SUPER / THIS**:

### **STATIC : ( class, method , variable , block )**

* **Members & Memory**: Can be used with variables, methods, blocks, and nested classes; class metadata is in Metaspace (bytecode), but actual static variables reside on the Heap inside the java.lang.Class instance.
* **Where It Cannot Be Used**: Cannot be used with top-level classes, constructors (cannot be inherited (final) & meant to initialize new objects (static)), local variables inside methods (stack variables can't be class variables), or abstract methods (meant to be overridden).
* **No this or super**: Illegal because this and super require an object instance, but static contexts load at the class level before any object exists in memory.
* ** Method Hiding & Binding**: Static methods are hidden, not overridden; the compiler binds them at compile-time (invokestatic) based on the reference type, bypassing the runtime virtual method table just like private methods do.
* ** Nested vs. Inner Classes**: Static nested classes don't need an outer instance; non-static inner classes require an outer instance but can now contain static members natively (allowed since Java 16).
* **Singleton Pattern**: Uses a private static variable to hold the single global instance and a public static method to return it without creating new objects.
* **Resolution Mechanics**: Resolved at compile-time via Static Binding, where the compiler hardcodes the exact class method into the bytecode rather than waiting for runtime dynamic lookup.
* **Interfaces** : Interface fields are implicitly `public static final (constants)`, and interface static methods must be called directly using the Interface name, never via an implementing class reference.

### **FINAL : (class , method , variable)**


1. final class can't be inherited.
2. final method can't be overridden
3. final variable can't be changed. variable cannot be modified after it has been initialized. (IMMUTABLE) 
    - (final means reference cannot change. It does not guarantee immutability of the object.)   

```java 
final List<Integer> list = new ArrayList<>(); 
list.add(1); // allowed 
```

4. final variable can have setter method, but setters are meaningless since we can’t set new value
5. final can’t be used with static/non-static block. [ collection of statements executed at a specific point in the class lifecycle. You cannot "override" a block, you cannot "extend" a block, and you cannot "reassign" a block. ]
6. final var --> Used for constants that are specific to "each object instance" and remain unchanged after initialization.  (can be initialized for each object using a constructor, non-static block. that's why this differs from static final)
7. static final var --> Used for constants that are associated with the class itself and are shared across "all instances".

### **SUPER : (INHERITANCE)**

1. can't use super in static content (methods)
2. constructor call must be the first statement in constructor. (using super)
   (only one constructor can be called from a constructor)

4. can be used to call **variable** of parent class (super.parentClassVariable .. both static & non-static) (but static should be accessed only with class name)
5. can be used to call **method** of parent class (super.parentClassMethod .. both static & non-static) (but static should be accessed only with class name)
    (If parent's method not implemented in child class no need to use super)
6. Can call parent's constructor with its param.(can be called **only** from child constructor, Constructor chaining)

### **THIS : (ENCAPSULATION)**

1. to refer instance(non-static) variable in current class. (but static should be accessed only with class name)
2. this(....params) to call current class matching constructor.
3. this can be used to refer "current class Instance". (use -> method chaining)
4. the previous one can be passed as argument to another method which have (Class objName) as Parameter.
5. this can be used to call current class non static method with matching params.
6. constructor call must be the first statement in constructor. (using this)
7. **cant use with static content**.

## **INHERITANCE: (encourages polymorphism)**

* All classes by default extends Object class.
* types --> single, multilevel, hierarchical.
* 1 Java doesn't support multiple inheritance (DIAMOND PROBLEM). A class cannot extends multiple classes. solved with interface.
* In case of Interface method as private, static, default(package-private) we can't override it.
* In normal classes , private/static cant be overrided. other methods can have equal or greater visibility.
  
   1. **protected** --> protected,  public.
   2. **default** --> default, protected, public.
   3. **public** --> public.

* child class object can access all methods from parent class.
* If A a = new C(); , In this, instance of C is created and with this we can access all methods of the classes (A, B, C)
  [**but if the method is overriden in all the classes , then the last overriden method only can be called. To call precisely need to create object specifically.**]
* If Instance created for A only its method can be accessed. If instance created for B methods of A & B can be accessed.
* IF we create B b =(B) new A(); JVM will throw ClassCastException. B is a subset of A. so it will think B can't be initialized with A object.
* Upcasting A a = new B() || Downcasting B b = (B) a; (explicit, runtime check)
* `instanceof` operator used to check object type before casting avoids **ClassCastException** 
* A final class can't be made parent class.
* **Constructors** are NOT inherited, but are executed from parent → child using super().

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

* Encapsulation is useful when you don’t expose raw fields, but control access through behavior.

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

* can have any kind of variable like normal class
* can't create object. To create object must be inherited. (Abstract class have constructor)
* can have both abstract method and concrete method.
* static & final keyword never comes with abstract method.
* can't have default method like interface
* abstract methods must be declared not defined.
* abstract methods must be overriden. 
* variable can't be abstract. (since the syntax abstract itself doesn't apply for variable)
* can have static and non static block.

### **INTERFACE**:

* Can't have an object. To create object must be inherited. (interface dont have constructor)
* default methods only allowed in interfaces. If it is overridden , it's AM should be public.
* we can use private / static / default method to implement some logic in interface itself.
  * Default Methods: [**can be accessed with child object if not overrided**] [**else this can be accessed using interface.super.methodName**]
    * Backward Compatibility: Allow new methods to be added to interfaces without requiring existing implementing classes to override them, preventing code breakage.
    * Code Reusability: Provide common functionality directly in the interface, reducing code duplication across implementing classes.
  * Static Methods: [**can be accessed with class name**]
    * Utility Methods: Enable utility functions related to the interface, accessible without creating an instance of an implementing class, keeping relevant methods together.
  * Private Methods:
    Hold all the logic that can be used only by default/static methods inside interface.

* all methods are public abstract.
* all variables are public static final.
* can't have static and non static block.
* Anonymous inner class of interface can be created in subclass with all its method implementation for that particular object.

## **INNER CLASSES**:

| Inner Class Type | Spring Boot Relevance | Primary Use Case |
| :--- | :--- | :--- |
| **Static Nested** | **Critical** | Grouping DTOs, Builders (`@Builder`), custom configuration properties classes. |
| **Non-Static Inner** | **Situational** | Strictly bound domain logic (Aggregate Roots/Entities) where the child *must* mutate parent state. |
| **Anonymous** | **Legacy** | Heavy interface implementations with multiple methods. (Otherwise, use Lambdas). |
| **Method Local** | **Anti-Pattern** | Do not use. Refactor into private methods or dedicated beans. |

---

## **INBUILD CLASSES** : 

* By default java.lang package will be imported (implicitly)
* STATIC import can be made only for a particular class.
* Other packages we're importing it to use them in your code without needing to specify their full package.
* classes like Math, String, StringBuffer, StringBuilder, all wrapper classes, Arrays and etc.
---
### **STRING MANIPULATION**:

| Type           | Mutability | Thread Safety   | Performance |
|----------------|------------|-----------------|-------------|
| String         | Immutable  | Not synchronized | Faster      |
| StringBuilder  | Mutable    | Not synchronized | Faster      |
| StringBuffer   | Mutable    | Synchronized     | Slower      |

* String has two main concepts. **Immutability and String pool**
* **Immutability** -->  Once a String object is created, its value cannot be changed.
  ( Each modification (like concatenation, substring extraction, etc.) results in the creation of a new String object rather than modifying the existing one.)
* **String pool** --> The String pool is a special memory area in the Java heap where String literals are stored.
  (Purpose: It allows Java to reuse String literals to save memory.)
* **difference between == and equals() method**:
  ```java
  String s1 = "hello";
  String s2 = "hello";
  String s3 = new String("hello");

  // Case 1: Reference comparison only in object. Primitive value check
  System.out.println(s1 == s2); // true (same reference in string pool)
  System.out.println(s1 == s3); // false (different objects in memory)

  // Case 2: Value comparison. Not applicable for primitive type
  System.out.println(s1.equals(s2)); // true (content is the same)
  System.out.println(s1.equals(s3)); // true (content is the same)


  // another example
  String s1 = "Task";
  String s2 = "Task";
  String s3 = "Mask";
  s3 = "Flask"; // Mask is eligible for garbage collection

  ```
---
### **WRAPPER CLASSES** : 

1. Wrapper classes -> Byte, Short, Integer, Long, Float, Double, Character, Boolean. (wraps its respective primitive type)
2. Once an object is created, its value cannot be changed. **(immutable)**.  [AtomicInteger, AtomicLong are mutable, suitable for multithreading]
3. Also these classes are also final and can't be subclassed.
4. wrapper classes cache frequently used values to improve performance. [Integer.valueOf(100) will return same object reference]
5. **Conversion Utilities** to convert between datatypes.
6. Wrapper classes **can have null values**, when we use Optional values, Database Interaction, working with Collections.
7. All wrapper classes are **SERIALIZABLE BY DEFAULT**

**USES** :
1. Thread safe
2. Immutable object created
3. has conversion utilities (one datatype to another)
4. working with collection objects (as this can have null values)
---
### **ENUM**:

1. Constants defined this way make the code more readable,
  allow for compile-time checking, 
  document the list of accepted values upfront, 
  and avoid unexpected behavior due to invalid values being passed in.
2. Can only implements interface & its method should be overridden by each ENUM CONSTANTS, Can't extends any class
3. Enum can be used in switch statements
4. Enum should initialize values using constructor and have getter method to retrieve specific field from the enum. (i.e Season.SPRING.getDesc();)
5. enums are implicitly constants, and are seperated by "," and end with ";" and declared only in capitals
6. public enum can be in a public class

**USES**:
* define fixed set of constants
* Improve code readability work well with switch
* ensure type safety
  
---
### **EXCEPTION HANDLING** :

1. **try-catch-finally Structure**:
   - A `try` block must be followed by a `catch` block or a `finally` block.
   - A `catch` block must be paired with a `try` block.
   - `try` and `catch` can exist without a `finally` block.
   - The `finally` block is used to execute cleanup actions, such as closing database connections, regardless of whether an exception occurs or not.

2. **Exception Hierarchy**:
   - `Throwable` is the root class in the exception hierarchy, with `Error` and `Exception` as its main subclasses.
   - `Exception` is divided into two types:
     - **Checked exceptions** (compile-time exceptions).
     - **Unchecked exceptions** (runtime exceptions).

3. **Handling Checked Exceptions**:
   - If a checked exception is thrown (either due to inbuilt functionality or via `throw new IOException()`), it must be either:
     - Enclosed in a `try-catch` block, or
     - Declared in the method’s signature using the `throws` keyword to propagate it up the call stack.

4. **Handling Unchecked Exceptions**:
   - Unchecked exceptions do not need to be explicitly caught or declared in the `throws` clause.
   - **Developer’s Choice**: It is up to the developer to decide whether to handle them with a `try-catch` block or use the `throws` keyword.

5. **Multiple Catch Blocks**:
   - Multiple catch blocks can be used to handle different exception types.
   - They should be arranged in **hierarchical order**—the subclass-specific catch block should come before its parent class catch block (e.g., `IOException` before `Exception`).

6. **Exception Hierarchy for Checked Exceptions**:
   - For checked exceptions, this hierarchical order must be strictly followed to avoid compile-time errors (e.g., catching `Exception` before `IOException` is incorrect if both are expected).

7. **Custom Exceptions**:
   - Custom exceptions can be created by extending either `Exception` or `RuntimeException`.
     - Extending `Exception` makes it a **checked exception**.
     - Extending `RuntimeException` makes it an **unchecked exception**.
   - Custom exceptions behave like the exception type they extend (checked or unchecked).

8. Business rule failures are programming/runtime flow issues and hence we use RuntimeException (unchecked exception) 
    - We don’t want to force try-catch everywhere.
    - Handled globally (e.g., using global exception handler).

9. we use "private static final long serialVersionUID = 1L;" 
    - but its not required for modern applications<br>
    ```(Exception → @ControllerAdvice → ErrorResponse DTO → JSON (via Jackson) (doesn't use Java object serialization.)```

--- 
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

**EXECUTOR FRAMEWORK** :

**FUTURE** :

## **IO STREAM** (**SERIALIZATION** (Serial ID for exception class & other such class)) :

## **JAVA 17 feature** :
