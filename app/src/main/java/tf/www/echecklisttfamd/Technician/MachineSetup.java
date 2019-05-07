package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
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
    private EditText evEstool, evNoofpins, eveScapno;
    private String device, daily;
    private Boolean UnicornDevice;
    private int option,rubberoption;
    private Button btnSubmit;
    private RadioGroup rgRubberApollo, rgRubberUnicorn;
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
        RelativeLayout unicorn = view.findViewById(R.id.unicornrubbertip);
        RelativeLayout apollo = view.findViewById(R.id.apollorubbertip);
        tvEquipment = view.findViewById(R.id.equipmentname);
        tvJR = view.findViewById(R.id.jrnumber);
        tvDatetime = view.findViewById(R.id.nowdatetime);
        tvTech = view.findViewById(R.id.techemp);
        tvRequestor = view.findViewById(R.id.requestorid);
        tvDaily = view.findViewById(R.id.purposeoption);
        evEstool = view.findViewById(R.id.estool);
        evNoofpins = view.findViewById(R.id.noofpins);
        eveScapno = view.findViewById(R.id.escapno);
        rgRubberApollo = view.findViewById(R.id.rubberapollo);
        rgRubberUnicorn = view.findViewById(R.id.rubberunicorn);
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
            UnicornDevice = true;
            option =1;
        }
        else{
            if(device.equals("6101") ){
                unicorn6101.setVisibility(View.VISIBLE);
                UnicornDevice = true;
                option=2;
            }
            else{
                UnicornDevice = false;
                option=0;
            }

        }

        if(UnicornDevice==true){
            unicorn.setVisibility(View.VISIBLE);
            rubberoption=1;
        }
        else{
            apollo.setVisibility(View.VISIBLE);
            rubberoption=0;
        }

        btnSubmit = view.findViewById(R.id.setupsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                CreateData();
            }
        });
        return view;
    }

    protected void getAll(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvJR.setText(prefs.getString("jr","no data"));
        tvEquipment.setText(prefs.getString("equipmentname","no data"));
        tvRequestor.setText(prefs.getString("requestor","no data"));
        tvTech.setText(prefs.getString("techid","no data"));
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

    private void CreateData(){
        String data, jr, rubbertip;
        int optionrg, id;
        jr = tvJR.getText().toString().replace("-","");


        if(rubberoption==0){
            optionrg = rgRubberApollo.indexOfChild(view.findViewById(rgRubberApollo.getCheckedRadioButtonId()));
        }
        else{
            optionrg= rgRubberUnicorn.indexOfChild(view.findViewById(rgRubberUnicorn.getCheckedRadioButtonId())); //rgRubberUnicorn.getCheckedRadioButtonId();
        }

        rubbertip = String.valueOf(optionrg);



        data = "{\"jr\":\"" + jr + "\",\"techid\":\""
                + tvTech.getText().toString() + "\",\"time\":"
                + tvDatetime.getText().toString() + "\",\"rubber\":"
                + rubbertip + "\",\"config\":"
                + evEstool.getText().toString() + "\",\"pins\":\""
                + evNoofpins.getText().toString() + "\",\"escap\":\""
                + eveScapno.getText().toString();
        ShowAlert("Rubbertip", data);
    }

}
