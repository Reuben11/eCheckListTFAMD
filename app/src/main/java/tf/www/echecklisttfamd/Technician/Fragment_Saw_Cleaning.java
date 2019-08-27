package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Saw_Cleaning extends Fragment {
    private TextView tvEquipment, tvDatetime, tvTech, tvTechName, tvShift;
    private EditText etRemarks;
    private String scode, shift;
    private RadioGroup rgchuck, rgspinner, rgtransport;
    private Button btnSubmit;
    View view;

    public static Fragment_Saw_Cleaning newInstance() {
        // Required empty public constructor
        Fragment_Saw_Cleaning Fragment = new Fragment_Saw_Cleaning();
        return  Fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saw_cleaning, container, false);

        tvTech = view.findViewById(R.id.techemp);
        tvTechName = view.findViewById(R.id.techname);
        tvDatetime = view.findViewById(R.id.nowdatetime);
        tvEquipment= view.findViewById(R.id.equipmentname);
        tvShift = view.findViewById(R.id.shift);
        etRemarks = view.findViewById(R.id.remarks);
        rgchuck = view.findViewById(R.id.chuck);
        rgspinner = view.findViewById(R.id.spinnert);
        rgtransport = view.findViewById(R.id.transport);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDatetime.setText(dateStr);

        getAll();

        btnSubmit = view.findViewById(R.id.setupsubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                if(checkAnswer()==true){
                    ShowAlertSubmit("Machine Setup Submission","Please make sure all informations are correct before submission");
                }
            }
        });
        return view;
    }

    private void ShowAlertSubmit(String title, String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CreateData();
            }
        });

        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }

    private Boolean checkAnswer(){
        Boolean ok = false;

        if(rgchuck.indexOfChild(view.findViewById(rgchuck.getCheckedRadioButtonId())) != -1){
            if(rgspinner.indexOfChild(view.findViewById(rgspinner.getCheckedRadioButtonId())) != -1) {
                if(rgtransport.indexOfChild(view.findViewById(rgtransport.getCheckedRadioButtonId())) != -1) {
                    ok = true;
                }else{
                    rgtransport.requestFocusFromTouch();
                    showToastMsg("Invalid Transport Arms Selection!");
                }
            }else{
                rgspinner.requestFocusFromTouch();
                showToastMsg("Invalid Spinner Selection!");
            }
        }else{
            rgchuck.requestFocusFromTouch();
            showToastMsg("Invalid Chuck Selection!");
        }

        return ok;
    }

    private void CreateData(){
        String url;
        url = "/api/eChecklist?CreateSawCleaning={\""
                + "equipment\":\"" + tvEquipment.getText().toString() + "\",\""
                + "techid\":\"" + tvTech.getText().toString() + "\",\""
                + "techscode\":\"" + scode + "\",\""
                + "shift\":\"" + tvShift.getText().toString() + "\",\""
                + "chuck\":\"" + rgchuck.indexOfChild(view.findViewById(rgchuck.getCheckedRadioButtonId())) + "\",\""
                + "spinner\":\"" + rgspinner.indexOfChild(view.findViewById(rgspinner.getCheckedRadioButtonId())) + "\",\""
                + "transport\":\"" + rgtransport.indexOfChild(view.findViewById(rgtransport.getCheckedRadioButtonId())) + "\",\""
                + "remarks\":\"" + etRemarks.getText().toString() + "\"}";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(allclass.CreateCleaning.class).CreateData(url);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    showToastMsg("Cleaning Record Updated!");

                    Fragment newFragment = new TermOfUseTechnician();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.master_container, TermOfUseTechnician.newInstance());
                    transaction.commit();

                }else{
                    ShowAlert("No Response From Server!", "Please check the wireless connection. if problem persist, please contact IT");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(t instanceof IOException){
                    ShowAlert("Connection Error!", "Server Error!");
                }
            }
        });

        showToastMsg("Cleaning Record Updated!");

        Fragment newFragment = new TermOfUseTechnician();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.master_container, TermOfUseTechnician.newInstance());
        transaction.commit();

    }

    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
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

    protected void getAll(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvEquipment.setText(prefs.getString("equipmentname","no data"));
        tvTech.setText(prefs.getString("techid","no data"));
        tvTechName.setText(prefs.getString("techname", "no data").toUpperCase());
        scode = prefs.getString("scode", "no data");
        tvShift.setText(prefs.getString("shift", "no data"));
    }

}