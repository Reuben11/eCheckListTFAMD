package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tf.www.echecklisttfamd.Operator.allclass;
import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class MachineSetup extends Fragment {
    private TextView tvEquipment, tvJR, tvDatetime, tvTech, tvRequestor, tvDaily;
    private String device, daily;
    View view;

    public static MachineSetup newInstance() {
        // Required empty public constructor
        MachineSetup fragment = new MachineSetup();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_machine_setup, container, false);
        RelativeLayout dailylayout = view.findViewById(R.id.dailychecklist);
        RelativeLayout unicorn510461045112B = view.findViewById(R.id.unicorn510461045112B);
        RelativeLayout unicorn6101 = view.findViewById(R.id.unicorn6101);
        tvEquipment = view.findViewById(R.id.equipmentname);
        tvJR = view.findViewById(R.id.jrnumber);
        tvDatetime = view.findViewById(R.id.nowdatetime);
        tvTech = view.findViewById(R.id.technicianid);
        tvRequestor = view.findViewById(R.id.requestorid);
        tvDaily = view.findViewById(R.id.purposeoption);
        getAll();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a,  dd MMM yy");
        String dateStr = df.format(c);
        tvDatetime.setText(dateStr);

        if(daily.equals("True")){
            tvDaily.setText("DAILY");
            dailylayout.setVisibility(View.VISIBLE);
        }
        else{
            tvDaily.setText("NORMAL");
            dailylayout.setVisibility(View.GONE);
        }

        if(device.equals("5104")| device.equals("6104") | device.equals("5112B") ){
            unicorn510461045112B.setVisibility(View.VISIBLE);
        }

        if(device.equals("6101") ){
            unicorn6101.setVisibility(View.VISIBLE);
        }

        return view;
    }

    protected void getAll(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvJR.setText(prefs.getString("jr","no data"));
        tvEquipment.setText(prefs.getString("equipmentname","no data"));
        tvRequestor.setText(prefs.getString("requestor","no data"));
        //tvTech.setText(prefs.getString("techid","no data"));
        daily = prefs.getString("daily","no data");
        device = prefs.getString("device","no data");
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
