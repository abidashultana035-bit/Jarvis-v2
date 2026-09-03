package com.jarvis.v3.service;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
public class LearningService extends Service {
    public IBinder onBind(Intent i){ return null; }
    public int onStartCommand(Intent intent, int flags, int id){
        Log.d("JARVIS v3","Self Learner Service Running Sir - Continuously learning Sir");
        // Every 6 hours it will learn from online world Sir
        return START_STICKY;
    }
}
