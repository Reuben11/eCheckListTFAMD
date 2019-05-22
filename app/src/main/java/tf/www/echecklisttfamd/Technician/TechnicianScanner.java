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
import tf.www.echecklisttfamd.Operator.OperatorActivity;
import tf.www.echecklisttfamd.Operator.OperatorJobRequestList;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianScanner extends Fragment {
    private String equipmentname, jr, msid, techScode,empScode;
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


          if(eBarcode.getText().toString().equals(equipmentname)){
              triggerMSInfo();
          }
          else{
                if(clearText==false){
                    ShowAlert("Scanner Bar Code Error Code : SBC0001", eBarcode.getText().toString() + "  Invalid Equipment Barcode!");
                }
          }


        }
    };

    private void triggerMSInfo(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eChecklist/GetEmployeeInfo?Empcode={\"scode\":\"" + empScode + "\"}";

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

        String link = "/api/eChecklist?TechHold={\"jr\":\"" + jr + "\",\"time\":\"" +  dateStr + "\",\"scode\":\"" + techScode + "\"}";


        Call<resultApi> call = retrofit.create(allclass.GetST.class).getSTDone(link);
        call.enqueue(new Callback<resultApi>() {
            @Override
            public void onResponse(Call<resultApi> call, Response<resultApi> response) {

                if (response.isSuccessful()) {
                    Fragment newFragment = new MachineSetup();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.master_container, ((MachineSetup) newFragment).newInstance());
                    transaction.commit();

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
        tvEquip.setText(equipmentname);

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

    protected void GetSharesPreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        equipmentname = prefs.getString("equipmentname","no data");
        jr = prefs.getString("jr","no data");
        empScode = prefs.getString("empscode", "no data");
        techScode = prefs.getString("scode","no data");
        msid = prefs.getString("requestor","no data");
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
