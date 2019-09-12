package tf.www.echecklisttfamd.Operator;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tf.www.echecklisttfamd.BuildConfig;
import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermOfUseOperator extends Fragment {
    private TextView tvName, tvJob, tvVersion, tvEmpShift;
    View view;

    public static TermOfUseOperator newInstance() {
        // Required empty public constructor
        TermOfUseOperator fragment = new TermOfUseOperator();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_term_of_use_operator, container, false);
         tvName = view.findViewById(R.id.name);
         tvJob = view.findViewById(R.id.jobtitle);
         tvVersion = view.findViewById(R.id.versiontosOps);
         tvEmpShift = view.findViewById(R.id.empshift);
        displayVersionName();
        GetEmpData();
        return view;
    }

    private void displayVersionName() {
        tvVersion.setText("Version : " + BuildConfig.VERSION_NAME);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    protected void GetEmpData(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        tvName.setText(prefs.getString("empname","no data").toUpperCase());
        tvJob.setText(prefs.getString("empjobtitle", "no data").toUpperCase());
        tvEmpShift.setText("Shift : " + prefs.getString("shift", "no data"));
    }


}
