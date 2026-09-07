package com.jarvis.v3.security;
import android.content.Context;
import com.jarvis.v3.util.CameraCapture;

public class IntruderDetector {
    public static int failedAttempts = 0;
    public static void onFailedUnlock(Context ctx){
        failedAttempts++;
        if(failedAttempts >= 3){
            CameraCapture.captureFrontSelfie(ctx, "Intruders");
            failedAttempts = 0;
        }
    }
}
