package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//20,30,40,50,20,10,50- identify the duplicate numbers
		List<Integer> numbers = Arrays.asList(20,30,40,50,20,10,50);
		List<Integer> duplicate = numbers.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
				.filter(e ->e.getValue() >1)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
			duplicate.stream().forEach(System.out::println);
	}

}
