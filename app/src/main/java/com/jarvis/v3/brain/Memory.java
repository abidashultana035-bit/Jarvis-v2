package com.jarvis.v3.brain;
import java.util.*;
public class Memory {
    public static Map<String,String> knowledge = new HashMap<>();
    public static Map<String,Long> timestamps = new HashMap<>();

    public static void learn(String q, String a){
        String key = q.toLowerCase();
        knowledge.put(key, a);
        timestamps.put(key, System.currentTimeMillis());
    }
    public static String recall(String q){
        return knowledge.get(q.toLowerCase());
    }
}
