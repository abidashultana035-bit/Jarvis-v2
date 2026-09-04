package com.jarvis.v3.commands;
import android.hardware.camera2.CameraManager; import android.content.Context;
public class TorchController {
    public static void turnOnTorch(Context ctx){
        try{ CameraManager cm = (CameraManager)ctx.getSystemService(Context.CAMERA_SERVICE);
        cm.setTorchMode(cm.getCameraIdList()[0], true); }catch(Exception e){}
    }
    public static void turnOffTorch(Context ctx){
        try{ CameraManager cm = (CameraManager)ctx.getSystemService(Context.CAMERA_SERVICE);
        cm.setTorchMode(cm.getCameraIdList()[0], false); }catch(Exception e){}
    }
}
