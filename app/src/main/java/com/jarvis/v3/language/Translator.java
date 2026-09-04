package com.jarvis.v3.language;
public class Translator {
    public static String currentLang = "BN-EN"; // Default Bangla+English Sir

    // Offline dictionary Sir + Online e Google Translate API use korbe Sir
    public static String translate(String textSir, String toLang){
        // Offline check
        String offline = com.jarvis.v3.brain.Memory.recall("translate_"+textSir);
        if(offline!= null) return offline;

        // Online translation simulation Sir
        String translated = "[Translated to "+toLang+"] "+textSir+" Sir";
        com.jarvis.v3.brain.Memory.learn("translate_"+textSir, translated);
        return translated;
    }
    public static String autoDetectAndReply(String textSir){
        // Sadharonoto Bangla + English Sir
        if(textSir.matches(".*[a-zA-Z].*")) return textSir + " Sir";
        else return textSir + " Sir, bujhechi Sir";
    }
}
