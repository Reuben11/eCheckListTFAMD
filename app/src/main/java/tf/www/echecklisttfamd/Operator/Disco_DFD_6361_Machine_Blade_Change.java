package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Disco_DFD_6361_Machine_Blade_Change#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Disco_DFD_6361_Machine_Blade_Change extends Fragment {
    private TextView tvEmp, tvEquipmentName, tvDateTime, tvMsEmp, tvMsName;
    private String  Scode;
    View view;

    public Disco_DFD_6361_Machine_Blade_Change() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Disco_DFD_6361_Machine_Blade_Change newInstance() {
        Disco_DFD_6361_Machine_Blade_Change fragment = new Disco_DFD_6361_Machine_Blade_Change();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_disco__dfd_6361__machine__blade__change, container, false);

        tvMsEmp = view.findViewById(R.id.msemp);
        tvEquipmentName = view.findViewById(R.id.changeequipmentname);
        tvDateTime = view.findViewById(R.id.nowdatetime);
        tvMsName = view.findViewById(R.id.msname);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDateTime.setText(dateStr);

        GetSharePreference();


        Button b = view.findViewById(R.id.jrsubmit);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               /* int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;*/

                int width = FrameLayout.LayoutParams.WRAP_CONTENT;
                int height = FrameLayout.LayoutParams.WRAP_CONTENT;


                PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.popwindow, null, false),width,height, true);
                pw.setElevation(10);

                pw.setWindowLayoutMode(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                pw.setHeight(8);
                pw.setWidth(1);

                pw.showAtLocation(v, Gravity.CENTER, 0, 0);
            }
        });

        return view;
    }


    protected void GetSharePreference(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvEquipmentName.setText(prefs.getString("equipmentname","no data"));
        tvMsEmp.setText(prefs.getString("empid","no data"));
        tvMsName.setText(prefs.getString("empname", "no data"));
        Scode = prefs.getString("scode", "no data");
    }

    private void ShowAlert(String title, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }
}
