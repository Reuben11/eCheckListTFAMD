package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
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
 * Use the {@link Disco_DFD_6361_Machine_Blade_Change#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Disco_DFD_6361_Machine_Blade_Change extends Fragment {
    private TextView tvEmp, tvEquipmentName, tvDateTime, tvMsEmp, tvMsName;
    private String  Scode;
    private CheckBox cbz1, cbz2, cbz1newblade, cbz2newblade;
    private RadioGroup rgbladegroup, rgz1bladecondition, rgz2bladecondition;
    private EditText z1bladeline, z2bladeline;
    private Button bnsubmit;
    private String changez1, changez2, usedz1, usedz2, lifez1, lifez2, newz1, newz2;


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
        cbz1 = view.findViewById(R.id.z1);
        cbz2 = view.findViewById(R.id.z2);
        rgz1bladecondition = view.findViewById(R.id.z1conditiontype);
        rgz2bladecondition = view.findViewById(R.id.z2conditiontype);
        rgbladegroup = view.findViewById(R.id.cbladegrouptype);
        z1bladeline = view.findViewById(R.id.bladelifez1);
        z2bladeline = view.findViewById(R.id.bladelifez2);
        cbz1newblade = view.findViewById(R.id.newconditionz1);
        cbz2newblade = view.findViewById(R.id.newconditionz2);
        bnsubmit = view.findViewById(R.id.jrsubmit);

        z1bladecondition(false);
        z2bladecondition(false);

        cbz1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    z1bladecondition(true);
                }
                else{
                    z1bladecondition(false);
                }
            }
        });

        cbz2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    z2bladecondition(true);
                }
                else{
                    z2bladecondition(false);
                }
            }
        });

        bnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

                /*ShowAlertSubmit("Job Request Submission","Please make sure all informations are correct before submission");*/
            }
        });

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDateTime.setText(dateStr);

        GetSharePreference();


        ImageView b = view.findViewById(R.id.infobladegroup);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                saw_blade_table dialog = new saw_blade_table ();
                dialog .show(getFragmentManager(), "Infomations");
            }
        });


        return view;
    }

    private  void validation(){
        Boolean z1, z2, finalsubmit;
        changez1 = "0";
        changez2 = "0";
        usedz1 ="na";
        usedz2 = "na";
        lifez1 = "na";
        lifez2 =  "na";
        newz1 = "0";
        newz2 = "0";
        z1 = false;
        z2 = false;
        finalsubmit = false;


        if(cbz1.isChecked()){
            changez1 = "1";
            lifez1 = z1bladeline.getText().toString();
        }

        if(cbz2.isChecked()){
            changez2 = "1";
            lifez2 = z2bladeline.getText().toString();
        }

        usedz1 = Integer.toString(rgz1bladecondition.indexOfChild(view.findViewById(rgz1bladecondition.getCheckedRadioButtonId())));
        usedz2 = Integer.toString(rgz2bladecondition.indexOfChild(view.findViewById(rgz2bladecondition.getCheckedRadioButtonId())));

        if(cbz1newblade.isChecked()){
            newz1 = "1";
        }

        if(cbz2newblade.isChecked()){
            newz2 = "1";
        }

        if(changez1 != "0" || changez2 != "0"){
            if(rgbladegroup.indexOfChild(view.findViewById(rgbladegroup.getCheckedRadioButtonId())) != -1){
                if(changez1.equals("1")){
                    if(rgz1bladecondition.indexOfChild(view.findViewById(rgz1bladecondition.getCheckedRadioButtonId())) != -1){
                        if(!TextUtils.isEmpty(z1bladeline.getText().toString())){
                            if(newz1.equals("1")){
                                z1 = true;
                            }else{
                                ShowAlert("Alert!", "Invalid New Blade Condition!");
                                cbz1newblade.requestFocus();
                            }

                        }else{
                            ShowAlert("Alert!", "Invalid Blade Life Line Input!");
                            z1bladeline.requestFocus();
                        }
                    }else {
                        ShowAlert("Alert!", "Invalid Used Blade Condition Selection!");
                        rgz1bladecondition.requestFocus();
                    }

                }

                if(changez2.equals("1")){
                    if(rgz2bladecondition.indexOfChild(view.findViewById(rgz2bladecondition.getCheckedRadioButtonId())) != -1){
                        if(!TextUtils.isEmpty(z2bladeline.getText().toString())){
                            if(newz2.equals("1")){
                                z2 = true;
                            }else{
                                ShowAlert("Alert!", "Invalid New Blade Condition!");
                                cbz2newblade.requestFocus();
                            }

                        }else{
                            ShowAlert("Alert!", "Invalid Blade Life Line Input!");
                            z2bladeline.requestFocus();
                        }
                    }else {
                        ShowAlert("Alert!", "Invalid Used Blade Condition Selection!");
                        rgz2bladecondition.requestFocus();
                    }

                }
            }else
            {
                ShowAlert("Alert!", "Invalid Blade Group Selection!");
                rgbladegroup.requestFocus();
            }


        }
        else{
            ShowAlert("Alert!", "Invalid Blade Change Selection!");
            if(changez1.equals("0")){
                cbz1.requestFocus();
            }else{
                cbz2.requestFocus();
            }
        }

        if(changez1.equals("1") && changez2.equals("0")){
            if(z1==true){
                ShowAlertSubmit("Job Request Submission","Please make sure all informations are correct before submission");
            }
        }else if(changez2.equals("1") && changez1.equals("0")){
            if(z2==true){
                ShowAlertSubmit("Job Request Submission","Please make sure all informations are correct before submission");
            }
        }else if(changez1.equals("1") && changez2.equals("1")){
            if(z1==true && z2==true){
                ShowAlertSubmit("Job Request Submission","Please make sure all informations are correct before submission");
            }
        }

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



                String test = "api/eCheckListTest?datalist={\"equipment\":\"" + tvEquipmentName.getText().toString()
                        + "\",\"clid\":\"2\",\"time\":\"" + tvDateTime.getText().toString() + "\",\"emp\":\"" + tvMsEmp.getText().toString() + "\",\"scode\":\"" + Scode + "\","
                        + "\"z1\":\"" + changez1 + "\"" + ",\"z2\":\"" + changez2 + "\",\"group\":\"" + rgbladegroup.indexOfChild(view.findViewById(rgbladegroup.getCheckedRadioButtonId())) + "\",\""
                        + "usedz1\":\"" + usedz1 + "\",\"usdez2\":\"" + usedz2  + "\",\"newz1\":\"" + newz1 + "\",\"newz2\":\"" + newz2 + "\",\""
                        + "lifez1\":\"" + newz1 + "\",\"lifez2\":\"" + newz2 + "\"}";

                Call<Device_Change_Setup_CheckList.jR> call = retrofit.create(allclass.CreateJR.class).getCreateJR(test);
                call.enqueue(new Callback<Device_Change_Setup_CheckList.jR>() {
                    @Override
                    public void onResponse(Call<Device_Change_Setup_CheckList.jR> call, Response<Device_Change_Setup_CheckList.jR> response) {
                        if(response.isSuccessful()){
                            Device_Change_Setup_CheckList.jR obj = response.body();

                            showToastMsg("Job Request JR " + obj.id + " created!");

                            Fragment newFragment = new TermOfUseOperator();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();
                            transaction.replace(R.id.master_container, TermOfUseOperator.newInstance());
                            /*transaction.addToBackStack(null);*/
                            transaction.commit();
                        }
                        else{
                            ShowAlert("Connection Error!", "Invalid Data!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Device_Change_Setup_CheckList.jR> call, Throwable t) {
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

    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
    }

    private void z1bladecondition(Boolean option){
        rgz1bladecondition.getChildAt(0).setEnabled(option);
        rgz1bladecondition.getChildAt(1).setEnabled(option);
        z1bladeline.setEnabled(option);
        cbz1newblade.setEnabled(option);
    }

    private void z2bladecondition(Boolean option){
        rgz2bladecondition.getChildAt(0).setEnabled(option);
        rgz2bladecondition.getChildAt(1).setEnabled(option);
        z2bladeline.setEnabled(option);
        cbz2newblade.setEnabled(option);
    }
}
