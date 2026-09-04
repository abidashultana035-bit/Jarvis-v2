package com.jarvis.v3.commands;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.database.Cursor;
import android.provider.ContactsContract;

public class CallManager {

    // Normal call Sir - device theke
    public static String makeCall(Context ctx, String nameSir){
        try{
            String phone = findNumber(ctx, nameSir);
            if(phone == null) return "Contact not found Sir: "+nameSir+" Sir";
            
            Intent call = new Intent(Intent.ACTION_CALL);
            call.setData(Uri.parse("tel:"+phone));
            ctx.startActivity(call);
            return "Calling "+nameSir+" Sir from device Sir - "+phone;
        }catch(Exception e){
            return "Call permission needed Sir for "+nameSir+" Sir";
        }
    }

    // WhatsApp call Sir
    public static String makeWhatsAppCall(Context ctx, String nameSir){
        try{
            String phone = findNumber(ctx, nameSir);
            if(phone == null) return "Contact not found Sir: "+nameSir+" Sir";
            
            // WhatsApp call intent Sir
            Intent whatsapp = new Intent(Intent.ACTION_VIEW);
            whatsapp.setData(Uri.parse("https://wa.me/"+phone.replace("+","").replace(" ","")));
            // Direct WhatsApp call package Sir
            whatsapp.setPackage("com.whatsapp");
            ctx.startActivity(whatsapp);
            return "WhatsApp calling "+nameSir+" Sir - "+phone+" Sir";
        }catch(Exception e){
            // Jodi WhatsApp direct call na hoy Sir - WhatsApp open korbe Sir
            try{
                Intent i = ctx.getPackageManager().getLaunchIntentForPackage("com.whatsapp");
                ctx.startActivity(i);
                return "Opening WhatsApp for "+nameSir+" Sir - please call Sir";
            }catch(Exception e2){ return "WhatsApp not installed Sir"; }
        }
    }

    // Contact theke number khujbe Sir
    public static String findNumber(Context ctx, String nameSir){
        try{
            Cursor cur = ctx.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" LIKE ?", 
                new String[]{"%"+nameSir+"%"}, null);
            if(cur!=null && cur.moveToFirst()){
                String num = cur.getString(cur.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                cur.close();
                return num;
            }
        }catch(Exception e){}
        // Jodi direct number dey Sir - jemon "call 01712345678"
        if(nameSir.matches(".*\\d+.*")) return nameSir.replaceAll("[^0-9+]","");
        return null;
    }
}
