package com.practice.pattern;

public class TrianglePattern {
	static void pattern(int num){
		for(int i=0 ; i<=num; i++) {
			for(int k=num-1; k>=i; k--) {
				System.out.print(" ");
			}
			for(int j=1; j<=i; j++) {
				System.out.print(" *");
			}
			
			System.out.println();
		}
	}
public static void main(String[] args) {
	pattern(5);
}
}
