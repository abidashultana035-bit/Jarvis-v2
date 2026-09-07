package com.jarvis.v3.commands;
import android.os.AsyncTask;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONArray;

public class MarketingTrends {
    // Get your own free key at https://www.alphavantage.co/support/#api-key
    private static final String API_KEY = "YOUR_ALPHAVANTAGE_KEY_HERE";

    public interface PriceCallback{ void onResult(String info); }

    public static void getStockPrice(String symbol, PriceCallback cb){
        new AsyncTask<Void,Void,String>(){
            protected String doInBackground(Void... v){
                try{
                    URL url = new URL("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+symbol+"&apikey="+API_KEY);
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line=br.readLine())!=null) sb.append(line);
                    JSONObject obj = new JSONObject(sb.toString()).getJSONObject("Global Quote");
                    String price = obj.optString("05. price", "unknown");
                    String change = obj.optString("10. change percent", "unknown");
                    return symbol+" price: "+price+" ("+change+") Sir. This is current data only Sir, not a prediction.";
                }catch(Exception e){
                    return "Couldn't fetch market data Sir - check API key or connection.";
                }
            }
            protected void onPostExecute(String res){ cb.onResult(res); }
        }.execute();
    }
}
