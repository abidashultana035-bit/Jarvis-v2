package com.jarvis.v3.voice;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
public class VoiceEngine {
    public static TextToSpeech tts;
    public static String currentVoice = "JARVIS"; // JARVIS or EDITH

    public static void speak(String textSir){
        String finalText = textSir + " Sir";
        if(currentVoice.equals("EDITH")){
            // EDITH female voice - spider man style Sir
            tts.setPitch(1.3f); tts.setSpeechRate(1.0f);
        } else {
            // JARVIS male - Iron man style Sir
            tts.setPitch(0.8f); tts.setSpeechRate(0.95f);
        }
        tts.speak(finalText, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    public static void setJarvisVoice(){ currentVoice = "JARVIS"; }
    public static void setEdithVoice(){ currentVoice = "EDITH"; }
}
