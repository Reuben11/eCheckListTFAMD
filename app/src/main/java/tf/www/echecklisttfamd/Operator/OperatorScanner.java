package tf.www.echecklisttfamd.Operator;


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
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Retrofit;
import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.Technician.MachineSetup;
import tf.www.echecklisttfamd.Technician.TechnicianActivity;
import tf.www.echecklisttfamd.Technician.TechnicianScanner;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorScanner extends Fragment {
    private EditText eBarcode;
    private TextView tvBarcodeLabel, tvEquipmentID;
    private String link, type, empID, techID, techscode, jr, empscode, checkListType, area, checklist;
    private Boolean clearText;
    private Boolean pass = true;
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
           Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss , dd-MMM-yy");
            String dateStr = df.format(c);


           switch (type){
               case "1":
                   if (clearText == false) {
                       triggerJobRequest(dateStr);
                   }
                   break;
               case "2":
                   if(eBarcode.getText().toString().equals(tvEquipmentID.getText().toString())){
                       triggerTechInfo();
                   }
                   else{
                       if(clearText==false){
                           ShowAlert("Scanner Bar Code Error Code : SBC0001", eBarcode.getText().toString() + "  Invalid Equipment Barcode!");
                       }
                   }

                   break;

           }

        }
    };

    private void triggerTechInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eCheckList?Empcode={\"scode\":\"" + techscode + "\"}";

        Call<LoginActivity.EmpInfo> call = retrofit.create(allclass.GetEmpInfo.class).getEmpData(link);
        call.enqueue(new Callback<LoginActivity.EmpInfo>() {
            @Override
            public void onResponse(Call<LoginActivity.EmpInfo> call, Response<LoginActivity.EmpInfo> response) {

                if (response.isSuccessful()) {
                    LoginActivity.EmpInfo obj = response.body();
                    SetTechName(obj.getEmployeename().toUpperCase());
                    triggerOperatorHold();

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

    private void triggerOperatorHold(){
        jr = jr.replace("-","");
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss,  dd MMM yy");
        String dateStr = df.format(c);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eCheckList?OpeHold={\"jr\":\"" + jr + "\",\"time\":\"" +  dateStr + "\",\"scode\":\"" + empscode + "\"}";

        Call<TechnicianScanner.resultApi> call = retrofit.create(allclass.GetST.class).getSTDone(link);
        call.enqueue(new Callback<TechnicianScanner.resultApi>() {
            @Override
            public void onResponse(Call<TechnicianScanner.resultApi> call, Response<TechnicianScanner.resultApi> response) {

                if (response.isSuccessful()) {
                  if(checklist.equals("Device Change Setup Checklist")) {
                      Fragment newFragment = new Buy_Off_Check_List();
                      FragmentTransaction transaction = getFragmentManager().beginTransaction();
                      transaction.replace(R.id.master_container, newFragment);
                      transaction.commit();
                  }
                     else if(checklist.equals("Blade Replacement")) {
                      Fragment newFragment = new blade_replacement_buyoff();
                      FragmentTransaction transaction = getFragmentManager().beginTransaction();
                      transaction.replace(R.id.master_container, newFragment);
                      transaction.commit();
                  }else {
                      ShowAlert("Alert!", "Invalid Checklist!");
                  }
                }
            }

            @Override
            public void onFailure(Call<TechnicianScanner.resultApi> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
                if (t instanceof IOException) {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                } else {

                    ShowAlert("Alert!!", "Application Issue!");
                }

            }
        });
    }

    private void triggerJobRequest(String dateStr){
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://pngjvfa01")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    link = "/api/eCheckList?checkInfo={\"name\":\"" + eBarcode.getText().toString() + "\",\"time\":\"" + dateStr + "\",\"area\":\"" + area + "\"}";
                    /*Toast.makeText(getContext(), eBarcode.getText().toString(), Toast.LENGTH_SHORT).show();*/


                    Call<GetExist> call = retrofit.create(allclass.CheckJR.class).getJRCheckData(link);
                    call.enqueue(new Callback<GetExist>() {
                        @Override
                        public void onResponse(Call<GetExist> call, Response<GetExist> response) {

                            if (response.isSuccessful()) {
                                GetExist obj = response.body();

                                if (obj.equip == true) {
                                    if (obj.exist == true) {
                                        ShowAlert("Job Pending Error Code : JRE0001", "This Equipment Still Pending For Previous Job Request!");
                                    } else {
                                        /*ValidateCert();*/
                                        SetEquipmentName();
                                        if(checkListType.equals("Device Change Setup")){
                                            if(pass==true){
                                                SetDaily(obj.daily);

                                                Fragment newFragment = new Device_Change_Setup_CheckList();
                                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                transaction.replace(R.id.master_container, newFragment);
                                                transaction.commit();
                                            }
                                            else{
                                                ShowAlert("Alert!", "Employee Not Certified In LMS!");
                                            }
                                        }
                                        else if(checkListType.equals("Disco DFD 6361 Machine Blade Change")){
                                                Fragment newFragment = new Disco_DFD_6361_Machine_Blade_Change();
                                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                                transaction.replace(R.id.master_container, newFragment);
                                                transaction.commit();
                                        }
                                        else{
                                            ShowAlert("Alert!", "CheckList Not Available!");
                                        }


                                    }
                                } else {
                                    ShowAlert("Alert!", "Invalid Equipment For This CheckList!");
                                }

                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<GetExist> call, Throwable t) {
                            if (t instanceof IOException) {
                                ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                            } else {
                                // todo log to some central bug tracking service
                            }

                        }
                    });


    }


    public static OperatorScanner newInstance() {
        // Required empty public constructor
        OperatorScanner fragment = new OperatorScanner();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_operator_equipment_scanner, container, false);

        eBarcode = view.findViewById(R.id.operatorbarcodescanner);
        tvBarcodeLabel = view.findViewById(R.id.operatorbarcodelabe1);
        tvEquipmentID = view.findViewById(R.id.equipmentid);
        clearText = false;
        eBarcode.setShowSoftInputOnFocus(false);
        eBarcode.addTextChangedListener(textWatcher);
        eBarcode.requestFocus();
        GetTypeName();
        GetEmpID();
        GetChecklist();
        if(type.equals("2")){
            GetEquipmentName();
        }


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }

    public class GetExist{
        @Expose
        protected boolean exist;
        protected boolean daily;
        protected boolean equip;

        public boolean isEquip() {
            return equip;
        }

        public void setEquip(boolean equip) {
            this.equip = equip;
        }

        public boolean isDaily() {
            return daily;
        }

        public void setDaily(boolean daily) {
            this.daily = daily;
        }

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }
    }

    protected void SetTechName(String Name) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("techname", Name);
        editor.commit();
    }

    protected void SetEquipmentName() {
       /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("equipmentname", eBarcode.getText().toString());
        editor.commit();
    }

    protected void SetDaily(boolean daily) {
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putBoolean("daily", daily);
        editor.commit();
    }

    protected void GetEmpID(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        empID = prefs.getString("emp","no data");
        empscode = prefs.getString("scode","no data");
        techID = prefs.getString("techid","na data");
        techscode = prefs.getString("techscode", "no data");
        checkListType = prefs.getString("checklist", "no data");
        area  = prefs.getString("area", "no data");

    }

    protected void GetTypeName(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        type = prefs.getString("type","no data");
    }

    protected void GetEquipmentName(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvEquipmentID.setText(prefs.getString("equipmentname","no data"));
        jr = prefs.getString("jr","no data");
    }

    protected void GetChecklist(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        checklist = prefs.getString("checklistname", "no data");
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
