package com.jarvis.v3.language;
import android.os.AsyncTask;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class Translator {
    public static String currentLang = "bn"; // default Bangla

    public interface TranslateCallback{ void onResult(String translated); }

    public static void translate(String textSir, String toLangCode, TranslateCallback cb){
        new AsyncTask<Void,Void,String>(){
            protected String doInBackground(Void... v){
                try{
                    String encoded = URLEncoder.encode(textSir, "UTF-8");
                    URL url = new URL("https://api.mymemory.translated.net/get?q="+encoded+"&langpair=auto|"+toLangCode);
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) sb.append(line);
                    JSONObject obj = new JSONObject(sb.toString());
                    return obj.getJSONObject("responseData").getString("translatedText");
                }catch(Exception e){
                    return "Translation failed Sir.";
                }
            }
            protected void onPostExecute(String result){ cb.onResult(result); }
        }.execute();
    }
}
