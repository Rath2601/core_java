# **CORE JAVA NOTES**

## **SYNTAX**:

### Class Definition

```java
{public/default} {abstract/final --if needed} class/interface {class_name} {....... }

abstract/final --> not needed for interface

### Variable Definition

{public/default/protected/private} {transient - if needed} {volatile/final --if needed} {static --if needed} {datatype} {variable_name} = {value} ;

transient --> marking a variable as transient indicates that it should not be serialized.
             serialization --> persistence, networking, distributed systems, caching, JMS & message brokers.
volatile  --> to indicate that a variable's value will be modified by different threads. (change in one thread is visible to all threads)

### Method Definition

{public/default/protected/private} {abstract/final --if needed} {static --if needed} {synchronized -- if needed} {return type --> void & others} {method_name} (parameters ...any number) { ... }
