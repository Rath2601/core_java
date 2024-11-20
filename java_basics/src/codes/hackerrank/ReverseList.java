package codes.hackerrank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ReverseList {
	public static List<Integer> reverseArray(List<Integer> a) {
		List<Integer> b = new ArrayList();
		for (int i = a.size() - 1; i >= 0; i--) {
			b.add(a.get(i));
		}
		return b;

	}

	public static void main(String[] args) throws IOException {
		List<Integer> a = new ArrayList();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
              reverseArray(a);
	}
}
