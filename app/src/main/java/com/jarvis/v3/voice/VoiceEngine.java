package com.jarvis.v3.voice;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import java.util.Locale;
public class VoiceEngine {
    public static TextToSpeech tts;
    public static String currentVoice = "JARVIS";

    public static void init(Context ctx){
        tts = new TextToSpeech(ctx, status -> {
            if(status == TextToSpeech.SUCCESS) tts.setLanguage(Locale.US);
        });
    }
    public static void speak(String textSir){
        if(tts == null) return;
        String finalText = textSir + " Sir";
        if(currentVoice.equals("EDITH")){ tts.setPitch(1.3f); tts.setSpeechRate(1.0f); }
        else { tts.setPitch(0.8f); tts.setSpeechRate(0.95f); }
        tts.speak(finalText, TextToSpeech.QUEUE_FLUSH, null, null);
    }
    public static void setJarvisVoice(){ currentVoice = "JARVIS"; }
    public static void setEdithVoice(){ currentVoice = "EDITH"; }
}
