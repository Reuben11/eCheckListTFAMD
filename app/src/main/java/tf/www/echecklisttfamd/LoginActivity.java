package tf.www.echecklisttfamd;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.OperatorActivity;
import tf.www.echecklisttfamd.Operator.OperatorScanner;
import tf.www.echecklisttfamd.Technician.TechnicianActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText etempid;
    private TextView tvSecurityID, tvVersionName;
    private Boolean clearText;

    private long delay = 1000;

    private TimerTask task = new TimerTask(){
        @Override
        public void run() {
            // TDO Auto-generated method stub
           /* startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
            finish();*/
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ClearInfo();
        etempid = findViewById(R.id.EmpId);
        tvSecurityID = findViewById(R.id.SecurityId);
        tvVersionName = findViewById(R.id.versionname);
        displayVersionName();
        /*Button technician, operator;
        technician = findViewById(R.id.button2);
        operator = findViewById(R.id.button1);*/
        clearText = false;
      /*  Timer timer = new Timer();
        timer.schedule(task,delay);*/

        etempid.setShowSoftInputOnFocus(false);
        etempid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /*SetEmp(etempid.getText().toString());*/
                if(clearText==false){
                    triggerJobRequest();
                }

            }
        });

    }

    private void displayVersionName() {
        tvVersionName.setText("Version : " + BuildConfig.VERSION_NAME);
    }

    private void triggerJobRequest(){
        final String[] listitems = new String[]{"Operator","Technician"};


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String link = "/api/eChecklist/GetEmployeeInfo?Empcode={\"scode\":\"" + etempid.getText().toString() + "\"}";

        Call<EmpInfo> call = retrofit.create(allclass.GetEmpInfo.class).getEmpData(link);
        call.enqueue(new Callback<EmpInfo>() {
            @Override
            public void onResponse(Call<EmpInfo> call, Response<EmpInfo> response) {

                if (response.isSuccessful()) {
                    EmpInfo obj = response.body();
                    if (obj.employeeid.equals("na")) {
                        if(clearText==false) {
                            ShowAlert("Alert!!", "Invalid User!");
                        }
                    } else {

                        if(obj.employeeid.equals("495870") || obj.employeeid.equals("493740") || obj.employeeid.equals("495550") || obj.employeeid.equals("496326") || obj.employeeid.equals("342036") || obj.employeeid.equals("1002316")){
                            final String empid = obj.employeeid;
                            final String jobtitle = obj.jobtitle;
                            final String name = obj.employeename;

                            AlertDialog.Builder mBuilder = new AlertDialog.Builder(LoginActivity.this);
                            mBuilder.setTitle("Select Login Type");
                            mBuilder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if(which==0){
                                        SetEmp(empid, name, jobtitle, etempid.getText().toString());
                                        startActivity(new Intent(LoginActivity.this, OperatorActivity.class));
                                        finish();
                                    }
                                    else{
                                           SetTech(empid, name, jobtitle, etempid.getText().toString());
                                           startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
                                        finish();
                                    }
                                }


                            });
                            mBuilder.setCancelable(false);
                            AlertDialog mDialog = mBuilder.create();
                            mDialog.show();
                        }
                        else{
                            String[] last = obj.jobtitle.split("\\s");
                            Integer CaseNumber = 0;
                            for(int i = 0; i <= last.length - 1; i++ ){
                                if(last[i].matches("[Oo][Pp][Ee][Rr][Aa][Tt][Oo][Rr]")==true){
                                    /*if(obj.jobtitle.matches("^[Oo][Pp][Ee][Rr][Aa][Tt][Oo][Rr]")==true){*/
                                    CaseNumber =1;

                                    if(last.length > i+1){
                                        if(last[i+1].matches("[Tt][Ee][Cc][Hh][Nn][Ii][Cc][Ii][Aa][Nn]")==true){
                                            CaseNumber =2;
                                        }
                                    }
                                   break;
                                }
                                else if(last[i].matches("[Tt][Ee][Cc][Hh][Nn][Ii][Cc][Ii][Aa][Nn]")==true){
                                    /*else if(last[last.length - 1].matches("[Tt][Ee][Cc][Hh][Nn][Ii][Cc][Ii][Aa][Nn]")==true){*/
                                    CaseNumber =2;
                                    break;
                                }
                                else if(last[i].matches("[Ee][Qq][Uu][Ii][Pp][Mm][Ee][Nn][Tt]")==true) {
                                    /*else if(last[last.length - 1].matches("[Tt][Ee][Cc][Hh][Nn][Ii][Cc][Ii][Aa][Nn]")==true){*/
                                    CaseNumber =3;
                                    break;
                                }
                                else if(last[i].matches("[Ee][Xx][Pp][Ee][Rr][Tt]")==true) {
                                    /*else if(last[last.length - 1].matches("[Tt][Ee][Cc][Hh][Nn][Ii][Cc][Ii][Aa][Nn]")==true){*/
                                    CaseNumber =3;
                                    break;
                                }
                            }


                            if(CaseNumber > 0){
                                if(CaseNumber==1){
                                    SetEmp(obj.employeeid, obj.employeename, obj.getJobtitle(), etempid.getText().toString());
                                    startActivity(new Intent(LoginActivity.this, OperatorActivity.class));
                                    finish();
                                }
                                else if(CaseNumber==2){
                                    SetTech(obj.employeeid, obj.employeename, obj.getJobtitle(), etempid.getText().toString());
                                    startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
                                    finish();
                                }
                                else if(CaseNumber==3){
                                    SetTech(obj.employeeid, obj.employeename, obj.getJobtitle(), etempid.getText().toString());
                                    startActivity(new Intent(LoginActivity.this, TechnicianActivity.class));
                                    finish();
                                }
                            }
                            else{
                                ShowAlert("Alert!!", "Invalid User!");
                            }
                        }
                    }

                } else {
                    ShowAlert("Server Error!", "No Response from Server!");
                }
            }

            @Override
            public void onFailure(Call<EmpInfo> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
                if (t instanceof IOException) {
                    ShowAlert("Server Connection Error Code: SCE0002!", "Can't Communicate With Server Connection");
                } else {

                    ShowAlert("Alert!!", "Application Issue!");
                }

            }
        });
    }

    protected  void ClearInfo(){
        SharedPreferences prefs = getApplication().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        prefs.edit().clear().commit();
        prefs = getApplication().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        prefs.edit().clear().commit();
    }

    protected void SetEmp(String empid,String empname, String jobtitle, String scode) {
        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("scode", scode);
        editor.putString("empid", empid);
        editor.putString("empname", empname.toUpperCase());
        editor.putString("empjobtitle", jobtitle);
        editor.commit();

    }


    protected void SetTech(String techid,String empname, String jobtitle, String scode) {

        SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("scode", scode);
        editor.putString("techid", techid);
        editor.putString("techname", empname);
        editor.putString("techjobtitle", jobtitle);
        editor.commit();

    }

    public class EmpInfo{
        protected String employeeid;
        protected String employeename;
        protected String jobtitle;

        public String getEmployeeid() {
            return employeeid;
        }

        public void setEmployeeid(String employeeid) {
            this.employeeid = employeeid;
        }

        public String getEmployeename() {
            return employeename;
        }

        public void setEmployeename(String employeename) {
            this.employeename = employeename;
        }

        public String getJobtitle() {
            return jobtitle;
        }

        public void setJobtitle(String jobtitle) {
            this.jobtitle = jobtitle;
        }
    }


    private void ShowAlert(String title, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               clearText = true;
                etempid.getText().clear();
                clearText = false;
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }


}
