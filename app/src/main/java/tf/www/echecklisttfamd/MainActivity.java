package tf.www.echecklisttfamd;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ImageView androidhead;
    private long delay = 1800;

    private TimerTask task = new TimerTask(){
        @Override
        public void run() {
            // TDO Auto-generated method stub
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidhead = findViewById(R.id.androidhead);

        Animation slide_up = AnimationUtils.loadAnimation(getApplication(),R.anim.androidhead);
        androidhead.startAnimation(slide_up);
        Timer timer = new Timer();
        timer.schedule(task,delay);
}
}
