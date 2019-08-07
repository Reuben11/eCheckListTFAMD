package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
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
public class Device_Change_Setup_CheckList extends Fragment {
    private AlertDialog alertDialog;
    private TextView tvEmp, tvEquipmentName, tvDateTime, tvMsEmp, tvMsName;
    private Button btnSubmit;
    private EditText etDevice, etMesLot, etWaffepart;
    private RadioGroup rgOrientation, cbdailycheck;
    private RadioButton rbCon;
    private String daily = "false";
    private String Orientation, Scode;
       View view;

    TextWatcher textWatcherdevice = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           /* etMesLot.requestFocus();*/
        }
    };

    TextWatcher textWatchermeslot = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            /*etWaffepart.requestFocus();*/
        }
    };

    TextWatcher textWatcherwpackpart = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            /*rgOrientation.requestFocus();*/
        }
    };

    public static Device_Change_Setup_CheckList newInstance() {
        // Required empty public constructor
        Device_Change_Setup_CheckList fragment = new Device_Change_Setup_CheckList();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_device__change__setup__check_list, container, false);

        tvMsEmp = view.findViewById(R.id.msemp);
        tvEquipmentName = view.findViewById(R.id.changeequipmentname);
        tvDateTime = view.findViewById(R.id.nowdatetime);
        cbdailycheck = view.findViewById(R.id.type);
        rbCon = view.findViewById(R.id.typeD);
        tvMsName = view.findViewById(R.id.msname);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDateTime.setText(dateStr);


        etDevice = view.findViewById(R.id.device);
       /* etDevice.setShowSoftInputOnFocus(false);*/
        etDevice.addTextChangedListener(textWatcherdevice);
        etDevice.setLongClickable(false);

        etMesLot = view.findViewById(R.id.meslot);
    /*    etMesLot.setShowSoftInputOnFocus(false);*/
        etMesLot.addTextChangedListener(textWatchermeslot);
        etMesLot.setLongClickable(false);

        etWaffepart = view.findViewById(R.id.wpackpart);
    /*    etWaffepart.setShowSoftInputOnFocus(false);*/
        etWaffepart.addTextChangedListener(textWatcherwpackpart);
        etWaffepart.setLongClickable(false);

        rgOrientation = view.findViewById(R.id.radioorientation);


        btnSubmit = view.findViewById(R.id.jrsubmit);
        tvEmp = view.findViewById(R.id.ms);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                CreateData();

            }
        });

        /*if(savedInstanceState != null){*/
        GetSharePreference();
       /* }*/

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (alertDialog!=null) {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
            alertDialog = null;
        }
    }



    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
    }

    protected void GetSharePreference(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvEquipmentName.setText(prefs.getString("equipmentname","no data"));
        tvMsEmp.setText(prefs.getString("empid","no data"));
        tvMsName.setText(prefs.getString("empname", "no data"));
        Scode = prefs.getString("scode", "no data");
        if(prefs.getBoolean("daily",false)==true){
            cbdailycheck.getChildAt(0).setEnabled(false);
            ((RadioButton)cbdailycheck.getChildAt(1)).setChecked(true);
        }
        else{
            cbdailycheck.getChildAt(0).setEnabled(true);
            cbdailycheck.clearCheck();
        }
    }


    private void CreateData(){
        boolean alert = true;
        String msg = null;

        if(!TextUtils.isEmpty(etDevice.getText().toString())){
            if(!TextUtils.isEmpty(etMesLot.getText().toString())){
                if(!TextUtils.isEmpty(etWaffepart.getText().toString())){
                    if(rgOrientation.indexOfChild(view.findViewById(rgOrientation.getCheckedRadioButtonId())) != -1){
                        if(cbdailycheck.indexOfChild(view.findViewById(cbdailycheck.getCheckedRadioButtonId()))  != -1){
                            alert = false;
                        }
                        else{
                            msg = "Invalid Conversion Type!";
                            cbdailycheck.requestFocusFromTouch();
                        }
                    }
                    else {
                        msg = "Invalid Waffle Pack Orientation!";
                        rgOrientation.requestFocusFromTouch();
                    }
                }
                else{
                    msg = "Invalid Waffle Pack Part!";
                    etWaffepart.requestFocus();
                }
            }
            else{
                msg = "Invalid MES Lot!";
                etMesLot.requestFocus();
            }
        }
        else{
            msg = "Invalid Device!";
            etDevice.requestFocus();
        }

        if(alert == true){
            ShowAlert("Alert!", msg);
        }
        else{

            Orientation = String.valueOf(rgOrientation.indexOfChild(view.findViewById(rgOrientation.getCheckedRadioButtonId())));

            if(cbdailycheck.indexOfChild(view.findViewById(cbdailycheck.getCheckedRadioButtonId()))  == 0){
                daily = "true";
            }

            ShowAlertSubmit("Job Request Submission","Please make sure all informations are correct before submission");


        }
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

    private void ShowAlertSubmit(String title, String msg) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://pngjvfa01")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                String test = "api/eCheckList?datalist={\"equipment\":\"" + tvEquipmentName.getText().toString()
                        + "\",\"clid\":\"1\",\"time\":\"" + tvDateTime.getText().toString() + "\",\"daily\":\"" + daily + "\",\"emp\":\"" + tvMsEmp.getText().toString() + "\",\"device\":\""
                        +  etDevice.getText().toString() + "\",\"mes\":\"" + etMesLot.getText().toString() + "\",\"part\":\"" + etWaffepart.getText().toString() + "\","
                        + "\"orientation\":\"" + Orientation + "\",\"scode\":\"" + Scode + "\"}";


                Call<Device_Change_Setup_CheckList.jR> call = retrofit.create(allclass.CreateJR.class).getCreateJR(test);
                call.enqueue(new Callback<jR>() {
                    @Override
                    public void onResponse(Call<jR> call, Response<jR> response) {
                        if(response.isSuccessful()){
                            jR obj = response.body();

                            showToastMsg("Job Request JR " + obj.id + " created!");

                            Fragment newFragment = new TermOfUseOperator();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.master_container, TermOfUseOperator.newInstance());
                            /*transaction.addToBackStack(null);*/
                            transaction.commit();
                        }
                        else{
                            ShowAlert("Connection Error!", "Please check the wireless connection. if problem persist, please contact IT");
                        }
                    }

                    @Override
                    public void onFailure(Call<jR> call, Throwable t) {
                        if(t instanceof IOException){
                            ShowAlert("Connection Error!", "Please check the wireless connection. if problem persist, please contact IT");
                        }
                    }
                });


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

    public class jR{
        protected String id;

        public String getID() {
            return id;
        }

        public void setID(String ID) {
            this.id = ID;
        }
    }
}
