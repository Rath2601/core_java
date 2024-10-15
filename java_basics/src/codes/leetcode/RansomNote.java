package codes.leetcode;

import java.util.HashMap;


public class RansomNote {
	public static boolean canConstruct(String ransomNote, String magazine) {
		char[] charsSpace = magazine.toCharArray();
		HashMap<Character,Integer> hm = new HashMap<Character,Integer>();
		for(int i=0; i<charsSpace.length; i++) {
			hm.put(charsSpace[i],i);
		}
		System.out.println(hm);
		
		
		char[] ransomNoteSpace = ransomNote.toCharArray();
		return magazine.contains(ransomNote) ? true : false;
	}
	public static void main(String[] args) {
		System.out.println(canConstruct("ba", "aab"));
	}
}

