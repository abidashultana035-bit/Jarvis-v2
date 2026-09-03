package com.jarvis.v3;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.Gravity;
import android.content.Intent;
import com.jarvis.v3.brain.SelfBrain;
import com.jarvis.v3.service.LearningService;

public class MainActivity extends Activity {
  TextView ansView;
  protected void onCreate(Bundle b){
    super.onCreate(b);
    startService(new Intent(this, LearningService.class));

    LinearLayout layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setGravity(Gravity.CENTER);
    layout.setPadding(40,40,40,40);

    TextView title = new TextView(this);
    title.setText("JARVIS v3\nSelf Learner Sir");
    title.setTextSize(24);
    title.setGravity(Gravity.CENTER);
    title.setPadding(0,0,0,20);

    TextView status = new TextView(this);
    status.setText("Status: Continuous Learning ON Sir | Offline Ready");
    status.setTextSize(12);
    status.setGravity(Gravity.CENTER);
    status.setPadding(0,0,0,20);

    final EditText input = new EditText(this);
    input.setHint("Ask anything Sir...");

    Button btn = new Button(this);
    btn.setText("Ask JARVIS Sir");

    ansView = new TextView(this);
    ansView.setText("Sir ami JARVIS v3. Ja bolben mone rakhbo Sir, online theke sikhbo Sir, offline eo bolbo Sir!");
    ansView.setPadding(0,30,0,0);
    ansView.setTextSize(16);

    btn.setOnClickListener(v -> {
      String q = input.getText().toString();
      if(q.isEmpty()){ ansView.setText("Sir kichu likhun?"); return; }
      ansView.setText("Sir thinking... offline check + online learning Sir...");
      SelfBrain.answer(q, result -> {
        runOnUiThread(() -> ansView.setText(result));
      });
    });

    layout.addView(title);
    layout.addView(status);
    layout.addView(input);
    layout.addView(btn);
    layout.addView(ansView);
    setContentView(layout);
  }
}
