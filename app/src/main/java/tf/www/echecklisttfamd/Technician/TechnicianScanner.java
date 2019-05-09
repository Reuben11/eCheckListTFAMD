package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tf.www.echecklisttfamd.Operator.OperatorJobRequestList;
import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianScanner extends Fragment {
    private String equipmentname;
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
              Fragment newFragment = new MachineSetup();
              FragmentTransaction transaction = getFragmentManager().beginTransaction();
              transaction.replace(R.id.master_container, ((MachineSetup) newFragment).newInstance());
               transaction.addToBackStack(null);
              transaction.commit();
          }
          else{
                if(clearText==false){
                    ShowAlert("Scanner Bar Code Error Code : SBC0001", eBarcode.getText().toString() + "  Invalid Equipment Barcode!");
                }
          }


        }
    };

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
        GetEquipmentName();
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

    protected void GetEquipmentName(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        equipmentname = prefs.getString("equipmentname","no data");
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
