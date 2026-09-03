package com.jarvis.v3.brain;
public class SelfBrain {
    public static String answer(String q, BrainCallback cb){
        String offline = Memory.recall(q);
        if(offline!= null){
            cb.onAnswer("OFFLINE BRAIN Sir: "+offline);
            return offline;
        }
        // If not offline, learn online
        OnlineLearner.learnFromOnline(q, result -> {
            cb.onAnswer("ONLINE LEARNING Sir (now saved offline): "+result);
        });
        return "Sir ami online theke sikhchi... ektu wait koren Sir...";
    }
    public interface BrainCallback{ void onAnswer(String ans); }
}
