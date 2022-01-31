package com.title;

import java.util.ArrayList;
import java.util.List;

public class Sample {
	public static void main(String[] args) {
		List list=new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(1);
		List list2=new ArrayList();
		for(int i=0;i<list.size();i++) {
			if(!list2.contains(list.get(i))) {
				list2.add(list.get(i));
			}
			
		}
		System.out.println(list2);
		
		
	}
}
