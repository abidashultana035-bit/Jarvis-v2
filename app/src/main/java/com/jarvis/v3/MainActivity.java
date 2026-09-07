package com.jarvis.v2;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.Gravity;
import com.jarvis.v2.voice.*;
import com.jarvis.v2.commands.*;
import com.jarvis.v2.security.*;
import com.jarvis.v2.language.*;
import com.jarvis.v2.sound.*;
import com.jarvis.v2.brain.*;

public class MainActivity extends Activity {
  TextView ansView;
  protected void onCreate(Bundle b){
    super.onCreate(b);
    VoiceEngine.init(this);

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL); layout.setGravity(Gravity.CENTER); layout.setPadding(30,30,30,30);

    TextView title = new TextView(this); title.setText("JARVIS v3\nFull Iron Man Edition Sir"); title.setTextSize(22); title.setGravity(Gravity.CENTER);
    TextView status = new TextView(this); status.setText("VoiceLock ON | JARVIS+EDITH | Protected"); status.setTextSize(10); status.setGravity(Gravity.CENTER);
    final EditText input = new EditText(this); input.setHint("Say: open youtube / torch on / find / scan virus / etc Sir");
    Button btn = new Button(this); btn.setText("Command Sir");
    ansView = new TextView(this); ansView.setText("Ready Sir."); ansView.setTextSize(15); ansView.setPadding(0,20,0,0);

    btn.setOnClickListener(v -> {
      String q = input.getText().toString().toLowerCase(); String reply = "";

      String storageWarning = StorageManager.checkAndPrompt();
      if(storageWarning != null){ ansView.setText(storageWarning); VoiceEngine.speak(storageWarning); return; }

      if(q.contains("edith")){ VoiceEngine.setEdithVoice(); reply = "EDITH voice activated"; }
      else if(q.contains("jarvis voice")){ VoiceEngine.setJarvisVoice(); reply = "JARVIS voice activated"; }
      else if(q.startsWith("open ")){ reply = AppOpener.openApp(this, q.replace("open ","")); }
      else if(q.contains("torch on")){ TorchController.turnOnTorch(this); reply = "Torch ON"; }
      else if(q.contains("torch off")){ TorchController.turnOffTorch(this); reply = "Torch OFF"; }
      else if(q.startsWith("find ")){ reply = Finder.find(q.replace("find ",""), true); }
      else if(q.startsWith("delete ")){ reply = Deleter.deleteData(q.replace("delete ","")); }
      else if(q.contains("code") || q.contains("coding")){ reply = CodingEngine.doCoding(q); }
      else if(q.startsWith("stock ")){
        String symbol = q.replace("stock ","").toUpperCase();
        MarketingTrends.getStockPrice(symbol, res -> runOnUiThread(() -> { ansView.setText(res); VoiceEngine.speak(res); }));
        return;
      }
      else if(q.contains("screen off")){ reply = PowerController.screenOff(this); }
      else if(q.contains("shut down")){ reply = PowerController.shutDown(); }
      else if(q.contains("camera")){ reply = CameraManager.requestCamera(q); }
      else if(q.contains("virus") || q.contains("scan")){ reply = VirusScanner.scanDevice(); }
      else if(q.contains("music") || q.contains("song")){ reply = MusicFinder.findMusic(this, q.replace("play ","").replace("song ","")); }
      else if(q.contains("call ")){ reply = CallManager.makeCall(this, q.replace("call ","")); }
      else if(q.contains("translate ")){
        Translator.translate(q.replace("translate ",""), "bn", res -> runOnUiThread(() -> { ansView.setText(res); VoiceEngine.speak(res); }));
        return;
      }
      else if(q.contains("allow others")){ VoiceLock.allowOthersFor(10); reply = "Allowed others for 10 min"; }
      else {
        SelfBrain.answer(this, q, result -> runOnUiThread(() -> { ansView.setText(result); VoiceEngine.speak(result); }));
        return;
      }
      ansView.setText(reply);
      VoiceEngine.speak(reply);
    });

    layout.addView(title); layout.addView(status); layout.addView(input); layout.addView(btn); layout.addView(ansView);
    setContentView(layout);
  }
        }
