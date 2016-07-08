package com.example.guju.utils;

import com.example.guju.bean.SortModel;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortModel> {

    @Override
    public int compare(SortModel s1, SortModel s2) {
        if("&".equals(s1.getSortLetters())||"#".equals(s1.getSortLetters())){
            return -1;
        }else if("&".equals(s1.getSortLetters())||"#".equals(s2.getSortLetters())){
            return -1;
        }else{
            return s1.getSortLetters().compareTo(s2.getSortLetters());
        }
    }
}
