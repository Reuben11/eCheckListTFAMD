package tf.www.echecklisttfamd.Technician;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobAvailable extends Fragment {
    private ListView lv;
    View view;

    public static JobAvailable newInstance() {
        // Required empty public constructor
        JobAvailable fragment = new JobAvailable();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_job_available, container, false);
        return view;
    }

}
