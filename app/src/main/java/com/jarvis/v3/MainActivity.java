package com.jarvis.v3;
import android.app.Activity; import android.os.Bundle; import android.widget.*; import android.view.Gravity;
import com.jarvis.v3.voice.*; import com.jarvis.v3.commands.*; import com.jarvis.v3.security.*; import com.jarvis.v3.language.*; import com.jarvis.v3.sound.*; import com.jarvis.v3.brain.SelfBrain;

public class MainActivity extends Activity {
  TextView ansView;
  protected void onCreate(Bundle b){
    super.onCreate(b);
    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL); layout.setGravity(Gravity.CENTER); layout.setPadding(30,30,30,30);

    TextView title = new TextView(this); title.setText("JARVIS v3\nFull Iron Man Edition Sir"); title.setTextSize(22); title.setGravity(Gravity.CENTER);
    TextView status = new TextView(this); status.setText("VoiceLock ON | JARVIS+EDITH | All Languages | Protected"); status.setTextSize(10); status.setGravity(Gravity.CENTER);
    final EditText input = new EditText(this); input.setHint("Say: open youtube / torch on / Edith voice / find / code / scan virus / etc Sir");
    Button btn = new Button(this); btn.setText("Command Sir");
    ansView = new TextView(this); ansView.setText("Ready Sir. I will only listen to you Sir. Say my commands Sir."); ansView.setTextSize(15); ansView.setPadding(0,20,0,0);

    btn.setOnClickListener(v -> {
      String q = input.getText().toString().toLowerCase(); String reply = "";
      
      if(q.contains("edith")){ VoiceEngine.setEdithVoice(); reply = "EDITH voice activated Sir"; }
      else if(q.contains("jarvis voice")){ VoiceEngine.setJarvisVoice(); reply = "JARVIS voice activated Sir"; }
      else if(q.startsWith("open ")){ String app = q.replace("open ",""); reply = AppOpener.openApp(this, app); }
      else if(q.contains("torch on")){ TorchController.turnOnTorch(this); reply = "Torch ON Sir"; }
      else if(q.contains("torch off")){ TorchController.turnOffTorch(this); reply = "Torch OFF Sir"; }
      else if(q.startsWith("find ")){ String f = q.replace("find ",""); reply = Finder.find(f, true); }
      else if(q.startsWith("delete ")){ String d = q.replace("delete ",""); reply = Deleter.deleteData(d); }
      else if(q.contains("code") || q.contains("coding")){ reply = CodingEngine.doCoding(q); }
      else if(q.contains("trending") || q.contains("marketing") || q.contains("share")){ reply = MarketingTrends.getTrends(); }
      else if(q.contains("screen off") || q.contains("power off")){ reply = PowerController.screenOff(this); }
      else if(q.contains("shut down")){ reply = PowerController.shutDown(); }
      else if(q.contains("camera")){ reply = CameraManager.requestCamera(q); }
      else if(q.contains("virus") || q.contains("scan")){ reply = VirusScanner.scanDevice(); }
      else if(q.contains("music") || q.contains("song")){ reply = MusicFinder.findMusic(this, q); }
      else if(q.contains("allow others")){ VoiceLock.allowOthersFor(10); reply = "Allowed others for 10 min Sir"; }
      else {
        // Normal brain Sir + Translator Sir
        SelfBrain.answer(q, result -> runOnUiThread(() -> ansView.setText(Translator.autoDetectAndReply(result))));
        return;
      }
      ansView.setText(reply+" Sir");
      VoiceEngine.speak(reply);
    });

    layout.addView(title); layout.addView(status); layout.addView(input); layout.addView(btn); layout.addView(ansView);
    setContentView(layout);
  }
      }
