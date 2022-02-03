package com.winner.winningRecords;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;



public class SplitArrayList {
    public static void main(String args[]) {
        List<Integer> list = new ArrayList(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11));
		/*
		 * List< List<String> > smallerLists = Lists.partition(list,
		 * maxRecordsInABatch);
		 */
        /* Change the value of chunkSize to get desired number of elements
        in the subLists */
        int chunkSize = 2;
        AtomicInteger counter = new AtomicInteger();
        final Collection<List<Integer>> partitionedList = 
                        list.stream().collect(Collectors.groupingBy(i -> counter.getAndIncrement() / chunkSize))
                            .values(); 
        System.out.println("partitionedList---->"+partitionedList);
		/* List<Integer> subsubList=new ArrayList<>(); */
        for(List<Integer> subList : partitionedList) {
        	List<Integer> subsubList=new  ArrayList<>();
        	for(Integer first:subList) {
        		
        		subsubList.add(first);
        	}
        	System.out.println(subsubList);
        	
        } 
        final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        f.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(f.format(new Date()));
        System.out.println(Calendar.getInstance(TimeZone.getTimeZone("GMT"))
                .get(Calendar.HOUR_OF_DAY) + " Hours");
        Date date=new Date();
        long datems = date.getTime();
        long timezoneoffset = TimeZone.getDefault().getOffset(datems);
        datems -= timezoneoffset;
        System.out.println(new Date(datems));
        
		/*
		 * List<Integer> intList = newArrayList(1, 2, 3, 4, 5, 6, 7, 8); Map<Integer,
		 * List<Integer>> groups = intList.stream().collect(Collectors.groupingBy(s ->
		 * (s - 1) / 3)); System.out.println(groups); List<List<Integer>> subSets = new
		 * ArrayList<List<Integer>>(groups.values()); System.out.println(subSets);
		 */
    }
}
  
