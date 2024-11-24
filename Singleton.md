## **singleton pattern with its potential issues**

### **Singleton Steps**:
1. **Private constructor**: Prevent direct instantiation.  
2. **Static instance & method**: For global access.  
3. **Double-checked locking**: Synchronized block with `null` checks for thread safety.  
4. **Volatile instance**: Ensures visibility across threads.
---
### **Breaches and Fixes**:
1. **Reflection API**:  
   - **Reason**: Access private constructor.  
   - **Fix**: Throw exception in constructor if the instance exists.
2. **Cloning**:  
   - **Reason**: Cloneable creates new instance.  
   - **Fix**: Override `clone()` to throw `CloneNotSupportedException`.
3. **Serialization**:  
   - **Reason**: Deserialization creates new instance.  
   - **Fix**: Use `readResolve()` to return the same instance.
---
```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VolatilePrac {
	//there are three ways to breach a double checked singleton pattern
	
	//Reflection in Java allows access to private fields, methods, and constructors.
	//By using reflection, one can instantiate a Singleton class even if the constructor is marked as private.
	
	// If a Singleton class implements Cloneable and does not override the clone() method,
	// Java will allow the object to be cloned, which can break the Singleton pattern.
	
	//When a singleton class is serialized and then deserialized, 
	//the deserialization process can create a new instance of the class, bypassing the Singleton pattern.
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, CloneNotSupportedException, FileNotFoundException, IOException, ClassNotFoundException {
		ExecutorService es = Executors.newFixedThreadPool(2);

		es.execute(() -> Singleton.getSingletonInstance());
		es.execute(() -> Singleton.getSingletonInstance());
		
		Singleton singleton = Singleton.getSingletonInstance();

		// Breaching the Singleton using Reflection
		Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
		constructor.setAccessible(true); // Make the private constructor accessible
		Singleton singleton2 = constructor.newInstance();
		
		// using clonable 
//		Singleton singletonClone = (Singleton) singleton.clone();
		
		//using serializable  //.ser means it has a serializable object
		
		
		 try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("singleton.ser"))) {
	            out.writeObject(singleton);
	        }

	        // Deserialize the Singleton instance (this can create a new instance)
	        Singleton singleton3;
	        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("singleton.ser"))) {
	            singleton3 = (Singleton) in.readObject();
	        }
	     System.out.println(singleton==singleton3);
	}
}

class Singleton implements Cloneable, Serializable {
	private static volatile Singleton obj;

	private Singleton() {
		System.out.println("obj initiated");
	}

	public static Singleton getSingletonInstance() {
		if (obj == null) {
			synchronized (Singleton.class) {
				if (obj == null) {
					obj = new Singleton();
				}
				return obj;
			}
		}
		return obj;
	}
	
	 private Object readResolve() {
	        return obj;  // Return the existing singleton instance
	    }

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
```
