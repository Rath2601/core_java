package com.practice.string;

public class RomanToInteger {

	static int change(String s) {

		char[] c = s.toCharArray();

		int num = 0;

		for (int i = 0; i <= c.length - 1; i++) {
			if (i > 0) {
				switch (c[i]) {
				case 'I':
					num = num + 1;
					break;
				case 'V':
					if (c[i - 1] == 'I') {
						num = num + 3;
					} else {
						num = num + 5;
					}
					break;
				case 'X':
					if (c[i - 1] == 'I') {
						num = num + 8;
					} else {
						num = num + 10;
					}
					break;
				case 'L':
					if (c[i - 1] == 'X') {
						num = num + 30;
					} else {
						num = num + 50;
					}
					break;
				case 'C':
					if (c[i - 1] == 'X') {
						num = num + 80;
					} else {
						num = num + 100;
					}
					break;
				case 'D':
					if (c[i - 1] == 'C') {
						num = num + 300;
					} else {
						num = num + 500;
					}
					break;
				case 'M':
					if (c[i - 1] == 'C') {
						num = num + 800;
					} else {
						num = num + 1000;
					}
					break;
				}
			} else {
				switch (c[0]) {
				case 'I':
					num = num + 1;
					break;
				case 'V':
					num = num + 5;
					break;
				case 'X':
					num = num + 10;
					break;
				case 'L':
					num = num + 50;
					break;
				case 'C':
					num = num + 100;
					break;
				case 'D':
					num = num + 500;
					break;
				case 'M':
					num = num + 1000;
					break;
				}
			}

		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println(change("MCMXCIV"));
	}
}
