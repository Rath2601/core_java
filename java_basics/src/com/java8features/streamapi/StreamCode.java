package com.java8features.streamapi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCode {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("suthan", "rathna", "Naveen", "arun", "suthan", "", null);

		List<StudClass> students = Arrays.asList(new StudClass(1l, "suthan", "chennai", "male", 0.0),
				new StudClass(2l, "sweety", "karaikudi", "female", 0.0),
				new StudClass(3l, "priya", "chennai", "female", 0.0),
				new StudClass(4l, "sathish", "chennai", "male", 0.0),
				new StudClass(5l, "nishanth", "bangalore", "male", 0.0),
				new StudClass(6l, "parthiban", "chennai", "female", 0.0));

		Predicate<String> p1 = e -> {
			return (Objects.nonNull(e)) && (e.length() > 5) && (Objects.equals(e.toLowerCase(), e));
		};

		names = names.stream().filter(p1).collect(Collectors.toList()); // filter

		Function<StudClass, StudClass> f1 = e -> {
			if (e.getLocation().equals("chennai")) {
				e.setSalary(30000d);
			} else if (e.getLocation().equals("bangalore")) {
				e.setSalary(50000d);
			} else {
				e.setSalary(25000d);
			}
			return e;
		};

		students = students.stream().map(f1).toList(); // map

		HashMap<String, Integer> voterList = new HashMap<>();
		voterList.put("male", 0);
		voterList.put("female", 0);

		Consumer<String> c1 = e -> {
			voterList.put(e, voterList.get(e) + 1);
		};
//
//		students.stream().flatMap(student -> Stream.of(student.getGender())).map(f2).collect(Collectors.toList());

		List<String> locations = students.stream().flatMap(student -> Stream.of(student.getLocation())).distinct().toList();
		
		 students.stream().flatMap(student -> Stream.of(student.getGender())).forEach(c1);
		
//		 students.stream().reduce(null)
		 
		System.out.println(names);
		System.out.println(students);
		
		System.out.println(locations);
		
		System.out.println(voterList);
		
		System.out.println(students);
	}
}
