package tf.www.echecklisttfamd.Technician;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.Operator.jobrequestequipmentlist;
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
        ArrayList<JobAvailableClass> jobAvailableArr = getJobAvailable();
        lv = view.findViewById(R.id.jobawailablelist);
        lv.setAdapter(new JobAvailableAdapter(getActivity(), jobAvailableArr));
        return view;
    }

    private ArrayList<JobAvailableClass> getJobAvailable(){
        ArrayList<JobAvailableClass> newBuyOffList = new ArrayList<JobAvailableClass>();

        JobAvailableClass newBF;

        String[] jrnumber = getResources().getStringArray(R.array.jrnumber);
        String[] area = getResources().getStringArray(R.array.area);
        String[] jobtype = getResources().getStringArray(R.array.jobtype);
        String[] equipment = getResources().getStringArray(R.array.equipmentname);
        String[] requestor = getResources().getStringArray(R.array.requestor);
        String[] requesttime = getResources().getStringArray(R.array.date);


        for(int i = 0; i < jrnumber.length; i++){
            newBF = new JobAvailableClass();
            newBF.setJrNumber(jrnumber[i]);
            newBF.setArea(area[i]);
            newBF.setJobType(jobtype[i]);
            newBF.setEquipment(equipment[i]);
            newBF.setRequestor(requestor[i]);
            newBF.setJrDateTime(requesttime[i]);
            newBuyOffList.add(newBF);
        }


        return newBuyOffList;
    }
}
