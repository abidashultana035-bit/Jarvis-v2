package com.jarvis.v3.sound;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.provider.MediaStore;

public class MusicFinder {
    public static String findMusic(Context ctx, String soundSir){
        Cursor cur = ctx.getContentResolver().query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null,
            MediaStore.Audio.Media.TITLE+" LIKE ?", new String[]{"%"+soundSir+"%"}, null);
        if(cur != null && cur.moveToFirst()){
            int col = cur.getColumnIndexOrThrow(MediaStore.Audio.Media._ID);
            long id = cur.getLong(col);
            cur.close();
            Uri songUri = Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, ""+id);
            Intent play = new Intent(Intent.ACTION_VIEW);
            play.setDataAndType(songUri, "audio/*");
            play.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            ctx.startActivity(play);
            return "Playing "+soundSir+" from your device Sir";
        }
        // Not on device Sir - open YouTube search
        Intent yt = new Intent(Intent.ACTION_VIEW,
            Uri.parse("https://www.youtube.com/results?search_query="+Uri.encode(soundSir)));
        ctx.startActivity(yt);
        return "Not on device Sir, opening YouTube search Sir";
    }
}
