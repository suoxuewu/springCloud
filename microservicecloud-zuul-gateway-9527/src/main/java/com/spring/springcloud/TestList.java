package com.spring.springcloud;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TestList {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("222");
        list.add("444");
        list.add("333");

        System.out.println(list);
        list.clear();
        System.out.println(list);
    }
}
