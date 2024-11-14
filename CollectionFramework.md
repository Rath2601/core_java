## **COLLECTION FRAMEWORK**:

![Collection Framework](https://github.com/Rath2601/core_java/blob/feature_java8/images/Collections-in-Java.png)

---
### **Important Terms** :
#### **Marker Interface** :
It merely marks a class as capable of doing a specific task to the JRE.

1. **Serializable**  : marks a particular class can be serialized.
2. **Clonable**      : indicates a class permits cloning.
3. **Random access** : to mark list implementations that supports fast random access.

while serializing we should keep in mind that,
 * transient field should not be serialized  (e.g., sensitive data like passwords).
 * To prevent issues when deserializing an object, a class can declare a serialVersionUID to maintain compatibility.

**serialVersionUID** : long value uniquely identifies a version of a serializable class.
* How to Generate -> Automatic Generation by IDE / Manual assignment
* Minor Changes -> If change is backward compatible leave as is it.
  Major Changes -> increment the serialVersionUID to signal a new version.

---

### **Array** :

1. container object that holds a fixed number of values of a single type (homogeneous elements) stored in contiguous memory locations.
2. Directly retrieving an element by index O(1) (efficient random access to elements).
3. Array of a parent class can have its subclass elements but its not vice versa.
4. final -->  prevents reassigning the array reference but doesn’t make the array itself immutable.
```  
final int[] arr = new int[2]; 
arr = new int[2]; // this is not possible 
```
5. elements inside an array are always mutable.
```
arr[0] = 15;
arr[0] = 14; // array elements are mutable.
```

6. Array are allocated in heap memory.
7. Arrays are serializable by default. 
   

---
### **List** :

1. Allow duplicates.
2. Insertion order is maintained.
3. Can have null values.
4. Allows elements to be accessed/inserted/deleted by their position.
---
### **ArrayList** : (equivalent to vector, but unsynchronized)

1. Resizable-array implementation of the List interface. (stores element in contiguous memory list)
2. can have duplicates and null value.
3. insetion order maintained.
4. methods - add, contains, forEach, get, indexOf, iterator, remove, reversed, set, sort, size, stream, toArray.
5. default capacity of ArrayList is 10, but we can specify in constructor while initializing.

**use**:
* fast random access O(1) but slow insertion and deletion.O(n) 
* traverse is bidirectional (by using listIterator)
---
### **LinkedList** : 

1. It stores the object and the pointers to next and previous nodes.(inefficient memory)

use:
* allows for fast insertion and deletion O(1) but slow random access.O(n)
* traverse is bidirectional (by using listIterator)
---
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

---
| Feature                                | ArrayList                                              | Vector                                                |
|----------------------------------------|--------------------------------------------------------|-------------------------------------------------------|
| **Synchronization**                    | ArrayList is not synchronized.                         | Vector is synchronized.                               |
| **Growth Strategy**                    | ArrayList increments 50% of current array size if the number of elements exceeds its capacity. | Vector increments 100%, meaning it doubles the array size if the total number of elements exceeds its capacity. |
| **Legacy Status**                      | ArrayList is not a legacy class. It was introduced in JDK 1.2. | Vector is a legacy class.                             |
| **Performance**                        | ArrayList is fast because it is non-synchronized.       | Vector is slow because it is synchronized. In a multithreading environment, it holds other threads in a runnable or non-runnable state until the current thread releases the lock of the object. |
| **Traversal**                          | ArrayList uses the `Iterator` interface to traverse the elements. | Vector can use the `Iterator` interface or `Enumeration` interface to traverse the elements. |
---
### **STACK** :

1. class represents a last-in-first-out (LIFO) stack of objects.
2. it has the methods that supports LIFO. but it also have List and Vector methods as it extends them. (it behaves like list as well)
3. synchronized and a thread safe class.

#### uses:

1. Undo mechanism (Text Editors)
2. Backtracking Algorithms (Maze solvers)
3. Depth-First Search (DFS)
4. Navigation in Web Browsers (history management).
---
### **LIST NOTES** :

1. **List Interface**: A general-purpose ordered collection. It doesn't enforce FIFO or LIFO; it's up to the specific implementation or usage.
2. **Stack Class**: Implements LIFO behavior and extends Vector, which implements List.
3. **LinkedList Class**: Implements both List and Deque, allowing for both FIFO and LIFO behaviors depending on the methods used.
---
### **Set**

1. Does not allow duplicates.
2. Insertion order is not maintained.(generally)
   * HashSet (doesn't guarantee insertion order or any order)
   * TreeSet (sort based on natural sorting)
   * LinkedHashSet (preserve insertion order)
4. Can have one null value.
5. Sets do not maintain an index-based structure.primary purpose of a Set is to maintain unique elements without duplicates, not to store elements in a particular order.
6. Sets are designed for fast lookups (like contains()), additions, and removals based on the value itself rather than its position.
7. it is implemented with mathematical set logic.

classes -> HashSet, LinkedHashSet, TreeSet.
