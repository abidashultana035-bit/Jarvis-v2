package com.jarvis.v3.voice;
import java.util.*;
public class VoiceLock {
    public static String ownerVoicePrint = null; // First time apnar voice save hobe Sir
    public static boolean allowOthers = false; // Apni permission dile true hobe Sir
    public static long allowOthersUntil = 0;

    public static void registerOwnerVoice(String voicePrintSir){
        ownerVoicePrint = voicePrintSir;
    }
    public static boolean isOwner(String currentVoiceSir){
        if(allowOthers && System.currentTimeMillis() < allowOthersUntil) return true;
        if(ownerVoicePrint == null) return true; // First time
        return ownerVoicePrint.equals(currentVoiceSir);
    }
    public static void allowOthersFor(int minutesSir){
        allowOthers = true;
        allowOthersUntil = System.currentTimeMillis() + (minutesSir * 60 * 1000);
    }
    public static void blockOthers(){ allowOthers = false; }
}
