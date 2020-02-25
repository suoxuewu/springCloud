package test;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class TestCollections {
    public static void main(String[] args) {
        List list = new ArrayList<>();
        list.add("2");
        boolean flag = CollectionUtils.isEmpty(list);
        System.out.println(flag);
    }
}
