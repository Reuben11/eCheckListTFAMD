package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
public class blade_replacement_buyoff extends Fragment {
    private AlertDialog alertDialog;
    private TextView tvEmp, tvEquipmentName, tvJR, tvTechID,tvDateTime, tvTechName, tvEmpName;
    private String empscode;
    private Button btnSubmit;
    private RadioGroup topside;
    View view;

    public blade_replacement_buyoff() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_blade_replacement_buyoff, container, false);
        btnSubmit = view.findViewById(R.id.buyoffsubmit);
        tvEmp = view.findViewById(R.id.msemp);
        tvJR = view.findViewById(R.id.jrnumber);
        tvEquipmentName = view.findViewById(R.id.equipmentname);
        tvTechID = view.findViewById(R.id.technicianid);
        tvTechName = view.findViewById(R.id.techname);
        tvDateTime = view.findViewById(R.id.nowdatetime);
        tvEmpName = view.findViewById(R.id.buyoffmsname);
        topside = view.findViewById(R.id.topside);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss,  dd MMM yy");
        String dateStr = df.format(c);
        tvDateTime.setText(dateStr);

        GetSharePreferences();

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                alertDialog = showJobRequestSubmitDialog();
                alertDialog.show();
            }
        });

        return view;
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

    private AlertDialog showJobRequestSubmitDialog(){
        return new AlertDialog.Builder(getActivity())
                .setTitle("Buy Off Submission")
                .setMessage("Please make sure all informations are correct before submission")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UpdateData();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .create();
    }

    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
    }

    public void UpdateData(){
        String msg = null;
        Boolean alert = true;

        if (topside.indexOfChild(view.findViewById(topside.getCheckedRadioButtonId())) != -1) {
                alert = false;
        }
        else{
            msg ="Invalid Topside Selection!";
            topside.requestFocusFromTouch();
        }


        if(alert==true){
            ShowAlert("Alert!", msg);
        }
        else{
            final String textData;
            String jr = tvJR.getText().toString().replace("-","");
            textData = "api/eCheckList?bladebuyoff={\"jr\":\"" + jr + "\",\"opid\":\""
                    + tvEmp.getText().toString() + "\",\"topside\":\""
                    + topside.indexOfChild(view.findViewById(topside.getCheckedRadioButtonId())) + "\",\"time\":\""
                    + tvDateTime.getText().toString() + "\",\"scode\":\""
                    + empscode + "\"}";

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://pngjvfa01")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Call<String> call = retrofit.create(allclass.GetBuyOffData.class).getBuyOffInfo(textData);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){

                        showToastMsg("Buy Off Submitted!");

                        Fragment newFragment = new TermOfUseOperator();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.master_container, TermOfUseOperator.newInstance());
                        transaction.commit();
                    }
                    else{
                        ShowAlert("No Response From Server!", "Please check the wireless connection. if problem persist, please contact IT");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    if(t instanceof IOException){
                        ShowAlert("Connection Error!", "Please check the wireless connection. if problem persist, please contact IT");
                    }
                }
            });


        }
    }

    protected void GetSharePreferences(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvTechID.setText(prefs.getString("techid","no data"));
        tvTechName.setText(prefs.getString("techname","no data"));
        tvJR.setText(prefs.getString("jr","no data"));
        tvEquipmentName.setText(prefs.getString("equipmentname","no data"));
        tvEmpName.setText(prefs.getString("empname", "no data"));
        tvEmp.setText(prefs.getString("empid","no data"));
        empscode = prefs.getString("scode","no data");
    }

}
