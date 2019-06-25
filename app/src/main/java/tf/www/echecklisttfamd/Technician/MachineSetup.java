package tf.www.echecklisttfamd.Technician;


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
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
public class MachineSetup extends Fragment {
    private TextView tvEquipment, tvJR, tvDatetime, tvTech, tvRequestor, tvDaily, tvRequestorName, tvTechName;
    private EditText evEsconfig, evNoofpins, evEscapno, evEskit, evEsTool, evCheckTool, evCheckEs, evNeedle, evPP, evCal;
    private String device, daily, scode;
    private Boolean UnicornDevice;
    private int option,rubberoption;
    private Button btnSubmit;
    private RadioGroup rgdiesticky, rgdieposition, rgdiepickup, rgdieeject, rgmacautocal, rgRubberApollo, rgRubberUnicorn, rgCalEs, rgNeedleType,rgNeedlePosition, rgEjectorMark,rgNeedleType6101,rgNeedlePosition6101, rgEjectorMark6101, rgEjectorMarkCenter, rgmachineblowerheater, machineblowersolenid;
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
        tvRequestorName = view.findViewById(R.id.requestorname);
        tvTechName = view.findViewById(R.id.techname);
        evEsconfig = view.findViewById(R.id.estool);
        evNoofpins = view.findViewById(R.id.noofpins);
        evEscapno = view.findViewById(R.id.escapno);
        evEskit = view.findViewById(R.id.eskitno);
        evEsTool = view.findViewById(R.id.estooltno);
        evCheckEs = view.findViewById(R.id.checkes);
        evCheckTool = view.findViewById(R.id.checkestool);
        evNeedle = view.findViewById(R.id.checkneedle);
        evPP = view.findViewById(R.id.pptoolcentering);
        evCal = view.findViewById(R.id.calibrateESTool);
        rgRubberApollo = view.findViewById(R.id.rubberapollo);
        rgRubberUnicorn = view.findViewById(R.id.rubberunicorn);
        rgCalEs = view.findViewById(R.id.cales);
        rgNeedleType = view.findViewById(R.id.needletype);
        rgNeedlePosition = view.findViewById(R.id.needleposition);
        rgEjectorMark = view.findViewById(R.id.ejectormark);
        rgNeedleType6101 = view.findViewById(R.id.needletyp6101);
        rgNeedlePosition6101 = view.findViewById(R.id.needleposition6101);
        rgEjectorMark6101 = view.findViewById(R.id.ejectormark6101);
        rgEjectorMarkCenter = view.findViewById(R.id.ejectmarkcenter);
        rgmachineblowerheater = view.findViewById(R.id.machineblowerheater);
        machineblowersolenid = view.findViewById(R.id.machineblowersolenid);
        rgmacautocal = view.findViewById(R.id.macautocal);
        rgdieeject = view.findViewById(R.id.dieeject);
        rgdiepickup = view.findViewById(R.id.diepickup);
        rgdieposition = view.findViewById(R.id.dieposition);
        rgdiesticky = view.findViewById(R.id.diesticky);
        getAll();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
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
                if(checkAnswer()==false){
                    ShowAlertSubmit("Machine Setup Submission","Please make sure all informations are correct before submission");
                }
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
        tvRequestorName.setText(prefs.getString("msname", "no data"));
        daily = prefs.getString("daily","no data");
        device = prefs.getString("device","no data");
        tvTechName.setText(prefs.getString("techname", "no data").toUpperCase());
        scode = prefs.getString("scode", "no data");
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

    private Boolean checkAnswer(){
        Boolean rubber = false;
        Boolean alert = false;
        String msg = null;

        if(rubberoption==0){
            if(rgRubberApollo.getCheckedRadioButtonId() == -1){
                rubber = true;
                alert = true;
                msg = "Invalid Rubber Tip Size!";
            }
        }
        else{
            if(rgRubberUnicorn.getCheckedRadioButtonId() == -1){
                rubber = true;
                alert = true;
                msg = "Invalid Rubber Tip Size!";
            }
        }

        if(alert==false){
            if(!TextUtils.isEmpty(evEsconfig.getText().toString())){
                if(!TextUtils.isEmpty(evNoofpins.getText().toString())){
                    if(!TextUtils.isEmpty(evEscapno.getText().toString())){
                        if(!TextUtils.isEmpty(evEskit.getText().toString())){
                            if(!TextUtils.isEmpty(evEsTool.getText().toString())){
                                if(!TextUtils.isEmpty(evCheckTool.getText().toString())){
                                    if(!TextUtils.isEmpty(evCheckEs.getText().toString())){
                                        if(!TextUtils.isEmpty(evNeedle.getText().toString())){
                                            if(!TextUtils.isEmpty(evPP.getText().toString())){
                                                if(!TextUtils.isEmpty(evCal.getText().toString())){
                                                    if(rgCalEs.indexOfChild(view.findViewById(rgCalEs.getCheckedRadioButtonId())) == 0){
                                                        if(rgmacautocal.indexOfChild(view.findViewById(rgmacautocal.getCheckedRadioButtonId())) == 0){
                                                            if(rgdieeject.indexOfChild(view.findViewById(rgdieeject.getCheckedRadioButtonId())) == 0){
                                                                if(rgdiepickup.indexOfChild(view.findViewById(rgdiepickup.getCheckedRadioButtonId())) == 0){
                                                                    if(rgdieposition.indexOfChild(view.findViewById(rgdieposition.getCheckedRadioButtonId())) == 0){
                                                                        if(rgdiesticky.indexOfChild(view.findViewById(rgdiesticky.getCheckedRadioButtonId()))  == 0){

                                                                        }
                                                                        else{
                                                                            alert = true;
                                                                            msg = "Invalid Die Sticking On Rubber!";
                                                                            rgdiesticky.requestFocusFromTouch();
                                                                        }
                                                                    }
                                                                    else{
                                                                        alert = true;
                                                                        msg = "Invalid Die Position!";
                                                                        rgdieposition.requestFocusFromTouch();
                                                                    }
                                                                }
                                                                else{
                                                                    alert = true;
                                                                    msg = "Invalid Die Pick Up!";
                                                                    rgdiepickup.requestFocusFromTouch();
                                                                }
                                                            }
                                                            else{
                                                                alert = true;
                                                                msg = "Invalid Die Ejected Higher!";
                                                                rgdieeject.requestFocusFromTouch();
                                                            }
                                                        }
                                                        else{
                                                            alert = true;
                                                            msg = "Invalid Machine Auto Calibration!";
                                                            rgmacautocal.requestFocusFromTouch();
                                                        }
                                                    }
                                                    else{
                                                        alert = true;
                                                        msg = "Invalid Calibration Es Tool!";
                                                        rgCalEs.requestFocusFromTouch();
                                                    }
                                                }
                                                else{
                                                    alert = true;
                                                    msg = "Invalid Es Tool Height, XY and needle position!";
                                                    evCal.requestFocusFromTouch();
                                                }
                                            }
                                            else{
                                                alert = true;
                                                msg = "Invalid PP Tool Centering!";
                                                evPP.requestFocusFromTouch();
                                            }
                                        }
                                        else{
                                            alert = true;
                                            msg = "Invalid Needle Leveling!";
                                            evNeedle.requestFocusFromTouch();
                                        }
                                    }
                                    else{
                                        alert = true;
                                        msg = "Invalid Es Tool Configuration!";
                                        evCheckEs.requestFocusFromTouch();
                                    }
                                }
                                else{
                                    alert = true;
                                    msg = "Invalid Check ES tool!";
                                    evCheckTool.requestFocusFromTouch();
                                }
                            }
                            else{
                                alert = true;
                                msg = "Invalid Es Tool#!";
                                evEsTool.requestFocusFromTouch();
                            }
                        }
                        else{
                            alert = true;
                            msg = "Invalid Es Kit#!";
                            evEskit.requestFocusFromTouch();
                        }
                    }
                    else{
                        alert = true;
                        msg = "Invalid Es Cap#!";
                        evEscapno.requestFocusFromTouch();
                    }
                }
                else{
                    alert = true;
                    msg = "Invalid Number of Pins!";
                    evNoofpins.requestFocusFromTouch();
                }
            }
            else{
                alert = true;
                msg = "Invalid Es Tool Configuration!";
                evEsconfig.requestFocusFromTouch();
            }
        }

        if(alert==false){
            if(UnicornDevice==true){
                if(option==1){
                    if(rgNeedleType.indexOfChild(view.findViewById(rgNeedleType.getCheckedRadioButtonId()))  == 0){
                        if(rgNeedlePosition.indexOfChild(view.findViewById(rgNeedlePosition.getCheckedRadioButtonId()))  == 0){
                            if(rgEjectorMark.indexOfChild(view.findViewById(rgEjectorMark.getCheckedRadioButtonId()))  == 0){

                            }
                            else{
                                alert = true;
                                msg = "Invalid Ejector Mark!";
                                rgEjectorMark.requestFocusFromTouch();
                            }
                        }
                        else {
                            alert = true;
                            msg = "Invalid Needle Position!";
                            rgNeedlePosition.requestFocusFromTouch();
                        }
                    }
                    else{
                        alert = true;
                        msg = "Invalid Needle Type1!";
                        rgNeedleType.requestFocusFromTouch();
                    }
                }
                else{
                    if(rgNeedleType6101.indexOfChild(view.findViewById(rgNeedleType6101.getCheckedRadioButtonId()))  == 0){
                        if(rgNeedlePosition6101.indexOfChild(view.findViewById(rgNeedlePosition6101.getCheckedRadioButtonId()))  == 0){
                            if(rgEjectorMark6101.indexOfChild(view.findViewById(rgEjectorMark6101.getCheckedRadioButtonId()))  == 0){

                            }
                            else{
                                alert = true;
                                msg = "Invalid Needle Position!";
                                rgEjectorMark6101.requestFocusFromTouch();
                            }
                        }
                        else{
                            alert = true;
                            msg = "Invalid Needle Position!";
                            rgNeedlePosition6101.requestFocusFromTouch();
                        }
                    }
                    else{
                        alert = true;
                        msg = "Invalid Needle Type!";
                        rgNeedleType6101.requestFocusFromTouch();
                    }
                }
            }
        }

        if(alert==false) {
            if (daily.equals("True")) {
                if (rgEjectorMarkCenter.indexOfChild(view.findViewById(rgEjectorMarkCenter.getCheckedRadioButtonId())) == 0) {
                    if (rgmachineblowerheater.indexOfChild(view.findViewById(rgmachineblowerheater.getCheckedRadioButtonId())) == 0) {
                        if (machineblowersolenid.indexOfChild(view.findViewById(machineblowersolenid.getCheckedRadioButtonId())) == 0) {

                        } else {
                            alert = true;
                            msg = "Invalid Machine Blower Solenind!";
                            machineblowersolenid.requestFocusFromTouch();
                        }
                    } else {
                        alert = true;
                        msg = "Invalid Machine Blower Heater!";
                        rgmachineblowerheater.requestFocusFromTouch();
                    }
                } else {
                    alert = true;
                    msg = "Invalid Ejector Mark Center!";
                    rgEjectorMarkCenter.requestFocusFromTouch();
                }
            }
        }

        if(alert==true){
            ShowAlert("Alert!", msg);

            if(rubber==true){
                if(rubberoption==0){
                    rgRubberApollo.requestFocusFromTouch();
                }
                else{
                    rgRubberUnicorn.requestFocusFromTouch();
                }
            }
        }

        return  alert;
    }

    private void CreateData(){
        String data, jr, rubbertip;
        int optionrg;
        jr = tvJR.getText().toString().replace("-","");


        if(rubberoption==0){
            optionrg = rgRubberApollo.indexOfChild(view.findViewById(rgRubberApollo.getCheckedRadioButtonId()));
        }
        else{
            optionrg= rgRubberUnicorn.indexOfChild(view.findViewById(rgRubberUnicorn.getCheckedRadioButtonId())); //rgRubberUnicorn.getCheckedRadioButtonId();
        }



        rubbertip = String.valueOf(optionrg);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);


        data = "api/eChecklist?setupinfo={\"jr\":\"" + jr + "\",\"techid\":\""
                + tvTech.getText().toString() + "\",\"time\":\""
                + dateStr + "\",\"rubber\":\""
                + rubbertip + "\",\"config\":\""
                + evEsconfig.getText().toString() + "\",\"pins\":\""
                + evNoofpins.getText().toString() + "\",\"escap\":\""
                + evEscapno.getText().toString() + "\",\"eskit\":\""
                + evEskit.getText().toString() + "\",\"estool\":\""
                + evEsTool.getText().toString() + "\",\"checkedtool\":\""
                + evCheckTool.getText().toString() + "\",\"escondition\":\""
                + evCheckEs.getText().toString() + "\",\"needle\":\""
                + evNeedle.getText().toString() + "\",\"pp\":\""
                + evPP.getText().toString() + "\",\"cal\":\""
                + evCal.getText().toString() + "\",\"scode\":\""
                + scode + "\"}";


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

    public class msg{
        protected String result;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }
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

    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
    }
}
