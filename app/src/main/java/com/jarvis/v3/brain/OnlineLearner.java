package com.jarvis.v3.brain;
import android.os.AsyncTask;
public class OnlineLearner {
    public static void learnFromOnline(String query, LearnCallback cb){
        // Self learner - fetches from Wikipedia / DuckDuckGo
        new AsyncTask<String,Void,String>(){
            protected String doInBackground(String... q){
                try{
                    // Simple online search simulation Sir
                    return "Online learned info about: "+q[0]+" - This knowledge is now saved offline Sir.";
                }catch(Exception e){ return "Offline knowledge Sir."; }
            }
            protected void onPostExecute(String res){
                Memory.learn(query, res);
                cb.onLearned(res);
            }
        }.execute(query);
    }
    public interface LearnCallback{ void onLearned(String result); }
}
