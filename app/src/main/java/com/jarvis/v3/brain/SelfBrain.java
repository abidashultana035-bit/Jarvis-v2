package com.jarvis.v3.brain;
import android.content.Context;
import com.jarvis.v3.util.NetworkUtils;

public class SelfBrain {
    public static String answer(Context ctx, String q, BrainCallback cb){
        String offline = Memory.recall(q);
        if(offline != null){
            cb.onAnswer(offline);
            return offline;
        }
        if(!NetworkUtils.isOnline(ctx)){
            String msg = "Sorry Sir, but you don't have the data Sir.";
            cb.onAnswer(msg);
            return msg;
        }
        OnlineLearner.learnFromOnline(q, result -> cb.onAnswer(result));
        return "One moment Sir, checking online...";
    }
    public interface BrainCallback{ void onAnswer(String ans); }
}
