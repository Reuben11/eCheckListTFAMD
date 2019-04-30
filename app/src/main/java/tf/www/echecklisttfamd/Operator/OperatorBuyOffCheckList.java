package tf.www.echecklisttfamd.Operator;


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
import android.widget.TextView;
import android.widget.Toast;

import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorBuyOffCheckList extends Fragment {
    private AlertDialog alertDialog;
    private TextView tvEmp, tvEquipmentName;
    private Button btnSubmit;
    View view;

    public static OperatorBuyOffCheckList newWInstance() {
        // Required empty public constructor
        OperatorBuyOffCheckList fragment = new OperatorBuyOffCheckList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_operator_buy_off_check_list, container, false);

        btnSubmit = view.findViewById(R.id.buyoffsubmit);
        tvEmp = view.findViewById(R.id.jrnumberlable);
        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                alertDialog = showJobRequestSubmitDialog();
                alertDialog.show();
            }
        });



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
                .setTitle("Buy Off Submission")
                .setMessage("Please make sure all informations are correct before submission")
                .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        showToastMsg("Buy Off Submitted!");

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


}
