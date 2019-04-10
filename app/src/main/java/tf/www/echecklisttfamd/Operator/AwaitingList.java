package tf.www.echecklisttfamd.Operator;


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
public class AwaitingList extends Fragment {
    private ListView lv;
    View view;

    public static AwaitingList newInstance() {
        // Required empty public constructor
        AwaitingList fragment = new AwaitingList();
        return  fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_awaiting_list, container, false);
        return view;
    }

}
