package tf.www.echecklisttfamd.Operator;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.Expose;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Retrofit;
import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorEquipmentScanner extends Fragment {
    private EditText eBarcode;
    private TextView tvBarcodeLabel;

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
           /* Snackbar snackbar = Snackbar.make(tvBarcodeLabel, eBarcode.getText().toString(), Snackbar.LENGTH_LONG);
            snackbar.show();*/

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://pngjvfa01")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Call<GetExist> call = retrofit.create(allclass.CheckJR.class).getJRCheckData();
            call.enqueue(new Callback<GetExist>() {
                @Override
                public void onResponse(Call<GetExist> call, Response<GetExist> response) {
                    /*String results = response.body();*/
                        /*Snackbar snackbar1 = Snackbar.make(tvLocation, results, Snackbar.LENGTH_LONG);
                        snackbar1.show();*/

                    if (response.isSuccessful()){
                        GetExist obj = response.body();
                        //try {

                    if(obj.exist == true){
                        Snackbar snackbar = Snackbar.make(tvBarcodeLabel, obj.exist + " - OK!", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                    else{
                        SetEquipmentName();

                        /*eBarcode.getText().clear();*/
                        Fragment newFragment = new Device_Change_Setup_CheckList();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.master_container, newFragment);
                        transaction.commit();
                    }



                    }
                    else
                    {
                      /*  Snackbar snackbar = Snackbar.make(tvLocation, "Error1", Snackbar.LENGTH_LONG);
                        snackbar.show();*/
                    }
                }
                @Override
                public void onFailure(Call<GetExist> call, Throwable t) {
                    if(t instanceof IOException){

                    }
                    else {

                        // todo log to some central bug tracking service
                    }

                }
            });



        }
    };

    public static OperatorEquipmentScanner newInstance() {
        // Required empty public constructor
        OperatorEquipmentScanner fragment = new OperatorEquipmentScanner();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_operator_equipment_scanner, container, false);

        eBarcode = view.findViewById(R.id.equipmentbarcode1);
        tvBarcodeLabel = view.findViewById(R.id.barcodelabe1);

        eBarcode.setShowSoftInputOnFocus(false);
        eBarcode.addTextChangedListener(textWatcher);
        eBarcode.requestFocus();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }

    public class GetExist{
        @Expose
        protected boolean exist;

        public boolean isExist() {
            return exist;
        }

        public void setExist(boolean exist) {
            this.exist = exist;
        }
    }


    protected void SetEquipmentName() {
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("equipmentname", eBarcode.getText().toString());
        editor.apply();

      /*  String test = prefs.getString("equipmentname",null);
        if(test!=null){

        }*/
    }



}
