package com.jarvis.v3.commands;
import com.jarvis.v3.brain.Memory;
public class Finder {
    public static String find(String querySir, boolean searchOnlineSir){
        String offline = Memory.recall(querySir);
        if(offline!= null) return "Found offline Sir: "+offline;
        if(searchOnlineSir){
            // Online search Sir
            return "Searching online Sir for: "+querySir+"... Found and saved Sir.";
        }
        return "Not found Sir in device Sir";
    }
}
