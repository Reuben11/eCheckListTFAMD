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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.TermOfUseOperator;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class saw_blade_replacement extends Fragment {
    private TextView tvEquipment, tvJR, tvDatetime, tvTech, tvRequestor, tvDaily, tvRequestorName, tvTechName;
    private String scode;
    private RadioGroup hubmountz1, hubmountz2, coolerbarz1, coolerbarz2, verifybladez1, verifybladez2, bladetypez1, bladetypez2, ncsprismz1, ncsprismz2, bladesetupz1, bladesetupz2, bladedressingz1, bladedressingz2, bladebbdsetupz1, bladebbdsetupz2, tapez1, tapez2, waferbackside;
    private EditText bladelifelinez1, bladelifelinez2, bladencsz1, bladencsz2, bbdopenz1, bbdopenz2, bbdclosez1, bbdclosez2, ncsvaluez1, ncsvaluez2, ncsexpoz1, ncsexpoz2, receipez1, receipez2, precutz1, precutz2, verifykerf, buyoffkerf;
    private CheckBox hairlinez1, hairlinez2, bladeexposurez1, bladeexposurez2, inspection;
    private Spinner magslot;
    private Button submit;
    private View view;


    public static saw_blade_replacement newInstance() {
        // Required empty public constructor
        saw_blade_replacement fragment = new saw_blade_replacement();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_saw_blade_replacement, container, false);
        tvEquipment = view.findViewById(R.id.equipmentname);
        tvJR = view.findViewById(R.id.jrnumber);
        tvDatetime = view.findViewById(R.id.nowdatetime);
        tvTech = view.findViewById(R.id.techemp);
        tvRequestor = view.findViewById(R.id.requestorid);
        tvDaily = view.findViewById(R.id.purposeoption);
        tvRequestorName = view.findViewById(R.id.requestorname);
        tvTechName = view.findViewById(R.id.techname);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDatetime.setText(dateStr);

        hubmountz1 = view.findViewById(R.id.z1hubmount);
        hubmountz2 = view.findViewById(R.id.z2hubmount);

        coolerbarz1 = view.findViewById(R.id.z1coolerbarmount);
        coolerbarz2 = view.findViewById(R.id.z2coolerbarmount);

        verifybladez1 = view.findViewById(R.id.z1verifymount);
        verifybladez2 = view.findViewById(R.id.z2verifymount);

        bladetypez1 = view.findViewById(R.id.z1bladetype);
        bladetypez2 = view.findViewById(R.id.z2bladetype);

        bladelifelinez1 = view.findViewById(R.id.lifespanz1);
        bladelifelinez2 = view.findViewById(R.id.lifespanz2);

        bbdopenz1 = view.findViewById(R.id.bbdopenz1);
        bbdopenz2 = view.findViewById(R.id.bbdopenz1);

        bbdclosez1 = view.findViewById(R.id.bbdclosez1);
        bbdclosez2 = view.findViewById(R.id.bbdclosez2);

        ncsvaluez1 = view.findViewById(R.id.ncsz1);
        ncsvaluez2 = view.findViewById(R.id.ncsz2);

        ncsprismz1 = view.findViewById(R.id.z1ncsprism);
        ncsprismz2 = view.findViewById(R.id.z2ncsprism);

        bladesetupz1 = view.findViewById(R.id.z1bladesetup);
        bladesetupz2 = view.findViewById(R.id.z2bladesetup);

        ncsexpoz1 = view.findViewById(R.id.bladeexposurez1);
        ncsexpoz2 = view.findViewById(R.id.bladeexposurez2);

        bladedressingz1 = view.findViewById(R.id.bladedressingz1);
        bladedressingz2 = view.findViewById(R.id.bladedressingz2);

        receipez1 = view.findViewById(R.id.receipez1);
        receipez2 = view.findViewById(R.id.receipez2);

        precutz1 = view.findViewById(R.id.precutz1);
        precutz2 = view.findViewById(R.id.precutz2);

        hairlinez1 = view.findViewById(R.id.z1hairalignmentyes);
        hairlinez2 = view.findViewById(R.id.z2hairalignmentyes);


        bladencsz1 = view.findViewById(R.id.kerfz1);
        bladencsz2 = view.findViewById(R.id.kerfz2);

        bladeexposurez1 = view.findViewById(R.id.z1checkncsyes);
        bladeexposurez2 = view.findViewById(R.id.z2checkncsyes);

        bladebbdsetupz1 = view.findViewById(R.id.z1bbdbladesetup);
        bladebbdsetupz2 = view.findViewById(R.id.z2bbdbladesetup);


        tapez1 = view.findViewById(R.id.z1tape);
        tapez2 = view.findViewById(R.id.z2tape);

        inspection = view.findViewById(R.id.inspection);

        magslot = view.findViewById(R.id.checkmagno);

        verifykerf = view.findViewById(R.id.hairlinecorrection);

        buyoffkerf = view.findViewById(R.id.buyoffkerf);

        waferbackside = view.findViewById(R.id.waferbackside);

        submit = view.findViewById(R.id.setupsubmit);

        getAll();
        return view;
    }

    private void CreateData(){
        String data, jr, rubbertip;

        jr = tvJR.getText().toString().replace("-","");


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);


        data = "api/eCheckListTest?setupinfo={\"jr\":\"" + jr
                + "\",\"techid\":\"" + tvTech.getText().toString()
                + "\",\"time\":\"" + dateStr
                + "\",\"scode\":\"" + scode
                + "\",\"hubmountz1\":" + hubmountz1.indexOfChild(view.findViewById(hubmountz1.getCheckedRadioButtonId()))
                + "\",\"hubmountz2\":" + hubmountz2.indexOfChild(view.findViewById(hubmountz2.getCheckedRadioButtonId()))
                + "\",\"coolerz1\":" + coolerbarz1.indexOfChild(view.findViewById(coolerbarz1.getCheckedRadioButtonId()))
                + "\",\"coolerz2\":" + coolerbarz2.indexOfChild(view.findViewById(coolerbarz2.getCheckedRadioButtonId()))
                + "\",\"verifybladez1\":" + verifybladez1.indexOfChild(view.findViewById(verifybladez1.getCheckedRadioButtonId()))
                + "\",\"verifybladez2\":" + verifybladez2.indexOfChild(view.findViewById(verifybladez2.getCheckedRadioButtonId()))
                + "\",\"bladetypez1\":" + bladetypez1.indexOfChild(view.findViewById(verifybladez1.getCheckedRadioButtonId()))
                + "\",\"bladetypez2\":" + bladetypez2.indexOfChild(view.findViewById(bladetypez2.getCheckedRadioButtonId()))
                + "\",\"bladelifez1\":" + bladelifelinez1.getText().toString()
                + "\",\"bladelifez2\":" + bladelifelinez2.getText().toString()
                + "\",\"bbdopenz1\":" + bbdopenz1.getText().toString()
                + "\",\"bbdopenz2\":" + bbdopenz2.getText().toString()
                + "\",\"bbdclosez1\":" + bbdclosez1.getText().toString()
                + "\",\"bbdclosez2\":" + bbdclosez2.getText().toString()
                + "\",\"ncsvaluez1\":" + ncsvaluez1.getText().toString()
                + "\",\"ncsvaluez2\":" + ncsvaluez2.getText().toString()
                + "\",\"ncsprismz1\":" + ncsprismz1.indexOfChild(view.findViewById(ncsprismz1.getCheckedRadioButtonId()))
                + "\",\"ncsprismz2\":" + ncsprismz2.indexOfChild(view.findViewById(ncsprismz2.getCheckedRadioButtonId()))
                + "\",\"bladesetupz1\":" + bladesetupz1.indexOfChild(view.findViewById(bladesetupz1.getCheckedRadioButtonId()))
                + "\",\"bladesetupz2\":" + bladesetupz2.indexOfChild(view.findViewById(bladesetupz2.getCheckedRadioButtonId()))
                + "\",\"ncsexpoz1\":" + ncsexpoz1.getText().toString()
                + "\",\"ncsexpoz2\":" + ncsexpoz2.getText().toString()
                + "\",\"bladedressingz1\":" + bladedressingz1.indexOfChild(view.findViewById(bladedressingz1.getCheckedRadioButtonId()))
                + "\",\"bladedressingz2\":" + bladedressingz2.indexOfChild(view.findViewById(bladedressingz2.getCheckedRadioButtonId()))
                + "\",\"receipez1\":" + receipez1.getText().toString()
                + "\",\"receipez2\":" + receipez2.getText().toString()
                + "\",\"precutz1\":" + precutz1.getText().toString()
                + "\",\"precutz2\":" + precutz2.getText().toString()
                + "\",\"hairlinez1\":" + hairlinez1.isChecked()
                + "\",\"hairlinez2\":" + hairlinez2.isChecked()
                + "\",\"bladencsz1\":" + bladencsz1.getText().toString()
                + "\",\"bladencsz2\":" + bladencsz2.getText().toString()
                + "\",\"bladeexposurez1\":" + bladeexposurez1.isChecked()
                + "\",\"bladeexposurez2\":" + bladeexposurez2.isChecked()
                + "\",\"bladebbdsetupz1\":" + bladebbdsetupz1.indexOfChild(view.findViewById(bladebbdsetupz1.getCheckedRadioButtonId()))
                + "\",\"bladebbdsetupz2\":" + bladebbdsetupz2.indexOfChild(view.findViewById(bladebbdsetupz2.getCheckedRadioButtonId()))
                + "\",\"tapez1\":" + tapez1.indexOfChild(view.findViewById(tapez1.getCheckedRadioButtonId()))
                + "\",\"tapez2\":" + tapez2.indexOfChild(view.findViewById(tapez2.getCheckedRadioButtonId()))
                + "\",\"inspection\":" + inspection.isChecked()
                + "\",\"magslot\":" + magslot.getSelectedItem().toString()
                + "\",\"verifykerf\":" + verifykerf.getText().toString()
                + "\",\"buyoffkerf\":" + buyoffkerf.getText().toString()
                + "\",\"waferbackside\":" + waferbackside.indexOfChild(view.findViewById(waferbackside.getCheckedRadioButtonId()))
                + "\"}";


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(allclass.GetSetup.class).getSetup(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    //msg obj = response.body();
                    showToastMsg("Job Request JR " + tvJR.getText().toString() + " Machine Setup Completed!");


                    Fragment newFragment = new TermOfUseTechnician();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.master_container, TermOfUseTechnician.newInstance());
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


    protected void getAll(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvJR.setText(prefs.getString("jr","no data"));
        tvEquipment.setText(prefs.getString("equipmentname","no data"));
        tvRequestor.setText(prefs.getString("requestor","no data"));
        tvTech.setText(prefs.getString("techid","no data"));
        tvRequestorName.setText(prefs.getString("msname", "no data"));
        tvTechName.setText(prefs.getString("techname", "no data").toUpperCase());
        scode = prefs.getString("scode", "no data");
    }
}
