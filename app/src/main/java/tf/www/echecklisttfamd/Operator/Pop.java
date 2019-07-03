package tf.www.echecklisttfamd.Operator;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import tf.www.echecklisttfamd.R;

public class Pop extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      setContentView(R.layout.popwindow);

       DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Double width = dm.widthPixels *.8;
        Double height = dm.heightPixels * .6;



        getWindow().setLayout(Integer.parseInt(width.toString()),Integer.parseInt(height.toString()));


    }


}
