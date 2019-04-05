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
public class OperatorJobRequestList extends Fragment {
    private ListView lv;

    public static OperatorJobRequestList newInstance() {
        OperatorJobRequestList fragment = new OperatorJobRequestList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operator_jobrequest, container, false);
        ArrayList<jobrequestequipmentlist> listprocess = getBuyOfflist();
        lv = view.findViewById(R.id.processequipment);
        lv.setAdapter(new JobRequestAdapter(getActivity(), listprocess));
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

                /*Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();*/
                /*Fragment newFragment = new picknplace();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.master_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();*/

            }
        });
    }

    private ArrayList<jobrequestequipmentlist> getBuyOfflist(){
        ArrayList<jobrequestequipmentlist> newBuyOffList = new ArrayList<jobrequestequipmentlist>();

        jobrequestequipmentlist newBF;

        String[] process = getResources().getStringArray(R.array.process);
        String[] equipment = getResources().getStringArray(R.array.equipment);

        for(int i = 0; i < process.length; i++){
            newBF = new jobrequestequipmentlist();
            newBF.setProcess(process[i]);
            newBF.setEquipment(equipment[i]);
            newBuyOffList.add(newBF);
        }


        return newBuyOffList;
    }
}
