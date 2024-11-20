package codes.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class Array2DSum {
	public static int hourglassSum(List<List<Integer>> arr) {
		int max = 0;
		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 5; j++) {
				int val = arr.get(i - 1).get(i - 1) + arr.get(i - 1).get(i) + arr.get(i - 1).get(i + 1)
						+ arr.get(i).get(i) + arr.get(i + 1).get(i - 1) + arr.get(i + 1).get(i)
						+ arr.get(i + 1).get(i + 1);
				max = Math.max(max, val);
			}
		}
		return max;

	}
	 static int val =0;
	    public static int simpleArraySum(List<Integer> ar) {
	      if(ar.size() ==0){
	        return val;
	      }
	      val +=  ar.get(ar.size()-1);
	     ar.remove(ar.size()-1);
	       return (simpleArraySum(ar));

	    }

	public static void main(String[] args) {
		List ar = new ArrayList<>();
		ar.add(10);
		ar.add(12);
		ar.add(14);
		ar.add(16);
		ar.add(18);
         simpleArraySum(ar);
	}
}
