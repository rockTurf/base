package com.srj.common.utils;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class OtherUtils {

    //Set排序
    public static Set<String> sortSet(Set<String> set){
        Set<String> sortSet = new TreeSet<String>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);//降序排列
            }
        });
        sortSet.addAll(set);
        return sortSet;
    }
}
