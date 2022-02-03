package com.winner.winningRecords;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Ideone
{
	public static void main (String[] args) throws java.lang.Exception {
		List<Integer> l = Arrays.asList(1,2,3,4,5,5,6,6,7,7,7,8,3,8,8);
		final int N=3;
		nSizeParts(l,N);
		System.out.println(nTimeParts(l,N));
		  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd'T'HH:mm:ss.SSSZ");  
		   LocalDateTime now = LocalDateTime.now();  
		   System.out.println(dtf.format(now));  
	}
		private static <T> List<List<T>> nSizeParts(List<T> objs, final int N) {
			List<List<T>> list= new ArrayList<>(IntStream.range(0, objs.size()).boxed().collect(
				Collectors.groupingBy(e->e/N,Collectors.mapping(e->objs.get(e), Collectors.toList())
						)).values());
			System.out.println(list.size());
			return list;
	}
		private static <T> List<List<T>> nTimeParts(List<T> objs, final int N) {
		return new ArrayList<>(IntStream.range(0, objs.size()).boxed().collect(
				Collectors.groupingBy(e->e%N,Collectors.mapping(e->objs.get(e), Collectors.toList())
						)).values());
	}
		
}
