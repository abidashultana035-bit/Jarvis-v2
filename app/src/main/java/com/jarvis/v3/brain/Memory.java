package com.jarvis.v3.brain;
import java.util.*;
public class Memory {
    public static Map<String,String> knowledge = new HashMap<>();
    public static void learn(String q, String a){ knowledge.put(q.toLowerCase(), a); }
    public static String recall(String q){
        if(knowledge.containsKey(q.toLowerCase())) return knowledge.get(q.toLowerCase());
        return null;
    }
}
