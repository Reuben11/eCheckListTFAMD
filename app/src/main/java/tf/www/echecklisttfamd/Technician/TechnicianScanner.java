package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.Expose;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.Disco_DFD_6361_Machine_Blade_Change;
import tf.www.echecklisttfamd.Operator.OperatorActivity;
import tf.www.echecklisttfamd.Operator.OperatorJobRequestList;
import tf.www.echecklisttfamd.Operator.OperatorScanner;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianScanner extends Fragment {
    private String equipmentname, jr, msid, techScode, empScode, area;
    private EditText eBarcode;
    private Boolean clearText;
    private TextView tvEquip;

    View view;

    TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (eBarcode.getText().toString().equals("")){

            }else{
            if(area.equals("0")){
                Date c = Calendar.getInstance().getTime();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss , dd-MMM-yy");
                String dateStr = df.format(c);
                GetValidation(dateStr);
            }else {
                if (eBarcode.getText().toString().equals(equipmentname)) {
                    triggerMSInfo();
                } else {
                    if (clearText == false) {
                        ShowAlert("Scanner Bar Code Error Code : SBC0001", eBarcode.getText().toString() + "  Invalid Equipment Barcode!");
                    }
                }
            }
            }
        }
    };

    private void triggerMSInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eCheckList?Empcode={\"scode\":\"" + techScode + "\"}";

        Call<LoginActivity.EmpInfo> call = retrofit.create(allclass.GetEmpInfo.class).getEmpData(link);
        call.enqueue(new Callback<LoginActivity.EmpInfo>() {
            @Override
            public void onResponse(Call<LoginActivity.EmpInfo> call, Response<LoginActivity.EmpInfo> response) {

                if (response.isSuccessful()) {
                    LoginActivity.EmpInfo obj = response.body();
                    SetMsName(obj.getEmployeename().toUpperCase());

                    triggerJobRequest();
                } else {
                    ShowAlert("Server Error!", "No Response from Server!");
                }
            }

            @Override
            public void onFailure(Call<LoginActivity.EmpInfo> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
                if (t instanceof IOException) {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                } else {

                    ShowAlert("Alert!!", "Application Issue!");
                }

            }
        });

    }


    private void triggerJobRequest(){
         jr = jr.replace("-","");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eCheckList?TechHold={\"jr\":\"" + jr + "\",\"time\":\"" +  dateStr + "\",\"scode\":\"" + empScode + "\"}";


        Call<resultApi> call = retrofit.create(allclass.GetST.class).getSTDone(link);
        call.enqueue(new Callback<resultApi>() {
            @Override
            public void onResponse(Call<resultApi> call, Response<resultApi> response) {
                Fragment newFragment;
                FragmentTransaction transaction;
                if (response.isSuccessful()) {
                    switch (area) {
                        case "1":
                            newFragment = new MachineSetup();
                            transaction = getFragmentManager().beginTransaction();
//                            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                            transaction.replace(R.id.master_container, MachineSetup.newInstance());
                            transaction.commit();
                            break;
                        case "2":
                            newFragment = new saw_blade_replacement();
                            transaction = getFragmentManager().beginTransaction();
//                            transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                            transaction.replace(R.id.master_container, saw_blade_replacement.newInstance());
                            transaction.commit();
                    }

//                    if (area=="1"){
//                        Fragment newFragment = new MachineSetup();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                        transaction.replace(R.id.master_container, MachineSetup.newInstance());
//                        transaction.commit();
//                    }
//                    else if(area=="2"){
//                        Fragment newFragment = new saw_blade_replacement();
//                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                        transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
//                        transaction.replace(R.id.master_container, saw_blade_replacement.newInstance());
//                        transaction.commit();
//                    }
//
//                    else{
//                        ShowAlert("Alert!", "Invalid Equipment For This CheckList!");
//                    }


                } else {
                    ShowAlert("Server Error!", "No Response from Server for update!");
                }
            }

            @Override
            public void onFailure(Call<resultApi> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
                if (t instanceof IOException) {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                } else {

                    ShowAlert("Alert!!", "Application Issue!");
                }

            }
        });
    }


    private void GetValidation(String dateStr){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eChecklist?cleanequipment=" + eBarcode.getText().toString();

        /*Toast.makeText(getContext(), link, Toast.LENGTH_SHORT).show();*/

        Call<String> call = retrofit.create(allclass.CheckJRT.class).getCheckData(link);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {//
                    try {
                        JSONObject obj = new JSONObject(response.body());
                        if (obj.getBoolean("equip")==true) {
                            if(obj.getBoolean("exist")==false){
                                SetEquipmentName(eBarcode.getText().toString());
                                Fragment newFragment = new MachineSetup();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                                transaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                                transaction.replace(R.id.master_container, Fragment_Saw_Cleaning.newInstance());
                                transaction.commit();
                            }else{
                                ShowAlert("Alert!", "Equipment Already Done Cleaning!");
                            }
                    } else {
                        ShowAlert("Alert!", "Invalid Equipment For This CheckList!");
                    }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



                } else {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (t instanceof IOException) {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                } else {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                    // todo log to some central bug tracking service
                }
            }
        });

    }


    public class resultApi{
        protected String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
    }

    public static TechnicianScanner newInstance() {
        // Required empty public constructor
        TechnicianScanner fragment = new TechnicianScanner();
        return  fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_technician_scanner, container, false);
        tvEquip = view.findViewById(R.id.equipmentid);
        GetSharesPreferences();
        eBarcode = view.findViewById(R.id.technicianbarcodescanner);
        eBarcode.setShowSoftInputOnFocus(false);
        eBarcode.addTextChangedListener(textWatcher);
        eBarcode.requestFocus();
        clearText = false;

        if(area.equals("0")){

        }else{
            tvEquip.setText(equipmentname);
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

    }

    protected void SetMsName(String Name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("msname", Name);
        editor.commit();
    }

    protected void SetEquipmentName(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("equipmentname", name);
        editor.commit();
    }

    protected void GetSharesPreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        equipmentname = prefs.getString("equipmentname","no data");
        jr = prefs.getString("jr","no data");
        empScode = prefs.getString("empscode", "no data");
        techScode = prefs.getString("scode","no data");
        msid = prefs.getString("requestor","no data");
        area = prefs.getString("area","no data");
    }

    private void ShowAlert(String title, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearText = true;
                eBarcode.getText().clear();
                clearText = false;
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }
}
