package com.practice.array;

import java.util.Arrays;
import java.util.List;

public class CoinDenomination {
	public static void main(String[] args) {

		System.out.println(getWays(4, Arrays.asList(1l, 2l, 3l)));
	}

	public static long getWays(int n, List<Long> c) {

		long output = 0l;
		if (n == 0 || c.size() == 0) {
			return output;
		}

		Long val = 0l;

		for (int i =0; i<c.size(); i++) {
			
			while (val < n) {
				val = c.get(i) + val;
				if (val == n) {
					output = output + 1;
					break;
				}
			}
			
			
			
			val = 0l;
		}
		return output;

	}
}
