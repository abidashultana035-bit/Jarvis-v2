package com.jarvis.v2;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import android.view.Gravity;
import android.content.Intent;
import com.jarvis.v3.security.IntruderDetector;

public class LockActivity extends Activity {
    SharedPreferences prefs;
    protected void onCreate(Bundle b){
        super.onCreate(b);
        prefs = getSharedPreferences("jarvis_lock", MODE_PRIVATE);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(40,40,40,40);

        String saved = prefs.getString("pin", null);
        TextView title = new TextView(this);
        title.setText(saved == null ? "Set your JARVIS PIN Sir" : "Enter PIN Sir");
        title.setTextSize(20);

        final EditText pinInput = new EditText(this);
        pinInput.setInputType(android.text.InputType.TYPE_CLASS_NUMBER | android.text.InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        Button go = new Button(this);
        go.setText(saved == null ? "Set PIN" : "Unlock");

        go.setOnClickListener(v -> {
            String entered = pinInput.getText().toString();
            if(saved == null){
                if(entered.length() < 4){ Toast.makeText(this,"PIN must be 4+ digits Sir",Toast.LENGTH_SHORT).show(); return; }
                prefs.edit().putString("pin", entered).apply();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else if(entered.equals(saved)){
                IntruderDetector.failedAttempts = 0;
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                IntruderDetector.onFailedUnlock(this);
                Toast.makeText(this,"Wrong PIN Sir",Toast.LENGTH_SHORT).show();
                pinInput.setText("");
            }
        });

        layout.addView(title); layout.addView(pinInput); layout.addView(go);
        setContentView(layout);
    }
}
