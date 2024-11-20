package practice;

import java.lang.ref.WeakReference;

public class WrapperDemo {
	public static void main(String[] args) {

//		int i = 10;
//
//		Integer i1 = i; // P-O

//		String s = String.valueOf(i1); // O-S

//		Integer i2 = Integer.valueOf(s); // S-O
//
//		int i3 = i2.intValue(); // O-P
//
//		String s2 = String.valueOf(i3); // P-S
//
//		int i4 = Integer.parseInt(s2); // S-P

//		System.out.println(i2);
//		System.out.println(i3);
//		System.out.println(i4);
//
//		Long l = 10L;
//		WeakReference<Long> weakRef = new WeakReference<>(l);
//
//		System.out.println("Value: " + l);
//
//		l = 20L; // Now '10L' is no longer strongly referenced
//
//		System.out.println("Value after reassignment: " + l);
//
//		// Request garbage collection
//		System.gc();
//
//		if (weakRef.get() == null) {
//			System.out.println("10L has been garbage collected.");
//		} else {
//			System.out.println("10L is still in memory.");
//		}

		String s = "sam";// s points to "sam"
		s = s + " anderson"; // s now points to a new object "sam anderson"
		System.out.println(s);

	}
}
