package tf.www.echecklisttfamd.Technician;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.Operator.OperatorBuyOffCheckList;
import tf.www.echecklisttfamd.Operator.OperatorEquipmentScanner;
import tf.www.echecklisttfamd.Operator.jobrequestequipmentlist;
import tf.www.echecklisttfamd.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobAvailable extends Fragment {
    private ArrayList<AvailableJobs> data;
    private ListView lv;
    View view;

 /*   getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.master_container, OperatorEquipmentScanner.newInstance())
            .commit();*/

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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                // Do the onItemClick action
                String name = adapterView.getItemAtPosition(position).toString();

                switch (position){
                    case 0:
                        Fragment newFragment = new TechnicianScanner();
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

    public class JsonResponse{
        private AvailableJobs[] joblists;

        public AvailableJobs[] getJoblists(){
            return joblists;
        }
    }

    public class AvailableJobs{
        private String jrNumber;
        private String area;
        private String checklistname;
        private String equipmentName;
        private String requestor;
        private String date;

        public  AvailableJobs(String jrNumber, String area, String checklistname, String equipmentName, String requestor, String date){
            this.jrNumber = jrNumber;
            this.area = area;
            this.checklistname = checklistname;
            this.equipmentName = equipmentName;
            this.requestor = requestor;
            this.date = date;
        }

        public String getJrNumber() {
            return jrNumber;
        }

        public void setJrNumber(String jrNumber) {
            this.jrNumber = jrNumber;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getChecklistname() {
            return checklistname;
        }

        public void setChecklistname(String checklistname) {
            this.checklistname = checklistname;
        }

        public String getEquipmentName() {
            return equipmentName;
        }

        public void setEquipmentName(String equipmentName) {
            this.equipmentName = equipmentName;
        }

        public String getRequestor() {
            return requestor;
        }

        public void setRequestor(String requestor) {
            this.requestor = requestor;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
