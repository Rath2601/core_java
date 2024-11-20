package codes.leetcode;

public class NumOfSteps {
	static int count =0;
	
	public static int numberOfSteps(int num) {
		
		if (num == 0) {
			return count;
		}
		if (num % 2 == 0) {
			num = num / 2;
			count++;
		}
		if (num % 2 != 0) {
			num = num - 1;
			count++;
		}
		return numberOfSteps(num);
	}
	
	public static void main(String[] args) {
		System.out.println(numberOfSteps(14));
	}
}
