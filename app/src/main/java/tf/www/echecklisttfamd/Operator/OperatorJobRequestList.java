package tf.www.echecklisttfamd.Operator;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorJobRequestList extends Fragment {
    private ListView lv;
    private TextView tvTextsnack;
    private EditText edBarcode;


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
       /* tvTextsnack = view.findViewById(R.id.textsnack);*/
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

                switch (position){
                   case 0:
                       SetCheckListType("Device Change Setup");
                       SetArea("1");
                        break;

                        case 1:
                            SetCheckListType("Disco DFD 6361 Machine Blade Change");
                            SetArea("2");
                            break;
                }


                Fragment newFragment = new OperatorScanner();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.master_container, newFragment);
                transaction.commit();

                /* switch (position){
                   case 0:
                        Fragment newFragment = new OperatorScanner();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.master_container, newFragment);
                        transaction.commit();
                        break;
                }*/
                /*Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();*/


                /*Snackbar snackbar = Snackbar.make(tvTextsnack, name, Snackbar.LENGTH_LONG);
                snackbar.show();*/

            }
        });
    }

    private ArrayList<jobrequestequipmentlist> getBuyOfflist(){
        ArrayList<jobrequestequipmentlist> newBuyOffList = new ArrayList<jobrequestequipmentlist>();

        jobrequestequipmentlist newBF;

        String[] process = getResources().getStringArray(R.array.process);
        String[] area = getResources().getStringArray(R.array.area);
        String[] equipment = getResources().getStringArray(R.array.equipment);
        String[] revision = getResources().getStringArray(R.array.revision);

        for(int i = 0; i < process.length; i++){
            newBF = new jobrequestequipmentlist();
            newBF.setProcess(process[i]);
            newBF.setArea(area[i]);
            newBF.setEquipment(equipment[i]);
            newBF.setRevision(revision[i]);
            newBuyOffList.add(newBF);
        }


        return newBuyOffList;
    }

    protected void SetCheckListType(String option) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("checklist", option);
        editor.commit();
    }

    protected void SetArea(String area) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("area", area);
        editor.commit();
    }
}
