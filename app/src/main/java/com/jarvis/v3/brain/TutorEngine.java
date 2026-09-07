package com.jarvis.v3.brain;
public class TutorEngine {
    public static String tutor(String subjectAndQuestion){
        String offline = Memory.recall("tutor_"+subjectAndQuestion);
        if(offline != null) return offline;
        return null; // fall through to SelfBrain/OnlineLearner in MainActivity
    }
}
