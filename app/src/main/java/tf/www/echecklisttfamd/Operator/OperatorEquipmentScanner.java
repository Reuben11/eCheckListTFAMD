package tf.www.echecklisttfamd.Operator;


import android.content.Intent;
import android.os.Bundle;
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

import tf.www.echecklisttfamd.LoginActivity;
import tf.www.echecklisttfamd.R;

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
            Snackbar snackbar = Snackbar.make(tvBarcodeLabel, eBarcode.getText().toString(), Snackbar.LENGTH_LONG);
            snackbar.show();

               Fragment newFragment = new OperatorJobRequestList();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.master_container, ((OperatorJobRequestList) newFragment).newInstance());
                /*transaction.addToBackStack(null);*/
                transaction.commit();
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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }

}
