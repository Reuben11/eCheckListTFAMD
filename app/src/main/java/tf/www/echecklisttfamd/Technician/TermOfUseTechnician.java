package tf.www.echecklisttfamd.Technician;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermOfUseTechnician extends Fragment {
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
        return view;
    }

}
