package tf.www.echecklisttfamd.Technician;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermOfUseTechnician extends Fragment {
    private TextView tvName, tvJob;
    View view;

    public static TermOfUseTechnician newInstance() {
        // Required empty public constructor
        TermOfUseTechnician fragment = new TermOfUseTechnician();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_term_of_use_technician, container, false);
        tvName = view.findViewById(R.id.techname);
        tvJob = view.findViewById(R.id.techjobtitle);
        GetEmpData();
        return view;
    }

    protected void GetEmpData(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvName.setText(prefs.getString("techname","no data").toUpperCase());
        tvJob.setText(prefs.getString("techjobtitle", "no data").toUpperCase());
    }
}
