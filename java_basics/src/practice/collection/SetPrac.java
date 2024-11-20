package practice.collection;

import java.util.TreeSet;

public class SetPrac {
	public static void main(String[] args) {
		TreeSet<Integer> hs = new TreeSet<>();
		hs.add(25);
		hs.add(28);
		hs.add(26);
		hs.add(14);
		
		hs.add(1);
		
		hs.contains(18);

		System.out.println(hs);
	}
}
