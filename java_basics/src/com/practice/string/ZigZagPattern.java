package com.practice.string;

public class ZigZagPattern {
	public String convert(String s, int numRows) {
		char[] letters = s.toCharArray();
		String result = "";

		for (int i = 0; i < s.length();) {
			result = result + letters[i];
			i = i + numRows + 1;
		}

		for (int i = 1; i <= s.length();) {
			result = result + letters[i];
			i = i + numRows + 1;
		}

		return "";
	}

	public static void main(String[] args) {
		ZigZagPattern p = new ZigZagPattern();
		p.convert("PAYPALISHIRING", 3);
	}
}
// P(0) A(4) H(8) N(12) A(1) P(3) L(5) S(7) I(9) I(11) G(13) Y(2) I(6) R(10)

// 