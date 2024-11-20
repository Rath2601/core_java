package com.practice.string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

	public static int getUniqueCharacter(String s) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();

		char[] strChars = s.toCharArray();

		for (char c : strChars) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		
		System.out.println(map);

		for(int i=0 ; i<s.length(); i++) {
			if(map.get(s.charAt(i))==1) {
				return i;
			}
		}
		return -1;
	}

}

public class FirstUniqueCharacter {
	public static void main(String[] args) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

		String s = bufferedReader.readLine();

		int result = Result.getUniqueCharacter(s);
		System.out.println(result);

//		bufferedWriter.write(String.valueOf(result));
//		bufferedWriter.newLine();
//
//		bufferedReader.close();
//		bufferedWriter.close();
	}
}
