package tf.www.echecklisttfamd.Technician;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import tf.www.echecklisttfamd.Operator.OperatorJobRequestList;
import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TechnicianScanner extends Fragment {
    private EditText eBarcode;
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

            Fragment newFragment = new MachineSetup();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.master_container, ((MachineSetup) newFragment).newInstance());
           /* transaction.addToBackStack(null);*/
            transaction.commit();
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
        eBarcode = view.findViewById(R.id.technicianbarcodescanner);
        eBarcode.setShowSoftInputOnFocus(false);
        eBarcode.addTextChangedListener(textWatcher);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);


    }
}
