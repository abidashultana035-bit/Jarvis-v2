package com.jarvis.v3.voice;
import android.speech.SpeechRecognizer;
public class VoiceListener {
    // Eita check korbe Sir voice ta apnar kina
    public static String onVoiceHeard(String textSir, String voiceIdSir){
        if(!VoiceLock.isOwner(voiceIdSir)){
            return "Sorry Sir, I only listen to my owner Sir. Permission needed Sir.";
        }
        return textSir;
    }
}
