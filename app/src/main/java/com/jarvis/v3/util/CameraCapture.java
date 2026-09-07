package com.jarvis.v3.util;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.*;
import android.media.Image;
import android.media.ImageReader;
import android.os.Handler;
import android.os.HandlerThread;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

public class CameraCapture {
    public static void captureFrontSelfie(Context ctx, String folderName){
        try{
            CameraManager cm = (CameraManager) ctx.getSystemService(Context.CAMERA_SERVICE);
            String frontId = null;
            for(String id : cm.getCameraIdList()){
                Integer facing = cm.getCameraCharacteristics(id).get(CameraCharacteristics.LENS_FACING);
                if(facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT){ frontId = id; break; }
            }
            if(frontId == null) return;

            HandlerThread thread = new HandlerThread("JarvisCam"); thread.start();
            Handler handler = new Handler(thread.getLooper());

            ImageReader reader = ImageReader.newInstance(640, 480, ImageFormat.JPEG, 1);
            reader.setOnImageAvailableListener(r -> {
                Image img = r.acquireLatestImage();
                if(img == null) return;
                ByteBuffer buf = img.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buf.remaining()];
                buf.get(bytes);
                img.close();
                try{
                    File dir = new File(ctx.getFilesDir(), folderName);
                    if(!dir.exists()) dir.mkdirs();
                    File out = new File(dir, "intruder_"+System.currentTimeMillis()+".jpg");
                    FileOutputStream fos = new FileOutputStream(out);
                    fos.write(bytes); fos.close();
                }catch(Exception e){}
            }, handler);

            cm.openCamera(frontId, new CameraDevice.StateCallback(){
                public void onOpened(CameraDevice device){
                    try{
                        CaptureRequest.Builder req = device.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
                        req.addTarget(reader.getSurface());
                        device.createCaptureSession(java.util.Arrays.asList(reader.getSurface()), new CameraCaptureSession.StateCallback(){
                            public void onConfigured(CameraCaptureSession session){
                                try{ session.capture(req.build(), null, handler); }catch(Exception e){}
                            }
                            public void onConfigureFailed(CameraCaptureSession session){}
                        }, handler);
                    }catch(Exception e){}
                }
                public void onDisconnected(CameraDevice device){ device.close(); }
                public void onError(CameraDevice device, int error){ device.close(); }
            }, handler);
        }catch(Exception e){}
    }
}
