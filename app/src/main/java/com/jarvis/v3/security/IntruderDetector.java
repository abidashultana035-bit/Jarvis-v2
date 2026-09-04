package com.jarvis.v3.security;
import android.content.Context;
public class IntruderDetector {
    public static int failedAttempts = 0;
    public static void onFailedUnlock(Context ctx){
        failedAttempts++;
        if(failedAttempts >= 3){
            // Selfie tulbe Sir
            takeSelfie(ctx);
            failedAttempts = 0;
        }
    }
    public static void takeSelfie(Context ctx){
        // Camera diye selfie save korbe Sir - /sdcard/JARVIS/Intruders/
        com.jarvis.v3.commands.CameraManager.requestCamera("Intruder detection Sir");
        // Code to capture image Sir
    }
}
