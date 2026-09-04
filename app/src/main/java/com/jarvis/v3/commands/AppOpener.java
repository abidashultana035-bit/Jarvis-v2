package com.jarvis.v3.commands;
import android.content.Context; import android.content.Intent; import android.content.pm.PackageManager;
public class AppOpener {
    public static String openApp(Context ctx, String appNameSir){
        try{
            PackageManager pm = ctx.getPackageManager();
            Intent launch = pm.getLaunchIntentForPackage(appNameSir);
            if(launch == null){
                // Name diye khujbe Sir - youtube, facebook
                launch = pm.getLaunchIntentForPackage("com."+appNameSir.toLowerCase());
            }
            ctx.startActivity(launch);
            return "Opening "+appNameSir+" Sir";
        }catch(Exception e){ return "App not found Sir: "+appNameSir; }
    }
}
