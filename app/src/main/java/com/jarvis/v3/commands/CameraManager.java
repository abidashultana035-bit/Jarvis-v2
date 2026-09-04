package com.jarvis.v3.commands;
public class CameraManager {
    public static boolean needPermissionForUse = true;
    public static String requestCamera(String reasonSir){
        if(needPermissionForUse){
            return "Sir, I need permission to use camera for: "+reasonSir+" Sir. Allow Sir?";
        }
        return "Camera access granted Sir, I can see Sir";
    }
}
