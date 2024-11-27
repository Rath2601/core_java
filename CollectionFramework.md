## **COLLECTION FRAMEWORK**:

![Collection Framework](https://github.com/Rath2601/core_java/blob/main/images/Collections-in-Java.png)

---
## **underlying concepts**:

---
## **Important Terms** :

### **Iterable and Iterator**:
* **Iterable**: An interface that represents a collection that can be iterated over; it provides the iterator() method to return an Iterator.
* **Iterator**: An interface used to traverse a collection, offering methods like hasNext() and next() to access elements sequentially.

*why use iterable with collection class?*

* **Uniformity**: Implementing Iterable makes the collection **compatible with enhanced for-loops (for-each)** and Java's iteration utilities.
* **Separation of Concerns**: Iterable defines the capability to be iterated, while Iterator encapsulates the traversal logic, **keeping the iteration independent of the collection's internal structure**.

*note*

*  to use forEach loop, the instance should be array or it should implements iterable. **Can only iterate over an array or an instance of java.lang.Iterable**
*  map is not implemented collection which implements Iterable, then how we traverse over the elements ?

---
### **Marker Interface** :
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
### **Object class methods** :
we need to override hashCode() and equals() method for ,

* Correctness in collections (especially hash-based ones like HashMap, HashSet).
* Efficiency in object storage, searching, and retrieval.
* Proper customization of equality based on the fields you consider relevant for equality.

---
### **Comparable vs Comparator**
#### Key Points
1. **TreeSet/TreeMap and Ordering**  
- If neither `Comparable` (natural ordering) nor a `Comparator` is provided, a `ClassCastException` will occur when adding elements to a `TreeSet` or `TreeMap` that do not have a natural ordering.
- **TreeSet** and **TreeMap** require either one of `Comparable` or `Comparator` to determine the order of elements.
  
2. **Custom Class with TreeSet/TreeMap**
- If you define a custom class and use it in a `TreeSet` or `TreeMap`, it **must implement `Comparable`** or be paired with a **`Comparator`**.
- Without either, adding elements will throw a `ClassCastException`.
  
3. **Collections.sort() vs Arrays.sort()**
- Both `Collections.sort()` and `Arrays.sort()` can use either **`Comparable`** (for default natural ordering) or **`Comparator`** (for custom ordering).
- If a `Comparator` is provided, it takes **precedence** over the `Comparable`.

```java
Collections.sort(list, Comparator.reverseOrder()); // Custom ordering
System.out.println(list); // Output: [8, 5, 2]

Integer[] array = {5, 2, 8};
Arrays.sort(array); // Natural ordering
Arrays.sort(array, Comparator.reverseOrder()); // Custom ordering
```
4. **Collections.sort() is designed for List** implementations and uses **TimSort algorithm**.
5. **Arrays.sort() is designed for arrays** and uses:
   * Dual-pivot quicksort for primitive types (e.g., int[], char[]).
   * TimSort for reference types (e.g., Integer[]).
6. **Comparable** allows multiple sorting logics within the compareTo() method itself.- **fixed as part of the class's natural order**
7. **Comparator** allows defining multiple sorting logics in separate classes- **apply different Comparators as needed, without altering the original class or its natural ordering.**
8. If **compareTo() returns 0 (indicating equality), equals() should also return true** to maintain consistency.
   * Inconsistent compareTo() and equals() can cause issues in collections like TreeSet or TreeMap, leading to unpredictable behavior and violations of the Set or Map contract.
   * Always override equals() and hashCode() along with compareTo() when using a class in sorted collections.

| Feature                     | Comparable                                    | Comparator                                      |
|-----------------------------|-----------------------------------------------|-------------------------------------------------|
| **Definition**               | A class implements Comparable to define its own natural order using compareTo(). | A separate comparator class defines custom sorting logic via compare(). |
| **Default Sorting Logic**    | Natural ordering is fixed and cannot be changed. | Custom sorting logic can be defined dynamically using multiple comparators. |
| **Usage**                    | Typically used when you want a class to define its own ordering. | Used when sorting logic is defined externally or multiple sorting criteria are needed. |
| **Application**              | compareTo() is applied directly on the class. | You can create multiple comparators and apply them as needed. |
| **Order Precedence**         | N/A                                           | Comparator takes precedence over Comparable. |
| **Sorting Mechanism**        | Sorting logic is embedded in the class itself. | Allows for flexible sorting based on different criteria. |
| **Consistency with equals()**| Should be consistent (if compareTo() returns 0, equals() should return true). | Must also ensure consistency with equals() for predictable behavior in sorted collections. |
| **Example**                  | `class Patient implements Comparable<Patient>` | `Comparator<Patient> genderComparator = new GenderComparator();` |

---
### **Fail-Fast vs. Fail-Safe Mechanism**

| **Aspect**              | **Fail-Fast**                                                                                  | **Fail-Safe**                                                                                  |
|--------------------------|-----------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| **Definition**           | Throws `ConcurrentModificationException` when the collection is modified during iteration.     | Iterates over a snapshot or copy of the collection, avoiding exceptions during modifications. |
| **How It Works**         | Tracks structural modifications using an internal counter called `modCount`.                  | Operates on a snapshot or a separate copy of the collection.                                  |
| **modCount Explanation** | `modCount` increments with every structural modification (e.g., `add`, `remove`). The iterator checks `modCount` during iteration to detect changes. | Snapshot is independent of `modCount` and modifications, ensuring consistent iteration.      |
| **Examples of Behavior** | Throws `ConcurrentModificationException` if `modCount` changes during iteration.              | No exception; ongoing iteration is unaffected by live modifications.                         |
| **Collections Implementing** | `ArrayList`, `HashMap`, `HashSet`, `TreeSet` from `java.util`.                                | `CopyOnWriteArrayList`, `ConcurrentHashMap`, `ConcurrentSkipListMap` from `java.util.concurrent`. |
| **Impact of Modifications** | Any modification to the collection (other than through the iterator) results in an exception. | Modifications to the live collection do not affect the snapshot or iteration.                |
| **Performance**          | Faster, as it works on the live collection without creating a snapshot.                       | Slower, as it creates a snapshot or uses additional memory for iteration.                    |
| **Thread Safety**        | Not thread-safe; designed for single-threaded environments.                                   | Thread-safe; designed for concurrent environments.                                            |
| **Usage**                | Suitable for non-concurrent collections or where modifications during iteration are infrequent. | Ideal for concurrent collections where modifications are frequent during iteration.           |
| **Memory Overhead**      | No additional memory required for iteration.                                                  | Requires extra memory for creating a snapshot or copying the collection.                     |
| **Examples**             | `ArrayList`, `HashMap`: Modifications during iteration throw an exception.                    | `CopyOnWriteArrayList`, `ConcurrentHashMap`: Iteration works on a snapshot, no exceptions.    |
| **Iterator Behavior**    | Iterators work directly on the live collection.                                               | Iterators work on a snapshot or a copy of the original collection.                           |
| **Application Scenarios**| Used in single-threaded environments for better performance.                                  | Used in multi-threaded environments to handle concurrent modifications safely.               |
| **Key Limitation**       | Not suitable for concurrent modifications; prone to `ConcurrentModificationException`.        | May consume significant memory and is slower due to snapshot creation.                       |

---

## **Array** :

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
## **List** :

1. Allow duplicates.
2. Insertion order is maintained.
3. Can have null values.
4. Allows elements to be accessed/inserted/deleted by their position.
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
| **Resize Capability**       | Not resizable                            | Automatically resizes. default capacity(10)                   | Automatically resizes                        |
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
### **Points Related to Immutable Lists and Objects**

- **We can't add any new elements**. *(Applies to fixed-size mutable lists like `Arrays.asList()` but not truly immutable lists.)*
- **All unmodifiable lists are thread-safe**.
- **We can use `set(index, element)` to modify elements in fixed-size mutable lists** like `Arrays.asList()`.  
  - However, this does **not work** in `List.of()` or `Collections.unmodifiableList()`.
- **We can manipulate the elements if mutable elements are inside the list**.
- **If we try to manipulate immutable objects in the list**, it creates new objects, which must be stored in a new list.
- **Immutable lists can be created using**:
  - `Arrays.asList()`
  - `Collections.unmodifiableList()`
  - `List.of()`

## Steps to Create an Immutable Object
- **Declare the class as `final`** to prevent inheritance.
- **Make all fields `private final`** and initialize them only through the constructor.
- **Ensure mutable references are immutable** using defensive copying.  
```java
    private final Date eventDate; //final field

    public ImmutableEvent(String eventName, Date eventDate) {
        this.eventName = eventName;
        this.eventDate = new Date(eventDate.getTime()); // Clone mutable object
    }
    public Date getEventDate() {
        return new Date(eventDate.getTime()); // Return a copy to preserve immutability
    }
```
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
#### **LIST NOTES** :

1. **List Interface**: A general-purpose ordered collection. It doesn't enforce FIFO or LIFO; it's up to the specific implementation or usage.
2. **Stack Class**: Implements LIFO behavior and extends Vector, which implements List.
3. **LinkedList Class**: Implements both List and Deque, allowing for both FIFO and LIFO behaviors depending on the methods used.
---
## **Set**

1. Does not allow duplicates.
2. Insertion order is not maintained.(generally)
   * HashSet (doesn't guarantee insertion order or any order)
   * TreeSet (sort based on natural sorting)
   * LinkedHashSet (preserve insertion order)
4. Can have one null value. (generally)
5. Sets do not maintain an index-based structure.primary purpose of a Set is to maintain unique elements without duplicates, not to store elements in a particular order.
6. Sets are designed for fast lookups (like contains()), additions, and removals **based on the value** itself **rather than its position**.
7. it is implemented with **mathematical set** logic.Supports set operations like union (addAll), intersection (retainAll), and difference (removeAll).

CLASSES -> **HashSet**, **LinkedHashSet**, **TreeSet**.

| Feature                 | `HashSet`                             | `LinkedHashSet`                        | `TreeSet`                               |
|-------------------------|---------------------------------------|----------------------------------------|----------------------------------------|
| **Order of Elements**    | No guaranteed order                  | Maintains insertion order              | Sorted order (natural or custom)       |
| **Null Elements**        | Allows one `null` element             | Allows one `null` element              | Does not allow `null` elements         |
| **Underlying Structure** | Hash table                            | Hash table with a linked list          | Red-Black Tree                         |
| **Performance**          | O(1) average for `add`, `remove`, `contains` | Slightly slower than `HashSet` due to linked list | O(log n) for `add`, `remove`, `contains` |
| **Thread-Safety**        | Not synchronized                     | Not synchronized                       | Not synchronized                       |
| **Duplicates**           | Does not allow duplicates             | Does not allow duplicates              | Does not allow duplicates              |
| **Usage**                | Best for unordered sets with fast lookups | Best for ordered sets with fast lookups | Best for sorted sets                   |


---
## **QUEUE**

1.FIFO 
2.Insertion order is not preserved.
3.Can have duplicate values.


| **Feature**               | **`PriorityQueue`**                               | **`ArrayDeque`**                              | **`LinkedList`**                             |
|---------------------------|--------------------------------------------------|-----------------------------------------------|---------------------------------------------|
| **Interface Implemented**  | Implements `Queue`, `Deque` (not `List`)         | Implements `Deque`, `Queue`                   | Implements `Deque`, `Queue`, `List`          |
| **Order of Elements**      | Elements ordered according to priority (natural order or custom comparator) | Elements are ordered in the order they are added | Elements ordered in the order they are added (FIFO) or LIFO based on usage |
| **Accessing Elements**     | Only the head (smallest element in a min-heap) is accessible efficiently | Allows access to both ends of the queue efficiently | Allows access to both ends efficiently and supports random access (by index) |
| **Performance (Insertion/Deletion)** | **O(log n)** for insertion/removal of elements | **O(1)** for adding/removing elements at both ends | **O(1)** for adding/removing elements at both ends, but **O(n)** for accessing/removing elements by index |
| **Memory Usage**           | Efficient for heap-based storage but with some overhead | More memory-efficient than `LinkedList` because it uses an array | Higher memory usage due to node-based structure (pointers for next/prev) |
| **Thread-Safety**          | Not thread-safe                                   | Not thread-safe                               | Not thread-safe                             |
| **Internal Structure**     | Backed by a **heap** (usually a binary heap)     | Backed by an **array** (dynamically resized)   | Backed by a **doubly linked list** (nodes with next/previous pointers) |
| **Random Access**          | **Not supported** (does not allow index-based access) | **Not supported** (does not allow index-based access) | **Supported** (allows random access via index) |
| **Use Case**               | Used when elements need to be processed by priority (min-heap by default) | Used for fast, efficient queue and deque operations | Used for general-purpose queue, deque, and list operations, including random access |
| **Null Elements**          | Does **not** allow `null` elements                | Can allow `null` elements                     | Can allow `null` elements                   |
| **Resizing**               | Does not resize dynamically (fixed size heap)    | Resizes dynamically when needed               | Resizes dynamically when needed             |
| **Ordering Guarantee**     | Guarantees **priority-based order** (min-heap or max-heap) | Does **not** guarantee any specific order apart from insertion order | Guarantees **insertion order** (FIFO or LIFO depending on usage) |

---
## **Map**
### **Overview**
- A `Map` is a collection of objects that map **keys** to **values**.
- **Key Characteristics**:
  - **Key-Value Mapping**: Each key maps to exactly one value.
  - **No Duplicate Keys**: Keys must be unique.
  - **Replaces `Dictionary`**: `Map` takes the place of the older `Dictionary` abstract class.
  - Allows a map's contents to be viewed as:
    - A **Set** of keys.
    - A **Collection** of values.
    - A **Set** of key-value mappings.
---
### **Order Behavior in Common Implementations**
1. **HashMap**:
   - Does not maintain order.
   - Uses hashing for storage and retrieval.
2. **TreeMap**:
   - Maintains keys in **natural order**.
   - Does not allow `null` keys (throws `NullPointerException`).
3. **LinkedHashMap**:
   - Maintains keys in **insertion order**.
---
### **Hashing Considerations** -[This is common for all Hashing based collection classes]
- All hashing-based map implementations should override:
  - `hashCode` method.
  - `equals` method.
    
- **Mutable Objects as Keys**:
  - Should be avoided as they can cause unexpected behavior.
  - Example:
    ```java
    Map<StringBuilder, String> map = new HashMap<>();
    StringBuilder key = new StringBuilder("example");
    map.put(key, "value");
    key.append("changed"); // Modifies the key's state
    System.out.println(map.get(key)); // Might return null
    ```
---
### **Self-Referential Maps**
- **A Map Cannot Contain Itself as a Key**:
  - Causes `StackOverflowError`.
- **A Map Can Contain Itself as a Value**:
  - Allowed but must be used cautiously to avoid issues in:
    - `clone()`
    - `equals()`
    - `hashCode()`
    - `toString()`
---
### **Unsupported Operations**
- If a map does not allow modification:
  - Destructive methods (`put`, `remove`, `clear`) throw `UnsupportedOperationException`.
  - Optional behavior: Even if the operation has no effect (e.g., `putAll` on an empty map), the exception **may** still be thrown.
---
### **Standard Constructors**
1. **No-args Constructor**:
   - Creates an empty map.
   - Example:
     ```java
     Map<String, String> emptyMap = new HashMap<>();
     ```
2. **Copy Constructor**:
   - Creates a new map by copying the key-value pairs from an existing map.
   - Example:
     ```java
     Map<String, String> original = new HashMap<>();
     original.put("A", "Apple");
     Map<String, String> copy = new HashMap<>(original);
     ```
---
### **Behavior of Keys and Values**
- **Restrictions**:
  - Some map implementations (e.g., `Hashtable`) do not allow:
    - `null` keys or values.
    - Keys/values of incompatible types (`ClassCastException`).
- **Behavior**:
  - May throw exceptions like:
    - `NullPointerException`
    - `ClassCastException`
  - Query methods (`containsKey`, `containsValue`) may:
    - Return `false` for ineligible keys/values.
    - Throw exceptions in some implementations.
---
### **Relation to `equals` and `hashCode`**
- Many methods in `Map` rely on `equals` and `hashCode`:
  - Example: `containsKey` checks if a key exists.
    - If `key == null`: Looks for a `null` key.
    - Otherwise: Uses `key.equals(k)` to match.
- **Optimizations**:
  - Hashing implementations (e.g., `HashMap`) compare hash codes first before invoking `equals`.
---
### **Unmodifiable Maps**
- Created using:
  - `Map.of`
  - `Map.ofEntries`
  - `Map.copyOf`
- **Characteristics**:
  - **Immutable**: Modifying operations (`put`, `remove`) throw `UnsupportedOperationException`.
    ```
    Map test = Map.of(1, "Rathna",2,"Sathya");
    test.put(2, "Keerthi");
    ```
  - **Disallow `null` keys and values** (`NullPointerException`).
    ```
    Map test = Map.of(1, "Rathna",2,"Sathya", null,null); // NullPointerException
    ```
  - **Reject duplicate keys at creation** (`IllegalArgumentException`).
    ```
    Map test = Map.of(1, "Rathna",2,"Sathya", 2,"Rathna"); //IllegalArgumentException
    ```
  - **Iteration order is unspecified.**
  - Value-based:
    - Treat logically equal instances as interchangeable.(logically equal map, but distinct)
    - Avoid using them for synchronization.
      [**If synchronization is required, a synchronized or concurrent map is a better choice because they are explicitly designed for multi-threaded environments.**]
---
### **Serialization**
- Maps created via `Map.of`, `Map.ofEntries`, or `Map.copyOf` are **serializable** if all keys and values are serializable.
---
### **Key Notes on Behavior**
1. **Poor `hashCode` or `equals` Implementation**:
   - Leads to inconsistent behavior (e.g., `containsKey` may fail).
2. **Recursive Traversal in Self-Referential Maps**:
   - Methods like `clone`, `equals`, `hashCode`, and `toString` may fail.
---
| **Aspect**                | **HashMap**                                 | **TreeMap**                                | **LinkedHashMap**                           |
|---------------------------|---------------------------------------------|--------------------------------------------|--------------------------------------------|
| **Ordering**               | No ordering (unordered)                    | Ordered according to **natural order** of keys or a custom comparator | Maintains insertion order of elements |
| **Null Keys/Values**       | Allows one `null` key and multiple `null` values | Does not allow `null` keys (throws `NullPointerException`), but allows `null` values | Allows one `null` key and multiple `null` values |
| **Performance (Time Complexity)** | O(1) for basic operations (put, get)    | O(log n) for most operations (put, get, remove) | O(1) for basic operations (put, get)       |
| **Order of Iteration**     | Unspecified                                 | Sorted order (ascending order of keys)     | Iteration order is the same as insertion order |
| **Comparator**             | Does not require a comparator for ordering | Requires a comparator for custom ordering or uses natural order | No comparator, but maintains insertion order |
| **Thread Safety**          | Not thread-safe (use `Collections.synchronizedMap()` for synchronization) | Not thread-safe (use `Collections.synchronizedMap()` for synchronization) | Not thread-safe (use `Collections.synchronizedMap()` for synchronization) |
| **Usage**                  | Ideal for general-purpose maps that don't require ordering | Ideal for maps where sorting or range queries are needed | Ideal when insertion order needs to be preserved |
| **Space Overhead**         | Less overhead                               | Higher overhead due to tree structure      | Higher overhead due to maintaining insertion order |
| **Iterator**               | Allows `null` key, but iterators are not ordered | Sorted iterators based on key order        | Iterators maintain the insertion order of elements |
| **KeySet**                  | Unordered key set                          | Sorted key set                             | Key set is in insertion order              |


