package com.practice.array;

import java.util.ArrayList;
import java.util.List;

public class RemoveElementFromArray {
	public static void main(String[] args) {
		int[] nums = { 0, 1, 2, 2, 3, 0, 4, 2 };
		
		int[] arr2 = {3,2,2,3};
		System.out.println(removeElement(nums, 2));
		
		System.out.println(removeElement(arr2, 3));
	}

	private static int removeElement(int[] nums, int a) {
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == a) {
				nums[i] = -1;
			}
		}
		
		for(int i = 0; i < nums.length; i++) {
			if(nums[i]!=-1) {
				list.add(nums[i]);
			}
		}

		return list.size();
	}
}
