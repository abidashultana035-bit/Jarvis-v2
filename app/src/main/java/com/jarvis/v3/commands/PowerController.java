package com.jarvis.v3.commands;
import android.app.admin.DevicePolicyManager; import android.content.Context;
public class PowerController {
    public static String screenOff(Context ctx){
        // Device admin permission lagbe Sir
        return "Screen off Sir - Device admin needed Sir";
    }
    public static String shutDown(){
        return "Shutdown command ready Sir - Root permission needed Sir, otherwise I will show power dialog Sir";
    }
}
