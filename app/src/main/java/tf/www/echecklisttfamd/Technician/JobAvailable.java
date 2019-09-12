package tf.www.echecklisttfamd.Technician;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.JobAvailableClass;
import tf.www.echecklisttfamd.allclass;
import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class JobAvailable extends Fragment {
    private ArrayList<JobAvailableClass> data;
    private ListView lv;
    private Boolean datamsg;
    private  Timer timer;
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
        datamsg = false;
        GetReqJobs();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GetReqJobs();
            }
        },0, 10000);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void GetReqJobs(){
        String requestapilink = "/api/eCheckListTest?requestlist=ok";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<JsonResponse> call = retrofit.create(allclass.GetJobRequest.class).getJson(requestapilink);
        call.enqueue(new Callback<JsonResponse>() {
            @Override
            public void onResponse(Call<JsonResponse> call, Response<JsonResponse> response) {

                if (response.isSuccessful()) {
                    JsonResponse jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList((jsonResponse.getJoblists())));
                    lv = view.findViewById(R.id.jobawailablelist);
                    lv.setAdapter(new JobAvailableAdapter(getActivity(), data));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                            // Do the onItemClick action

                            if(data.get(position).getChecklist().equals("Device Change Setup Checklist")){
                                SetJRName(data.get(position).getJr());
                                SetRequestor(data.get(position).getRequestor());
                                SetDevice(data.get(position).getDevice());
                                SetEquipmentName( data.get(position).getEquipment());
                                SetDaily(data.get(position).getDaily());
                                SetScode(data.get(position).getScode());
                                SetArea("1");

                                Fragment newFragment = new TechnicianScanner();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.master_container, newFragment);
                                transaction.commit();
                            }
                            else if(data.get(position).getChecklist().equals("Blade Replacement")){
                                SetJRName(data.get(position).getJr());
                                SetRequestor(data.get(position).getRequestor());
                                SetDevice(data.get(position).getDevice());
                                SetEquipmentName( data.get(position).getEquipment());
                                SetScode(data.get(position).getScode());
                                SetArea("2");

                                Fragment newFragment = new TechnicianScanner();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.replace(R.id.master_container, newFragment);
                                transaction.commit();
                            }


                        }
                    });

                } else {
                    if(datamsg==false){
                        ShowAlert("Informations", "No Job Request!");
                        datamsg=true;
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    ShowAlert("Error!", t.getMessage());
                } else {

                    // todo log to some central bug tracking service
                }

            }
        });
    }

    public class JsonResponse{
        private JobAvailableClass[] joblists;

        public JobAvailableClass[] getJoblists(){
            return joblists;
        }
    }

    private void ShowAlert(String title, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }

    protected void SetJRName(String jr) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("jr", jr);
        editor.commit();
    }

    protected void SetScode(String scode) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("empscode", scode);
        editor.commit();
    }

    protected void SetRequestor(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("requestor", name);
        editor.commit();
    }
    protected void SetDevice(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("device", name);
        editor.commit();
    }
    protected void SetEquipmentName(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("equipmentname", name);
        editor.commit();
    }

    protected void SetDaily(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("daily", name);
        editor.commit();
    }

    protected void SetArea(String area) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE).edit();
        editor.putString("area", area);
        editor.commit();
    }

}
