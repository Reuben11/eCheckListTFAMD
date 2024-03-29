package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.TermOfUseOperator;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class saw_blade_replacement extends Fragment {
    private String apilink;
    private TextView tvEquipment, tvJR, tvDatetime, tvTech, tvRequestor, tvDaily, tvRequestorName, tvTechName;
    private String scode;
    private RadioGroup hubmountz1, hubmountz2, coolerbarz1, coolerbarz2, verifybladez1, verifybladez2, bladetypez1, bladetypez2, ncsprismz1, ncsprismz2, bladesetupz1, bladesetupz2, bladedressingz1, bladedressingz2, bladebbdsetupz1, bladebbdsetupz2, tapez1, tapez2,  bumpcheck, bladeexposurez1, bladeexposurez2, chuckandspinnerz1, chuckandspinnerz2;
    private EditText bladelifelinez1, bladelifelinez2, bladencsz1, bladencsz2, bbdopenz1, bbdopenz2, bbdclosez1, bbdclosez2, ncsvaluez1, ncsvaluez2, ncsexpoz1, ncsexpoz2, receipez1, receipez2, precutz1, precutz2, verifykerf, buyoffkerf, exposurez1, exposurez2;
    private CheckBox hairlinez1, hairlinez2,  inspection;
    private Spinner magslot;
    private Button submit;
    private Boolean z1, z2;
    private View mIndicator;

    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int indicatorWidth;

    SharedPreferences prf;
    View view;


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
        apilink = getString(R.string.api);
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

//        hubmountz1 = view.findViewById(R.id.z1hubmount);
//        hubmountz2 = view.findViewById(R.id.z2hubmount);
//
//        coolerbarz1 = view.findViewById(R.id.z1coolerbarmount);
//        coolerbarz2 = view.findViewById(R.id.z2coolerbarmount);
//
//        verifybladez1 = view.findViewById(R.id.z1verifymount);
//        verifybladez2 = view.findViewById(R.id.z2verifymount);
//
//        bladetypez1 = view.findViewById(R.id.z1bladetype);
//        bladetypez2 = view.findViewById(R.id.z2bladetype);
//
//        bladelifelinez1 = view.findViewById(R.id.lifespanz1);
//        bladelifelinez2 = view.findViewById(R.id.lifespanz2);
//
//        bbdopenz1 = view.findViewById(R.id.bbdopenz1);
//        bbdopenz2 = view.findViewById(R.id.bbdopenz2);
//
//        bbdclosez1 = view.findViewById(R.id.bbdclosez1);
//        bbdclosez2 = view.findViewById(R.id.bbdclosez2);
//
//        ncsvaluez1 = view.findViewById(R.id.ncsz1);
//        ncsvaluez2 = view.findViewById(R.id.ncsz2);
//
//        ncsprismz1 = view.findViewById(R.id.z1ncsprism);
//        ncsprismz2 = view.findViewById(R.id.z2ncsprism);
//
//        bladesetupz1 = view.findViewById(R.id.z1bladesetup);
//        bladesetupz2 = view.findViewById(R.id.z2bladesetup);
//
//        ncsexpoz1 = view.findViewById(R.id.bladeexposurez1);
//        ncsexpoz2 = view.findViewById(R.id.bladeexposurez2);
//
//        bladedressingz1 = view.findViewById(R.id.bladedressingz1);
//        bladedressingz2 = view.findViewById(R.id.bladedressingz2);
//
//        receipez1 = view.findViewById(R.id.receipez1);
//        receipez2 = view.findViewById(R.id.receipez2);
//
//        precutz1 = view.findViewById(R.id.precutz1);
//        precutz2 = view.findViewById(R.id.precutz2);
//
//        hairlinez1 = view.findViewById(R.id.z1hairalignmentyes);
//        hairlinez2 = view.findViewById(R.id.z2hairalignmentyes);
//
//
//        bladencsz1 = view.findViewById(R.id.kerfz1);
//        bladencsz2 = view.findViewById(R.id.kerfz2);
//
//        bladeexposurez1 = view.findViewById(R.id.z1checkncs);
//        bladeexposurez2 = view.findViewById(R.id.z2checkncs);
//
//        exposurez1 = view.findViewById(R.id.exposurez1); //Rev P
//        exposurez2 = view.findViewById(R.id.exposurez2); //Rev P
//
//        chuckandspinnerz1 = view.findViewById(R.id.z1chuckandspinner); //Rev P
//        chuckandspinnerz2 = view.findViewById(R.id.z2chuckandspinner); //Rev P
//
//        bladebbdsetupz1 = view.findViewById(R.id.z1bbdbladesetup);
//        bladebbdsetupz2 = view.findViewById(R.id.z2bbdbladesetup);
//
//
//        tapez1 = view.findViewById(R.id.z1tape);
//        tapez2 = view.findViewById(R.id.z2tape);
//
//        inspection = view.findViewById(R.id.inspection);
//
//        magslot = view.findViewById(R.id.checkmagno);
//
//        verifykerf = view.findViewById(R.id.hairlinecorrection);
//
//        buyoffkerf = view.findViewById(R.id.buyoffkerf);
//
//        bumpcheck = view.findViewById(R.id.bumpcheck);
//
//        submit = view.findViewById(R.id.setupsubmit);
        //Assign view reference
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        mIndicator = view.findViewById(R.id.indicator);

        //Assign up the view pager and fragments
        adapter = new TabAdapter(getFragmentManager());
        adapter.addFragment(new Fragment_Setup_Info(), "Set Up Information");
        adapter.addFragment(new Fragment_Blade_Change(), "Blade Change Checklist");

        viewPager.setAdapter(adapter);
        viewPager.measure(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tabLayout.setupWithViewPager(viewPager);

        //Determine indicator width at runtime
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = tabLayout.getWidth()/tabLayout.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams)mIndicator.getLayoutParams();

                //Multiply positionOffset with indicatiorWidth to get tanslation
                float translationOffset = (v+i)*indicatorWidth;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                checkall();
//            }
//        });

        getAll();
//        GetBlade();

        return view;
    }

    private void GetBlade(){
        String data = apilink +  "BladeJR=" + tvJR.getText().toString().replace("-","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(allclass.GetBlade.class).getBladeSelection(data);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    try{
                        JSONObject json = new JSONObject(response.body());
                        z1 = Boolean.valueOf(json.getString("z1"));
                        z2 = Boolean.valueOf(json.getString("z2"));
                        validcheck();
                    }catch(JSONException e){
                        showToastMsg("Invalid Blade Selection!");
                    }

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                showToastMsg("Failed to Connect eChecklist Server");
            }
        });
    }

    private void validcheck(){
        if(z1==false){
            hubmountz1.getChildAt(0).setEnabled(false);
            hubmountz1.getChildAt(1).setEnabled(false);

            coolerbarz1.getChildAt(0).setEnabled(false);
            coolerbarz1.getChildAt(1).setEnabled(false);

            verifybladez1.getChildAt(0).setEnabled(false);
            verifybladez1.getChildAt(1).setEnabled(false);

            bladetypez1.getChildAt(0).setEnabled(false);
            bladetypez1.getChildAt(1).setEnabled(false);

            bladelifelinez1.setEnabled(false);
            bbdopenz1.setEnabled(false);
            bbdclosez1.setEnabled(false);
            ncsvaluez1.setEnabled(false);

            ncsprismz1.getChildAt(0).setEnabled(false);
            ncsprismz1.getChildAt(1).setEnabled(false);

            bladesetupz1.getChildAt(0).setEnabled(false);
            bladesetupz1.getChildAt(1).setEnabled(false);

            ncsexpoz1.setEnabled(false);

            bladedressingz1.getChildAt(0).setEnabled(false);
            bladedressingz1.getChildAt(1).setEnabled(false);

            receipez1.setEnabled(false);
            precutz1.setEnabled(false);
            hairlinez1.setEnabled(false);
            bladencsz1.setEnabled(false);

            bladeexposurez1.getChildAt(0).setEnabled(false);
            bladeexposurez1.getChildAt(1).setEnabled(false);

            exposurez1.setEnabled(false);

            chuckandspinnerz1.getChildAt(0).setEnabled(false);
            chuckandspinnerz1.getChildAt(1).setEnabled(false);

            bladebbdsetupz1.getChildAt(0).setEnabled(false);
            bladebbdsetupz1.getChildAt(1).setEnabled(false);

            tapez1.getChildAt(0).setEnabled(false);
            tapez1.getChildAt(1).setEnabled(false);
        }

        if(z2==false){
            hubmountz2.getChildAt(0).setEnabled(false);
            hubmountz2.getChildAt(1).setEnabled(false);
            coolerbarz2.getChildAt(0).setEnabled(false);
            coolerbarz2.getChildAt(1).setEnabled(false);

            verifybladez2.getChildAt(0).setEnabled(false);
            verifybladez2.getChildAt(1).setEnabled(false);

            bladetypez2.getChildAt(0).setEnabled(false);
            bladetypez2.getChildAt(1).setEnabled(false);

            bladelifelinez2.setEnabled(false);
            bbdopenz2.setEnabled(false);
            bbdclosez2.setEnabled(false);
            ncsvaluez2.setEnabled(false);

            ncsprismz2.getChildAt(0).setEnabled(false);
            ncsprismz2.getChildAt(1).setEnabled(false);

            bladesetupz2.getChildAt(0).setEnabled(false);
            bladesetupz2.getChildAt(1).setEnabled(false);

            ncsexpoz2.setEnabled(false);

            bladedressingz2.getChildAt(0).setEnabled(false);
            bladedressingz2.getChildAt(1).setEnabled(false);

            receipez2.setEnabled(false);
            precutz2.setEnabled(false);
            hairlinez2.setEnabled(false);
            bladencsz2.setEnabled(false);

            bladeexposurez2.getChildAt(0).setEnabled(false);
            bladeexposurez2.getChildAt(1).setEnabled(false);

            exposurez2.setEnabled(false);

            chuckandspinnerz2.getChildAt(0).setEnabled(false);
            chuckandspinnerz2.getChildAt(1).setEnabled(false);

            bladebbdsetupz2.getChildAt(0).setEnabled(false);
            bladebbdsetupz2.getChildAt(1).setEnabled(false);

            tapez2.getChildAt(0).setEnabled(false);
            tapez2.getChildAt(1).setEnabled(false);
        }
    }

    private void checkall(){
        Integer blade1, blade2;
        blade1 = 0;
        blade2 = 0;

        if(z1==true){
            if(hubmountz1.indexOfChild(view.findViewById(hubmountz1.getCheckedRadioButtonId())) != -1){
                if(coolerbarz1.indexOfChild(view.findViewById(coolerbarz1.getCheckedRadioButtonId())) != -1){
                    if(verifybladez1.indexOfChild(view.findViewById(verifybladez1.getCheckedRadioButtonId())) != -1){
                        if(bladetypez1.indexOfChild(view.findViewById(bladetypez1.getCheckedRadioButtonId())) != -1){
                            if(!TextUtils.isEmpty(bladelifelinez1.getText().toString())){
                                if(!TextUtils.isEmpty(bbdopenz1.getText().toString())){
                                    if(Integer.valueOf(bbdopenz1.getText().toString()) >= 90 && Integer.valueOf(bbdopenz1.getText().toString()) <= 99 ){
                                        if(!TextUtils.isEmpty(bbdclosez1.getText().toString())){
                                            if(Integer.valueOf(bbdclosez1.getText().toString()) >= 10 && Integer.valueOf(bbdclosez1.getText().toString()) <= 15 ){
                                                if(!TextUtils.isEmpty(ncsvaluez1.getText().toString())){
                                                    if(Integer.valueOf(ncsvaluez1.getText().toString()) >= 98 && Integer.valueOf(ncsvaluez1.getText().toString()) <= 102 ) {
                                                        if (ncsprismz1.indexOfChild(view.findViewById(ncsprismz1.getCheckedRadioButtonId())) != -1) {
                                                            if (bladesetupz1.indexOfChild(view.findViewById(bladesetupz1.getCheckedRadioButtonId())) != -1) {
                                                                if (!TextUtils.isEmpty(ncsexpoz1.getText().toString())) {
                                                                    if (bladedressingz1.indexOfChild(view.findViewById(bladedressingz1.getCheckedRadioButtonId())) != -1) {
                                                                        if (!TextUtils.isEmpty(receipez1.getText().toString())) {
                                                                            if (!TextUtils.isEmpty(precutz1.getText().toString())) {
                                                                                if (hairlinez1.isChecked()) {
                                                                                    if (!TextUtils.isEmpty(bladencsz1.getText().toString())) {
                                                                                        if (bladeexposurez1.indexOfChild(view.findViewById(bladeexposurez1.getCheckedRadioButtonId())) != -1) {
                                                                                            if(!TextUtils.isEmpty(exposurez1.getText().toString())){
                                                                                                if(chuckandspinnerz1.indexOfChild(view.findViewById(chuckandspinnerz1.getCheckedRadioButtonId())) == 0) {
                                                                                                    if (bladebbdsetupz1.indexOfChild(view.findViewById(bladebbdsetupz1.getCheckedRadioButtonId())) != -1) {
                                                                                                        if (tapez1.indexOfChild(view.findViewById(tapez1.getCheckedRadioButtonId())) != -1) {
                                                                                                            blade1 = 1;
                                                                                                        } else {
                                                                                                            tapez1.requestFocusFromTouch();
                                                                                                            ShowAlert("Alert!", "Invalid Perform Tape Hairline Selection!");
                                                                                                        }
                                                                                                    } else {
                                                                                                        bladebbdsetupz1.requestFocusFromTouch();
                                                                                                        ShowAlert("Alert!", "Invalid Perform Tape Hairline Selection!");
                                                                                                    }
                                                                                                }else{
                                                                                                    chuckandspinnerz1.requestFocusFromTouch();
                                                                                                    ShowAlert("Alert!", "Invalid Chuck And Spinner Cleaning Selection!");
                                                                                                }
                                                                                            }else{
                                                                                                exposurez1.requestFocusFromTouch();
                                                                                                ShowAlert("Alert!", "Invalid Exposure After Setup Value!");
                                                                                            }
                                                                                        } else {
                                                                                            bladeexposurez1.requestFocusFromTouch();
                                                                                            ShowAlert("Alert!", "Invalid Blade Exposure Selection!");
                                                                                        }
                                                                                    } else {
                                                                                        bladencsz1.requestFocusFromTouch();
                                                                                        ShowAlert("Alert!", "Invalid Verify Kerf Width Input!");
                                                                                    }
                                                                                } else {
                                                                                    hairlinez1.requestFocusFromTouch();
                                                                                    ShowAlert("Alert!", "Invalid Performed Hairline Alignment Selection!");
                                                                                }
                                                                            } else {
                                                                                precutz1.requestFocusFromTouch();
                                                                                ShowAlert("Alert!", "Invalid Blade Pre-cut board batch no. Input!");
                                                                            }
                                                                        } else {
                                                                            receipez1.requestFocusFromTouch();
                                                                            ShowAlert("Alert!", "Invalid Blade Dressing Recipe Input!");
                                                                        }
                                                                    } else {
                                                                        bladedressingz1.requestFocusFromTouch();
                                                                        ShowAlert("Alert!", "Invalid Blade Dressing Selection!");
                                                                    }
                                                                } else {
                                                                    ncsexpoz1.requestFocusFromTouch();
                                                                    ShowAlert("Alert!", "Invalid Blade Exposure Input!");
                                                                }
                                                            } else {
                                                                bladesetupz1.requestFocusFromTouch();
                                                                ShowAlert("Alert!", "Invalid Blade Setup Selection!");
                                                            }
                                                        } else {
                                                            ncsprismz1.requestFocusFromTouch();
                                                            ShowAlert("Alert!", "Invalid NCS Prism Condition Selection!");
                                                        }
                                                    }else{
                                                        ncsvaluez1.requestFocusFromTouch();
                                                        ShowAlert("Alert!", "Invalid NCS Value Range Input!");
                                                    }
                                                }else {
                                                    ncsvaluez1.requestFocusFromTouch();
                                                    ShowAlert("Alert!", "Invalid NCS Value  Input!");
                                                }
                                            }else{
                                                bbdclosez1.requestFocusFromTouch();
                                                ShowAlert("Alert!", "Invalid BBD Value - Close Range Input!");
                                            }
                                        }else{
                                            bbdclosez1.requestFocusFromTouch();
                                            ShowAlert("Alert!", "Invalid BBD Value - Close Input!");
                                        }
                                    }else{
                                        bbdopenz1.requestFocusFromTouch();
                                        ShowAlert("Alert!", "Invalid BBD Value - Open Range Input!");
                                    }
                                }else{
                                    bbdopenz1.requestFocusFromTouch();
                                    ShowAlert("Alert!", "Invalid BBD Value - Open Input!");
                                }
                            }else{
                                bladelifelinez1.requestFocusFromTouch();
                                ShowAlert("Alert!", "Invalid Blade Life Line Input!");
                            }
                        }else{
                            bladetypez1.requestFocusFromTouch();
                            ShowAlert("Alert!", "Invalid Blade Type On Screen Selection!");
                        }
                    }else{
                        verifybladez1.requestFocusFromTouch();
                        ShowAlert("Alert!", "Invalid Verify Blade Type Selection!");
                    }
                }else{
                    coolerbarz1.requestFocusFromTouch();
                    ShowAlert("Alert!", "Invalid Cooler Bar Condition Selection!");
                }

            }else{
                hubmountz1.requestFocusFromTouch();
                ShowAlert("Alert!", "Invalid Hub Mount Condition Selection!");
            }
        } else{
            blade1 = 1;
        }

        if(z2==true){
            if(hubmountz2.indexOfChild(view.findViewById(hubmountz2.getCheckedRadioButtonId())) != -1){
                if(coolerbarz2.indexOfChild(view.findViewById(coolerbarz2.getCheckedRadioButtonId())) != -1){
                    if(verifybladez2.indexOfChild(view.findViewById(verifybladez2.getCheckedRadioButtonId())) != -1){
                        if(bladetypez2.indexOfChild(view.findViewById(bladetypez2.getCheckedRadioButtonId())) != -1){
                            if(!TextUtils.isEmpty(bladelifelinez2.getText().toString())){
                                if(!TextUtils.isEmpty(bbdopenz2.getText().toString())){
                                    if(!TextUtils.isEmpty(bbdclosez2.getText().toString())){
                                        if(!TextUtils.isEmpty(ncsvaluez2.getText().toString())){
                                            if(ncsprismz2.indexOfChild(view.findViewById(ncsprismz2.getCheckedRadioButtonId())) != -1){
                                                if(bladesetupz2.indexOfChild(view.findViewById(bladesetupz2.getCheckedRadioButtonId())) != -1){
                                                    if(!TextUtils.isEmpty(ncsexpoz2.getText().toString())){
                                                        if(bladedressingz2.indexOfChild(view.findViewById(bladedressingz2.getCheckedRadioButtonId())) != -1){
                                                            if(!TextUtils.isEmpty(receipez2.getText().toString())){
                                                                if(!TextUtils.isEmpty(precutz2.getText().toString())){
                                                                    if(hairlinez2.isChecked()){
                                                                        if(!TextUtils.isEmpty(bladencsz2.getText().toString())){
                                                                            if(bladeexposurez2.indexOfChild(view.findViewById(bladeexposurez2.getCheckedRadioButtonId())) != -1){
                                                                                if(!TextUtils.isEmpty(exposurez2.getText().toString())) {
                                                                                    if(chuckandspinnerz2.indexOfChild(view.findViewById(chuckandspinnerz2.getCheckedRadioButtonId())) == 0) {
                                                                                        if (bladebbdsetupz2.indexOfChild(view.findViewById(bladebbdsetupz2.getCheckedRadioButtonId())) != -1) {
                                                                                            if (tapez2.indexOfChild(view.findViewById(tapez2.getCheckedRadioButtonId())) != -1) {
                                                                                                blade2 = 1;
                                                                                            } else {
                                                                                                tapez2.requestFocusFromTouch();
                                                                                                ShowAlert("Alert!", "Invalid Perform Tape Hairline Selection!");
                                                                                            }
                                                                                        } else {
                                                                                            bladebbdsetupz2.requestFocusFromTouch();
                                                                                            ShowAlert("Alert!", "Invalid Blade Setup Selection!");
                                                                                        }
                                                                                    }else{
                                                                                        chuckandspinnerz2.requestFocusFromTouch();
                                                                                        ShowAlert("Alert!", "Invalid Chuck And Spinner Cleaning Selection!");
                                                                                    }
                                                                                }else{
                                                                                    exposurez2.requestFocusFromTouch();
                                                                                    ShowAlert("Alert!", "Invalid Exposure After Setup Value!");
                                                                                }
                                                                            }else{
                                                                                bladeexposurez2.requestFocusFromTouch();
                                                                                ShowAlert("Alert!", "Invalid Blade Exposure Selection!");
                                                                            }
                                                                        }else{
                                                                            bladencsz2.requestFocusFromTouch();
                                                                            ShowAlert("Alert!", "Invalid Verify Kerf Width Input!");
                                                                        }
                                                                    }else{
                                                                        hairlinez2.requestFocusFromTouch();
                                                                        ShowAlert("Alert!", "Invalid Performed Hairline Alignment Selection!");
                                                                    }
                                                                }else{
                                                                    precutz2.requestFocusFromTouch();
                                                                    ShowAlert("Alert!", "Invalid Blade Pre-cut board batch no. Input!");
                                                                }
                                                            }else{
                                                                receipez2.requestFocusFromTouch();
                                                                ShowAlert("Alert!", "Invalid Blade Dressing Recipe Input!");
                                                            }
                                                        }else{
                                                            bladedressingz2.requestFocusFromTouch();
                                                            ShowAlert("Alert!", "Invalid Blade Dressing Selection!");
                                                        }
                                                    }else{
                                                        ncsexpoz2.requestFocusFromTouch();
                                                        ShowAlert("Alert!", "Invalid Blade Exposure Input!");
                                                    }
                                                }else{
                                                    bladesetupz2.requestFocusFromTouch();
                                                    ShowAlert("Alert!", "Invalid Blade Setup Selection!");
                                                }
                                            }else{
                                                ncsprismz2.requestFocusFromTouch();
                                                ShowAlert("Alert!", "Invalid NCS Prism Condition Selection!");
                                            }
                                        }else{
                                            ncsvaluez2.requestFocusFromTouch();
                                            ShowAlert("Alert!", "Invalid NCS Value - Close Input!");
                                        }
                                    }else{
                                        bbdclosez2.requestFocusFromTouch();
                                        ShowAlert("Alert!", "Invalid BBD Value - Close Input!");
                                    }
                                }else{
                                    bbdopenz2.requestFocusFromTouch();
                                    ShowAlert("Alert!", "Invalid BBD Value - Open Input!");
                                }
                            }else{
                                bladelifelinez2.requestFocusFromTouch();
                                ShowAlert("Alert!", "Invalid Blade Life Line Input!");
                            }
                        }else{
                            bladetypez2.requestFocusFromTouch();
                            ShowAlert("Alert!", "Invalid Blade Type On Screen Selection!");
                        }
                    }else{
                        verifybladez2.requestFocusFromTouch();
                        ShowAlert("Alert!", "Invalid Verify Blade Type Selection!");
                    }
                }else{
                    coolerbarz2.requestFocusFromTouch();
                    ShowAlert("Alert!", "Invalid Cooler Bar Condition Selection!");
                }

            }else{
                hubmountz2.requestFocusFromTouch();
                ShowAlert("Alert!", "Invalid Hub Mount Condition Selection!");
            }
        }else{
            blade2 = 1;
        }

        if(blade1==1 && blade2==1){
            if(inspection.isChecked()){
                if(magslot.getSelectedItemPosition() != 0){
                    if(!TextUtils.isEmpty(verifykerf.getText().toString())) {
                        if(!TextUtils.isEmpty(buyoffkerf.getText().toString())) {
                            if (bumpcheck.indexOfChild(view.findViewById(bumpcheck.getCheckedRadioButtonId())) != -1) {
                                    CreateData();
                            }else{
                                bumpcheck.requestFocusFromTouch();
                                ShowAlert("Alert!", "Invalid Bump Check!");
                            }
                        }else{
                            buyoffkerf.requestFocusFromTouch();
                            ShowAlert("Alert!", "Invalid BuyOff Correction!");
                        }
                    }else {
                        verifykerf.requestFocusFromTouch();
                        ShowAlert("Alert!", "Invalid 1st Kerf Check!");
                    }
                }else{
                    magslot.requestFocusFromTouch();
                    ShowAlert("Alert!", "Invalid Magslot Selection!");
                }
            }else{
                inspection.requestFocusFromTouch();
                ShowAlert("Alert!", "Invalid Inspection Button Selection!");
            }
        }
    }

    private void CreateData(){
        String data, jr;

        jr = tvJR.getText().toString().replace("-","");

//        prf = getContext().getSharedPreferences("jr_details", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prf.edit();
//        editor.putString("jrid", jr);
//        editor.commit();

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);


        data =  apilink + "BladeChangeInfo={\"jr\":\"" + jr
                + "\",\"techid\":\"" + tvTech.getText().toString()
                + "\",\"time\":\"" + dateStr
                + "\",\"scode\":\"" + scode
                + "\",\"hubmountz1\":\"" + hubmountz1.indexOfChild(view.findViewById(hubmountz1.getCheckedRadioButtonId()))
                + "\",\"hubmountz2\":\"" + hubmountz2.indexOfChild(view.findViewById(hubmountz2.getCheckedRadioButtonId()))
                + "\",\"coolerz1\":\"" + coolerbarz1.indexOfChild(view.findViewById(coolerbarz1.getCheckedRadioButtonId()))
                + "\",\"coolerz2\":\"" + coolerbarz2.indexOfChild(view.findViewById(coolerbarz2.getCheckedRadioButtonId()))
                + "\",\"verifybladez1\":\"" + verifybladez1.indexOfChild(view.findViewById(verifybladez1.getCheckedRadioButtonId()))
                + "\",\"verifybladez2\":\"" + verifybladez2.indexOfChild(view.findViewById(verifybladez2.getCheckedRadioButtonId()))
                + "\",\"bladetypez1\":\"" + bladetypez1.indexOfChild(view.findViewById(verifybladez1.getCheckedRadioButtonId()))
                + "\",\"bladetypez2\":\"" + bladetypez2.indexOfChild(view.findViewById(bladetypez2.getCheckedRadioButtonId()))
                + "\",\"bladelifez1\":\"" + bladelifelinez1.getText().toString()
                + "\",\"bladelifez2\":\"" + bladelifelinez2.getText().toString()
                + "\",\"bbdopenz1\":\"" + bbdopenz1.getText().toString()
                + "\",\"bbdopenz2\":\"" + bbdopenz2.getText().toString()
                + "\",\"bbdclosez1\":\"" + bbdclosez1.getText().toString()
                + "\",\"bbdclosez2\":\"" + bbdclosez2.getText().toString()
                + "\",\"ncsvaluez1\":\"" + ncsvaluez1.getText().toString()
                + "\",\"ncsvaluez2\":\"" + ncsvaluez2.getText().toString()
                + "\",\"ncsprismz1\":\"" + ncsprismz1.indexOfChild(view.findViewById(ncsprismz1.getCheckedRadioButtonId()))
                + "\",\"ncsprismz2\":\"" + ncsprismz2.indexOfChild(view.findViewById(ncsprismz2.getCheckedRadioButtonId()))
                + "\",\"bladesetupz1\":\"" + bladesetupz1.indexOfChild(view.findViewById(bladesetupz1.getCheckedRadioButtonId()))
                + "\",\"bladesetupz2\":\"" + bladesetupz2.indexOfChild(view.findViewById(bladesetupz2.getCheckedRadioButtonId()))
                + "\",\"ncsexpoz1\":\"" + ncsexpoz1.getText().toString()
                + "\",\"ncsexpoz2\":\"" + ncsexpoz2.getText().toString()
                + "\",\"bladedressingz1\":\"" + bladedressingz1.indexOfChild(view.findViewById(bladedressingz1.getCheckedRadioButtonId()))
                + "\",\"bladedressingz2\":\"" + bladedressingz2.indexOfChild(view.findViewById(bladedressingz2.getCheckedRadioButtonId()))
                + "\",\"receipez1\":\"" + receipez1.getText().toString()
                + "\",\"receipez2\":\"" + receipez2.getText().toString()
                + "\",\"precutz1\":\"" + precutz1.getText().toString()
                + "\",\"precutz2\":\"" + precutz2.getText().toString()
                + "\",\"hairlinez1\":\"" + hairlinez1.isChecked()
                + "\",\"hairlinez2\":\"" + hairlinez2.isChecked()
                + "\",\"bladencsz1\":\"" + bladencsz1.getText().toString()
                + "\",\"bladencsz2\":\"" + bladencsz2.getText().toString()
                + "\",\"bladeexposurez1\":\"" + bladeexposurez1.indexOfChild(view.findViewById(bladeexposurez1.getCheckedRadioButtonId()))
                + "\",\"bladeexposurez2\":\"" + bladeexposurez2.indexOfChild(view.findViewById(bladeexposurez2.getCheckedRadioButtonId()))
                + "\",\"exposurez1\":\"" + exposurez1.getText().toString()
                + "\",\"exposurez2\":\"" + exposurez2.getText().toString()
                + "\",\"chuckandspinnerz1\":\"" + chuckandspinnerz1.indexOfChild(view.findViewById(chuckandspinnerz1.getCheckedRadioButtonId()))
                + "\",\"chuckandspinnerz2\":\"" + chuckandspinnerz2.indexOfChild(view.findViewById(chuckandspinnerz2.getCheckedRadioButtonId()))
                + "\",\"bladebbdsetupz1\":\"" + bladebbdsetupz1.indexOfChild(view.findViewById(bladebbdsetupz1.getCheckedRadioButtonId()))
                + "\",\"bladebbdsetupz2\":\"" + bladebbdsetupz2.indexOfChild(view.findViewById(bladebbdsetupz2.getCheckedRadioButtonId()))
                + "\",\"tapez1\":\"" + tapez1.indexOfChild(view.findViewById(tapez1.getCheckedRadioButtonId()))
                + "\",\"tapez2\":\"" + tapez2.indexOfChild(view.findViewById(tapez2.getCheckedRadioButtonId()))
                + "\",\"inspection\":\"" + inspection.isChecked()
                + "\",\"magslot\":\"" + magslot.getSelectedItem().toString()
                + "\",\"verifykerf\":\"" + verifykerf.getText().toString()
                + "\",\"buyoffkerf\":\"" + buyoffkerf.getText().toString()
                + "\",\"bumpcheck\":\"" + bumpcheck.indexOfChild(view.findViewById(bumpcheck.getCheckedRadioButtonId()))
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
                    showToastMsg("Job Request JR " + tvJR.getText().toString() + " Blade Change Completed!");

                    InputMethodManager inputManager = (InputMethodManager)getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

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
        SharedPreferences prefs_Tech = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);

        tvJR.setText(prefs_Tech.getString("jr","no data"));
        tvEquipment.setText(prefs_Tech.getString("equipmentname","no data"));
        tvRequestor.setText(prefs_Tech.getString("requestor","no data"));
        tvTech.setText(prefs_Tech.getString("techid","no data"));
        tvRequestorName.setText(prefs_Tech.getString("msname", "no data"));
        tvTechName.setText(prefs_Tech.getString("techname", "no data").toUpperCase());
        scode = prefs_Tech.getString("scode", "no data");

        String jr = tvJR.getText().toString().replace("-","");

        prf = getContext().getSharedPreferences("jr_details", MODE_PRIVATE);
        SharedPreferences.Editor editor = prf.edit();
        editor.putString("jrid", jr);
        editor.commit();
    }
}
