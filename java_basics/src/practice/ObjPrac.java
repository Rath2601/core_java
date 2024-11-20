package practice;

import java.util.Objects;
import static java.lang.Math.*;

class Temp{
	String name;
	int age;
	
	@Override
	public int hashCode() {
		return Objects.hash(age, name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Temp other = (Temp) obj;
		return age == other.age && Objects.equals(name, other.name);
	}
}

public class ObjPrac {
public static void main(String[] args) {
	
	max(PI, TAU); //static import
	
	Temp t = new Temp();
	t.name = "rathna";
	t.age = 24;
	
	Temp t1 = new Temp();
	t1.name = "rathna";
	t1.age = 24;
	
	String s = "test";
	String s1 = "test";
	
	System.out.println(s.equals(s1));
	System.out.println(t.equals(t1));
}
}
