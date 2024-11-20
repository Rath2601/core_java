package codes.leetcode;

public class SumOfChar {
	public static void main(String[] args) {
		System.out.println(sum("a1b2c3"));
	}

	private static int sum(String string) {
		int result = 0;
		for(char c : string.toCharArray()) { // ['a','1','b','2','c','3']
			int a = c - '0';
			if(48< c && c <= 57) {
				result = a+result;
			}
		}
		return result;
	}
}
