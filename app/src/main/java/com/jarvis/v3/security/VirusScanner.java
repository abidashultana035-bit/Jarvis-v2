package com.jarvis.v3.security;
public class VirusScanner {
    public static String scanDevice(){
        // Simple scan logic Sir
        return "Scanning Sir for malware/virus... Found 0 threat Sir. Safe Sir. Action Sir?";
    }
    public static String onThreatFound(String threatSir){
        return "Sir threat found: "+threatSir+" Sir. Delete or Quarantine Sir?";
    }
}
