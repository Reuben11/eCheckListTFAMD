package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Device_Change_Setup_CheckList extends Fragment {
    private AlertDialog alertDialog;
    private TextView tvEmp, tvEquipmentName, tvDateTime;
    private Button btnSubmit;
    private EditText etDevice, etMesLot, etWaffepart;
    private RadioGroup rgOrientation;
    private CheckBox cbdailycheck;
    View view;

    TextWatcher textWatcherdevice = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            etMesLot.requestFocus();
        }
    };

    TextWatcher textWatchermeslot = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            etWaffepart.requestFocus();
        }
    };

    TextWatcher textWatcherwpackpart = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            btnSubmit.requestFocus();
        }
    };

    public static Device_Change_Setup_CheckList newInstance() {
        // Required empty public constructor
        Device_Change_Setup_CheckList fragment = new Device_Change_Setup_CheckList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_device__change__setup__check_list, container, false);

        tvEquipmentName = view.findViewById(R.id.changeequipmentname);
        tvDateTime = view.findViewById(R.id.nowdatetime);
        cbdailycheck = view.findViewById(R.id.checkBox);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss a, dd-MMM-yy");
        String dateStr = df.format(c);
        tvDateTime.setText(dateStr);


        etDevice = view.findViewById(R.id.device);
        etDevice.setShowSoftInputOnFocus(false);
        etDevice.addTextChangedListener(textWatcherdevice);
        etDevice.setLongClickable(false);

        etMesLot = view.findViewById(R.id.meslot);
        etMesLot.setShowSoftInputOnFocus(false);
        etMesLot.addTextChangedListener(textWatchermeslot);
        etMesLot.setLongClickable(false);

        etWaffepart = view.findViewById(R.id.wpackpart);
        etWaffepart.setShowSoftInputOnFocus(false);
        etWaffepart.addTextChangedListener(textWatcherwpackpart);
        etWaffepart.setLongClickable(false);

        rgOrientation = view.findViewById(R.id.radioorientation);


        btnSubmit = view.findViewById(R.id.jrsubmit);
        tvEmp = view.findViewById(R.id.ms);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                alertDialog = showJobRequestSubmitDialog();
                alertDialog.show();
            }
        });

        /*if(savedInstanceState != null){*/
            GetEquipmentName();
       /* }*/

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();

        if (alertDialog!=null) {
            if (alertDialog.isShowing())
                alertDialog.dismiss();
            alertDialog = null;
        }
    }

    private AlertDialog showJobRequestSubmitDialog(){
        return new AlertDialog.Builder(getActivity())
            .setTitle("Job Request Submission")
            .setMessage("Please make sure all informations are correct before submission")
            .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showToastMsg("Job Request Submitted!");

                    Fragment newFragment = new TermOfUseOperator();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.master_container, ((TermOfUseOperator) newFragment).newInstance());
                    /*transaction.addToBackStack(null);*/
                    transaction.commit();
                }
            })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
            .setCancelable(false)
            .create();
    }

    private void showToastMsg(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG)
                .show();
    }

    protected void GetEquipmentName(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvEquipmentName.setText(prefs.getString("equipmentname","no data"));
    }

    protected void GetEmp(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvEmp.setText(prefs.getString("emp","no data"));
    }
}
