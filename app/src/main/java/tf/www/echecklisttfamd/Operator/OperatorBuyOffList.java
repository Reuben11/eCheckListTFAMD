package tf.www.echecklisttfamd.Operator;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        lv.setAdapter(new BuyOffRequestListAdapter(getActivity(), jobawait));
        return view;
    }

    @Override
    public  void onActivityCreated(Bundle savedInstance){
        super.onActivityCreated(savedInstance);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                // Do the onItemClick action
                String name = adapterView.getItemAtPosition(position).toString();

                switch (position){
                    case 0:
                        Fragment newFragment = new OperatorBuyOffCheckList();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.master_container, newFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                }
                /*Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();*/


                /*Snackbar snackbar = Snackbar.make(tvTextsnack, name, Snackbar.LENGTH_LONG);
                snackbar.show();*/

            }
        });
    }

    private ArrayList<OperatorJobRequestNumberList> getJrNumber(){
        ArrayList<OperatorJobRequestNumberList> newJRList = new ArrayList<OperatorJobRequestNumberList>();

        OperatorJobRequestNumberList newJR;

        String[] jrnumber = getResources().getStringArray(R.array.jrnumber);
        String[] equipmentname = getResources().getStringArray(R.array.equipmentname);
        String[] nowdate = getResources().getStringArray(R.array.date);

        for(int i = 0; i < jrnumber.length; i++){
            newJR = new OperatorJobRequestNumberList();
           newJR.setjRNumber(jrnumber[i]);
            newJR.setEquipmentName(equipmentname[i]);
            newJR.setNowDateTime(nowdate[i]);
            newJRList.add(newJR);
        }


        return newJRList;
    }
}
