package tf.www.echecklisttfamd.Operator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Device_Change_Setup_CheckList extends Fragment {


    public static Device_Change_Setup_CheckList newInstance() {
        // Required empty public constructor
        Device_Change_Setup_CheckList fragment = new Device_Change_Setup_CheckList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device__change__setup__check_list, container, false);
    }

}
