package com.example.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsExample {

	public static void main(String[] args) {
		StreamsExample ex = new StreamsExample();
		List<Integer> nums = Arrays.asList(1,3,2,9,4);
		List<String> list = Arrays.asList("apple", "banana", "apple");
//    	ex.sortFunc(list);
		ex.filterFunc();
//		ex.mapfunc(nums);
//		ex.distinct();
//		ex.function(list);
//		ex.even(nums);
//		ex.reduce(nums);
//		ex.gt10(nums);
//		ex.sortByLength(list);
//    	ex.allPositive(nums);
//		ex.max(nums);
//		ex.highest3(nums);
//		ex.duplicate(nums);
//		ex.charFrequency("aakanksha");
//		ex.reverseString();
//		ex.duplicate2(list);
//		ex.repeatedChar();
//		ex.areAnagrams();
//		ex.secondLargesmall(nums);
//		ex.flatmap();
//		ex.wordFrequency();
		
	}
	//SELECT DISTINCT salary
//	FROM employees
//	ORDER BY salary DESC
//	LIMIT 1 OFFSET 2;
	void flatmap() {
		List<String> sentences = Arrays.asList("Java is fun", "Streams are powerful");

        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" "))) // Stream<Stream<String>> → Stream<String>
                .collect(Collectors.toList());

        System.out.println(words);
        List<List<String>> nestedList = Arrays.asList(
        	    Arrays.asList("a", "b"),
        	    Arrays.asList("c", "d")
        	);

        	List<String> flatList = nestedList.stream()
        	    .flatMap(Collection::stream)
        	    .collect(Collectors.toList());

        	System.out.println(flatList);
	}
	void filterFunc() {
		System.out.println("filter operation to show numbers starting with 2");
		List<String> list = Arrays.asList("s200", "100", "d254", "324", "f987");
		//filter
		list.stream()
		.filter(s -> s.startsWith("2"))
		.forEach(System.out::println);
	}
	void wordstartswithnum() {
		System.out.println("filter operation to show numbers starting with 2");
		List<String> list = Arrays.asList("s200", "100", "d254", "324", "f987");
		//filter
		list.stream()
		.filter(word -> Character.isDigit(word.charAt(0)))
		.forEach(System.out::println);
	}
	void mapfunc(List<Integer> nums) {
		System.out.println("map operation to show n*2 of 1,2,3,4");
		nums.stream()
		.map(n -> n*2)
		.forEach(System.out::println);
	}
	void sortFunc(List<String> list) {
		System.out.println("sort operation by default of Reflection, Collection, Stream");
		list.stream()
		.sorted()
		.map(String::toUpperCase)
		.forEach(System.out::println);
	}
	void distinct() {
		System.out.println("display distinct of 1,1,2,2,3,4,5,5");
		Stream.of(1,1,2,2,3,4,5,5)
		.distinct()
		.forEach(System.out::println);
	}
	void reduce(List<Integer> nums) {
		int sum = nums.stream().reduce(0, (a,b) -> a+b);
		System.out.println("using reduce, sum of 1,2,3,4 = " + sum);
	}
	void even(List<Integer> nums) {
		System.out.println("even numbers among 1,2,3,4");
		nums.stream()
		.filter(n -> n%2 ==0)
		.forEach(System.out::println);
	}
	void function(List<String> list) {
		System.out.println("method to display characters at 2nd index of Reflection, Collection, Stream");
		list.stream()
		.filter(s -> s.length() >= 3) //checks length to be at least 3
		.map(s -> s.charAt(2))
		.forEach(System.out::println);
	}
	void gt10(List<Integer> nums) {
		Optional<Integer> n = nums.stream()
				.filter(a -> a> 10)
				.findFirst();
		System.out.println("the first element greater than 10 among 2,23,4,12 is " +n);
	}
	void sortByLength(List<String> str) {
		System.out.println("sort by length of Reflection, Collection, Stream");
		str.stream()
		.sorted(Comparator.comparing(String::length))
		.forEach(System.out::println);
	}
	void allPositive(List<Integer> nums) {
		System.out.println("all positive numbers among -1,2,3,-4");
		nums.stream()
		.filter(n -> n>0)
		.forEach(System.out::println);
	}
	void max(List<Integer> nums) {
		System.out.println("max value among 1,2,3,4 is "+nums.stream()
		.max(Integer::compare));
	}
	void highest3(List<Integer> nums) {
		System.out.println("The highest 3 integers in the list: ");
		List<Integer> arr = nums.stream()
				.sorted(Comparator.reverseOrder())
				.limit(3)
				.toList();
		for(int i:arr) {
			System.out.println(i);
		}
	}
	void charFrequency(String str) {
		System.out.println("The frquency of ech character in the string: ");
		Map<Character, Long> map = str.chars()
				.mapToObj(c ->(char)c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		map.entrySet().stream().forEach(entry -> System.out.println(entry.getKey()+" - "+entry.getValue()));
	}
	void wordFrequency() {
		String str = "The frquency of ech character in the string java java ";
		 Map<String, Long> wordCounts = Arrays.stream(str.split("\\s+"))
	                .map(word -> word.toLowerCase()) // Optional: for case-insensitive count
	                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

	        // Print the result
	        wordCounts.forEach((word, count) ->
	                System.out.println(word + " : " + count));
	}
	void duplicate(List<Integer> nums) {
		System.out.println("The duplicate integers in this list: ");
		List<Integer> duplicate = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() >1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
			duplicate.stream().forEach(System.out::println);	
	}
	void reverseString(){
		System.out.println("Reverse individual words");
		String input = "Hello world nice here";
		String result = Arrays.stream(input.split(" ")).map(word -> new StringBuilder(word).reverse().toString())
				.collect(Collectors.joining(" "));
		System.out.println(result);
		String[] arr = input.split(" ");
		StringBuilder newstr = new StringBuilder();
		for(String words: arr){
			newstr.append(new StringBuilder(words).reverse().toString()).append(" ");
		}
		System.out.println(newstr);
		Arrays.stream(input.split(" "))
		.map(word -> new StringBuilder(word).reverse().toString())
		.forEach(rword -> System.out.print(rword + " "));
	}
	void duplicate2(List<String> nums) {
		System.out.println("The duplicate integers in this list: ");
		List<String> duplicate = nums.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(e -> e.getValue() >1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
			duplicate.stream().forEach(System.out::println);	
	}
	void repeatedChar() {
		String word = "programming";
		 word.chars()
				.mapToObj(c -> (char)c)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(entry -> entry.getValue() > 1)
				.forEach(entry -> System.out.println(entry.getKey()));
	}
	void areAnagrams() {
		String s1 = "listen";
		String s2 = "silent";
		if(s1.length() != s2.length()) {
			System.out.println("no");
		} else {
			var map1 = s1.chars()
					.mapToObj(c -> (char)c)
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			var map2 = s2.chars()
					.mapToObj(c -> (char)c)
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
			if(map1.equals(map2)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}
	void secondLargesmall(List<Integer> nums) {
		nums.sort(Comparator.naturalOrder());
		System.out.println(nums.get(1));
		System.out.println(nums.get(nums.size() -2));
		List<Integer> sortedList = nums.stream()
                .distinct()
                .sorted()
                //.boxed()
                .collect(Collectors.toList());
		int secondSmallest = sortedList.get(1);
        int secondLargest = sortedList.get(sortedList.size() - 2);
        //for array
        /*
         * List<Integer> sortedList = Arrays.stream(numbers)
                .distinct()
                .sorted()
                .boxed()
                .collect(Collectors.toList());
         */
	}
}
