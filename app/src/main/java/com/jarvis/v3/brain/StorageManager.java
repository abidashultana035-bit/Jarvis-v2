package com.jarvis.v3.brain;
import java.util.Map;

public class StorageManager {
    public static final long LIMIT_BYTES = 8L * 1024 * 1024 * 1024;

    public static long currentUsageEstimate(){
        long total = 0;
        for(Map.Entry<String,String> e : Memory.knowledge.entrySet()){
            total += e.getKey().length() + e.getValue().length();
        }
        return total;
    }

    public static boolean isFull(){ return currentUsageEstimate() >= LIMIT_BYTES; }

    public static String oldestEntryKey(){
        String oldestKey = null; long oldestTime = Long.MAX_VALUE;
        for(Map.Entry<String,Long> e : Memory.timestamps.entrySet()){
            if(e.getValue() < oldestTime){ oldestTime = e.getValue(); oldestKey = e.getKey(); }
        }
        return oldestKey;
    }

    public static String checkAndPrompt(){
        if(isFull()){
            String oldest = oldestEntryKey();
            return "Sir, storage limit reached. Should I delete: \""+oldest+"\"?";
        }
        return null;
    }

    public static void confirmDelete(String key){
        Memory.knowledge.remove(key);
        Memory.timestamps.remove(key);
    }
}
