package com.practice.string;

public class Reverse {
	static String reverse(String word) {
		if (word.length() < 1) {
			return word;
		}
		return  word.charAt(0) + reverse(word.substring(1));
	}

	public static void main(String[] args) {
		System.out.println(reverse("name"));
	}
}
