package tf.www.echecklisttfamd.Operator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorBuyOffList extends Fragment {
    private ListView lv;
    View view;


    public static OperatorBuyOffList newInstance() {
        // Required empty public constructor
        OperatorBuyOffList fragment = new OperatorBuyOffList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_operator_buy_off_list, container, false);
        ArrayList<OperatorJobRequestNumberList> jobawait = getJrNumber();
        lv = view.findViewById(R.id.buyofflist);
        return view;
    }

    private ArrayList<OperatorJobRequestNumberList> getJrNumber(){
        ArrayList<OperatorJobRequestNumberList> newJRList = new ArrayList<OperatorJobRequestNumberList>();

        OperatorJobRequestNumberList newJR;

        String[] process = getResources().getStringArray(R.array.process);
        String[] equipment = getResources().getStringArray(R.array.equipment);

        for(int i = 0; i < process.length; i++){
            newJR = new OperatorJobRequestNumberList();
         /*   newJR.setProcess(process[i]);
            newJR.setEquipment(equipment[i]);*/
            newJRList.add(newJR);
        }


        return newJRList;
    }
}
