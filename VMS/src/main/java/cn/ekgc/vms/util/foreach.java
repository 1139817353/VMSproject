package cn.ekgc.vms.util;

import javafx.scene.effect.SepiaTone;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class foreach {
	public static void main(String[] args) {
		Set<String> set = new HashSet<String>();
		set.add("A");
		set.add("B");
		set.add("C");
		set.add("D");
		set.add("E");
		set.add("F");

		Iterator iterator = set.iterator();
	    while (iterator.hasNext()){
	    	String index = (String)iterator.next();
	    	System.out.println(index);
	    }
	    //两者皆可迭代
        for(String str : set){
        	System.out.println(str);
        }
	}
}
