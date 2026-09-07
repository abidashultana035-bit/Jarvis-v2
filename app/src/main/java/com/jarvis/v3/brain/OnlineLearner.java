package com.jarvis.v3.brain;
import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class OnlineLearner {
    public static void learnFromOnline(String query, LearnCallback cb){
        new AsyncTask<String,Void,String>(){
            protected String doInBackground(String... q){
                try{
                    String topic = q[0].replace(" ","_");
                    URL url = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/"+topic);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("User-Agent","JarvisApp/1.0");
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) sb.append(line);
                    JSONObject obj = new JSONObject(sb.toString());
                    return obj.optString("extract", "No summary found Sir.");
                }catch(Exception e){
                    return "Sorry Sir, I couldn't reach the internet properly Sir.";
                }
            }
            protected void onPostExecute(String res){
                Memory.learn(query, res);
                cb.onLearned(res);
            }
        }.execute(query);
    }
    public interface LearnCallback{ void onLearned(String result); }
}
