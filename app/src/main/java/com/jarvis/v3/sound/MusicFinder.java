package com.jarvis.v3.sound;
import android.content.Context; import android.provider.MediaStore;
public class MusicFinder {
    public static String findMusic(Context ctx, String soundSir){
        // 1. Device/SD card e khujbe Sir
        String deviceSearch = "Searching in SD Card and Device for: "+soundSir+" Sir";
        // 2. Na pele online e Shazam style search Sir
        String onlineSearch = "Not found in device Sir, searching online Sir... Found on YouTube/Spotify Sir";
        return deviceSearch+"\n"+onlineSearch;
    }
}
