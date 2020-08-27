package ru.arcadudu.danatest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestList {

    public static List<Topic> list = new ArrayList<>();

    public static void init(){
        for (int i = 0; i <50 ; i++) {
            list.add(new Topic("Великобритания", "river, lake, fog, mountainous"));

        }
    }




}
