package com.jarvis.v2.commands;
import com.jarvis.v2.brain.Memory;
public class Deleter {
    public static String deleteData(String whatSir){
        Memory.knowledge.remove(whatSir.toLowerCase());
        return "Deleted Sir what you learned about: "+whatSir+" Sir. Confirm Sir?";
    }
}
