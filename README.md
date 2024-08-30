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

1.How bit level datatype consumes memory.
2. byte has range of 2^7-1 to -2^7 . If exceeds we have to cast explicitly and it may leads to overflow / underflow.
3. other datatypes their range based on memory they take. char has only range of 0 - 65535.
4. byte --> short --> int <-- long (but with casting) 
                      int --> float --> double 
                      long --> float --> double
5. by default whole number will be int & decimal number will be double.
6. we use f/F , d/D, l/L for proper identification.
7. If the value is out of range it will come to negative range (like rotation)
8. we can use large numbers like 2_147_483_647.

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

public --> visible to all classes.
default --> visible to classes under same package.
protected --> visible to only inherited classes.
private --> visible to only that class.
