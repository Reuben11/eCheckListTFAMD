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
public class OperatorBuyOffCheckList extends Fragment {
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
        return view;
    }

}
